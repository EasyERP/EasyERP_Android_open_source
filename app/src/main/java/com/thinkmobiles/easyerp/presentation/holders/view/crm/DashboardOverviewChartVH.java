package com.thinkmobiles.easyerp.presentation.holders.view.crm;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.michenko.simpleadapter.OnCardClickListener;
import com.michenko.simpleadapter.RecyclerVH;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.DashboardOverviewChartDH;

/**
 * @author michael.soyma@thinkmobiles.com (Created on 1/26/2017.)
 */

public final class DashboardOverviewChartVH extends RecyclerVH<DashboardOverviewChartDH> {

    private final TextView labelView, countView;

    public DashboardOverviewChartVH(View itemView, @Nullable OnCardClickListener listener, int viewType) {
        super(itemView, listener, viewType);
        labelView = findView(R.id.tvWokrkflowLabel_VCOWI);
        countView = findView(R.id.tvWokrkflowValue_VCOWI);
    }

    @Override
    public void bindData(DashboardOverviewChartDH data) {
        labelView.setText(data.getLabelWorkflow());
        countView.setText(String.valueOf(data.getValueCount()));
    }
}
