package com.thinkmobiles.easyerp.domain;

import com.thinkmobiles.easyerp.data.api.Rest;
import com.thinkmobiles.easyerp.data.model.leads.ResponseGetLeads;
import com.thinkmobiles.easyerp.data.services.LeadService;
import com.thinkmobiles.easyerp.presentation.screens.leads.LeadsContract;

import org.androidannotations.annotations.EBean;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Lynx on 1/16/2017.
 */

@EBean(scope = EBean.Scope.Singleton)
public class LeadsRepository implements LeadsContract.LeadsModel {

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

}
