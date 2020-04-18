package com.cmoutafidis.lambdaexample.utils;

import com.cmoutafidis.lambdaexample.dao.OrderDao;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Common {

    public static Properties CONFIG = new Properties();

    static {
        try (final InputStream inputStream = new FileInputStream("config.properties")) {
            CONFIG.load(inputStream);
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    public static final Map<String, String> JSON_HEADERS = new HashMap<String, String>() {
        private static final long serialVersionUID = 345904911405481615L;

        {
            this.put("Content-Type", "application/json");
            this.put("X-Custom-Header", "application/json");
        }
    };

    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public static String DYNAMO_DB_ORDER_TABLE = "Order";

    public static final OrderDao ORDER_DAO = new OrderDao();
}
