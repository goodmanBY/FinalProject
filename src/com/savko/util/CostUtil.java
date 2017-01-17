package com.savko.util;

import com.savko.dao.BookingDao;
import com.savko.dao.DaoException;
import com.savko.service.BookingService;
import com.savko.service.ServiceException;

public class CostUtil {

    public static double calculateCost(int amountOfPlaces, int amountOfDays) throws UtilException {
        try {
            return BookingService.getInstance().takeRoomCost() * amountOfPlaces * amountOfDays;
        } catch (ServiceException e) {
            throw new UtilException("Unable to calculate cost of reservation.", e);
        }
    }
}
