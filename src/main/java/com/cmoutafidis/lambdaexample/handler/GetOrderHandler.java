package com.cmoutafidis.lambdaexample.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.cmoutafidis.lambdaexample.gateway.GatewayResponse;
import com.cmoutafidis.lambdaexample.model.Order;
import com.cmoutafidis.lambdaexample.model.Request;
import com.cmoutafidis.lambdaexample.utils.Common;

public class GetOrderHandler implements RequestHandler<Object, GatewayResponse> {

    @Override
    public GatewayResponse handleRequest(final Object event, final Context context) {
        final LambdaLogger lambdaLogger = context.getLogger();
        final Request request = Common.GSON.fromJson(Common.GSON.toJson(event), Request.class);

        final Order order = Common.ORDER_DAO.get(request.getPathParameters().get("order_id"));

        return new GatewayResponse(Common.GSON.toJson(order), Common.JSON_HEADERS, 200);
    }

}
