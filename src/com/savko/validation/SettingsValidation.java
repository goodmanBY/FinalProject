package com.savko.validation;

public class SettingsValidation {

    public static boolean isRoomCostValid(String roomCost) {

        boolean result = true;

        if (roomCost.isEmpty()) {
            result = false;
        }

        return result;

    }

    public static boolean isDiscountValueValid(String discount) {

        boolean result = true;

        if (discount.isEmpty()) {
            result = false;
        }

        return result;

    }

}
