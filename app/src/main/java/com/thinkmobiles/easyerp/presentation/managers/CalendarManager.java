package com.thinkmobiles.easyerp.presentation.managers;

import android.graphics.Color;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.michenko.simpleadapter.OnCardClickListener;
import com.michenko.simpleadapter.RecyclerDH;
import com.michenko.simpleadapter.RecyclerVH;
import com.michenko.simpleadapter.SimpleRecyclerAdapter;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.EasyErpApplication;
import com.thinkmobiles.easyerp.presentation.custom.views.calendar.MonthView;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Alex Michenko on 22.03.2017.
 */

public class CalendarManager {


        @IntDef({NONE, CIRCLE, LEFT, CENTER, RIGHT})
        @Retention(RetentionPolicy.SOURCE)
        public @interface Shape {
        }

        public static final int NONE = 0;
        public static final int CIRCLE = 1;
        public static final int LEFT = 2;
        public static final int CENTER = 3;
        public static final int RIGHT = 4;

        private YearAdapter adapter;

        private int year = Calendar.getInstance().get(Calendar.YEAR);

        public CalendarManager(RecyclerView recyclerView) {
            setList(recyclerView);
            setYear(year);
        }

        private void setList(RecyclerView recyclerView) {
            adapter = new YearAdapter();
            GridLayoutManager layoutManager = new GridLayoutManager(recyclerView.getContext(), 2);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
        }

        public void setYear(int year) {
            this.year = year;
            ArrayList<MonthDH> monthDHs = new ArrayList<>();
            for (int i = 0; i < 12; i++) {
                monthDHs.add(new MonthDH(year, i));
            }
            adapter.setListDH(monthDHs);
        }

        public int getYear() {
            return year;
        }

        public void setMonth(int month, ArrayList<String> types) {
            ArrayList<DayDH> days = adapter.getListDH().get(month).dayDHs;
            int offset = adapter.getListDH().get(month).weekOffset;
            int d;
            @Shape int type;
            @Shape int prevType = NONE;
            for (int i = 0; i < types.size(); i++) {
                d = offset + i;
                type = types.get(i) == null ? NONE : CIRCLE;
                if (type != NONE) {
                    if (prevType != NONE) {
                        type = RIGHT;
                        if (prevType == RIGHT) {
                            days.get(d - 1).setShapeType(CENTER);
                        } else {
                            days.get(d - 1).setShapeType(LEFT);
                        }
                    }
                }
                prevType = d % 7 == 6 ? NONE : type;
                days.get(d).setShapeType(type);
                days.get(d).setShapeColor(getColor(types.get(i)));
            }
        }

        private int getColor(String type) {
            if (type == null)
                return Color.TRANSPARENT;
            switch (type) {
                case "V":
                    return ContextCompat.getColor(EasyErpApplication.getInstance(),R.color.color_vacation);
                case "P":
                    return ContextCompat.getColor(EasyErpApplication.getInstance(), R.color.color_personal);
                case "S":
                    return ContextCompat.getColor(EasyErpApplication.getInstance(), R.color.color_sick);
                case "E":
                    return ContextCompat.getColor(EasyErpApplication.getInstance(), R.color.color_education);
                default:
                    return Color.TRANSPARENT;
            }
        }

        private class YearAdapter extends SimpleRecyclerAdapter<MonthDH, MonthVH>  {

            @Override
            protected int getItemLayout() {
                return R.layout.view_month;
            }
        }

        private class MonthVH extends RecyclerVH<MonthDH> {

            private MonthView monthView;

            public MonthVH(View itemView, @Nullable OnCardClickListener listener, int viewType) {
                super(itemView, listener, viewType);
                monthView = findView(R.id.month);
            }

            @Override
            public void bindData(MonthDH monthDH) {
                monthView.setDays(monthDH.dayDHs);
                monthView.setMonthTitle(monthDH.month.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH));
            }
        }

        public class MonthDH extends RecyclerDH {
            private Calendar month;
            private ArrayList<DayDH> dayDHs;
            public int weekOffset;

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
        }

        public class DayDH {
            private Calendar date;
            private String day = "";
            @Shape
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

            @Shape
            public int getShapeType() {
                return shapeType;
            }

            public void setShapeType(@Shape int shapeType) {
                this.shapeType = shapeType;
            }

            public int getShapeColor() {
                return shapeColor;
            }

            public void setShapeColor(int shapeColor) {
                this.shapeColor = shapeColor;
            }
        }

    }

