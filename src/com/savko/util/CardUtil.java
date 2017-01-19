package com.savko.util;

import java.util.Calendar;

public class CardUtil {

    private static final int VALID_CARD_NUMBER_LENGTH = 16;
    private static final int VALID_CARD_CVV_LENGTH = 3;
    private static final int FIRST_MONTH_INDEX = 1;
    private static final int LAST_MONTH_INDEX = 12;
    private static final int LAST_FOUR_DIGITS = 4;
    private static final int CURRENT_YEAR = Calendar.getInstance().get(Calendar.YEAR);

    public static boolean isCardValid(String cardNumber, int month, int year, String ownerName, String cvvNumber) {
        boolean valid = true;

        if (cardNumber.length() != VALID_CARD_NUMBER_LENGTH) {
            valid = false;
        }

        if (month < FIRST_MONTH_INDEX && month > LAST_MONTH_INDEX) {
            valid = false;
        }

        if (year < CURRENT_YEAR) {
            valid = false;
        }

        if (!ownerName.equals(ownerName.toUpperCase())) {
            valid = false;
        }

        if (cvvNumber.length() != VALID_CARD_CVV_LENGTH) {
            valid = false;
        }

        return valid;
    }

    public static int getFourLastDigits(String cardNumber) {
        return Integer.parseInt(cardNumber.substring(cardNumber.length() - LAST_FOUR_DIGITS, cardNumber.length()));
    }

}
