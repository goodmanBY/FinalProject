package com.savko.dao;

import com.savko.pool.ConnectionPool;
import com.savko.pool.ConnectionProxy;
import com.savko.util.HashUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDao extends Dao {

    private static final String SQL_CHECK_ADMIN = "SELECT login, password FROM admin WHERE " +
            "login = ? AND password = ?;";
    private static final String SQL_ADD_ADMIN = "INSERT INTO admin(login, password) VALUES(?, ?);";

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

    public void addAdmin(String login, String password) throws DaoException {
        ConnectionProxy connection = ConnectionPool.getInstance().takeConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_ADD_ADMIN);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, HashUtil.getMd5Hash(password));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Unable to add admin.", e);
        } finally {
            closeResources(connection, preparedStatement);
        }
    }

    private static class StaticHolder {
        static final AdminDao INSTANCE = new AdminDao();
    }

}

