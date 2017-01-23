package com.thinkmobiles.easyerp.presentation.screens.crm.dashboard.detail;


import com.thinkmobiles.easyerp.data.model.crm.dashboard.detail.DashboardChartType;
import com.thinkmobiles.easyerp.presentation.base.BaseModel;
import com.thinkmobiles.easyerp.presentation.base.BasePresenter;
import com.thinkmobiles.easyerp.presentation.base.BaseView;

import java.util.Calendar;

import rx.Observable;

/**
 * Created by Lynx on 1/16/2017.
 */

public interface DashboardDetailChartContract {
    interface DashboardDetailChartView extends BaseView<DashboardDetailChartPresenter> {
        void displayHeader(final String title);
        void displayJson(final String json);
        void displayError(final String msg);
        void displayDateFilterFromTo(final String fromToDate);
        void chooseCustomDateFrom(final Calendar dateFrom);
        void chooseCustomDateTo(final Calendar dateTo);
        void reloadData();
    }
    interface DashboardDetailChartPresenter extends BasePresenter {
        void chooseFilterType(final DateFilterType dateFilterType);
        void setCustomFilterDateFrom(final Calendar dateFrom, boolean requireTo);
        void setCustomFilterDateTo(final Calendar dateTo);
        void loadChartInfo();
        DateFilterType getCurrentFilterType();
    }
    interface DashboardDetailChartModel extends BaseModel {
        Observable<?> getDashboardChartInfo(
                final String dataSet,
                final DashboardChartType chartType,
                final String filterDateFrom,
                final String filterDateTo);
    }
}
