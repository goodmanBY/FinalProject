package com.savko.util;

import com.savko.dao.DaoException;
import com.savko.dao.UserDao;

public class CostUtil {

    public static double calculateCost(int amountOfPlaces, int amountOfDays) throws UtilException {
        UserDao userDao = new UserDao();
        try {
            return userDao.takeRoomCost() * amountOfPlaces * amountOfDays;
        } catch (DaoException e) {
            throw new UtilException("Unable to calculate cost of reservation.", e);
        }
    }
}
