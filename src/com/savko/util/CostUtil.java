package com.savko.util;

import com.savko.service.BookingService;
import com.savko.service.ServiceException;
import com.savko.service.SettingService;

public class CostUtil {

    public static double calculateCost(int amountOfPlaces, int amountOfDays, int userDiscount) throws UtilException {
        try {
            double cost = SettingService.getInstance().takeRoomCost() * amountOfPlaces * amountOfDays;
            double discount = cost / 100 * userDiscount;
            return cost - discount;
        } catch (ServiceException e) {
            throw new UtilException("Unable to calculate cost of reservation.", e);
        }
    }

}
