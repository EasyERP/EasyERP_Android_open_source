package com.thinkmobiles.easyerp.presentation.managers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Lynx on 1/16/2017.
 */

public class DateManager {

    private static final String PATTERN_DATE = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    public static String convertLeadDate(String uglyDate) { //2017-01-16T11:45:04.183Z
        SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_DATE);
        try {
            Date date = sdf.parse(uglyDate);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            return String.format("%d %s, %d, %d:%d:%d",
                    calendar.get(Calendar.DAY_OF_MONTH),
                    calendar.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.US),
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.HOUR_OF_DAY),
                    calendar.get(Calendar.MINUTE),
                    calendar.get(Calendar.SECOND));
        } catch (ParseException e) {
            return "Error";
        }
    }
}
