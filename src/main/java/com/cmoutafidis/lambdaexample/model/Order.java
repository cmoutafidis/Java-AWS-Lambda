package com.cmoutafidis.lambdaexample.model;

public class Order {

    private String orderId;
    private String customerId;
    private String orderName;
    private String orderDescription;

    public Order() {

    }

    public String getOrderId() {
        return this.orderId;
    }

    public void setOrderId(final String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(final String customerId) {
        this.customerId = customerId;
    }

    public String getOrderName() {
        return this.orderName;
    }

    public void setOrderName(final String orderName) {
        this.orderName = orderName;
    }

    public String getOrderDescription() {
        return this.orderDescription;
    }

    public void setOrderDescription(final String orderDescription) {
        this.orderDescription = orderDescription;
    }
}
