package com.savko.util;

public class CostUtil {

    public static double calculateCost(int amountOfPlaces, int amountOfDays) {
        return 5 + 10 * (amountOfPlaces + amountOfDays);
    }

}
