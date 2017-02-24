package com.thinkmobiles.easyerp.presentation.screens.crm.opportunities;

import com.thinkmobiles.easyerp.data.model.crm.opportunities.ResponseGetOpportunities;
import com.thinkmobiles.easyerp.presentation.base.BaseView;
import com.thinkmobiles.easyerp.presentation.base.rules.master.filterable.FilterableModel;
import com.thinkmobiles.easyerp.presentation.base.rules.master.filterable.FilterablePresenter;
import com.thinkmobiles.easyerp.presentation.base.rules.master.filterable.FilterableView;
import com.thinkmobiles.easyerp.presentation.utils.filter.FilterHelper;

import rx.Observable;

/**
 * Created by Lynx on 1/30/2017.
 */

public interface OpportunitiesContract {

    interface OpportunitiesView extends BaseView<OpportunitiesPresenter>, FilterableView {
        void openDetailsScreen(String opportunityID);
    }

    interface OpportunitiesPresenter extends FilterablePresenter {}

    interface OpportunitiesModel extends FilterableModel {
        Observable<ResponseGetOpportunities> getOpportunities(FilterHelper query, int page);
    }
}
