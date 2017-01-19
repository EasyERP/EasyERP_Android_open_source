package com.thinkmobiles.easyerp.domain.crm;

import com.thinkmobiles.easyerp.data.api.Rest;
import com.thinkmobiles.easyerp.data.model.crm.leads.ResponseGetLeads;
import com.thinkmobiles.easyerp.data.model.crm.leads.details.LeadDetailWorkflow;
import com.thinkmobiles.easyerp.data.model.crm.leads.details.ResponseGetLeadDetails;
import com.thinkmobiles.easyerp.data.model.crm.leads.details.ResponseGetLeadWorkflow;
import com.thinkmobiles.easyerp.data.services.LeadService;
import com.thinkmobiles.easyerp.presentation.screens.crm.leads.LeadsContract;
import com.thinkmobiles.easyerp.presentation.screens.crm.leads.details.LeadDetailsContract;

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

    public LeadsRepository() {
        leadService = Rest.getInstance().getLeadService();
    }

    private <T> Observable<T> getNetworkObservable(Observable<T> observable) {
        return observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread());
    }

    public Observable<ResponseGetLeads> getLeads(String viewType, int page, int count, String contentType) {
        return getNetworkObservable(leadService.getLeads(viewType, page, count, contentType));
    }

    public Observable<ResponseGetLeads> getLeads(int page) {
        return getNetworkObservable(leadService.getLeads("list", page, 50, "Leads"));
    }

    @Override
    public Observable<ResponseGetLeadDetails> getLeadDetails(String leadId) {
        return getNetworkObservable(Observable.zip(leadService.getLeadDetails(leadId),
                leadService.getLeadWorkflow("Leads"),
                (responseGetLeadDetails, responseGetLeadWorkflow) -> {
                    boolean isSelected = false;
                    for (LeadDetailWorkflow w : responseGetLeadWorkflow.data) {
                        if (responseGetLeadDetails.workflow._id.equals(w._id)) {
                            isSelected = true;
                            w.color = "#0079bf";
                        } else if (isSelected) {
                            w.color = "#e0e0ff";
                        } else {
                            w.color = "#61bd4f";
                        }
                    }
                    responseGetLeadDetails.leadWorkflow = responseGetLeadWorkflow;
                    return responseGetLeadDetails;
                }));
    }
}
