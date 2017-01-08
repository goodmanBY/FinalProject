package com.savko.dao;


import com.savko.pool.ConnectionPool;
import com.savko.pool.ConnectionProxy;

import java.sql.SQLException;
import java.sql.Statement;

public class Dao {

    protected static final ConnectionPool pool = ConnectionPool.getInstance();

    void closeResources(ConnectionProxy connection) {
        pool.releaseConnection(connection);
    }

    void closeResources(ConnectionProxy connection, Statement statement) {
        try {
            if (statement!=null) {
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeResources(connection);
    }


}
