package com.cmoutafidis.lambdaexample.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.cmoutafidis.lambdaexample.gateway.GatewayResponse;
import com.cmoutafidis.lambdaexample.model.Hello;
import com.cmoutafidis.lambdaexample.utils.Common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.stream.Collectors;

public class HelloHandler implements RequestHandler<Object, GatewayResponse> {

    @Override
    public GatewayResponse handleRequest(final Object request, final Context context) {
        try {
            final String pageContents = this.getPageContents();
            final Hello helloResponse = new Hello("hello world", pageContents);

            return new GatewayResponse(Common.GSON.toJson(helloResponse), Common.JSON_HEADERS, 200);
        } catch (final IOException e) {
            return new GatewayResponse("{}", Common.JSON_HEADERS, 500);
        }
    }

    private String getPageContents() throws IOException {
        final URL url = new URL("https://checkip.amazonaws.com");
        try (final BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()))) {
            return br.lines().collect(Collectors.joining(System.lineSeparator()));
        }
    }
}