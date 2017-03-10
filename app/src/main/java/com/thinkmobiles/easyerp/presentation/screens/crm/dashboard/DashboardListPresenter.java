package com.thinkmobiles.easyerp.presentation.screens.crm.dashboard;

import com.thinkmobiles.easyerp.data.model.crm.dashboard.DashboardListItem;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.MasterSelectablePresenterHelper;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectableView;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.DashboardListDH;
import com.thinkmobiles.easyerp.presentation.managers.ErrorManager;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * @author michael.soyma@thinkmobiles.com (Created on 1/18/2017.)
 */

public class DashboardListPresenter extends MasterSelectablePresenterHelper implements DashboardListContract.DashboardListPresenter {

    private DashboardListContract.DashboardListView view;
    private DashboardListContract.DashboardListModel model;

    private ArrayList<DashboardListItem> charts = new ArrayList<>();

    public DashboardListPresenter(DashboardListContract.DashboardListView view, DashboardListContract.DashboardListModel model) {
        this.view = view;
        this.model = model;

        this.view.setPresenter(this);
    }

    @Override
    protected SelectableView getView() {
        return view;
    }

    @Override
    protected void loadPage(int page) {
        compositeSubscription.add(
                model.getDashboardListCharts()
                        .subscribe(getCRMDashboardCharts -> {
                            charts = getCRMDashboardCharts.get(0).charts;
                            totalItems = charts.size();
                            setData();
                        },  t -> error(t))
        );
    }

    @Override
    protected int getCountItems() {
        return charts.size();
    }

    @Override
    protected boolean hasContent() {
        return !charts.isEmpty();
    }

    @Override
    protected void retainInstance() {
        setData();
    }

    @Override
    public void clickItem(int position) {
        DashboardListItem item = charts.get(position);
        if (super.selectItem(item.id, position))
            view.openDashboardChartDetail(item);
    }

    private void setData() {
        if (charts.isEmpty()) {
            view.displayErrorState(ErrorManager.getErrorType(null));
        } else {
            view.showProgress(Constants.ProgressType.NONE);
            view.setDataList(prepareDashboardDHs(charts), true);
        }
    }

    private ArrayList<DashboardListDH> prepareDashboardDHs(final List<DashboardListItem> dashboardListItems) {
        final ArrayList<DashboardListDH> result = new ArrayList<>();
        for (DashboardListItem dashboardListItem : dashboardListItems) {
            final DashboardListDH dashboardListDH = new DashboardListDH(dashboardListItem);
            makeSelectedDHIfNeed(dashboardListDH, charts.indexOf(dashboardListItem));
            result.add(dashboardListDH);
        }
        selectFirstElementIfNeed(result);
        return result;
    }
}
