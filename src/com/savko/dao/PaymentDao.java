package com.savko.dao;

import com.savko.pool.ConnectionPool;
import com.savko.pool.ConnectionProxy;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PaymentDao extends Dao {

    private static final String SQL_PAY_USER_REQUEST = "UPDATE request SET paid = 1 WHERE request_id = ?;";

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

    private static class StaticHolder {
        static final PaymentDao INSTANCE = new PaymentDao();
    }

}
