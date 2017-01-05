package com.savko.pool.configuration;

import java.util.ResourceBundle;

/**
 * Class for setting connection pool fileds
 */
public class ConnectionPoolConfig {

    private static final String RESOURCE_NAME = "connection";

    private static ConnectionPoolConfig instance = new ConnectionPoolConfig();

    private ResourceBundle resourceBundle;

    private ConnectionPoolConfig() {
        resourceBundle = ResourceBundle.getBundle(RESOURCE_NAME);
    }

    /**
     * Gets instance
     *
     * @return ConnectionPoolConfig instance
     */
    public static ConnectionPoolConfig getInstance() {
        return instance;
    }

    /**
     * Gets value by key
     *
     * @param key paramer for define needed value
     * @return value by key
     */
    public String getString(String key) {
        return resourceBundle.getString(key);
    }
}