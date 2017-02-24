package com.thinkmobiles.easyerp.presentation.screens.crm.dashboard.detail;


import com.thinkmobiles.easyerp.data.model.crm.dashboard.detail.DashboardChartType;
import com.thinkmobiles.easyerp.presentation.base.BaseModel;
import com.thinkmobiles.easyerp.presentation.base.BaseView;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentPresenter;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentView;

import java.util.Calendar;

import rx.Observable;

/**
 * Created by Lynx on 1/16/2017.
 */

public interface DashboardDetailChartContract {
    interface DashboardDetailChartView extends BaseView<DashboardDetailChartPresenter>, ContentView {
        void displayHeader(final String title);
        void displayChart(final Object data, final DashboardChartType chartType);
        void displayDateFilterFromTo(final String fromToDate);
        void chooseCustomDateRangeFromTo(final Calendar dateFrom, final Calendar dateTo);
    }
    interface DashboardDetailChartPresenter extends ContentPresenter {
        void chooseFilterType(final DateFilterType dateFilterType);
        void setCustomFilterRangeDateFromTo(final Calendar dateFrom, final Calendar dateTo);
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
