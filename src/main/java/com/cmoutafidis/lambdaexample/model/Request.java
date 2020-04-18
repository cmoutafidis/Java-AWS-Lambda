package com.cmoutafidis.lambdaexample.model;

import java.util.Map;

public class Request {

    private String body;
    private Map<String, String> pathParameters;

    public String getBody() {
        return this.body;
    }

    public void setBody(final String body) {
        this.body = body;
    }

    public Map<String, String> getPathParameters() {
        return this.pathParameters;
    }

    public void setPathParameters(final Map<String, String> pathParameters) {
        this.pathParameters = pathParameters;
    }
}
