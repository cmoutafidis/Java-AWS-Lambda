package com.cmoutafidis.lambdaexample.dao;

import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.spec.DeleteItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.PutItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ReturnValue;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.cmoutafidis.lambdaexample.model.Order;
import com.cmoutafidis.lambdaexample.utils.Common;

import java.util.*;

public class OrderDao extends Dao<Order> {

    public OrderDao() {
        super(Common.DYNAMO_DB_ORDER_TABLE);
    }

    @Override
    public Order get(final String id) {
        Order order = new Order();

        final Iterator<Item> itemsIterator = this.getDynamoDB().getTable(this.getDynamoDbTableName()).query(new QuerySpec()
                .withKeyConditionExpression("OrderId = :v_id")
                .withValueMap(new ValueMap()
                        .withString(":v_id", id))).iterator();

        if (itemsIterator.hasNext()) {
            order = Common.GSON.fromJson(itemsIterator.next().toJSON(), Order.class);
        }

        return order;
    }

    @Override
    public List<Order> getAll() {
        final List<Order> orders = new ArrayList<>();

        final ScanResult scan = this.getDynamoDBClient().scan(new ScanRequest().withTableName(this.getDynamoDbTableName()));

        for (final Map<String, AttributeValue> item : scan.getItems()) {
            orders.add(new Order(item.get("OrderId").getS(), item.get("CustomerId").getS(), item.get("OrderName").getS(), item.get("OrderDescription").getS()));
        }

        return orders;
    }

    @Override
    public String save(final Order order) {
        final String id = UUID.randomUUID().toString();
        this.getDynamoDB().getTable(this.getDynamoDbTableName()).putItem(new PutItemSpec().withItem(new Item()
                .withString("OrderId", id)
                .withString("CustomerId", order.getCustomerId())
                .withString("OrderName", order.getOrderName())
                .withString("OrderDescription", order.getOrderDescription())
        ));
        return id;
    }

    @Override
    public boolean update(final Order order) {
        try {
            this.getDynamoDB().getTable(this.getDynamoDbTableName()).updateItem(new UpdateItemSpec()
                    .withPrimaryKey("OrderId", order.getOrderId(), "CustomerId", order.getCustomerId())
                    .withUpdateExpression("set OrderName = :n, OrderDescription = :d")
                    .withValueMap(new ValueMap().withString(":n", order.getOrderName()).withString(":d", order.getOrderDescription()))
                    .withReturnValues(ReturnValue.UPDATED_NEW));
            return true;
        } catch (final Exception e) {
            System.err.println("Unable to update item: " + Common.GSON.toJson(order));
            System.err.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(final Order order) {
        try {
            this.getDynamoDB().getTable(this.getDynamoDbTableName()).deleteItem(new DeleteItemSpec()
                    .withPrimaryKey("OrderId", order.getOrderId(), "CustomerId", order.getCustomerId()));
            return true;
        } catch (final Exception e) {
            System.err.println("Unable to delete item: " + Common.GSON.toJson(order));
            System.err.println(e.getMessage());
            return false;
        }
    }
}
