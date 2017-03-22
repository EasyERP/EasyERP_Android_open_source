package com.thinkmobiles.easyerp.presentation.screens.hr.dashboard.detail;

import com.thinkmobiles.easyerp.data.model.crm.dashboard.DashboardListItem;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentPresenterHelper;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentView;
import com.thinkmobiles.easyerp.presentation.managers.DateManager;
import com.thinkmobiles.easyerp.presentation.utils.AppDefaultStatesPreferences_;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * @author Michael Soyma (Created on 3/21/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public final class HRDashboardDetailChartPresenter extends ContentPresenterHelper implements HRDashboardDetailChartContract.HRDashboardDetailChartPresenter {

    private HRDashboardDetailChartContract.HRDashboardDetailChartView view;
    private HRDashboardDetailChartContract.HRDashboardDetailChartModel model;
    private DashboardListItem workDashboardInfoForChart;
    private AppDefaultStatesPreferences_ defaultStatesPreferences;

    private int month, year;
    private Object chartData;

    public HRDashboardDetailChartPresenter(
            HRDashboardDetailChartContract.HRDashboardDetailChartView view,
            HRDashboardDetailChartContract.HRDashboardDetailChartModel model,
            DashboardListItem workDashboardInfoForChart,
            AppDefaultStatesPreferences_ defaultStatesPreferences) {

        this.view = view;
        this.model = model;
        this.workDashboardInfoForChart = workDashboardInfoForChart;
        this.defaultStatesPreferences = defaultStatesPreferences;

        view.setPresenter(this);
    }

    @Override
    public void subscribe() {
        year = defaultStatesPreferences.yearForHRDashboardCharts().getOr(getCurrentYear());
        month = defaultStatesPreferences.monthForHRDashboardCharts().getOr(getCurrentMonth());

        if (!(workDashboardInfoForChart.type.equals("reverseHorizontalBar") && workDashboardInfoForChart.dataset.equals("hrEmployeesByGender")))
            view.displayYearMonth(getYearMonthToString());
        view.displayHeader(workDashboardInfoForChart.name);
        super.subscribe();
    }

    @Override
    public void refresh() {
        compositeSubscription.add(
                model.getHRDashboardChartInfo(
                        workDashboardInfoForChart.dataset,
                        workDashboardInfoForChart.getChartType(),
                        year,
                        month)
                        .subscribe(result -> {
                            view.showProgress(Constants.ProgressType.NONE);
                            view.displayChart(chartData = result, workDashboardInfoForChart);
                        }, t -> error(t)));

    }

    @Override
    protected ContentView getView() {
        return view;
    }

    @Override
    protected boolean hasContent() {
        return chartData != null;
    }

    @Override
    protected void retainInstance() {
        view.displayChart(chartData, workDashboardInfoForChart);
    }

    @Override
    public void setYearMonth(int year, int month) {
        this.year = year;
        this.month = month;

        defaultStatesPreferences.edit()
                .yearForHRDashboardCharts().put(this.year)
                .monthForHRDashboardCharts().put(this.month)
                .apply();

        view.displayYearMonth(getYearMonthToString());

        refresh();
    }

    @Override
    public void selectYearMonthValues() {
        view.displayPickerCustomYearMonth(year, month);
    }

    @Override
    public int getYear() {
        return year;
    }

    @Override
    public int getMonth() {
        return month;
    }

    @Override
    public String getYearMonthToString() {
        return new DateManager.DateConverter(new GregorianCalendar(year, month, 1)).setDstPattern(DateManager.PATTERN_DASHBOARD_YM_PREVIEW).toString();
    }

    private int getCurrentYear() {
        return GregorianCalendar.getInstance().get(Calendar.YEAR);
    }

    private int getCurrentMonth() {
        return GregorianCalendar.getInstance().get(Calendar.MONTH);
    }
}
