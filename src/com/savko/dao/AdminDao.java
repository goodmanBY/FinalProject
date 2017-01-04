package com.savko.dao;

import com.savko.constant.DBConstants;
import com.savko.entity.Admin;
import com.savko.entity.User;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDao {

    private final static Logger LOGGER = Logger.getLogger(AdminDao.class);

    private static final String SQL_CHECK_ADMIN = "SELECT login, password FROM admin WHERE " +
            "login = ? AND password = ?;";
    private static final String SQL_INSERT_ADMIN = "INSERT INTO admin(login, password) VALUES(?, ?)";
    private static final String SQL_TAKE_ALL_USERS = "SELECT * FROM client";

    private static Connection connection;

    public void addAdmin(Admin admin) {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            connection = DriverManager.getConnection(DBConstants.DB_URL, DBConstants.NAME, DBConstants.PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_ADMIN);
            preparedStatement.setString(1, admin.getLogin());
            preparedStatement.setString(2, admin.getPassword());
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

    public boolean checkAdmin(String login, String password) {
        boolean statement = false;
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            Connection connection = DriverManager.getConnection(DBConstants.DB_URL, DBConstants.NAME, DBConstants.PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_CHECK_ADMIN);
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

    public List<User> takeAllUsers() {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            connection = DriverManager.getConnection(DBConstants.DB_URL, DBConstants.NAME, DBConstants.PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_TAKE_ALL_USERS);
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("client_id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setBanned(resultSet.getByte("banned"));
                user.setDiscountId(resultSet.getShort("discount_id"));
                users.add(user);
            }
            return users;
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

}
