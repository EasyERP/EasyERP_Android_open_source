package com.thinkmobiles.easyerp.presentation.custom.views.calendar;

import com.michenko.simpleadapter.RecyclerDH;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Alex Michenko on 04.04.2017.
 */

public class MonthDH extends RecyclerDH {
    private Calendar month;
    private ArrayList<DayDH> dayDHs;
    private int weekOffset;

    public MonthDH(int year, int month) {
        this.month = Calendar.getInstance();
        this.month.set(year, month, 1);
        dayDHs = new ArrayList<>();
        int daysOfMonth = this.month.getActualMaximum(Calendar.DAY_OF_MONTH);
        weekOffset = (this.month.get(Calendar.DAY_OF_WEEK) + 5) % 7;
        for (int i = 0; i < weekOffset; i++) {
            dayDHs.add(new DayDH(null));
        }
        for (int i = 0; i < daysOfMonth; i++) {
            dayDHs.add(new DayDH(this.month));
            this.month.add(Calendar.DAY_OF_MONTH, 1);
        }
        this.month.add(Calendar.DAY_OF_MONTH, -daysOfMonth);
    }

    public Calendar getMonth() {
        return month;
    }
    public String getMonthName() {
        return month.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH);
    }

    public ArrayList<DayDH> getDayDHs() {
        return dayDHs;
    }

    public int getWeekOffset() {
        return weekOffset;
    }

    public void setMonth(Calendar month) {
        this.month = month;
    }

    public void setDayDHs(ArrayList<DayDH> dayDHs) {
        this.dayDHs = dayDHs;
    }

    public void setWeekOffset(int weekOffset) {
        this.weekOffset = weekOffset;
    }
}
