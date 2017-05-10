package com.thinkmobiles.easyerp.presentation.screens.hr.dashboard;

import com.thinkmobiles.easyerp.data.model.crm.dashboard.DashboardListItem;
import com.thinkmobiles.easyerp.presentation.screens.crm.dashboard.DashboardListFragment;
import com.thinkmobiles.easyerp.presentation.screens.hr.dashboard.detail.HRDashboardDetailChartFragment_;

import org.androidannotations.annotations.EFragment;

/**
 * @author Michael Soyma (Created on 3/21/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
@EFragment
public class HRDashboardListFragment extends DashboardListFragment {

    @Override
    public void openDashboardChartDetail(DashboardListItem itemChartDashboard) {
        if (itemChartDashboard != null) {
            getMasterDelegate().replaceFragmentContentDetail(HRDashboardDetailChartFragment_.builder()
                    .dashboardConfigsForChart(itemChartDashboard)
                    .moduleId(moduleId)
                    .build());
        } else {
            getMasterDelegate().replaceFragmentContentDetail(null);
        }
    }
}
