package com.cmoutafidis.lambdaexample.dao;

import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.spec.PutItemSpec;
import com.cmoutafidis.lambdaexample.model.Order;
import com.cmoutafidis.lambdaexample.utils.Common;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class OrderDao extends Dao<Order> {

    public OrderDao() {
        super(Common.DYNAMO_DB_ORDER_TABLE);
    }

    @Override
    public Optional<Order> get(final long id) {
        return Optional.empty();
    }

    @Override
    public List<Order> getAll() {
        return null;
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
