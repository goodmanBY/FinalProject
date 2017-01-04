package com.savko.dao;

import com.savko.constant.DBConstants;
import com.savko.entity.Admin;
import com.savko.entity.User;
import org.apache.log4j.Logger;

import java.sql.*;

public class AdminDao {

    private final static Logger LOGGER = Logger.getLogger(AdminDao.class);

    private static final String SQL_CHECK_ADMIN = "SELECT login, password FROM admin WHERE " +
            "login = ? AND password = ?;";
    private static final String SQL_INSERT_ADMIN= "INSERT INTO admin(login, password) VALUES(?, ?)";

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

}
