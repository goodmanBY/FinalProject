package com.savko.validation;

import com.savko.util.UtilException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DatesValidation {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public static boolean areDatesValid(String stringDateFrom, String stringDateTo) throws UtilException {

        boolean valid = true;
        try {
            if (!stringDateFrom.isEmpty() || !stringDateTo.isEmpty()) {
                Date dateFrom = DATE_FORMAT.parse(stringDateFrom);
                Date dateTo = DATE_FORMAT.parse(stringDateTo);
                if (dateTo.getTime() - dateFrom.getTime() < 1) {
                    valid = false;
                }
            } else {
                valid = false;
            }
            return valid;
        } catch (ParseException e) {
            throw new UtilException("Unable to calculate amount of days between dates.", e);
        }
    }

}
