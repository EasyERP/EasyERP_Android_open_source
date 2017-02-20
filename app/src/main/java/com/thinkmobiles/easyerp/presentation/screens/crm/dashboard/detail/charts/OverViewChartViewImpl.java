package com.thinkmobiles.easyerp.presentation.screens.crm.dashboard.detail.charts;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.data.model.crm.dashboard.detail.order.OrderItem;
import com.thinkmobiles.easyerp.data.model.crm.dashboard.detail.order.OrderStatus;
import com.thinkmobiles.easyerp.presentation.adapters.crm.DashboardOverviewChartAdapter;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.DashboardOverviewChartDH;
import com.thinkmobiles.easyerp.presentation.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author michael.soyma@thinkmobiles.com (Created on 1/26/2017.)
 */

public class OverViewChartViewImpl implements IChartView<ArrayList<DashboardOverviewChartDH>> {

    private final int[] afs = new int[3];
    private int totalCount;
    private double totalRevenue;

    @Override
    public void render(FrameLayout parent, Object data) {

        parent.removeAllViews();

        final View targetView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_chart_overview, parent, false);
        final ArrayList<DashboardOverviewChartDH> dataWorkflows = prepareData(data);

        displayAFS(targetView.findViewById(R.id.llAFSInfoLayout_VCO));
        ((TextView) targetView.findViewById(R.id.tvTotalCountValue_VCO)).setText(String.valueOf(totalCount));
        ((TextView) targetView.findViewById(R.id.tvTotalRevenueValue_VCO )).setText(StringUtil.getFormattedPrice(new DollarFormatter().getFormat(), totalRevenue));

        final RecyclerView workflowsRecycler = (RecyclerView) targetView.findViewById(R.id.rvWorkflows_VCO);
        final DashboardOverviewChartAdapter adapter = new DashboardOverviewChartAdapter();
        adapter.setListDH(dataWorkflows);

        if (dataWorkflows.size() > 0) {
            workflowsRecycler.setLayoutManager(new GridLayoutManager(parent.getContext(), 4, LinearLayoutManager.VERTICAL, false));
            workflowsRecycler.setAdapter(adapter);
        }

        parent.addView(targetView);
    }

    @SuppressWarnings("unchecked")
    @Override
    public ArrayList<DashboardOverviewChartDH> prepareData(Object data) {
        final ArrayList<DashboardOverviewChartDH> dataWorkflows = new ArrayList<>();
        for (OrderItem item: ((List<OrderItem>) data)) {
            dataWorkflows.add(new DashboardOverviewChartDH(item.name, item.count));

            if (item.status != null) {
                for (OrderStatus status : item.status) {
                    afs[0] += status.allocateStatus.equals("ALL") ? 1 : 0;
                    afs[1] += status.fulfillStatus.equals("ALL") ? 1 : 0;
                    afs[2] += status.shippingStatus.equals("ALL") ? 1 : 0;
                }
            }

            totalCount += item.count;
            totalRevenue += item.total;
        }
        return dataWorkflows;
    }

    private void displayAFS(final View rootAFS) {
        if (afs[0] == 0 && afs[1] == 0 && afs[2] == 0) {
            rootAFS.setVisibility(View.GONE);
        } else {
            ((TextView) rootAFS.findViewById(R.id.tvAllocatedValue_VCO)).setText(String.valueOf(afs[0]));
            ((TextView) rootAFS.findViewById(R.id.tvFulfilledValue_VCO)).setText(String.valueOf(afs[1]));
            ((TextView) rootAFS.findViewById(R.id.tvShippedValue_VCO)).setText(String.valueOf(afs[2]));
        }
    }
}
