package com.thinkmobiles.easyerp.presentation.custom.views.calendar;

import android.support.annotation.Nullable;
import android.view.View;

import com.michenko.simpleadapter.OnCardClickListener;
import com.michenko.simpleadapter.RecyclerVH;
import com.thinkmobiles.easyerp.R;

/**
 * Created by Alex Michenko on 04.04.2017.
 */

public final class MonthVH extends RecyclerVH<MonthDH> {

    private final MonthView monthView;

    public MonthVH(View itemView, @Nullable OnCardClickListener listener, int viewType) {
        super(itemView, listener, viewType);
        monthView = findView(R.id.month);
    }

    @Override
    public void bindData(MonthDH monthDH) {
        monthView.setDays(monthDH.getDayDHs());
        monthView.setMonthTitle(monthDH.getMonthName());
    }
}
