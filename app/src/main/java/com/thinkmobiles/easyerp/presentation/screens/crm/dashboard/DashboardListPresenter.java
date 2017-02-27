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
                            totalItems = 1;
                            charts = getCRMDashboardCharts.get(0).charts;
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

    @Override
    public void unsubscribe() {
        if (compositeSubscription.hasSubscriptions())
            compositeSubscription.clear();
    }

    private void setData() {
        if (charts.isEmpty()) {
            view.displayErrorState(ErrorManager.getErrorType(null));
        } else {
            view.showProgress(Constants.ProgressType.NONE);
            view.setDataList(prepareDashboardDHs(charts, true), true);
        }
    }

    private ArrayList<DashboardListDH> prepareDashboardDHs(final List<DashboardListItem> dashboardListItems, boolean needClear) {
        int position = 0;
        final ArrayList<DashboardListDH> result = new ArrayList<>();
        for (DashboardListItem dashboardListItem : dashboardListItems) {
            final DashboardListDH dashboardListDH = new DashboardListDH(dashboardListItem);
            makeSelectedDHIfNeed(dashboardListDH, position++, needClear);
            result.add(dashboardListDH);
        }
        selectFirstElementIfNeed(result);
        return result;
    }
}
