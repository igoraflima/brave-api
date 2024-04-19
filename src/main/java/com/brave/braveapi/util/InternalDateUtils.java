package com.brave.braveapi.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class InternalDateUtils {

    private static final String DEFAULT_PATTERN_DATE = "dd/MM/yyyy";

    public static Date minHoursDay(Long date) {
        return minHoursDay(new Date(date));
    }

    public static Date minHoursDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        return cal.getTime();
    }

    public static Date maxHoursDay(Long date) {
        return maxHoursDay(new Date(date));
    }

    public static Date maxHoursDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime();
    }

    public static boolean isEqualToday(Date date) {
        return minHoursDay(new Date()).equals(minHoursDay(date));
    }

    public static boolean isBeforeToday(Date date) {
        return minHoursDay(date).before(minHoursDay(new Date()));
    }

    public static boolean isAfterToday(Date date) {
        return minHoursDay(date).after(minHoursDay(new Date()));
    }

    public static String parseToString(Date date) {
        return parseToString(date, DEFAULT_PATTERN_DATE);
    }

    public static String parseToString(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static Date parse(String date) {
        return parse(date, DEFAULT_PATTERN_DATE);
    }

    public static Date parse(String date, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.parse(date);
        } catch (Exception e) {
            return new Date();
        }
    }
}
