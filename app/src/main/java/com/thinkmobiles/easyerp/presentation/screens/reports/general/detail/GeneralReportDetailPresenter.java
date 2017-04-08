package com.thinkmobiles.easyerp.presentation.screens.reports.general.detail;

import com.thinkmobiles.easyerp.data.model.crm.filter.FilterItem;
import com.thinkmobiles.easyerp.data.model.reports.general.Report;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentPresenterHelper;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentView;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.FilterDH;
import com.thinkmobiles.easyerp.presentation.utils.Constants;
import com.thinkmobiles.easyerp.presentation.utils.DynamicallyPreferencesTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Michael Soyma (Created on 4/8/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public final class GeneralReportDetailPresenter extends ContentPresenterHelper implements GeneralReportDetailContract.GeneralReportDetailPresenter {

    private GeneralReportDetailContract.GeneralReportDetailView view;
    private GeneralReportDetailContract.GeneralReportDetailModel model;
    private DynamicallyPreferencesTemplate dynamicallyPreferences;
    private String categoryKey;

    private List<FilterItem> reportTypes = new ArrayList<>();
    private List<Report> reports = new ArrayList<>();

    public GeneralReportDetailPresenter(GeneralReportDetailContract.GeneralReportDetailView view,
                                        GeneralReportDetailContract.GeneralReportDetailModel model,
                                        DynamicallyPreferencesTemplate dynamicallyPreferences,
                                        String categoryKey) {
        this.view = view;
        this.model = model;
        this.dynamicallyPreferences = dynamicallyPreferences;
        this.categoryKey = categoryKey;

        this.view.setPresenter(this);
    }

    @Override
    public void subscribe() {
        super.subscribe();
    }

    @Override
    public void refresh() {
        this.model.getReportTypes().subscribe(reportTypes -> {
            this.reportTypes = reportTypes;
            getReports();
        }, t -> error(t));
    }

    private void getReports() {
        final List<String> categoryReportTypes = new ArrayList<>();
        for (FilterItem reportType: reportTypes)
            if (dynamicallyPreferences.getBoolean(reportType.id))
                categoryReportTypes.add(reportType.id);

        this.model.getReports(categoryKey, categoryReportTypes).subscribe(reports -> {
            this.reports.clear();
            this.reports.addAll(reports);
            view.showProgress(Constants.ProgressType.NONE);
            view.makeAvailableReportTypes();
        }, t -> error(t));
    }

    private void reloadWithNewReportTypes() {
        getView().showProgress(Constants.ProgressType.CENTER);
        getReports();
    }

    @Override
    protected ContentView getView() {
        return view;
    }

    @Override
    protected boolean hasContent() {
        return !reports.isEmpty();
    }

    @Override
    protected void retainInstance() {
        //TODO set data to list
    }

    @Override
    public void chooseReportTypes() {
        this.view.showReportTypesDialog(prepareReportTypeDHs());
    }

    @Override
    public void filterByReportTypes(ArrayList<FilterDH> listReportTypes) {
        for (FilterDH filterDH: listReportTypes)
            dynamicallyPreferences.putBoolean(filterDH.id, filterDH.selected);
        reloadWithNewReportTypes();
    }

    @Override
    public void removeAllReportTypes() {
        for (FilterItem filterItem: reportTypes)
            dynamicallyPreferences.putBoolean(filterItem.id, false);
        reloadWithNewReportTypes();
    }

    private ArrayList<FilterDH> prepareReportTypeDHs() {
        final ArrayList<FilterDH> reportTypesDHs = new ArrayList<>();
        for (FilterItem reportType: reportTypes)
            reportTypesDHs.add(new FilterDH(reportType, dynamicallyPreferences.getBoolean(reportType.id)));
        return reportTypesDHs;
    }
}
