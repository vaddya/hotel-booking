package com.vaddya.hotelbooking.utils;

public class DateUtils {

    public static java.sql.Date toSqlDate(java.util.Date date) {
        return new java.sql.Date(date.getTime());
    }

    public static java.sql.Time toSqlTime(java.util.Date time) {
        return new java.sql.Time(time.getTime());
    }

}
