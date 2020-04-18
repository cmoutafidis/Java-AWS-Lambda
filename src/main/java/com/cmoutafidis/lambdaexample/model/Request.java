package com.cmoutafidis.lambdaexample.model;

public class Request {

    private String body;

    public Request(final String body) {
        this.body = body;
    }

    public String getBody() {
        return this.body;
    }

    public void setBody(final String body) {
        this.body = body;
    }
}
