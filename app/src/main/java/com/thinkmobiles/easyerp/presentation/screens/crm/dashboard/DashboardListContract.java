package com.thinkmobiles.easyerp.presentation.screens.crm.dashboard;

import com.thinkmobiles.easyerp.data.model.crm.dashboard.DashboardListItem;
import com.thinkmobiles.easyerp.data.model.crm.dashboard.ResponseGetCRMDashboardCharts;
import com.thinkmobiles.easyerp.presentation.base.BaseModel;
import com.thinkmobiles.easyerp.presentation.base.rules.ErrorViewHelper;
import com.thinkmobiles.easyerp.presentation.base.rules.MasterFlowSelectableBasePresenter;
import com.thinkmobiles.easyerp.presentation.base.rules.MasterFlowSelectableBaseView;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.DashboardListDH;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * Created by Lynx on 1/16/2017.
 */

public interface DashboardListContract {
    interface DashboardListView extends MasterFlowSelectableBaseView<DashboardListPresenter> {
        void displayDashboardChartsList(ArrayList<DashboardListDH> listDashboards);
        void openDashboardChartDetail(final DashboardListItem itemChartDashboard);
        void displayErrorState(final ErrorViewHelper.ErrorType errorType);
        void displayErrorToast(final String msg);
        void showProgress(Constants.ProgressType type);
    }
    interface DashboardListPresenter extends MasterFlowSelectableBasePresenter<String, DashboardListDH> {
        void loadDashboardChartsList();
    }
    interface DashboardListModel extends BaseModel {
        Observable<List<ResponseGetCRMDashboardCharts>> getDashboardListCharts();
    }
}
