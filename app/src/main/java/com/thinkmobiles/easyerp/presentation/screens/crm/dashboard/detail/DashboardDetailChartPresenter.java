package com.thinkmobiles.easyerp.presentation.screens.crm.dashboard.detail;


import android.support.v4.util.Pair;

import com.thinkmobiles.easyerp.data.model.crm.dashboard.DashboardListItem;
import com.thinkmobiles.easyerp.presentation.base.rules.ErrorViewHelper;
import com.thinkmobiles.easyerp.presentation.managers.DateManager;
import com.thinkmobiles.easyerp.presentation.utils.AppDefaultStatesPreferences_;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import java.util.Calendar;
import java.util.GregorianCalendar;

import rx.subscriptions.CompositeSubscription;

/**
 * @author michael.soyma@thinkmobiles.com (Created on 1/20/2017.)
 */

public class DashboardDetailChartPresenter implements DashboardDetailChartContract.DashboardDetailChartPresenter {

    private DashboardDetailChartContract.DashboardDetailChartView view;
    private DashboardDetailChartContract.DashboardDetailChartModel model;
    private DashboardListItem workDashboardInfoForChart;
    private DateFilterType dateFilterType;
    private AppDefaultStatesPreferences_ defaultStatesPreferences;

    private long customDateFrom, customDateTo;
    private Object chartData;

    private CompositeSubscription compositeSubscription;

    public DashboardDetailChartPresenter(
            DashboardDetailChartContract.DashboardDetailChartView view,
            DashboardDetailChartContract.DashboardDetailChartModel model,
            DashboardListItem workDashboardInfoForChart,
            AppDefaultStatesPreferences_ defaultStatesPreferences) {

        this.view = view;
        this.model = model;
        this.workDashboardInfoForChart = workDashboardInfoForChart;
        this.defaultStatesPreferences = defaultStatesPreferences;

        this.compositeSubscription = new CompositeSubscription();

        view.setPresenter(this);
    }

    @Override
    public void subscribe() {
        customDateFrom = defaultStatesPreferences.customDateFilterFromForCRMDashboardCharts().get();
        customDateTo = defaultStatesPreferences.customDateFilterToForCRMDashboardCharts().get();
        dateFilterType = DateFilterType.valueOf(defaultStatesPreferences.defaultDateFilterTypeForCRMDashboardCharts().get());

        view.displayDateFilterFromTo(getDateFromToString(getFromToFilterDate()));
        view.displayHeader(workDashboardInfoForChart.name);

        if (chartData == null) {
            view.showProgress(Constants.ProgressType.CENTER);
            loadChartInfo();
        } else {
            view.displayChart(chartData, workDashboardInfoForChart.getChartType());
        }
    }

    @Override
    public void unsubscribe() {
        if (compositeSubscription.hasSubscriptions())
            compositeSubscription.clear();
    }

    @Override
    public void loadChartInfo() {
        final Pair<Calendar, Calendar> fromToFilter = getFromToFilterDate();
        compositeSubscription.add(
                    model.getDashboardChartInfo(
                            workDashboardInfoForChart.dataset,
                            workDashboardInfoForChart.getChartType(),
                            new DateManager.DateConverter(fromToFilter.first).setDstPattern(DateManager.PATTERN_DASHBOARD_BACKEND).toString(),
                            new DateManager.DateConverter(fromToFilter.second).setDstPattern(DateManager.PATTERN_DASHBOARD_BACKEND).toString())
                            .subscribe(result -> {
                                view.showProgress(Constants.ProgressType.NONE);
                                view.displayChart(chartData = result, workDashboardInfoForChart.getChartType());
                            },throwable -> view.displayErrorState(throwable.getMessage(), ErrorViewHelper.ErrorType.NETWORK)));
    }

    @Override
    public DateFilterType getCurrentFilterType() {
        return dateFilterType;
    }

    @Override
    public void chooseFilterType(DateFilterType dateFilterType) {
        this.dateFilterType = dateFilterType;
        defaultStatesPreferences.edit().defaultDateFilterTypeForCRMDashboardCharts().put(this.dateFilterType.getType()).apply();

        final Pair<Calendar, Calendar> fromToPair = getFromToFilterDate();
        view.displayDateFilterFromTo(getDateFromToString(fromToPair));

        if (this.dateFilterType.equals(DateFilterType.CUSTOM_DATES))
            view.chooseCustomDateRangeFromTo(fromToPair.first, fromToPair.second);
        else {
            view.showProgress(Constants.ProgressType.CENTER);
            loadChartInfo();
        }
    }

