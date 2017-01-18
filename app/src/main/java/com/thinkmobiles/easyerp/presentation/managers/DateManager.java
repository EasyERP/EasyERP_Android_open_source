package com.thinkmobiles.easyerp.presentation.managers;

import android.text.TextUtils;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Lynx on 1/16/2017.
 */

public abstract class DateManager {

    private static final String PATTERN_DATE                = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    private static final String PATTERN_OUTPUT              = "dd MMM, yyyy, HH:mm:ss";
    private static final String PATTERN_COOKIE_EXPIRED      = "E, dd MMM yyyy HH:mm:ss z"; //Expires=Wed, 18 Jan 2017 08:58:07 GMT

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

    public static boolean isCookieExpired(String strExpired) {
        if(TextUtils.isEmpty(strExpired)) {
            Log.d("myLogs", "isCookieExpired() :: Cookie is empty");
            return true;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_COOKIE_EXPIRED, Locale.US);
        try {
            return sdf.parse(strExpired.substring(8)).getTime() < System.currentTimeMillis();
        } catch (ParseException e) {
            Log.d("myLogs", "Parse cookie date error " + e.getMessage());
            return true;
        }
    }
}
