package com.thinkmobiles.easyerp.presentation.managers;

import android.text.TextUtils;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
        if(TextUtils.isEmpty(strExpired)) return true;
        SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_COOKIE_EXPIRED, Locale.US);
        try {
            return sdf.parse(strExpired.substring(8)).getTime() < System.currentTimeMillis();
        } catch (ParseException e) {
            Log.d("myLogs", "Parse cookie date error " + e.getMessage());
            return true;
        }
    }

    public static DateConverter convert(String date) {
        return new DateConverter(date);
    }

    public static DateConverter convert(Calendar date) {
        return new DateConverter(date);
    }

    public static class DateConverter {
        private String srcPattern = PATTERN_DATE;
        private String dstPattern = PATTERN_OUTPUT;
        private Locale locale = Locale.ENGLISH;

        private String date;
        private Calendar calendar;

        public DateConverter(String date) {
            this.date = date;
        }

        public DateConverter(Calendar calendar) {
            this.calendar = calendar;
        }

        public DateConverter srcPattern(String srcPattern) {
            this.srcPattern = srcPattern;
            return this;
        }

        public DateConverter setDstPattern(String dstPattern) {
            this.dstPattern = dstPattern;
            return this;
        }

        public String toString() {
            SimpleDateFormat outFormat = new SimpleDateFormat(dstPattern, locale);
            if (calendar == null) {
                if (!TextUtils.isEmpty(date)) {
                    SimpleDateFormat inFormat = new SimpleDateFormat(srcPattern, locale);
                    try {
                        Date day = inFormat.parse(date);
                        if (day == null) {
                            return "Not match patterns";
                        } else {
                            return outFormat.format(day);
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                        return "Parse error";
                    }
                } else {
                    return "No specified";
                }
            } else {
                return outFormat.format(calendar.getTime());
            }
        }

        public Calendar toCalendar() {
            if (calendar == null) {
                SimpleDateFormat outFormat = new SimpleDateFormat(dstPattern, locale);
                try {
                    if (!TextUtils.isEmpty(date)) {
                        Date day = outFormat.parse(date);
                        if (day == null) {
                            new NullPointerException("Not match patterns").printStackTrace();
                            return Calendar.getInstance();
                        } else {
                            calendar = Calendar.getInstance();
                            calendar.setTime(day);
                            return calendar;
                        }
                    } else {
                        new NullPointerException("No specified string date").printStackTrace();
                        return Calendar.getInstance();
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                    return Calendar.getInstance();
                }
            } else {
                return calendar;
            }
        }
    }
}
