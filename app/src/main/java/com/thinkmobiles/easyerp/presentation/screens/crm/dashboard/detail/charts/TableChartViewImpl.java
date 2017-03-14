package com.thinkmobiles.easyerp.presentation.screens.crm.dashboard.detail.charts;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.data.model.ResponseGetTotalItems;
import com.thinkmobiles.easyerp.data.model.crm.invoice.Invoice;
import com.thinkmobiles.easyerp.presentation.adapters.crm.DashboardTableChartAdapter;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.DashboardTableChartDH;

import java.util.ArrayList;

/**
 * @author michael.soyma@thinkmobiles.com (Created on 1/25/2017.)
 */

public class TableChartViewImpl implements IChartView<ArrayList<DashboardTableChartDH>> {

    @Override
    public void render(FrameLayout parent, Object data) {
        parent.removeAllViews();

        final View targetView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_chart_table, parent, false);
        final ArrayList<DashboardTableChartDH> preparedData = prepareData(data);

        final RecyclerView workflowsRecycler = (RecyclerView) targetView.findViewById(R.id.rvListInvoices_VCT);
        final View noDataView = targetView.findViewById(R.id.tvNoData_VCT);

        if (preparedData.size() > 0) {
            final DashboardTableChartAdapter adapter = new DashboardTableChartAdapter();
            adapter.setListDH(preparedData);
            workflowsRecycler.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
            workflowsRecycler.setAdapter(adapter);
        } else noDataView.setVisibility(View.VISIBLE);

        parent.addView(targetView);
    }

    @Override
    public ArrayList<DashboardTableChartDH> prepareData(Object data) {
        final ResponseGetTotalItems<Invoice> responseGetInvoice = (ResponseGetTotalItems<Invoice>) data;
        final ArrayList<DashboardTableChartDH> dataList = new ArrayList<>();

        for (Invoice invoice : responseGetInvoice.data)
            dataList.add(new DashboardTableChartDH(invoice));

        return dataList;
    }
}
