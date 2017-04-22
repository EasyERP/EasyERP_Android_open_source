package com.thinkmobiles.easyerp.presentation.screens.crm.dashboard;

import com.thinkmobiles.easyerp.data.model.crm.dashboard.DashboardListItem;
import com.thinkmobiles.easyerp.domain.DomainHelper;
import com.thinkmobiles.easyerp.domain.crm.DashboardRepository;
import com.thinkmobiles.easyerp.presentation.adapters.crm.DashboardListAdapter;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.MasterSelectableFragment;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectableAdapter;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectablePresenter;
import com.thinkmobiles.easyerp.presentation.custom.views.drawer_menu.models.MenuConfigs;
import com.thinkmobiles.easyerp.presentation.managers.GoogleAnalyticHelper;
import com.thinkmobiles.easyerp.presentation.screens.crm.dashboard.detail.DashboardDetailChartFragment_;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;

/**
 * @author michael.soyma@thinkmobiles.com (Created on 1/18/2017.)
 */
@EFragment
public class DashboardListFragment extends MasterSelectableFragment implements DashboardListContract.DashboardListView {

    private DashboardListContract.DashboardListPresenter presenter;

    @FragmentArg
    protected int moduleId;

    protected DashboardRepository dashboardRepository;
    @Bean
    protected DashboardListAdapter dashboardListAdapter;

    @AfterInject
    @Override
    public void initPresenter() {
        dashboardRepository = DomainHelper.getDashboardRepository(moduleId);
        presenter = new DashboardListPresenter(this, dashboardRepository);
    }

    @Override
    public void setPresenter(DashboardListContract.DashboardListPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public String getScreenName() {
        return String.format("%s Dashboard list screen", MenuConfigs.getModuleLabel(moduleId));
    }

    @AfterViews
    protected void initUI() {
        GoogleAnalyticHelper.trackScreenView(this, getResources().getConfiguration());

        dashboardListAdapter.setOnCardClickListener((view, position, viewType) -> {
            String chartName = dashboardListAdapter.getItem(position).getDashboardListItem().name;
            GoogleAnalyticHelper.trackClick(this, GoogleAnalyticHelper.EventType.CLICK_LIST_ITEM, chartName);
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
                    .moduleId(moduleId)
                    .build());
        } else {
            mActivity.replaceFragmentContentDetail(null);
        }
    }
}
