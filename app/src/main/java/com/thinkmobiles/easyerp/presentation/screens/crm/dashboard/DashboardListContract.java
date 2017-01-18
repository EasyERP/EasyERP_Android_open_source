package com.thinkmobiles.easyerp.presentation.screens.crm.dashboard;

import com.thinkmobiles.easyerp.data.model.crm.dashboard.DashboardListItem;
import com.thinkmobiles.easyerp.presentation.base.BaseModel;
import com.thinkmobiles.easyerp.presentation.base.BasePresenter;
import com.thinkmobiles.easyerp.presentation.base.rules.MasterFlowSelectableBasePresenter;
import com.thinkmobiles.easyerp.presentation.base.rules.MasterFlowSelectableBaseView;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.DashboardListDH;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lynx on 1/16/2017.
 */

public interface DashboardListContract {
    interface DashboardListView extends MasterFlowSelectableBaseView<DashboardListPresenter> {
        void displayDashboardsList(ArrayList<DashboardListDH> listDashboards);
        void displayDashboardsDetail(final String param);
    }
    interface DashboardListPresenter extends MasterFlowSelectableBasePresenter<Integer> {
        void prepareDashboardList();
        void prepareDashboardDetailWithParams(String param);
    }
    interface DashboardListModel extends BaseModel {
        List<DashboardListItem> getStaticDashboardList();
    }
}
