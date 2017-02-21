package com.thinkmobiles.easyerp.presentation.screens.crm.dashboard;

import android.support.v7.widget.LinearLayoutManager;

import com.thinkmobiles.easyerp.data.model.crm.dashboard.DashboardListItem;
import com.thinkmobiles.easyerp.domain.crm.DashboardRepository;
import com.thinkmobiles.easyerp.presentation.adapters.crm.DashboardListAdapter;
import com.thinkmobiles.easyerp.presentation.base.rules.ErrorType;
import com.thinkmobiles.easyerp.presentation.base.rules.MasterFlowListSelectableFragment;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.DashboardListDH;
import com.thinkmobiles.easyerp.presentation.screens.crm.dashboard.detail.DashboardDetailChartFragment_;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;

import java.util.ArrayList;

/**
 * @author michael.soyma@thinkmobiles.com (Created on 1/18/2017.)
 */
@EFragment
public class DashboardListFragment extends MasterFlowListSelectableFragment implements DashboardListContract.DashboardListView {

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

    @AfterViews
    protected void initUI() {
        listRecycler.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));
        listRecycler.setAdapter(dashboardListAdapter);
        dashboardListAdapter.setOnCardClickListener((view, position, viewType) -> presenter.selectItem(dashboardListAdapter.getItem(position), position));

        presenter.subscribe();
    }

    @Override
    public int getCountItemsNow() {
        return dashboardListAdapter.getItemCount();
    }

    @Override
    public void changeSelectedItem(int oldPosition, int newPosition) {
        dashboardListAdapter.replaceSelectedItem(oldPosition, newPosition);
    }

    @Override
    protected void onLoadNextPage() {

    }

    @Override
    protected void onRetry() {
        presenter.subscribe();
    }

    @Override
    public void onRefreshData() {
        super.onRefreshData();
        presenter.loadDashboardChartsList();
    }

    @Override
    public void displayDashboardChartsList(ArrayList<DashboardListDH> listDashboards) {
        dashboardListAdapter.setListDH(listDashboards);
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

    @Override
    public void displayErrorState(ErrorType errorType) {
        showErrorState(errorType);
    }

    @Override
    public void displayErrorToast(String msg) {
        showErrorToast(msg);
    }

    @Override
    public void showProgress(Constants.ProgressType type) {
        showProgressBar(type);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.unsubscribe();
    }

    @Override
    public void clearSelectedItem() {
        presenter.clearSelectedInfo();
    }
}
