package com.thinkmobiles.easyerp.presentation.screens.crm.dashboard.detail;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thinkmobiles.easyerp.data.model.crm.dashboard.DashboardListItem;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by Asus_Dev on 1/20/2017.
 */

public class DashboardDetailChartPresenter implements DashboardDetailChartContract.DashboardDetailChartPresenter {

    private DashboardDetailChartContract.DashboardDetailChartView view;
    private DashboardDetailChartContract.DashboardDetailChartModel model;
    private DashboardListItem workDashboardInfoForChart;

    private CompositeSubscription compositeSubscription;

    public DashboardDetailChartPresenter(DashboardDetailChartContract.DashboardDetailChartView view, DashboardDetailChartContract.DashboardDetailChartModel model, DashboardListItem workDashboardInfoForChart) {
        this.view = view;
        this.model = model;
        this.workDashboardInfoForChart = workDashboardInfoForChart;
        this.compositeSubscription = new CompositeSubscription();

        view.setPresenter(this);
    }

    @Override
    public void subscribe() {
        view.displayHeader(workDashboardInfoForChart.name);
    }

    @Override
    public void unsubscribe() {
        compositeSubscription.unsubscribe();
    }

    @Override
    public void loadChartInfo() {
        compositeSubscription.add(
                    model.getDashboardChartInfo(
                            workDashboardInfoForChart.dataset,
                            workDashboardInfoForChart.getChartType(),
                            "Sun Jan 01 2017 00:00:00 GMT+0200",
                            "Tue Jan 31 2017 00:00:00 GMT+0200 (EET)")
                            .subscribe(
                                    result -> view.displayJson(new GsonBuilder().setPrettyPrinting().create().toJson(result)),
                                    throwable -> view.displayError(throwable.getMessage())));
    }

}
