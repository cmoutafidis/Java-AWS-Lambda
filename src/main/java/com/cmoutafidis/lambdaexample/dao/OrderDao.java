package com.cmoutafidis.lambdaexample.dao;

import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.spec.PutItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
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
    public void update(final Order order, final String[] params) {

    }

    @Override
    public void delete(final Order order) {

    }
}
