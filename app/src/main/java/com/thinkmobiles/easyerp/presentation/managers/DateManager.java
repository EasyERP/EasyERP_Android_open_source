package com.thinkmobiles.easyerp.presentation.managers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Lynx on 1/16/2017.
 */

public abstract class DateManager {

    private static final String PATTERN_DATE    = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    private static final String PATTERN_OUTPUT  = "dd MMM, yyyy, HH:mm:ss";

    public static String convertLeadDate(String uglyDate) { //2017-01-16T11:45:04.183Z
        SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_DATE, Locale.US);
        SimpleDateFormat sdfOut = new SimpleDateFormat(PATTERN_OUTPUT, Locale.US);
        try {
            Date date = sdf.parse(uglyDate);
            return sdfOut.format(date);
        } catch (ParseException e) {
            return "Error";
        }
    }
}
