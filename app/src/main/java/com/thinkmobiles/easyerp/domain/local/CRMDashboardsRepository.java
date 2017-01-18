package com.thinkmobiles.easyerp.domain.local;

import com.thinkmobiles.easyerp.data.model.crm.dashboard.DashboardListItem;
import com.thinkmobiles.easyerp.presentation.screens.crm.dashboard.DashboardListContract;

import org.androidannotations.annotations.EBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Asus_Dev on 1/18/2017.
 */
@EBean(scope = EBean.Scope.Singleton)
public class CRMDashboardsRepository implements DashboardListContract.DashboardListModel {

    @Override
    public List<DashboardListItem> getStaticDashboardList() {
        final List<DashboardListItem> dashboardList = new ArrayList<>();
        dashboardList.add(new DashboardListItem(0, "", "Sales Orders"));
        dashboardList.add(new DashboardListItem(1, "", "Sales Invoices"));
        dashboardList.add(new DashboardListItem(2, "", "Sales Invoices #2"));
        dashboardList.add(new DashboardListItem(3, "", "Revenue By Sales Manager"));
        dashboardList.add(new DashboardListItem(4, "", "Revenue By Customer"));
        dashboardList.add(new DashboardListItem(5, "", "Purchase Orders"));
        dashboardList.add(new DashboardListItem(6, "", "Purchase Invoices"));
        dashboardList.add(new DashboardListItem(7, "", "Purchase Invoices"));
        dashboardList.add(new DashboardListItem(8, "", "Costs By Sales Manager"));
        dashboardList.add(new DashboardListItem(9, "", "Costs By Supplier"));
        dashboardList.add(new DashboardListItem(10, "", "Past Due Sales Invoices"));
        dashboardList.add(new DashboardListItem(11, "", "Revenue By Country"));
        dashboardList.add(new DashboardListItem(12, "", "Past Due Purchase Invoices"));
        dashboardList.add(new DashboardListItem(13, "", "Costs By Country"));
        return dashboardList;
    }

}
