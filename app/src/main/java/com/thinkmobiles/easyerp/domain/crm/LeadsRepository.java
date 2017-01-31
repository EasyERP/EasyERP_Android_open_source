package com.thinkmobiles.easyerp.domain.crm;

import com.thinkmobiles.easyerp.data.api.Rest;
import com.thinkmobiles.easyerp.data.model.crm.leads.ResponseGetLeads;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.ResponseGetLeadDetails;
import com.thinkmobiles.easyerp.data.model.crm.leads.filter.ResponseGetLeadsFilters;
import com.thinkmobiles.easyerp.data.services.FilterService;
import com.thinkmobiles.easyerp.data.services.LeadService;
import com.thinkmobiles.easyerp.presentation.screens.crm.leads.LeadsContract;
import com.thinkmobiles.easyerp.presentation.screens.crm.leads.details.LeadDetailsContract;
import com.thinkmobiles.easyerp.presentation.utils.filter.FilterQuery;

import org.androidannotations.annotations.EBean;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Lynx on 1/16/2017.
 */

@EBean(scope = EBean.Scope.Singleton)
public class LeadsRepository implements LeadsContract.LeadsModel, LeadDetailsContract.LeadDetailsModel {

    private LeadService leadService;
    private FilterService filterService;

    public LeadsRepository() {
        leadService = Rest.getInstance().getLeadService();
        filterService = Rest.getInstance().getFilterService();
    }

    private <T> Observable<T> getNetworkObservable(Observable<T> observable) {
        return observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread());
    }

    @Override
    public Observable<ResponseGetLeads> getFilteredLeads(FilterQuery query, int page) {
        query.queryMap.put("viewType", "list");
        query.queryMap.put("contentType", "Leads");
        return getNetworkObservable(leadService.getFilteredLeads(
                query.queryMap,
                query.contactName,
                query.source,
                query.workflow,
                query.assignedTo,
                query.createdBy,
                page,
                50
        ));
    }

    public Observable<ResponseGetLeadsFilters> getLeadFilters() {
        return getNetworkObservable(filterService.getLeadFilters("Leads"));
    }

    @Override
    public Observable<ResponseGetLeadDetails> getLeadDetails(String leadId) {
        return getNetworkObservable(leadService.getLeadDetails(leadId));
    }
}