    @Override
    public void setCustomFilterRangeDateFromTo(Calendar dateFrom, Calendar dateTo) {
        customDateFrom = dateFrom.getTimeInMillis();
        customDateTo = dateTo.getTimeInMillis();

        defaultStatesPreferences.edit()
                .customDateFilterFromForCRMDashboardCharts().put(customDateFrom)
                .customDateFilterToForCRMDashboardCharts().put(customDateTo)
                .apply();

        view.displayDateFilterFromTo(getDateFromToString(getFromToFilterDate()));

        view.showProgress(Constants.ProgressType.CENTER);
        loadChartInfo();
    }

    private String getDateFromToString(final Pair<Calendar, Calendar> fromToPair) {
        return String.format(
                "%s - %s",
                new DateManager.DateConverter(fromToPair.first).setDstPattern(DateManager.PATTERN_DASHBOARD_PREVIEW).toString(),
                new DateManager.DateConverter(fromToPair.second).setDstPattern(DateManager.PATTERN_DASHBOARD_PREVIEW).toString());
    }

    private Pair<Calendar, Calendar> getFromToFilterDate() {
        final Calendar currentCalendar = GregorianCalendar.getInstance();
        final Calendar from = new GregorianCalendar(0, 0, 0, 0, 0, 0);
        final Calendar to = new GregorianCalendar(0, 0, 0, 0, 0, 0);
        switch (dateFilterType) {
            case THIS_MONTH:
                from.set(currentCalendar.get(Calendar.YEAR), currentCalendar.get(Calendar.MONTH), 1);
                to.set(currentCalendar.get(Calendar.YEAR), currentCalendar.get(Calendar.MONTH), currentCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));
                break;
            case THIS_FINANCIAL_YEAR:
                from.set(Calendar.YEAR, currentCalendar.get(Calendar.YEAR));
                from.set(Calendar.MONTH, Calendar.JANUARY);
                from.set(Calendar.DAY_OF_MONTH, 1);

                to.set(Calendar.YEAR, currentCalendar.get(Calendar.YEAR));
                to.set(Calendar.MONTH, Calendar.DECEMBER);
                to.set(Calendar.DAY_OF_MONTH, to.getActualMaximum(Calendar.DAY_OF_MONTH));
                break;
            case LAST_MONTH:
                final int month = currentCalendar.get(Calendar.MONTH);
                final int year = currentCalendar.get(Calendar.YEAR) - (month == 0 ? 1 : 0);

                from.set(Calendar.YEAR, year);
                from.set(Calendar.MONTH, month == 0 ? Calendar.DECEMBER : month - 1);
                from.set(Calendar.DAY_OF_MONTH, 1);

                to.set(Calendar.YEAR, year);
                to.set(Calendar.MONTH, month == 0 ? Calendar.DECEMBER : month - 1);
                to.set(Calendar.DAY_OF_MONTH, to.getMaximum(Calendar.DAY_OF_MONTH));
                break;
            case LAST_QUARTER:
                final int currentQuarter = currentCalendar.get(Calendar.MONTH) / 3 + 1;
                final int yearForPreviousQuarter = currentCalendar.get(Calendar.YEAR) - (currentQuarter == 1 ? 1 : 0);
                final int previousQuarter = currentQuarter == 1 ? 4 : currentQuarter - 1;

                from.set(Calendar.YEAR, yearForPreviousQuarter);
                from.set(Calendar.MONTH, (previousQuarter - 1) * 3);
                from.set(Calendar.DAY_OF_MONTH, 1);

                to.set(Calendar.YEAR, yearForPreviousQuarter);
                to.set(Calendar.MONTH, previousQuarter * 3 - 1);
                to.set(Calendar.DAY_OF_MONTH, to.getMaximum(Calendar.DAY_OF_MONTH));
                break;
            case LAST_FINANCIAL_YEAR:
                from.set(Calendar.YEAR, currentCalendar.get(Calendar.YEAR) - 1);
                from.set(Calendar.MONTH, Calendar.JANUARY);
                from.set(Calendar.DAY_OF_MONTH, 1);

                to.set(Calendar.YEAR, currentCalendar.get(Calendar.YEAR) - 1);
                to.set(Calendar.MONTH, Calendar.DECEMBER);
                to.set(Calendar.DAY_OF_MONTH, to.getMaximum(Calendar.DAY_OF_MONTH));
                break;
            case CUSTOM_DATES:
                from.setTimeInMillis(customDateFrom = (customDateFrom < 0 ? currentCalendar.getTimeInMillis() : customDateFrom));
                to.setTimeInMillis(customDateTo = (customDateTo < 0 ? currentCalendar.getTimeInMillis() : (customDateTo < customDateFrom ? customDateFrom : customDateTo)));
                break;
        }
        return Pair.create(from, to);
    }

}
