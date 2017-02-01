package com.thinkmobiles.easyerp.presentation.screens.crm.dashboard;

import com.thinkmobiles.easyerp.data.model.crm.dashboard.DashboardListItem;
import com.thinkmobiles.easyerp.presentation.base.rules.ErrorViewHelper;
import com.thinkmobiles.easyerp.presentation.base.rules.MasterFlowSelectablePresenterHelper;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.DashboardListDH;

import java.util.ArrayList;
import java.util.List;

import rx.subscriptions.CompositeSubscription;

/**
 * @author michael.soyma@thinkmobiles.com (Created on 1/18/2017.)
 */

public class DashboardListPresenter extends MasterFlowSelectablePresenterHelper<String> implements DashboardListContract.DashboardListPresenter {

    private DashboardListContract.DashboardListView view;
    private DashboardListContract.DashboardListModel model;
    private CompositeSubscription compositeSubscription;

    public DashboardListPresenter(DashboardListContract.DashboardListView view, DashboardListContract.DashboardListModel model) {
        this.view = view;
        this.model = model;
        compositeSubscription = new CompositeSubscription();

        this.view.setPresenter(this);
    }

    @Override
    public void subscribe() {
        prepareDashboardList();
    }

    @Override
    public void unsubscribe() {
        if (compositeSubscription.hasSubscriptions()) compositeSubscription.clear();
    }

    @Override
    public void prepareDashboardList() {
        compositeSubscription.add(
                model.getDashboardListCharts()
                        .subscribe(
                                getCRMDashboardCharts -> view.displayDashboardsList(prepareDashboardDHs(getCRMDashboardCharts.get(0).charts)),
                                t -> view.displayError(t.getMessage(), ErrorViewHelper.ErrorType.NETWORK))
        );
    }

    @Override
    public void prepareDashboardDetailWithParams(DashboardListDH itemDashboardDH) {
        view.displayDashboardsDetail(itemDashboardDH.getDashboardListItem());
    }

    private ArrayList<DashboardListDH> prepareDashboardDHs(final List<DashboardListItem> dashboardListItems) {
        int position = 0;
        final ArrayList<DashboardListDH> result = new ArrayList<>();
        for (DashboardListItem dashboardListItem : dashboardListItems) {
            final DashboardListDH dashboardListDH = new DashboardListDH(dashboardListItem);
            makeSelectedDHIfNeed(dashboardListDH, view, position++, true);
            result.add(dashboardListDH);
        }
        return result;
    }

}
