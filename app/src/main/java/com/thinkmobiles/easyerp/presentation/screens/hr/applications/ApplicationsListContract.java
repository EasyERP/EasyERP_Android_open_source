package com.thinkmobiles.easyerp.presentation.screens.hr.applications;

import com.thinkmobiles.easyerp.data.model.ResponseGetTotalItems;
import com.thinkmobiles.easyerp.data.model.hr.applications.Application;
import com.thinkmobiles.easyerp.presentation.base.BaseModel;
import com.thinkmobiles.easyerp.presentation.base.BaseView;
import com.thinkmobiles.easyerp.presentation.base.rules.master.filterable.FilterableModel;
import com.thinkmobiles.easyerp.presentation.base.rules.master.filterable.FilterablePresenter;
import com.thinkmobiles.easyerp.presentation.base.rules.master.filterable.FilterableView;
import com.thinkmobiles.easyerp.presentation.utils.filter.FilterHelper;

import rx.Observable;

/**
 * @author Michael Soyma (Created on 3/13/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public interface ApplicationsListContract {
    interface ApplicationsListView extends BaseView<ApplicationsListPresenter>, FilterableView {
        void openApplicationDetail(final String id);
    }
    interface ApplicationsListPresenter extends FilterablePresenter {

    }
    interface ApplicationsListModel extends BaseModel, FilterableModel {
        Observable<ResponseGetTotalItems<Application>> getApplications(FilterHelper query, final int page);
    }
}
