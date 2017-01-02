package com.savko.dao;

import com.savko.constant.DBConstants;
import com.savko.entity.User;
import com.savko.util.Hex;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    private final static Logger LOGGER = Logger.getLogger(UserDao.class);

    private static final String SQL_INSERT_USER = "INSERT INTO client(name, last_name, login, password) VALUES(?, ?, ?, ?)";
    private static final String SQL_TAKE_USER = "SELECT name, last_name, password FROM client WHERE login = ?";
    private static final String SQL_CHECK_USER = "SELECT login, password FROM client WHERE " +
            "login = ? AND password = ?;";
    private static final String SQL_CHECK_USER_LOGIN = "SELECT login FROM client WHERE login = ?;";
    private static final String SQL_DELETE_USER = "DELETE FROM client WHERE login = ?";

    private static Connection connection;

    public User createUser(String name, String lastName, String login, String password) {
        return new User(name, lastName, login, password);
    }

    public void addUser(User user) {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            connection = DriverManager.getConnection(DBConstants.DB_URL, DBConstants.NAME, DBConstants.PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_USER);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getLogin());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Some SQL issue!" + e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOGGER.error("Some SQL issue!" + e);
                }
            }
        }
    }

    public boolean checkUser(String login, String password) {
        boolean statement = false;
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            Connection connection = DriverManager.getConnection(DBConstants.DB_URL, DBConstants.NAME, DBConstants.PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_CHECK_USER);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            statement = resultSet.next();
        } catch (SQLException e) {
            LOGGER.error("Some SQL issue!" + e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOGGER.error("Some SQL issue!" + e);
                }
            }
        }
        return statement;
    }

    public boolean checkUserLogin(String login) {
        boolean statement = false;
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            Connection connection = DriverManager.getConnection(DBConstants.DB_URL, DBConstants.NAME, DBConstants.PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_CHECK_USER_LOGIN);
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            statement = resultSet.next();
        } catch (SQLException e) {
            LOGGER.error("Some SQL issue!" + e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOGGER.error("Some SQL issue!" + e);
                }
            }
        }
        return statement;
    }

    public User takeUser(String login) {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            connection = DriverManager.getConnection(DBConstants.DB_URL, DBConstants.NAME, DBConstants.PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_TAKE_USER);
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            User user = new User();
            while (resultSet.next()) {
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setLogin(login);
                user.setPassword(resultSet.getString("password"));
            }
            return user;
        } catch (SQLException e) {
            LOGGER.error("Some SQL issue!" + e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOGGER.error("Some SQL issue!" + e);
                }
            }
        }
        return null;
    }

    public void deleteUser(String login) {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            connection = DriverManager.getConnection(DBConstants.DB_URL, DBConstants.NAME, DBConstants.PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_USER);
            preparedStatement.setString(1, login);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Some SQL issue!" + e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOGGER.error("Some SQL issue!" + e);
                }
            }
        }
    }

}
