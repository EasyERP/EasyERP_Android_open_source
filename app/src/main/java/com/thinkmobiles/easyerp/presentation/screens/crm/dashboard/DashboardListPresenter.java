package com.thinkmobiles.easyerp.presentation.screens.crm.dashboard;

import com.thinkmobiles.easyerp.data.model.crm.dashboard.DashboardListItem;
import com.thinkmobiles.easyerp.presentation.base.rules.MasterFlowSelectablePresenterHelper;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.DashboardListDH;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Asus_Dev on 1/18/2017.
 */

public class DashboardListPresenter extends MasterFlowSelectablePresenterHelper<Integer> implements DashboardListContract.DashboardListPresenter {

    private DashboardListContract.DashboardListView view;
    private DashboardListContract.DashboardListModel model;

    public DashboardListPresenter(DashboardListContract.DashboardListView view, DashboardListContract.DashboardListModel model) {
        this.view = view;
        this.model = model;

        this.view.setPresenter(this);
    }

    @Override
    public void subscribe() {
        prepareDashboardList();
    }

    @Override
    public void unsubscribe() {

    }

    @Override
    public void prepareDashboardList() {
        view.displayDashboardsList(prepareDashboardDHs(model.getStaticDashboardList()));
    }

    @Override
    public void prepareDashboardDetailWithParams(String param) {
        view.displayDashboardsDetail(param);
    }

    private ArrayList<DashboardListDH> prepareDashboardDHs(final List<DashboardListItem> dashboardListItems) {
        int position = 0;
        ArrayList<DashboardListDH> result = new ArrayList<>();
        for (DashboardListItem dashboardListItem : dashboardListItems) {
            final DashboardListDH dashboardListDH = new DashboardListDH(dashboardListItem);
            makeSelectedDHIfNeed(dashboardListDH, view, position++);
            result.add(dashboardListDH);
        }
        return result;
    }

}
