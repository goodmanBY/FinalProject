package com.savko.dao;

import com.savko.entity.BookingRequest;
import com.savko.pool.ConnectionPool;
import com.savko.pool.ConnectionProxy;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookingDao extends Dao {

    private static final String SQL_TAKE_SETTING = "SELECT value FROM setting WHERE name = ?;";
    private static final String SQL_BOOK_REQUEST = "INSERT INTO request(client_id, places_num, " +
            "date_from, date_to, cost) VALUES(?, ?, ?, ?, ?);";
    private static final String SQL_TAKE_BOOKING_REQUEST_BY_REQUEST_ID = "SELECT request_id, places_num, " +
            "date_from, date_to, cost FROM request WHERE request_id = ?;";
    private static final String SQL_TAKE_BOOKING_REQUEST_BY_USER_ID = "SELECT * FROM request WHERE client_id = ?;";
    private static final String SQL_TAKE_ALL_BOOKING_REQUESTS = "SELECT * FROM request;";
    private static final String SQL_CONFIRM_BOOKING_REQUEST = "UPDATE request SET confirmed = 1, approved_by = ? WHERE request_id = ?;";
    private static final String SQL_CANCEL_CONFIRMATION = "UPDATE request SET confirmed = 0, approved_by = NULL WHERE request_id = ?;";
    private static final String SQL_DECLINE_BOOKING_REQUEST_BY_REQUEST_ID = "UPDATE request SET declined = 1, approved_by = ? WHERE request_id = ?;";
    private static final String SQL_CANCEL_DECLINATION = "UPDATE request SET declined = 0, approved_by = NULL WHERE request_id = ?;";

    public static BookingDao getInstance() {
        return StaticHolder.INSTANCE;
    }

    public void bookRequest(BookingRequest userRequest) throws DaoException {
        ConnectionProxy connection = ConnectionPool.getInstance().takeConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_BOOK_REQUEST);
            preparedStatement.setInt(1, userRequest.getUserId());
            preparedStatement.setInt(2, userRequest.getAmountOfPlaces());
            preparedStatement.setDate(3, new java.sql.Date(userRequest.getDateFrom().getTime()));
            preparedStatement.setDate(4, new java.sql.Date(userRequest.getDateTo().getTime()));
            preparedStatement.setDouble(5, userRequest.getCost());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Unable to book user's request.", e);
        } finally {
            closeResources(connection, preparedStatement);
        }
    }

    public BookingRequest takeBookingRequestByRequestId(int requestId) throws DaoException {
        ConnectionProxy connection = ConnectionPool.getInstance().takeConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_TAKE_BOOKING_REQUEST_BY_REQUEST_ID);
            preparedStatement.setInt(1, requestId);
            ResultSet resultSet = preparedStatement.executeQuery();
            BookingRequest bookingRequest = new BookingRequest();
            if (resultSet.next()) {
                bookingRequest.setRequestId(resultSet.getInt("request_id"))
                        .setAmountOfPlaces(resultSet.getInt("places_num"))
                        .setDateFrom(resultSet.getDate("date_from"))
                        .setDateTo(resultSet.getDate("date_to"))
                        .setCost(resultSet.getDouble("cost"));
            }
            return bookingRequest;
        } catch (SQLException e) {
            throw new DaoException("Unable to take requests from DB.", e);
        } finally {
            closeResources(connection, preparedStatement);
        }
    }

    public List<BookingRequest> takeAllBookingRequests() throws DaoException {
        ConnectionProxy connection = ConnectionPool.getInstance().takeConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_TAKE_ALL_BOOKING_REQUESTS);
            List<BookingRequest> bookingRequests = new ArrayList<>();
            while (resultSet.next()) {
                BookingRequest bookingRequest = new BookingRequest()
                        .setRequestId(resultSet.getInt("request_id"))
                        .setUserId(resultSet.getInt("client_id"))
                        .setAmountOfPlaces(resultSet.getInt("places_num"))
                        .setDateFrom(resultSet.getDate("date_from"))
                        .setDateTo(resultSet.getDate("date_to"))
                        .setCost(resultSet.getDouble("cost"))
                        .setConfirmed(resultSet.getByte("confirmed"))
                        .setDeclined(resultSet.getByte("declined"))
                        .setPaid(resultSet.getByte("paid"))
                        .setApprovedBy(resultSet.getString("approved_by"));
                bookingRequests.add(bookingRequest);
            }
            return bookingRequests;
        } catch (SQLException e) {
            throw new DaoException("Unable to take all booking requests from 'request' table.", e);
        } finally {
            closeResources(connection, statement);
        }
    }

    public List<BookingRequest> takeBookingRequestsByUserId(int userId) throws DaoException {
        ConnectionProxy connection = ConnectionPool.getInstance().takeConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_TAKE_BOOKING_REQUEST_BY_USER_ID);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<BookingRequest> bookingRequests = new ArrayList<>();
            while (resultSet.next()) {
                BookingRequest bookingRequest = new BookingRequest()
                        .setRequestId(resultSet.getInt("request_id"))
                        .setAmountOfPlaces(resultSet.getInt("places_num"))
                        .setDateFrom(resultSet.getDate("date_from"))
                        .setDateTo(resultSet.getDate("date_to"))
                        .setCost(resultSet.getDouble("cost"))
                        .setConfirmed(resultSet.getByte("confirmed"))
                        .setDeclined(resultSet.getByte("declined"))
                        .setPaid(resultSet.getByte("paid"))
                        .setApprovedBy(resultSet.getString("approved_by"));
                bookingRequests.add(bookingRequest);
            }
            return bookingRequests;
        } catch (SQLException e) {
            throw new DaoException("Unable to take requests from DB.", e);
        } finally {
            closeResources(connection, preparedStatement);
        }
    }

    public void confirmBookingRequest(int requestId, String adminLogin) throws DaoException {
        ConnectionProxy connection = ConnectionPool.getInstance().takeConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_CONFIRM_BOOKING_REQUEST);
            preparedStatement.setString(1, adminLogin);
            preparedStatement.setInt(2, requestId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Unable to take update 'request' table.", e);
        } finally {
            closeResources(connection, preparedStatement);
        }
    }

    public void cancelConfirmation(int requestId) throws DaoException {
        ConnectionProxy connection = ConnectionPool.getInstance().takeConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_CANCEL_CONFIRMATION);
            preparedStatement.setInt(1, requestId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Unable to take update 'request' table.", e);
        } finally {
            closeResources(connection, preparedStatement);
        }
    }

    public void declineBookingRequest(int request_id, String adminLogin) throws DaoException {
        ConnectionProxy connection = ConnectionPool.getInstance().takeConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_DECLINE_BOOKING_REQUEST_BY_REQUEST_ID);
            preparedStatement.setString(1, adminLogin);
            preparedStatement.setInt(2, request_id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Unable to take update 'request' table.", e);
        } finally {
            closeResources(connection, preparedStatement);
        }
    }

    public void cancelDeclination(int requestId) throws DaoException {
        ConnectionProxy connection = ConnectionPool.getInstance().takeConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_CANCEL_DECLINATION);
            preparedStatement.setInt(1, requestId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Unable to take update 'request' table.", e);
        } finally {
            closeResources(connection, preparedStatement);
        }
    }

    public int takeRoomCost() throws DaoException {
        ConnectionProxy connection = ConnectionPool.getInstance().takeConnection();
        PreparedStatement preparedStatement = null;
        int value = 0;
        try {
            preparedStatement = connection.prepareStatement(SQL_TAKE_SETTING);
            preparedStatement.setString(1, "room_cost");
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                value = resultSet.getInt("value");
            }
            return value;
        } catch (SQLException e) {
            throw new DaoException("Unable to take cost of room from 'setting' table.", e);
        } finally {
            closeResources(connection, preparedStatement);
        }
    }

    private static class StaticHolder {
        static final BookingDao INSTANCE = new BookingDao();
    }

}
