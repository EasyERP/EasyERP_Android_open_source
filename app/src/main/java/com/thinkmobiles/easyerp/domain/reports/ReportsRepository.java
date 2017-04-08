package com.thinkmobiles.easyerp.domain.reports;

import android.net.Uri;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.thinkmobiles.easyerp.data.api.Rest;
import com.thinkmobiles.easyerp.data.model.crm.filter.FilterItem;
import com.thinkmobiles.easyerp.data.model.reports.general.Category;
import com.thinkmobiles.easyerp.data.model.reports.general.Report;
import com.thinkmobiles.easyerp.data.services.ReportsService;
import com.thinkmobiles.easyerp.presentation.base.NetworkRepository;
import com.thinkmobiles.easyerp.presentation.screens.reports.general.GeneralCategoriesListContract;
import com.thinkmobiles.easyerp.presentation.screens.reports.general.detail.GeneralReportDetailContract;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import org.androidannotations.annotations.EBean;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * @author Michael Soyma (Created on 4/8/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
@EBean
public class ReportsRepository extends NetworkRepository implements GeneralCategoriesListContract.GeneralCategoriesListModel,
                                                                    GeneralReportDetailContract.GeneralReportDetailModel {

    private ReportsService reportsService;

    ReportsRepository() {
        reportsService = Rest.getInstance().getReportsService();
    }

    @Override
    public Observable<List<Category>> getListCategories() {
        final List<Category> generalCategories = new ArrayList<>();
        generalCategories.add(new Category("0", "Recent", "recent"));
        generalCategories.add(new Category("1", "Favorites", "favorite"));
        generalCategories.add(new Category("2", "Created By Me", "createdByMe"));
        generalCategories.add(new Category("3", "Private", "private"));
        generalCategories.add(new Category("4", "Public", "public"));
        generalCategories.add(new Category("5", "All", "all"));
        return Observable.just(generalCategories);
    }

    @Override
    public Observable<List<FilterItem>> getReportTypes() {
        List<FilterItem> reportTypes = new ArrayList<>();
        reportTypes.add(new FilterItem("salesReports", "Sales"));
        reportTypes.add(new FilterItem("inventoryReports", "Inventory"));
        reportTypes.add(new FilterItem("employeesReports", "Employees"));
        reportTypes.add(new FilterItem("personReports", "Persons"));
        reportTypes.add(new FilterItem("companyReports", "Companies"));
        reportTypes.add(new FilterItem("leadsReports", "Leads"));
        reportTypes.add(new FilterItem("opportunitiesReports", "Opportunities"));
        return Observable.just(reportTypes);
    }

    @Override
    public Observable<List<Report>> getReports(String categoryKey, List<String> queryReportTypes) {
        Uri.Builder builder = Uri.parse(Constants.BASE_URL).buildUpon();
        builder.appendPath(Constants.GET_REPORTS)
                .appendQueryParameter("page", "1")
                .appendQueryParameter("count", String.valueOf(Constants.COUNT_LIST_ITEMS_WITHOUT_PAGINATION));

        for (String reportType: queryReportTypes)
            builder.appendQueryParameter(Constants.KEY_QUERY_REPORT_CATEGORY, reportType);

        return getNetworkObservable(reportsService.getReports(builder.build().toString())
                .flatMap(reports -> {
                    final JsonObject reportsObj = new Gson().toJsonTree(reports.getAsJsonArray().get(0)).getAsJsonObject();
                    return Observable.just(new Gson().fromJson(reportsObj.get(categoryKey), new TypeToken<List<Report>>(){}.getType()));
                }));
    }
}
