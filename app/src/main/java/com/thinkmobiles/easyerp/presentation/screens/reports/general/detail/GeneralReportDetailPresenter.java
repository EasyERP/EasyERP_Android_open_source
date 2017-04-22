package com.thinkmobiles.easyerp.presentation.screens.reports.general.detail;

import android.text.TextUtils;
import android.view.View;

import com.thinkmobiles.easyerp.data.model.crm.filter.FilterItem;
import com.thinkmobiles.easyerp.data.model.reports.general.Report;
import com.thinkmobiles.easyerp.data.model.user.UserInfo;
import com.thinkmobiles.easyerp.presentation.base.rules.master.list.MasterListPresenterHelper;
import com.thinkmobiles.easyerp.presentation.base.rules.master.list.MasterListView;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.FilterDH;
import com.thinkmobiles.easyerp.presentation.holders.data.reports.ReportDH;
import com.thinkmobiles.easyerp.presentation.managers.ErrorManager;
import com.thinkmobiles.easyerp.presentation.utils.Constants;
import com.thinkmobiles.easyerp.presentation.utils.DynamicallyPreferencesTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Michael Soyma (Created on 4/8/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public final class GeneralReportDetailPresenter extends MasterListPresenterHelper implements GeneralReportDetailContract.GeneralReportDetailPresenter {

    private GeneralReportDetailContract.GeneralReportDetailView view;
    private GeneralReportDetailContract.GeneralReportDetailModel model;
    private DynamicallyPreferencesTemplate dynamicallyPreferences;
    private String categoryKey;
    private UserInfo userInfo;

    private List<FilterItem> reportTypes = new ArrayList<>();
    private List<Report> reports = new ArrayList<>();

    public GeneralReportDetailPresenter(GeneralReportDetailContract.GeneralReportDetailView view,
                                        GeneralReportDetailContract.GeneralReportDetailModel model,
                                        DynamicallyPreferencesTemplate dynamicallyPreferences,
                                        String categoryKey,
                                        UserInfo userInfo) {
        this.view = view;
        this.model = model;
        this.dynamicallyPreferences = dynamicallyPreferences;
        this.categoryKey = categoryKey;
        this.userInfo = userInfo;

        this.view.setPresenter(this);
    }

    @Override
    public void subscribe() {
        compositeSubscription.add(
            model.getReportTypes().subscribe(reportTypes -> {
                this.reportTypes = reportTypes;
                super.subscribe();
            }, t -> error(t))
        );
    }

    @Override
    public void chooseReportTypes() {
        this.view.showReportTypesDialog(prepareReportTypeDHs());
    }

    @Override
    public void filterByReportTypes(ArrayList<FilterDH> listReportTypes) {
        for (FilterDH filterDH: listReportTypes)
            dynamicallyPreferences.putBoolean(filterDH.id, filterDH.selected);
        getFirstPage();
    }

    @Override
    public void removeAllReportTypes() {
        for (FilterItem filterItem: reportTypes)
            dynamicallyPreferences.putBoolean(filterItem.id, false);
        getFirstPage();
    }

    @Override
    public void favorite(int position, boolean isFavorite) {
        compositeSubscription.add(model.favorite(reports.get(position).id, isFavorite).subscribe(
                result -> {
                    userInfo.favorite.favoriteReport(reports.get(position).id, isFavorite);
                    updateReportDH(position);
                },
                t -> updateReportDH(position)));
    }

    @Override
    public void displayDescription(int position) {
        view.showDescriptionPopUpWindow(position, reports.get(position).description);
    }

    private void updateReportDH(final int position) {
        view.updateItem(position);
    }

    private ArrayList<FilterDH> prepareReportTypeDHs() {
        final ArrayList<FilterDH> reportTypesDHs = new ArrayList<>();
        for (FilterItem reportType: reportTypes)
            reportTypesDHs.add(new FilterDH(reportType, dynamicallyPreferences.getBoolean(reportType.id)));
        return reportTypesDHs;
    }

    @Override
    public void clickItem(int position) {
        final Report report = reports.get(position);
        if (report != null && !TextUtils.isEmpty(report.id))
            view.seeFullReport(report.id);
    }

    @Override
    protected MasterListView getView() {
        return view;
    }

    @Override
    protected void loadPage(int page) {
        final boolean needClear = page == 1;
        compositeSubscription.add(model.getReports(page, categoryKey, prepareCategoryTypes()).subscribe(reports -> {
                currentPage = page;
                totalItems = reports.total;
                saveData(reports.data, needClear);
                setData(reports.data, needClear);
            }, t -> error(t))
        );
    }

    private List<String> prepareCategoryTypes() {
        final List<String> categoryReportTypes = new ArrayList<>();
        for (FilterItem reportType: reportTypes)
            if (dynamicallyPreferences.getBoolean(reportType.id))
                categoryReportTypes.add(reportType.id);
        return categoryReportTypes;
    }

    @Override
    protected int getCountItems() {
        return reports.size();
    }

    @Override
    protected boolean hasContent() {
        return !reports.isEmpty();
    }

    @Override
    protected void retainInstance() {
        setData(reports, true);
    }

    private void saveData(final List<Report> reports, boolean needClear) {
        if (needClear)
            this.reports.clear();
        this.reports.addAll(reports);
    }

    private void setData(final List<Report> reports, boolean needClear) {
        if (reports.isEmpty()) {
            view.displayErrorState(ErrorManager.getErrorType(null));
        } else {
            view.makeAvailableReportTypes();
            view.showProgress(Constants.ProgressType.NONE);
            view.setDataList(prepareDashboardDHs(reports), needClear);
        }
    }

    private ArrayList<ReportDH> prepareDashboardDHs(final List<Report> reports) {
        final ArrayList<ReportDH> result = new ArrayList<>();
        for (Report report: reports)
            result.add(new ReportDH(report, userInfo));
        return result;
    }
}
