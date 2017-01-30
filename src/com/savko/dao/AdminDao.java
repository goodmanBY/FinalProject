package com.savko.dao;

import com.savko.constant.DbColumns;
import com.savko.entity.Admin;
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
    private static final String SQL_TAKE_ADMIN = "SELECT admin_id FROM admin WHERE login = ?;";

    public static AdminDao getInstance() {
        return StaticHolder.INSTANCE;
    }

    public Admin takeAdminByLogin(String login) throws DaoException {
        ConnectionProxy connection = ConnectionPool.getInstance().takeConnection();
        PreparedStatement preparedStatement = null;
        Admin admin = new Admin();
        try {
            preparedStatement = connection.prepareStatement(SQL_TAKE_ADMIN);
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                admin.setId(resultSet.getInt(DbColumns.ADMIN_ID)).setLogin(login);
            }
            return admin;
        } catch (SQLException e) {
            throw new DaoException("Unable to take such admin.", e);
        } finally {
            closeResources(connection, preparedStatement);
        }
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

