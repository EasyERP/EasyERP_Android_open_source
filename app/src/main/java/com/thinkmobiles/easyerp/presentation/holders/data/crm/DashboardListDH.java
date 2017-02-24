package com.thinkmobiles.easyerp.presentation.holders.data.crm;

import com.thinkmobiles.easyerp.data.model.crm.dashboard.DashboardListItem;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectableDHHelper;

/**
 * @author michael.soyma@thinkmobiles.com (Created on 1/18/2017.)
 */

public final class DashboardListDH extends SelectableDHHelper {
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
