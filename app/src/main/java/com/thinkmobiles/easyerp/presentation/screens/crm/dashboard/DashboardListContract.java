package com.thinkmobiles.easyerp.presentation.screens.crm.dashboard;

import com.thinkmobiles.easyerp.data.model.crm.dashboard.DashboardListItem;
import com.thinkmobiles.easyerp.data.model.crm.dashboard.ResponseGetCRMDashboardCharts;
import com.thinkmobiles.easyerp.presentation.base.BaseModel;
import com.thinkmobiles.easyerp.presentation.base.BaseView;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectablePresenter;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectableView;

import java.util.List;

import rx.Observable;

/**
 * Created by Lynx on 1/16/2017.
 */

public interface DashboardListContract {
    interface DashboardListView extends BaseView<DashboardListPresenter>, SelectableView {
        void openDashboardChartDetail(final DashboardListItem itemChartDashboard);
    }
    interface DashboardListPresenter extends SelectablePresenter {
    }
    interface DashboardListModel extends BaseModel {
        Observable<ResponseGetCRMDashboardCharts> getDashboardListCharts();
    }
}
