package com.savko.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateUtil {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");


    public static Date getCurrentDateAndTime() throws UtilException {
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            return dateFormat.parse(dateFormat.format(date));
        } catch (ParseException e) {
            throw new UtilException("Unable to get current date and time.", e);
        }
    }

    public static boolean areDatesValid(String stringDateFrom, String stringDateTo) throws UtilException {
        boolean valid = true;
        try {
            Date dateFrom = DATE_FORMAT.parse(stringDateFrom);
            Date dateTo = DATE_FORMAT.parse(stringDateTo);
            if (dateTo.getTime() - dateFrom.getTime() < 1) {
                valid = false;
            }
            return valid;
        } catch (ParseException e) {
            throw new UtilException("Unable to calculate amount of days between dates.", e);
        }
    }

    public static int calculateAmountOfDays(String stringDateFrom, String stringDateTo) throws UtilException {
        try {
            Date dateFrom = DATE_FORMAT.parse(stringDateFrom);
            Date dateTo = DATE_FORMAT.parse(stringDateTo);
            long difference = dateTo.getTime() - dateFrom.getTime();
            return (int) TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS);
        } catch (ParseException e) {
            throw new UtilException("Unable to calculate amount of days between dates.", e);
        }

    }

    public static Date castToDate(String stringDate) throws UtilException {
        try {
            return DATE_FORMAT.parse(stringDate);
        } catch (ParseException e) {
            throw new UtilException("Unable to cast String to Date format.", e);
        }
    }

}
