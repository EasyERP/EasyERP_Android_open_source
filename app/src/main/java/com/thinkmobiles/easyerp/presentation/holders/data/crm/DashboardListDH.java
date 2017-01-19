package com.thinkmobiles.easyerp.presentation.holders.data.crm;

import com.thinkmobiles.easyerp.data.model.crm.dashboard.DashboardListItem;
import com.thinkmobiles.easyerp.presentation.base.rules.MasterFlowSelectableDHHelper;

/**
 * Created by Asus_Dev on 1/18/2017.
 */

public class DashboardListDH extends MasterFlowSelectableDHHelper<String> {

    private final DashboardListItem dashboardListItem;

    public DashboardListDH(DashboardListItem dashboardListItem) {
        this.dashboardListItem = dashboardListItem;
    }

    public DashboardListItem getDashboardListItem() {
        return dashboardListItem;
    }

    @Override
    public String getId() {
        return dashboardListItem.id;
    }

}
