package com.savko.dao;

import com.savko.entity.User;
import com.savko.pool.ConnectionPool;
import com.savko.pool.ConnectionProxy;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AdminDao extends Dao {

    private final static Logger LOGGER = Logger.getLogger(AdminDao.class);

    private static final String SQL_CHECK_ADMIN = "SELECT login, password FROM admin WHERE " +
            "login = ? AND password = ?;";
    private static final String SQL_TAKE_ALL_USERS = "SELECT * FROM client;";

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

    public List<User> takeAllUsers() throws DaoException {
        ConnectionProxy connection = ConnectionPool.getInstance().takeConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_TAKE_ALL_USERS);
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("client_id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setLogin(resultSet.getString("login"));
                user.setBanned(resultSet.getByte("banned"));
                user.setDiscountId(resultSet.getShort("discount_id"));
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            throw new DaoException("Unable to take all users.", e);
        } finally {
            closeResources(connection, statement);
        }
    }
}

