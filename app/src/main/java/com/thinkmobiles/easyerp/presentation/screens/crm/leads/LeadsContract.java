package com.thinkmobiles.easyerp.presentation.screens.crm.leads;

import com.thinkmobiles.easyerp.data.model.ResponseGetTotalItems;
import com.thinkmobiles.easyerp.data.model.crm.leads.LeadItem;
import com.thinkmobiles.easyerp.presentation.base.BaseModel;
import com.thinkmobiles.easyerp.presentation.base.BaseView;
import com.thinkmobiles.easyerp.presentation.base.rules.master.filterable.FilterableModel;
import com.thinkmobiles.easyerp.presentation.base.rules.master.filterable.FilterablePresenter;
import com.thinkmobiles.easyerp.presentation.base.rules.master.filterable.FilterableView;
import com.thinkmobiles.easyerp.presentation.utils.filter.FilterHelper;

import rx.Observable;

/**
 * Created by Lynx on 1/16/2017.
 */

public interface LeadsContract {

    interface LeadsView extends BaseView<LeadsPresenter>, FilterableView {
        void openDetailsScreen(String leadId);
    }

    interface LeadsPresenter extends FilterablePresenter {

    }

    interface LeadsModel extends BaseModel, FilterableModel {
        Observable<ResponseGetTotalItems<LeadItem>> getFilteredLeads(FilterHelper query, int page);
    }
}
