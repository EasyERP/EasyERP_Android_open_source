package com.thinkmobiles.easyerp.presentation.screens.hr.dashboard.detail;

import com.thinkmobiles.easyerp.data.model.crm.dashboard.detail.DashboardChartType;
import com.thinkmobiles.easyerp.presentation.base.BaseModel;
import com.thinkmobiles.easyerp.presentation.base.BaseView;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentPresenter;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentView;

import rx.Observable;

/**
 * @author Michael Soyma (Created on 3/21/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public interface HRDashboardDetailChartContract {
    interface HRDashboardDetailChartView extends BaseView<HRDashboardDetailChartPresenter>, ContentView {
        void displayHeader(final String title);
        void displayChart(final Object data, final DashboardChartType chartType);
        void displayYearMonth(final String yearMonth);
        void displayPickerCustomYearMonth(final int year, final int month);
    }
    interface HRDashboardDetailChartPresenter extends ContentPresenter {
        void setYearMonth(final int year, final int month);
        void selectYearMonthValues();
        int getYear();
        int getMonth();
        String getYearMonthToString();
    }
    interface HRDashboardDetailChartModel extends BaseModel {
        Observable<?> getHRDashboardChartInfo(
                final String dataSet,
                final DashboardChartType chartType,
                final int year,
                final int month);
    }
}
