package com.cmoutafidis.lambdaexample.dao;

import com.cmoutafidis.lambdaexample.model.Order;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class OrderDao implements Dao<Order> {

    public OrderDao() {
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
        System.out.println("Order saved with id: " + id);
        return id;
    }

    @Override
    public void update(final Order order, final String[] params) {

    }

    @Override
    public void delete(final Order order) {

    }
}
