package com.thinkmobiles.easyerp.domain.crm;

import com.thinkmobiles.easyerp.data.api.Rest;
import com.thinkmobiles.easyerp.data.model.ResponseGetTotalItems;
import com.thinkmobiles.easyerp.data.model.crm.filter.ResponseFilters;
import com.thinkmobiles.easyerp.data.model.crm.leads.LeadItem;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.ResponseGetLeadDetails;
import com.thinkmobiles.easyerp.data.services.FilterService;
import com.thinkmobiles.easyerp.data.services.LeadService;
import com.thinkmobiles.easyerp.presentation.base.NetworkRepository;
import com.thinkmobiles.easyerp.presentation.screens.crm.leads.LeadsContract;
import com.thinkmobiles.easyerp.presentation.screens.crm.leads.details.LeadDetailsContract;
import com.thinkmobiles.easyerp.presentation.utils.Constants;
import com.thinkmobiles.easyerp.presentation.utils.filter.FilterHelper;

import org.androidannotations.annotations.EBean;

import rx.Observable;

/**
 * Created by Lynx on 1/16/2017.
 */

@EBean(scope = EBean.Scope.Singleton)
public class LeadsRepository extends NetworkRepository implements LeadsContract.LeadsModel, LeadDetailsContract.LeadDetailsModel {

    private LeadService leadService;
    private FilterService filterService;

    public LeadsRepository() {
        leadService = Rest.getInstance().getLeadService();
        filterService = Rest.getInstance().getFilterService();
    }

    @Override
    public Observable<ResponseGetTotalItems<LeadItem>> getFilteredLeads(FilterHelper query, int page) {
        return getNetworkObservable(leadService.getLeads(query
                .createUrl(Constants.GET_LEADS, "Leads", page)
                .build()
                .toString()
        ));
    }

    public Observable<ResponseFilters> getFilters() {
        return getNetworkObservable(filterService.getListFilters("Leads"));
    }

    @Override
    public Observable<ResponseGetLeadDetails> getLeadDetails(String leadId) {
        return getNetworkObservable(leadService.getLeadDetails(leadId));
    }
}
