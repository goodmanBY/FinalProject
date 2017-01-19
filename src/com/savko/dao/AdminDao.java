package com.savko.dao;

import com.savko.pool.ConnectionPool;
import com.savko.pool.ConnectionProxy;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDao extends Dao {

    private static final String SQL_CHECK_ADMIN = "SELECT login, password FROM admin WHERE " +
            "login = ? AND password = ?;";

    public static AdminDao getInstance() {
        return StaticHolder.INSTANCE;
    }

    public boolean checkAdmin(String login, String password) throws DaoException {
        boolean statement = false;
        ConnectionProxy connection = ConnectionPool.getInstance().takeConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_CHECK_ADMIN);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            statement = resultSet.next();
        } catch (SQLException e) {
            throw new DaoException("Unable to find such admin.", e);
        } finally {
            closeResources(connection, preparedStatement);
        }
        return statement;
    }

    private static class StaticHolder {
        static final AdminDao INSTANCE = new AdminDao();
    }

}

