package com.thinkmobiles.easyerp.presentation.screens.crm.dashboard;

import com.thinkmobiles.easyerp.data.model.crm.dashboard.DashboardListItem;
import com.thinkmobiles.easyerp.domain.crm.DashboardRepository;
import com.thinkmobiles.easyerp.presentation.adapters.crm.DashboardListAdapter;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.MasterSelectableFragment;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectableAdapter;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectablePresenter;
import com.thinkmobiles.easyerp.presentation.base.rules.ErrorType;
import com.thinkmobiles.easyerp.presentation.base.rules.MasterFlowListSelectableFragment;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.DashboardListDH;
import com.thinkmobiles.easyerp.presentation.managers.GoogleAnalyticHelper;
import com.thinkmobiles.easyerp.presentation.screens.crm.dashboard.detail.DashboardDetailChartFragment_;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;

/**
 * @author michael.soyma@thinkmobiles.com (Created on 1/18/2017.)
 */
@EFragment
public class DashboardListFragment extends MasterSelectableFragment implements DashboardListContract.DashboardListView {

    private DashboardListContract.DashboardListPresenter presenter;

    @Bean
    protected DashboardRepository dashboardRepository;
    @Bean
    protected DashboardListAdapter dashboardListAdapter;

    @AfterInject
    @Override
    public void initPresenter() {
        presenter = new DashboardListPresenter(this, dashboardRepository);
    }

    @Override
    public void setPresenter(DashboardListContract.DashboardListPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public String getScreenName() {
        return "Dashboard list screen";
    }

    @AfterViews
    protected void initUI() {
        GoogleAnalyticHelper.trackScreenView(this, getResources().getConfiguration());

        dashboardListAdapter.setOnCardClickListener((view, position, viewType) -> {
            String chartName = dashboardListAdapter.getItem(position).getDashboardListItem().name;
            GoogleAnalyticHelper.trackClick(this, GoogleAnalyticHelper.EventType.CLICK_DASHBOARD_ITEM, chartName);
            presenter.clickItem(position);
        });
    }

    @Override
    protected SelectablePresenter getPresenter() {
        return presenter;
    }

    @Override
    protected SelectableAdapter getAdapter() {
        return dashboardListAdapter;
    }

    @Override
    public void openDashboardChartDetail(DashboardListItem itemChartDashboard) {
        if (itemChartDashboard != null) {
            mActivity.replaceFragmentContentDetail(DashboardDetailChartFragment_.builder()
                            .dashboardConfigsForChart(itemChartDashboard)
                            .build());
        } else {
            mActivity.replaceFragmentContentDetail(null);
        }
    }
}
