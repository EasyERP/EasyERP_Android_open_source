package com.thinkmobiles.easyerp.presentation.screens.crm.dashboard;

import com.thinkmobiles.easyerp.data.model.crm.dashboard.DashboardListItem;
import com.thinkmobiles.easyerp.data.model.crm.dashboard.ResponseGetCRMDashboardCharts;
import com.thinkmobiles.easyerp.presentation.base.BaseModel;
import com.thinkmobiles.easyerp.presentation.base.rules.ErrorViewHelper;
import com.thinkmobiles.easyerp.presentation.base.rules.MasterFlowSelectableBasePresenter;
import com.thinkmobiles.easyerp.presentation.base.rules.MasterFlowSelectableBaseView;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.DashboardListDH;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * Created by Lynx on 1/16/2017.
 */

public interface DashboardListContract {
    interface DashboardListView extends MasterFlowSelectableBaseView<DashboardListPresenter> {
        void displayDashboardsList(ArrayList<DashboardListDH> listDashboards);
        void displayDashboardsDetail(final DashboardListItem itemChartDashboard);
        void displayError(final String msg, final ErrorViewHelper.ErrorType errorType);
    }
    interface DashboardListPresenter extends MasterFlowSelectableBasePresenter<String> {
        void prepareDashboardList();
        void prepareDashboardDetailWithParams(DashboardListDH itemDashboardDH);
    }
    interface DashboardListModel extends BaseModel {
        Observable<List<ResponseGetCRMDashboardCharts>> getDashboardListCharts();
    }
}
