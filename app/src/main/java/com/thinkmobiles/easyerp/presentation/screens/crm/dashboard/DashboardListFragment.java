package com.thinkmobiles.easyerp.presentation.screens.crm.dashboard;

import android.support.v7.widget.LinearLayoutManager;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.domain.local.CRMDashboardsRepository;
import com.thinkmobiles.easyerp.presentation.adapters.crm.DashboardListAdapter;
import com.thinkmobiles.easyerp.presentation.base.rules.MasterFlowSelectablePresenterHelper;
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
    protected CRMDashboardsRepository crmDashboardsRepository;
    @Bean
    protected DashboardListAdapter dashboardListAdapter;

    @AfterInject
    @Override
    public void initPresenter() {
        presenter = new DashboardListPresenter(this, crmDashboardsRepository);
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
                dashboardListAdapter.replaceSelectedItem(presenter.getSelectedItemPosition(), position);
                presenter.setSelectedInfo(position, dashboardListAdapter.getItem(position).getId());
            }
        });

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
        return false;
    }

    @Override
    public void displayDashboardsList(ArrayList<DashboardListDH> listDashboards) {
        swipeContainer.setRefreshing(false);
        dashboardListAdapter.addListDH(listDashboards);
    }

    @Override
    public void displayDashboardsDetail(String param) {
        // TODO open detail fragment
    }

}
