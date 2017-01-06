package com.savko.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateUtil {

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static int calculateAmountOfDays(String stringDateFrom, String stringDateTo) {
        try {
            Date dateFrom = simpleDateFormat.parse(stringDateFrom);
            Date dateTo = simpleDateFormat.parse(stringDateTo);
            long difference = dateTo.getTime() - dateFrom.getTime();
            return (int) TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public static Date castToDate(String stringDate) {
        try {
            return simpleDateFormat.parse(stringDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}
