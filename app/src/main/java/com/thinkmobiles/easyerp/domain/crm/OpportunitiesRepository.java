package com.thinkmobiles.easyerp.domain.crm;

import com.thinkmobiles.easyerp.data.api.Rest;
import com.thinkmobiles.easyerp.data.model.crm.opportunities.ResponseGetOpportunities;
import com.thinkmobiles.easyerp.data.services.OpportunityService;
import com.thinkmobiles.easyerp.presentation.screens.crm.opportunities.OpportunitiesContract;

import org.androidannotations.annotations.EBean;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Lynx on 1/30/2017.
 */

@EBean
public class OpportunitiesRepository implements OpportunitiesContract.OpportunitiesModel {

    private OpportunityService opportunityService;

    public OpportunitiesRepository() {
        opportunityService = Rest.getInstance().getOpportunityService();
    }

    private <T> Observable<T> getNetworkObservable(Observable<T> observable) {
        return observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread());
    }

    public Observable<ResponseGetOpportunities> getOpportunities(int page) {
        return getNetworkObservable(opportunityService.getOpportunities("list", "Opportunities", 50, page));
    }

}
