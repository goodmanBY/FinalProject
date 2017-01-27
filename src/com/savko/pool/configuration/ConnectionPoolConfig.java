package com.savko.pool.configuration;

import java.util.ResourceBundle;

public class ConnectionPoolConfig {

    private static final String RESOURCE_NAME = "connection";

    private static ConnectionPoolConfig instance = new ConnectionPoolConfig();

    private ResourceBundle resourceBundle;

    private ConnectionPoolConfig() {
        resourceBundle = ResourceBundle.getBundle(RESOURCE_NAME);
    }

    public static ConnectionPoolConfig getInstance() {
        return instance;
    }

    public String getString(String key) {
        return resourceBundle.getString(key);
    }
}