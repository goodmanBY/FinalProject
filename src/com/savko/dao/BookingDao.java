package com.savko.dao;

import com.savko.entity.BookingRequest;
import com.savko.pool.ConnectionPool;
import com.savko.pool.ConnectionProxy;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookingDao extends Dao {

    private static final String SQL_TAKE_SETTING = "SELECT value FROM setting WHERE name = ?";
    private static final String SQL_BOOK_REQUEST = "INSERT INTO request(client_id, places_num, " +
            "date_from, date_to, cost) VALUES(?, ?, ?, ?, ?)";
    private static final String SQL_TAKE_REQUEST_BY_REQUEST_ID = "SELECT request_id, places_num, " +
            "date_from, date_to, cost FROM request WHERE request_id = ?";
    private static final String SQL_TAKE_REQUESTS_BY_USER_ID = "SELECT request_id, places_num, " +
            "date_from, date_to, cost, confirmed, paid, approved_by FROM request WHERE client_id = ?";

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
            preparedStatement = connection.prepareStatement(SQL_TAKE_REQUEST_BY_REQUEST_ID);
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

    public List<BookingRequest> takeRequestsByUserId(int userId) throws DaoException {
        ConnectionProxy connection = ConnectionPool.getInstance().takeConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_TAKE_REQUESTS_BY_USER_ID);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<BookingRequest> requestsById = new ArrayList<>();
            while (resultSet.next()) {
                BookingRequest bookingRequest = new BookingRequest().setRequestId(resultSet.getInt("request_id"))
                        .setAmountOfPlaces(resultSet.getInt("places_num")).setDateFrom(resultSet.getDate("date_from"))
                        .setDateTo(resultSet.getDate("date_to")).setCost(resultSet.getDouble("cost"))
                        .setConfirmed(resultSet.getByte("confirmed")).setPaid(resultSet.getByte("paid"))
                        .setApprovedBy(resultSet.getInt("approved_by"));
                requestsById.add(bookingRequest);
            }
            return requestsById;
        } catch (SQLException e) {
            throw new DaoException("Unable to take requests from DB.", e);
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
            while (resultSet.next()) {
                value = resultSet.getInt("value");
            }
            return value;
        } catch (SQLException e) {
            throw new DaoException("Unable to take cost of room.", e);
        } finally {
            closeResources(connection, preparedStatement);
        }
    }

}
