package com.thinkmobiles.easyerp.domain.crm;

import com.thinkmobiles.easyerp.data.api.Rest;
import com.thinkmobiles.easyerp.data.model.crm.dashboard.DashboardListItem;
import com.thinkmobiles.easyerp.data.model.crm.dashboard.ResponseGetCRMDashboardCharts;
import com.thinkmobiles.easyerp.data.model.crm.dashboard.detail.DashboardChartType;
import com.thinkmobiles.easyerp.data.services.DashboardService;
import com.thinkmobiles.easyerp.presentation.base.NetworkRepository;
import com.thinkmobiles.easyerp.presentation.screens.crm.dashboard.DashboardListContract;
import com.thinkmobiles.easyerp.presentation.screens.crm.dashboard.detail.DashboardDetailChartContract;
import com.thinkmobiles.easyerp.presentation.screens.hr.dashboard.detail.HRDashboardDetailChartContract;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * Created by Lynx on 1/16/2017.
 */
public class DashboardRepository extends NetworkRepository implements DashboardListContract.DashboardListModel,
        DashboardDetailChartContract.DashboardDetailChartModel, HRDashboardDetailChartContract.HRDashboardDetailChartModel {

    private final DashboardChartsLayerRepository dashboardChartsLayerRepository;
    private final DashboardService dashboardService;
    private final String contentType;

    public DashboardRepository(String contentType) {
        this.contentType = contentType;
        this.dashboardChartsLayerRepository = new DashboardChartsLayerRepository();
        this.dashboardService = Rest.getInstance().getDashboardService();
    }

    public Observable<List<ResponseGetCRMDashboardCharts>> getDashboardListCharts() {
        switch (contentType) {
            case "hrDashboard":
                return getHRDashboardListCharts();
            default:
                return getNetworkObservable(dashboardService.getDashboardListCharts(Constants.CRM_DASHBOARD_BASE_ID, contentType));
        }
    }

    @Override
    public Observable<?> getDashboardChartInfo(
            final String dataSet,
            final DashboardChartType chartType,
            final String filterDateFrom,
            final String filterDateTo) {
        return getNetworkObservable(dashboardChartsLayerRepository.getDashboardChartObservable(dataSet, chartType, filterDateFrom, filterDateTo));
    }

    @Override
    public Observable<?> getHRDashboardChartInfo(
            String dataSet,
            DashboardChartType chartType,
            int year,
            int month) {
        return getNetworkObservable(dashboardChartsLayerRepository.getHRDashboardChartObservable(dataSet, chartType, year, month));
    }

    private Observable<List<ResponseGetCRMDashboardCharts>> getHRDashboardListCharts() {
        final List<ResponseGetCRMDashboardCharts> responseGetCRMDashboardCharts = new ArrayList<>();
        final ArrayList<DashboardListItem> charts = new ArrayList<>();
        charts.add(new DashboardListItem("0", "colorCardsView", "hrEmployeesInfo", "Employees Info"));
        charts.add(new DashboardListItem("1", "reverseHorizontalBar", "hrEmployeesByGender", "Employees By Gender"));
        charts.add(new DashboardListItem("2", "horizontalBar", "hrEmployeesSalary", "Employees By Salary"));
        charts.add(new DashboardListItem("3", "horizontalBar", "hrEmployeesDepartment", "Departments By Salary"));
        responseGetCRMDashboardCharts.add(new ResponseGetCRMDashboardCharts(null, charts));
        return Observable.just(responseGetCRMDashboardCharts);
    }
}