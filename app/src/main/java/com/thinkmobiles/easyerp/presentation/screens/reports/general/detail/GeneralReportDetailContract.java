package com.thinkmobiles.easyerp.presentation.screens.reports.general.detail;

import com.thinkmobiles.easyerp.data.model.crm.filter.FilterItem;
import com.thinkmobiles.easyerp.data.model.reports.general.Report;
import com.thinkmobiles.easyerp.presentation.base.BaseModel;
import com.thinkmobiles.easyerp.presentation.base.BaseView;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentPresenter;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentView;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.FilterDH;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * @author Michael Soyma (Created on 4/8/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public interface GeneralReportDetailContract {
    interface GeneralReportDetailView extends BaseView<GeneralReportDetailPresenter>, ContentView {
        void showReportTypesDialog(final ArrayList<FilterDH> listReportTypes);
        void makeAvailableReportTypes();
    }
    interface GeneralReportDetailPresenter extends ContentPresenter {
        void chooseReportTypes();
        void filterByReportTypes(final ArrayList<FilterDH> listReportTypes);
        void removeAllReportTypes();
    }
    interface GeneralReportDetailModel extends BaseModel {
        Observable<List<FilterItem>> getReportTypes();
        Observable<List<Report>> getReports(final String categoryKey, final List<String> queryReportTypes);
    }
}
