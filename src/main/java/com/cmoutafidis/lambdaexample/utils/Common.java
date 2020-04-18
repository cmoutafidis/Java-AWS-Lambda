package com.cmoutafidis.lambdaexample.utils;

import com.cmoutafidis.lambdaexample.dao.OrderDao;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.Map;

public class Common {

    public static final Map<String, String> JSON_HEADERS = new HashMap<String, String>() {
        private static final long serialVersionUID = 345904911405481615L;

        {
            this.put("Content-Type", "application/json");
            this.put("X-Custom-Header", "application/json");
        }
    };

    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public static final OrderDao ORDER_DAO = new OrderDao();
}
