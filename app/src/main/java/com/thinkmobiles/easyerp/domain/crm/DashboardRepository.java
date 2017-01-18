package com.thinkmobiles.easyerp.domain.crm;

import com.thinkmobiles.easyerp.data.api.Rest;
import com.thinkmobiles.easyerp.data.model.crm.dashboard.ResponseGetCRMDashboardCharts;
import com.thinkmobiles.easyerp.data.services.DashboardService;
import com.thinkmobiles.easyerp.presentation.screens.crm.dashboard.DashboardListContract;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import org.androidannotations.annotations.EBean;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Lynx on 1/16/2017.
 */

@EBean(scope = EBean.Scope.Singleton)
public class DashboardRepository implements DashboardListContract.DashboardListModel {

    private DashboardService dashboardService;

    public DashboardRepository() {
        dashboardService = Rest.getInstance().getDashboardService();
    }

    private <T> Observable<T> getNetworkObservable(Observable<T> observable) {
        return observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread());
    }

    public Observable<List<ResponseGetCRMDashboardCharts>> getDashboardListCharts() {
        return getNetworkObservable(dashboardService.getDashboardListCharts(Constants.CRM_DASHBOARD_BASE_ID));
    }

    public Observable<?> getChartInfoBySuffix(final String urlSuffixDataForChart) {
        return getNetworkObservable(dashboardService.getChartInfoBySuffix(urlSuffixDataForChart));
    }

}
