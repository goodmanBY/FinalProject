package com.savko.dao;

import com.savko.entity.Discount;
import com.savko.pool.ConnectionPool;
import com.savko.pool.ConnectionProxy;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SettingDao extends Dao {

    private static final String SQL_TAKE_SETTING = "SELECT value FROM setting WHERE name = ?;";
    private static final String SQL_CHANGE_ROOM_COST = "UPDATE setting SET value = ?;";
    private static final String SQL_TAKE_ALL_DISCOUNTS = "SELECT discount_id, discount FROM discount;";
    private static final String SQL_CHANGE_DISCOUNT_BY_ID = "UPDATE discount SET discount.discount = ? WHERE discount_id = ?;";
    private static final String SQL_TAKE_DISCOUNT_BY_ID = "SELECT discount.discount FROM discount WHERE " +
            "discount_id = ?;";

    public static SettingDao getInstance() {
        return SettingDao.StaticHolder.INSTANCE;
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

    public void changeRoomCost(int roomCost) throws DaoException {
        ConnectionProxy connection = ConnectionPool.getInstance().takeConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_CHANGE_ROOM_COST);
            preparedStatement.setInt(1, roomCost);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Unable to update table 'setting'.", e);
        } finally {
            closeResources(connection, preparedStatement);
        }
    }

    public List<Discount> takeAllDiscounts() throws DaoException {
        ConnectionProxy connection = ConnectionPool.getInstance().takeConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_TAKE_ALL_DISCOUNTS);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Discount> discounts = new ArrayList<>();
            while (resultSet.next()) {
                Discount discount = new Discount()
                        .setDiscountId(resultSet.getInt("discount_id"))
                        .setDiscount(resultSet.getInt("discount"));
                discounts.add(discount);
            }
            return discounts;
        } catch (SQLException e) {
            throw new DaoException("Unable to take all discounts from 'setting' table.", e);
        } finally {
            closeResources(connection, preparedStatement);
        }
    }

    public Discount takeDiscountById(int discountId) throws DaoException {
        ConnectionProxy connection = ConnectionPool.getInstance().takeConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_TAKE_DISCOUNT_BY_ID);
            preparedStatement.setInt(1, discountId);
            ResultSet resultSet = preparedStatement.executeQuery();
            Discount discount = new Discount();
            if(resultSet.next()) {
                discount.setDiscountId(discountId)
                        .setDiscount(resultSet.getInt("discount"));
            }
            return discount;
        } catch (SQLException e) {
            throw new DaoException("Unable to take discount from 'setting' table.", e);
        } finally {
            closeResources(connection, preparedStatement);
        }
    }

    public void changeDiscountById(int discount, int discountId) throws DaoException {
        ConnectionProxy connection = ConnectionPool.getInstance().takeConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_CHANGE_DISCOUNT_BY_ID);
            preparedStatement.setInt(1, discount);
            preparedStatement.setInt(2, discountId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Unable to update 'discount' table.", e);
        } finally {
            closeResources(connection, preparedStatement);
        }
    }

    private static class StaticHolder {
        static final SettingDao INSTANCE = new SettingDao();
    }

}
