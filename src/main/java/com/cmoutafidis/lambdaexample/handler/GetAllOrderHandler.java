package com.cmoutafidis.lambdaexample.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.cmoutafidis.lambdaexample.gateway.GatewayResponse;
import com.cmoutafidis.lambdaexample.model.Order;
import com.cmoutafidis.lambdaexample.utils.Common;

import java.util.List;

public class GetAllOrderHandler implements RequestHandler<Object, GatewayResponse> {

    @Override
    public GatewayResponse handleRequest(final Object event, final Context context) {
        final LambdaLogger lambdaLogger = context.getLogger();

        final List<Order> orders = Common.ORDER_DAO.getAll();

        return new GatewayResponse(Common.GSON.toJson(orders), Common.JSON_HEADERS, 200);
    }

}
