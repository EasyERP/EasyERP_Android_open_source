package com.thinkmobiles.easyerp.presentation.custom.views.calendar;

import android.graphics.Color;

import java.util.Calendar;

import static com.thinkmobiles.easyerp.presentation.custom.views.calendar.CalendarManager.NONE;

/**
 * Created by Alex Michenko on 04.04.2017.
 */

public class DayDH {
    private Calendar date;
    private String day = "";
    @CalendarManager.Shape
    private int shapeType = NONE;
    private int shapeColor = Color.TRANSPARENT;

    public DayDH(Calendar date) {
        if (date != null) {
            this.date = (Calendar) date.clone();
            this.day = String.valueOf(date.get(Calendar.DAY_OF_MONTH));
        }
    }

    public Calendar getDate() {
        return date;
    }

    public String getDay() {
        return day;
    }

    @CalendarManager.Shape
    public int getShapeType() {
        return shapeType;
    }

    public void setShapeType(@CalendarManager.Shape int shapeType) {
        this.shapeType = shapeType;
    }

    public int getShapeColor() {
        return shapeColor;
    }

    public void setShapeColor(int shapeColor) {
        this.shapeColor = shapeColor;
    }
}
