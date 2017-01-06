package com.savko.dao;

import com.savko.entity.Request;
import com.savko.entity.User;
import com.savko.pool.ConnectionPool;
import com.savko.pool.ConnectionProxy;
import org.apache.log4j.Logger;

import javax.websocket.Session;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    private final static Logger LOGGER = Logger.getLogger(UserDao.class);

    private static final String SQL_INSERT_USER = "INSERT INTO client(name, last_name, login, password) VALUES(?, ?, ?, ?)";
    private static final String SQL_TAKE_USER = "SELECT client_id, name, last_name, password FROM client WHERE login = ?";
    private static final String SQL_CHECK_USER = "SELECT login, password FROM client WHERE " +
            "login = ? AND password = ?;";
    private static final String SQL_CHECK_USER_LOGIN = "SELECT login FROM client WHERE login = ?;";
    private static final String SQL_DELETE_USER = "DELETE FROM client WHERE login = ?";
    private static final String SQL_BOOK_REQUEST = "INSERT INTO request(client_id, places_num, " +
            "date_from, date_to, cost) VALUES(?, ?, ?, ?, ?)";


    public void addUser(User user) {
        ConnectionProxy connection = ConnectionPool.getInstance().takeConnection();
        try {
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
        ConnectionProxy connection = ConnectionPool.getInstance().takeConnection();
        try {
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
        ConnectionProxy connection = ConnectionPool.getInstance().takeConnection();
        try {
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

    public void bookRequest(Request userRequest) {
        ConnectionProxy connection = ConnectionPool.getInstance().takeConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_BOOK_REQUEST);
            preparedStatement.setInt(1, userRequest.getUserId());
            preparedStatement.setInt(2, userRequest.getAmountOfPlaces());
            preparedStatement.setDate(3, new java.sql.Date(userRequest.getDateFrom().getTime()));
            preparedStatement.setDate(4, new java.sql.Date(userRequest.getDateTo().getTime()));
            preparedStatement.setDouble(5, userRequest.getCost());
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

    public User takeUser(String login) {
        ConnectionProxy connection = ConnectionPool.getInstance().takeConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_TAKE_USER);
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            User user = new User();
            while (resultSet.next()) {
                user.setId(resultSet.getInt("client_id"));
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
        ConnectionProxy connection = ConnectionPool.getInstance().takeConnection();

        try {
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
