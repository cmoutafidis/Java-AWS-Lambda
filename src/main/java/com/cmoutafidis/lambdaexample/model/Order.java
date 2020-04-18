package com.cmoutafidis.lambdaexample.model;

import com.google.gson.annotations.SerializedName;

public class Order {

    @SerializedName("OrderId")
    private String orderId;
    @SerializedName("CustomerId")
    private String customerId;
    @SerializedName("OrderName")
    private String orderName;
    @SerializedName("OrderDescription")
    private String orderDescription;

    public Order() {

    }

    public Order(final String orderId, final String customerId, final String orderName, final String orderDescription) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.orderName = orderName;
        this.orderDescription = orderDescription;
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
