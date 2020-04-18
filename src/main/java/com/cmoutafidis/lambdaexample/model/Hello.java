package com.cmoutafidis.lambdaexample.model;

public class Hello {
    private String message;
    private String location;

    public Hello() {

    }

    public Hello(final String message, final String location) {
        this.message = message;
        this.location = location;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(final String location) {
        this.location = location;
    }
}
