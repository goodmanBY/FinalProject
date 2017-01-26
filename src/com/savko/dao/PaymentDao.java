package com.savko.dao;

import com.savko.constant.DbColumns;
import com.savko.entity.PaymentInfo;
import com.savko.pool.ConnectionPool;
import com.savko.pool.ConnectionProxy;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentDao extends Dao {

    private static final String SQL_PAY_USER_REQUEST = "UPDATE request SET paid = 1 WHERE request_id = ?;";
    private static final String SQL_ADD_PAYMENT_INFO = "INSERT INTO payment_info(client_id, request_id, last_four_digits, cost, " +
            "date_time) VALUES (?, ?, ?, ?, ?);";
    private static final String SQL_TAKE_PAYMENT_INFO_BY_ID = "SELECT payment_info_id, client_id, request_id, " +
            "last_four_digits, cost, date_time FROM payment_info WHERE request_id = ?;";

    public static PaymentDao getInstance() {
        return StaticHolder.INSTANCE;
    }

    public void payBookingRequestByRequestId(int requestId) throws DaoException {
        ConnectionProxy connection = ConnectionPool.getInstance().takeConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_PAY_USER_REQUEST);
            preparedStatement.setInt(1, requestId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Unable to take such user from DB.", e);
        } finally {
            closeResources(connection, preparedStatement);
        }
    }

    public void addPaymentInfo(PaymentInfo paymentInfo) throws DaoException {
        ConnectionProxy connection = ConnectionPool.getInstance().takeConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_ADD_PAYMENT_INFO);
            preparedStatement.setInt(1, paymentInfo.getUserId());
            preparedStatement.setInt(2, paymentInfo.getRequestId());
            preparedStatement.setInt(3, paymentInfo.getLastFourDigits());
            preparedStatement.setDouble(4, paymentInfo.getCost());
            preparedStatement.setTimestamp(5, new java.sql.Timestamp(paymentInfo.getDateAndTime().getTime()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Unable to insert payment info in DB.", e);
        } finally {
            closeResources(connection, preparedStatement);
        }
    }

    public PaymentInfo takePaymentInfoByRequestId(int requestId) throws DaoException {
        ConnectionProxy connection = ConnectionPool.getInstance().takeConnection();
        PreparedStatement preparedStatement = null;
        PaymentInfo paymentInfo = new PaymentInfo();
        try {
            preparedStatement = connection.prepareStatement(SQL_TAKE_PAYMENT_INFO_BY_ID);
            preparedStatement.setInt(1, requestId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                paymentInfo.setPaymentInfoId(resultSet.getInt(DbColumns.PAYMENT_INFO_ID))
                        .setUserId(resultSet.getInt(DbColumns.CLIENT_ID))
                        .setRequestId(resultSet.getInt(DbColumns.REQUEST_ID))
                        .setLastFourDigits(resultSet.getInt(DbColumns.LAST_FOUR_DIGITS))
                        .setCost(resultSet.getDouble(DbColumns.COST))
                        .setDateAndTime(resultSet.getTimestamp(DbColumns.DATE_TIME));
            }
            return paymentInfo;
        } catch (SQLException e) {
            throw new DaoException("Unable take data from DB.", e);
        } finally {
            closeResources(connection, preparedStatement);
        }
    }

    private static class StaticHolder {
        static final PaymentDao INSTANCE = new PaymentDao();
    }

}
