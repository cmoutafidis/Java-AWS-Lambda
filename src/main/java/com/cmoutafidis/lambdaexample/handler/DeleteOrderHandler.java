package com.cmoutafidis.lambdaexample.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.cmoutafidis.lambdaexample.gateway.GatewayResponse;
import com.cmoutafidis.lambdaexample.model.Order;
import com.cmoutafidis.lambdaexample.model.Request;
import com.cmoutafidis.lambdaexample.utils.Common;

public class DeleteOrderHandler implements RequestHandler<Object, GatewayResponse> {

    @Override
    public GatewayResponse handleRequest(final Object event, final Context context) {
        final Request request = Common.GSON.fromJson(Common.GSON.toJson(event), Request.class);
        final Order order = Common.GSON.fromJson(request.getBody(), Order.class);
        order.setOrderId(request.getPathParameters().get("order_id"));

        if (Common.ORDER_DAO.delete(order)) {
            return new GatewayResponse("{}", Common.JSON_HEADERS, 200);
        } else {
            return new GatewayResponse("{}", Common.JSON_HEADERS, 400);
        }

    }

}
