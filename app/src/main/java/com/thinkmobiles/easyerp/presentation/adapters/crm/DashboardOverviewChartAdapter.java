package com.thinkmobiles.easyerp.presentation.adapters.crm;

import com.michenko.simpleadapter.SimpleRecyclerAdapter;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.DashboardOverviewChartDH;
import com.thinkmobiles.easyerp.presentation.holders.view.crm.DashboardOverviewChartVH;

/**
 * Created by Asus_Dev on 1/26/2017.
 */

public class DashboardOverviewChartAdapter extends SimpleRecyclerAdapter<DashboardOverviewChartDH, DashboardOverviewChartVH> {

    @Override
    protected int getItemLayout() {
        return R.layout.view_chart_overview_workflow_item;
    }

}
