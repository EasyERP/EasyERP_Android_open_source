package com.thinkmobiles.easyerp.domain.crm;

import com.thinkmobiles.easyerp.data.api.Rest;
import com.thinkmobiles.easyerp.data.model.ResponseGetTotalItems;
import com.thinkmobiles.easyerp.data.model.crm.filter.ResponseFilters;
import com.thinkmobiles.easyerp.data.model.crm.opportunities.detail.ResponseGetOpportunityDetails;
import com.thinkmobiles.easyerp.data.model.crm.opportunities.list_item.OpportunityListItem;
import com.thinkmobiles.easyerp.data.services.FilterService;
import com.thinkmobiles.easyerp.data.services.OpportunityService;
import com.thinkmobiles.easyerp.presentation.base.NetworkRepository;
import com.thinkmobiles.easyerp.presentation.screens.crm.opportunities.OpportunitiesContract;
import com.thinkmobiles.easyerp.presentation.screens.crm.opportunities.details.OpportunityDetailsContract;
import com.thinkmobiles.easyerp.presentation.utils.Constants;
import com.thinkmobiles.easyerp.presentation.utils.filter.FilterHelper;

import org.androidannotations.annotations.EBean;

import rx.Observable;

/**
 * Created by Lynx on 1/30/2017.
 */

@EBean
public class OpportunitiesRepository extends NetworkRepository implements OpportunitiesContract.OpportunitiesModel, OpportunityDetailsContract.OpportunityDetailsModel {

    private OpportunityService opportunityService;
    private FilterService filterService;

    public OpportunitiesRepository() {
        opportunityService = Rest.getInstance().getOpportunityService();
        filterService = Rest.getInstance().getFilterService();
    }

    @Override
    public Observable<ResponseGetTotalItems<OpportunityListItem>> getOpportunities(FilterHelper query, int page) {
        return getNetworkObservable(opportunityService.getOpportunities(query
                .createUrl(Constants.GET_OPPORTUNITIES, "Opportunities", page)
                .build()
                .toString()
        ));
    }

    @Override
    public Observable<ResponseGetOpportunityDetails> getOpportunityDetails(String opportunityID) {
        return getNetworkObservable(opportunityService.getOpportunityDetails(opportunityID));
    }

    @Override
    public Observable<ResponseFilters> getFilters() {
        return getNetworkObservable(filterService.getListFilters("Opportunities"));
    }

}
