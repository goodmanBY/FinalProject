package com.savko.jdbc;

import com.savko.dao.UserDao;

import java.sql.*;

public class JDBCTest {

    private static Connection connection;
    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/Hostel";
    private static final String NAME = "root";
    private static final String PASSWORD = "root";

    public static void main(String args[]) {
        try {
            connection = DriverManager.getConnection(DB_URL, NAME, PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO client(name, last_name, login, " +
                    "password) VALUES (?, ?, ?, ?)");
            preparedStatement.setString(1, "Test");
            preparedStatement.setString(2, "Test");
            preparedStatement.setString(3, "Test");
            preparedStatement.setString(4, "Test");
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }

        }


    }

}
