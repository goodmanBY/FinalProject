package com.savko.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateUtil {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public static Timestamp getCurrentDateAndTime() throws UtilException {
        Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();
        return new Timestamp(now.getTime());
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
