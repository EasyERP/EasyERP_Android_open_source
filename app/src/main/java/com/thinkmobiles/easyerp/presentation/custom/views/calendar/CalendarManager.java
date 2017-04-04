package com.thinkmobiles.easyerp.presentation.custom.views.calendar;

import android.support.annotation.IntDef;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.michenko.simpleadapter.SimpleRecyclerAdapter;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.managers.ColorHelper;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Calendar;

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
    private WeakReference<RecyclerView> list;


    private int year = Calendar.getInstance().get(Calendar.YEAR);

    public CalendarManager(RecyclerView recyclerView) {
        setList(recyclerView);
        setYear(year);
    }

    private void setList(final RecyclerView recyclerView) {
        list = new WeakReference<>(recyclerView);
        adapter = new YearAdapter();
        GridLayoutManager layoutManager = new GridLayoutManager(recyclerView.getContext(), 2);
        list.get().setLayoutManager(layoutManager);
        list.get().setAdapter(adapter);
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
        adapter.getItem(month).setDayDHs(fillMonth(adapter.getItem(month), types));
    }

    public static ArrayList<DayDH> fillMonth(MonthDH monthDH, ArrayList<String> types) {
        ArrayList<DayDH> days = monthDH.getDayDHs();
        int d;
        @Shape int type;
        @Shape int prevType = NONE;
        for (int i = 0; i < types.size(); i++) {
            d = monthDH.getWeekOffset() + i;
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
            days.get(d).setShapeColor(ColorHelper.getLeaveTypeColor(types.get(i)));
        }
        return days;
    }

    private class YearAdapter extends SimpleRecyclerAdapter<MonthDH, MonthVH> {
        @Override
        protected int getItemLayout() {
            return R.layout.view_month;
        }
    }

}

