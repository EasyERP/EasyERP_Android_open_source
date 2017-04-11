package com.thinkmobiles.easyerp.presentation.screens.reports.general.detail;

import android.view.View;

import com.thinkmobiles.easyerp.data.model.ResponseGetTotalItems;
import com.thinkmobiles.easyerp.data.model.crm.filter.FilterItem;
import com.thinkmobiles.easyerp.data.model.reports.general.Report;
import com.thinkmobiles.easyerp.presentation.base.BaseModel;
import com.thinkmobiles.easyerp.presentation.base.BaseView;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentPresenter;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentView;
import com.thinkmobiles.easyerp.presentation.base.rules.master.list.MasterListPresenter;
import com.thinkmobiles.easyerp.presentation.base.rules.master.list.MasterListView;
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
    interface GeneralReportDetailView extends BaseView<GeneralReportDetailPresenter>, MasterListView {
        void showReportTypesDialog(final ArrayList<FilterDH> listReportTypes);
        void makeAvailableReportTypes();
        void seeFullReport(final String reportID);
        void showDescriptionPopUpWindow(final int position, final String description);
    }
    interface GeneralReportDetailPresenter extends MasterListPresenter {
        void chooseReportTypes();
        void filterByReportTypes(final ArrayList<FilterDH> listReportTypes);
        void removeAllReportTypes();
        void favorite(final int position, final boolean isFavorite);
        void displayDescription(final int position);
    }
    interface GeneralReportDetailModel extends BaseModel {
        Observable<List<FilterItem>> getReportTypes();
        Observable<ResponseGetTotalItems<Report>> getReports(final int page, final String categoryKey, final List<String> queryReportTypes);
        Observable<?> favorite(final String reportId, final boolean isFavorite);
    }
}
