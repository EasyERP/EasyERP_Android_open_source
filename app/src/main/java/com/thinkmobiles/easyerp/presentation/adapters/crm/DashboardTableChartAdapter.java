package com.thinkmobiles.easyerp.presentation.adapters.crm;

import com.michenko.simpleadapter.SimpleRecyclerAdapter;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.DashboardTableChartDH;
import com.thinkmobiles.easyerp.presentation.holders.view.crm.DashboardTableChartVH;

/**
 * @author Michael Soyma (Created on 2/14/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public class DashboardTableChartAdapter extends SimpleRecyclerAdapter<DashboardTableChartDH, DashboardTableChartVH> {

    @Override
    protected int getItemLayout() {
        return R.layout.view_chart_table_item;
    }
}
