package com.thinkmobiles.easyerp.presentation.holders.view.crm;

import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.michenko.simpleadapter.OnCardClickListener;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.data.model.crm.dashboard.detail.DashboardChartType;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectableVHHelper;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.DashboardListDH;

/**
 * @author michael.soyma@thinkmobiles.com (Created on 1/18/2017.)
 */

public final class DashboardVH extends SelectableVHHelper<DashboardListDH> {

    private final TextView tvDashboardName_VLICD;
    private final ImageView ivChartType_VLICD;

    public DashboardVH(View itemView, @Nullable OnCardClickListener listener, int viewType) {
        super(itemView, listener, viewType);

        tvDashboardName_VLICD = findView(R.id.tvDashboardName_VLICD);
        ivChartType_VLICD = findView(R.id.ivChartType_VLICD);
    }

    @Override
    public void bindData(DashboardListDH data) {
        super.bindData(data);

        tvDashboardName_VLICD.setText(data.getDashboardListItem().name);
        ivChartType_VLICD.setImageResource(getChartIconRes(data.getDashboardListItem().getChartType()));
    }

    private @DrawableRes int getChartIconRes(final DashboardChartType chartType) {
        switch (chartType) {
            case DONUT: return R.drawable.ic_chart_donut;
            case OVERVIEW: return R.drawable.ic_chart_overview;
            case TABLE: return R.drawable.ic_chart_table;
            case HORIZONTALBAR: return R.drawable.ic_chart_horizontal_bar;
            default : return 0;
        }
    }
}