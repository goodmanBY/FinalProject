package com.savko.util;

public class CardUtil {

    private static final int LAST_FOUR_DIGITS = 4;

    public static int getFourLastDigits(String cardNumber) {
        return Integer.parseInt(cardNumber.substring(cardNumber.length() - LAST_FOUR_DIGITS, cardNumber.length()));
    }

}
