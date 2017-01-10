package com.savko.util;

import com.savko.dao.BookingDao;
import com.savko.dao.DaoException;

public class CostUtil {

    public static double calculateCost(int amountOfPlaces, int amountOfDays) throws UtilException {
        BookingDao bookingDao = new BookingDao();
        try {
            return bookingDao.takeRoomCost() * amountOfPlaces * amountOfDays;
        } catch (DaoException e) {
            throw new UtilException("Unable to calculate cost of reservation.", e);
        }
    }
}
