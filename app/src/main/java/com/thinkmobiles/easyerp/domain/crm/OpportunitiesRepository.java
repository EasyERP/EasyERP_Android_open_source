package com.thinkmobiles.easyerp.domain.crm;

import com.thinkmobiles.easyerp.data.api.Rest;
import com.thinkmobiles.easyerp.data.model.crm.opportunities.ResponseGetOpportunities;
import com.thinkmobiles.easyerp.data.services.OpportunityService;
import com.thinkmobiles.easyerp.presentation.base.NetworkRepository;
import com.thinkmobiles.easyerp.presentation.screens.crm.opportunities.OpportunitiesContract;

import org.androidannotations.annotations.EBean;

import rx.Observable;

/**
 * Created by Lynx on 1/30/2017.
 */

@EBean
public class OpportunitiesRepository extends NetworkRepository implements OpportunitiesContract.OpportunitiesModel {

    private OpportunityService opportunityService;

    public OpportunitiesRepository() {
        opportunityService = Rest.getInstance().getOpportunityService();
    }

    public Observable<ResponseGetOpportunities> getOpportunities(int page) {
        return getNetworkObservable(opportunityService.getOpportunities("list", "Opportunities", 50, page));
    }

}
