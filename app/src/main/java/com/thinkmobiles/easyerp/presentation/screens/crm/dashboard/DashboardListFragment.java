package com.thinkmobiles.easyerp.presentation.screens.crm.dashboard;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.data.model.crm.dashboard.DashboardListItem;
import com.thinkmobiles.easyerp.domain.crm.DashboardRepository;
import com.thinkmobiles.easyerp.presentation.adapters.crm.DashboardListAdapter;
import com.thinkmobiles.easyerp.presentation.base.rules.ErrorViewHelper;
import com.thinkmobiles.easyerp.presentation.base.rules.MasterFlowListFragment;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.DashboardListDH;
import com.thinkmobiles.easyerp.presentation.screens.crm.dashboard.detail.DashboardDetailChartFragment_;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringRes;

import java.util.ArrayList;

/**
 * @author michael.soyma@thinkmobiles.com (Created on 1/18/2017.)
 */
@EFragment(R.layout.fragment_simple_list_with_swipe_refresh)
public class DashboardListFragment extends MasterFlowListFragment implements DashboardListContract.DashboardListView {

    private DashboardListContract.DashboardListPresenter presenter;

    @Bean
    protected DashboardRepository dashboardRepository;
    @Bean
    protected DashboardListAdapter dashboardListAdapter;
    @Bean
    protected ErrorViewHelper errorViewHelper;

    @StringRes(R.string.list_is_empty)
    protected String string_list_is_empty;

    @ViewById(R.id.llErrorLayout)
    protected View errorLayout;

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
        errorViewHelper.init(errorLayout, view -> presenter.subscribe());
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
    public void onRefresh() {
        errorViewHelper.hideError();
        presenter.loadDashboardChartsList();
    }

    @Override
    public void displayDashboardChartsList(ArrayList<DashboardListDH> listDashboards) {
        showProgress(false);
        swipeContainer.setRefreshing(false);
        dashboardListAdapter.setListDH(listDashboards);

        if (getCountItemsNow() == 0)
            displayError(null, ErrorViewHelper.ErrorType.LIST_EMPTY);
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
    public void displayError(String msg, ErrorViewHelper.ErrorType errorType) {
        displayProgress(false);
        swipeContainer.setRefreshing(false);

        final String resultMsg = errorType.equals(ErrorViewHelper.ErrorType.LIST_EMPTY) ? string_list_is_empty : msg;
        if (getCountItemsNow() == 0)
            errorViewHelper.showErrorMsg(resultMsg, errorType);
        else Toast.makeText(mActivity, resultMsg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showProgress(boolean isShow) {
        errorViewHelper.hideError();
        displayProgress(isShow);
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
