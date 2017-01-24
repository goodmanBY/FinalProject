package com.savko.validation;

import java.util.Calendar;

public class UserCardValidation {

    private static final int VALID_CARD_NUMBER_LENGTH = 16;
    private static final int VALID_SECURITY_CODE_LENGTH = 3;
    private static final int FIRST_MONTH_INDEX = 1;
    private static final int LAST_MONTH_INDEX = 12;
    private static final int CURRENT_YEAR = Calendar.getInstance().get(Calendar.YEAR);
    private static final int MAX_VALID_CARD_YEAR = CURRENT_YEAR + 5;

    public static boolean isCardValid(String cardNumber, String month, String year, String ownerName, String securityCode) {

        boolean result = true;

        if (cardNumber.isEmpty() || month.isEmpty() || year.isEmpty() || ownerName.isEmpty() || securityCode.isEmpty()) {
            result = false;
        }

        if (!cardNumber.isEmpty() && cardNumber.length() != VALID_CARD_NUMBER_LENGTH) {
            result = false;
        }

        if (!month.isEmpty() && Integer.parseInt(month) < FIRST_MONTH_INDEX && Integer.parseInt(month) > LAST_MONTH_INDEX) {
            result = false;
        }

        if (!year.isEmpty() && (Integer.parseInt(year) < CURRENT_YEAR || Integer.parseInt(year) > MAX_VALID_CARD_YEAR)) {
            result = false;
        }

        if (!ownerName.isEmpty() && !ownerName.equals(ownerName.toUpperCase())) {
            result = false;
        }

        if (!securityCode.isEmpty() && securityCode.length() != VALID_SECURITY_CODE_LENGTH) {
            result = false;
        }

        if (!securityCode.isEmpty() && securityCode.length() < VALID_SECURITY_CODE_LENGTH) {
            result = false;
        }

        return result;
    }

}
