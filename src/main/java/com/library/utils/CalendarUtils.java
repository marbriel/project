package com.library.utils;

import java.util.Calendar;
import java.util.Date;

public class CalendarUtils {

    private static final Calendar calendar = Calendar.getInstance();
    private static final Date currentDate = new Date();

    /*
        Expected of return date is 3 days after the borrowed date.
        this is the default number of days if the day is not specified
     */
    public static Date expectedDateReturn(){
        calendar.setTime(currentDate);
        calendar.add(Calendar.DATE, 3);
        return calendar.getTime();
    }


    public static Date expectedDateReturn(int days){
        calendar.setTime(currentDate);
        calendar.add(Calendar.DATE, days);
        return calendar.getTime();
    }

    public static Date getCurrentDate(){
        return currentDate;
    }
}
