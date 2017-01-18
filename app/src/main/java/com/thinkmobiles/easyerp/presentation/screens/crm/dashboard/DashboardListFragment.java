package com.thinkmobiles.easyerp.presentation.screens.crm.dashboard;

import android.support.v7.widget.LinearLayoutManager;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.data.model.crm.dashboard.DashboardListItem;
import com.thinkmobiles.easyerp.domain.crm.DashboardRepository;
import com.thinkmobiles.easyerp.presentation.adapters.crm.DashboardListAdapter;
import com.thinkmobiles.easyerp.presentation.base.rules.SimpleListWithRefreshFragment;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.DashboardListDH;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;

import java.util.ArrayList;

/**
 * Created by Asus_Dev on 1/18/2017.
 */
@EFragment(R.layout.fragment_simple_list_with_swipe_refresh)
public class DashboardListFragment extends SimpleListWithRefreshFragment implements DashboardListContract.DashboardListView {

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
        listRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        listRecycler.setAdapter(dashboardListAdapter);
        dashboardListAdapter.setOnCardClickListener((view, position, viewType) -> {
            if (position != presenter.getSelectedItemPosition()) {
                final DashboardListDH itemDH = dashboardListAdapter.getItem(position);
                dashboardListAdapter.replaceSelectedItem(presenter.getSelectedItemPosition(), position);
                presenter.setSelectedInfo(position, itemDH.getId());
                presenter.prepareDashboardDetailWithParams(itemDH);
            }
        });

        displayProgress(true);
        presenter.subscribe();
    }

    @Override
    public int getCountItemsNow() {
        return dashboardListAdapter.getItemCount();
    }

    @Override
    public void onRefresh() {
        presenter.setSelectedInfo(-1, presenter.getSelectedItemId());
        dashboardListAdapter.clear();
        presenter.subscribe();
    }

    @Override
    protected boolean needProgress() {
        return true;
    }

    @Override
    public void displayDashboardsList(ArrayList<DashboardListDH> listDashboards) {
        displayProgress(false);
        swipeContainer.setRefreshing(false);
        dashboardListAdapter.addListDH(listDashboards);
    }

    @Override
    public void displayDashboardsDetail(DashboardListItem itemChartDashboard) {
        // TODO open detail fragment
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.unsubscribe();
    }

}
