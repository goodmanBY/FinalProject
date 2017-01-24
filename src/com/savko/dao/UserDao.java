package com.savko.dao;

import com.savko.entity.User;
import com.savko.pool.ConnectionPool;
import com.savko.pool.ConnectionProxy;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends Dao {

    private static final String SQL_INSERT_USER = "INSERT INTO client(name, last_name, login, password) VALUES(?, ?, ?, ?);";
    private static final String SQL_TAKE_USER_BY_LOGIN = "SELECT * FROM client WHERE login = ?;";
    private static final String SQL_TAKE_USER_BY_ID = "SELECT * FROM client WHERE client_id = ?;";
    private static final String SQL_TAKE_USER_BY_REQUEST_ID = "SELECT client.client_id, name, last_name, login, banned, " +
            "discount FROM client RIGHT JOIN request ON client.client_id = request.client_id WHERE request.request_id = ?;";
    private static final String SQL_TAKE_ALL_USERS = "SELECT * FROM client;";
    private static final String SQL_CHECK_USER = "SELECT login, password FROM client WHERE login = ? AND password = ?;";
    private static final String SQL_CHECK_USER_LOGIN = "SELECT login FROM client WHERE login = ?;";
    private static final String SQL_BLOCK_USER = "UPDATE client SET banned = 1 WHERE client_id = ?;";
    private static final String SQL_UNBLOCK_USER = "UPDATE client SET banned = 0 WHERE client_id = ?;";
    private static final String SQL_ADD_BLOCK_DESCRIPTION = "INSERT INTO ban_info(client_id, ban_description) VALUES(?, ?);";
    private static final String SQL_TAKE_BLOCK_DESCRIPTION = "SELECT ban_description FROM ban_info WHERE client_id = ?;";
    private static final String SQL_CHANGE_USER_DISCOUNT = "UPDATE client SET discount = ? WHERE client_id = ?;";
    private static final String SQL_TAKE_DISCOUNT_VALUE_BY_USER_ID = "SELECT discount FROM client WHERE client_id = ?;";

    public static UserDao getInstance() {
        return StaticHolder.INSTANCE;
    }

    public void addUser(User user) throws DaoException {
        ConnectionProxy connection = ConnectionPool.getInstance().takeConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_INSERT_USER);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getLogin());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Unable to add user.", e);
        } finally {
            closeResources(connection, preparedStatement);
        }
    }

    public boolean checkUser(String login, String password) throws DaoException {
        boolean statement = false;
        ConnectionProxy connection = ConnectionPool.getInstance().takeConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_CHECK_USER);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            statement = resultSet.next();
        } catch (SQLException e) {
            throw new DaoException("Unable to find such user.", e);
        } finally {
            closeResources(connection, preparedStatement);
        }
        return statement;
    }

    public boolean checkUserLogin(String login) throws DaoException {
        boolean statement = false;
        ConnectionProxy connection = ConnectionPool.getInstance().takeConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_CHECK_USER_LOGIN);
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            statement = resultSet.next();
        } catch (SQLException e) {
            throw new DaoException("Unable to find such user.", e);
        } finally {
            closeResources(connection, preparedStatement);
        }
        return statement;
    }

    public User takeUser(String login) throws DaoException {
        ConnectionProxy connection = ConnectionPool.getInstance().takeConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_TAKE_USER_BY_LOGIN);
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            User user = new User();
            if (resultSet.next()) {
                user.setId(resultSet.getInt("client_id"))
                        .setName(resultSet.getString("name"))
                        .setLastName(resultSet.getString("last_name"))
                        .setLogin(login)
                        .setBanned(resultSet.getByte("banned"))
                        .setDiscount(resultSet.getInt("discount"));
            }
            return user;
        } catch (SQLException e) {
            throw new DaoException("Unable to take such user from DB.", e);
        } finally {
            closeResources(connection, preparedStatement);
        }
    }

    public User takeUser(int userId) throws DaoException {
        ConnectionProxy connection = ConnectionPool.getInstance().takeConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_TAKE_USER_BY_ID);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            User user = new User();
            if (resultSet.next()) {
                user.setId(resultSet.getInt("client_id"))
                        .setName(resultSet.getString("name"))
                        .setLastName(resultSet.getString("last_name"))
                        .setLogin(resultSet.getString("login"))
                        .setBanned(resultSet.getByte("banned"))
                        .setDiscount(resultSet.getInt("discount"));
            }
            return user;
        } catch (SQLException e) {
            throw new DaoException("Unable to take such user from DB.", e);
        } finally {
            closeResources(connection, preparedStatement);
        }
    }

    public User takeUserByRequestId(int requestId) throws DaoException {
        ConnectionProxy connection = ConnectionPool.getInstance().takeConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_TAKE_USER_BY_REQUEST_ID);
            preparedStatement.setInt(1, requestId);
            ResultSet resultSet = preparedStatement.executeQuery();
            User user = new User();
            if (resultSet.next()) {
                user.setId(resultSet.getInt("client_id"))
                        .setName(resultSet.getString("name"))
                        .setLastName(resultSet.getString("last_name"))
                        .setLogin(resultSet.getString("login"))
                        .setBanned(resultSet.getByte("banned"))
                        .setDiscount(resultSet.getInt("discount"));
            }
            return user;
        } catch (SQLException e) {
            throw new DaoException("Unable to take such user from DB.", e);
        } finally {
            closeResources(connection, preparedStatement);
        }
    }

    public List<User> takeAllUsers() throws DaoException {
        ConnectionProxy connection = ConnectionPool.getInstance().takeConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_TAKE_ALL_USERS);
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                User user = new User()
                        .setId(resultSet.getInt("client_id"))
                        .setName(resultSet.getString("name"))
                        .setLastName(resultSet.getString("last_name"))
                        .setLogin(resultSet.getString("login"))
                        .setBanned(resultSet.getByte("banned"))
                        .setDiscount(resultSet.getInt("discount"));
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            throw new DaoException("Unable to take all users.", e);
        } finally {
            closeResources(connection, statement);
        }
    }

    public void blockUser(int userId) throws DaoException {
        ConnectionProxy connection = ConnectionPool.getInstance().takeConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_BLOCK_USER);
            preparedStatement.setInt(1, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Unable to update table 'client'.", e);
        } finally {
            closeResources(connection, preparedStatement);
        }
    }

    public void unblockUser(int userId) throws DaoException {
        ConnectionProxy connection = ConnectionPool.getInstance().takeConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_UNBLOCK_USER);
            preparedStatement.setInt(1, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Unable to update table 'client'.", e);
        } finally {
            closeResources(connection, preparedStatement);
        }
    }

    public void addBlockDescription(int userId, String textDescription) throws DaoException {
        ConnectionProxy connection = ConnectionPool.getInstance().takeConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_ADD_BLOCK_DESCRIPTION);
            preparedStatement.setInt(1, userId);
            preparedStatement.setString(2, textDescription);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Unable to update table 'ban_info'.", e);
        } finally {
            closeResources(connection, preparedStatement);
        }
    }

    public String takeBlockDescription(int userId) throws DaoException {
        ConnectionProxy connection = ConnectionPool.getInstance().takeConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_TAKE_BLOCK_DESCRIPTION);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            String description = null;
            while (resultSet.next()) {
                description = resultSet.getString("ban_description");
            }
            return description;
        } catch (SQLException e) {
            throw new DaoException("Unable to take data from 'ban_info' table.", e);
        } finally {
            closeResources(connection, preparedStatement);
        }
    }

    public void changeUserDiscountValue(int discountValue, int userId) throws DaoException {
        ConnectionProxy connection = ConnectionPool.getInstance().takeConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_CHANGE_USER_DISCOUNT);
            preparedStatement.setInt(1, discountValue);
            preparedStatement.setInt(2, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Unable to update table 'client'.", e);
        } finally {
            closeResources(connection, preparedStatement);
        }
    }

    public int takeDiscountValueByUserId(int userId) throws DaoException {
        ConnectionProxy connection = ConnectionPool.getInstance().takeConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_TAKE_DISCOUNT_VALUE_BY_USER_ID);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            int discountValue = 0;
            if(resultSet.next()) {
                discountValue = resultSet.getInt("discount");
            }
            return discountValue;
        } catch (SQLException e) {
            throw new DaoException("Unable to take data from DB..", e);
        } finally {
            closeResources(connection, preparedStatement);
        }
    }

    private static class StaticHolder {
        static final UserDao INSTANCE = new UserDao();
    }

}
