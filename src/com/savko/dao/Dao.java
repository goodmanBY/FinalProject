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

    void closeResources(ConnectionProxy connection, Statement statement) throws DaoException {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            throw new DaoException("Unable to return connection.", e);
        }
        closeResources(connection);
    }


}
