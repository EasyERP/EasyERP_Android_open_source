package com.thinkmobiles.easyerp.presentation.holders.data.crm;

import com.thinkmobiles.easyerp.data.model.crm.dashboard.DashboardListItem;
import com.thinkmobiles.easyerp.presentation.base.rules.MasterFlowSelectableDHHelper;

/**
 * Created by Asus_Dev on 1/18/2017.
 */

public class DashboardListDH extends MasterFlowSelectableDHHelper<Integer> {

    private final DashboardListItem dashboardListItem;

    public DashboardListDH(DashboardListItem dashboardListItem) {
        this.dashboardListItem = dashboardListItem;
    }

    public String getLabel() {
        return dashboardListItem.label;
    }

    @Override
    public Integer getId() {
        return dashboardListItem.id;
    }

}
