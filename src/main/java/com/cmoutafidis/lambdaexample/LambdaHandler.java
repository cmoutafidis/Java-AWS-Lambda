package com.cmoutafidis.lambdaexample;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Map;

public class LambdaHandler implements RequestHandler<Map<String, String>, String> {

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public String handleRequest(final Map<String, String> event, final Context context) {
        final LambdaLogger logger = context.getLogger();
        final String response = new String("200 OK");
        // log execution details
        logger.log("ENVIRONMENT VARIABLES: " + this.gson.toJson(System.getenv()));
        logger.log("CONTEXT: " + this.gson.toJson(context));
        //process event
        logger.log("EVENT: " + this.gson.toJson(event));
        logger.log("EVENT TYPE: " + event.getClass().toString());
        return response;
    }
}
