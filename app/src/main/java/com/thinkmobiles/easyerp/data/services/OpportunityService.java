package com.thinkmobiles.easyerp.data.services;

import com.thinkmobiles.easyerp.data.model.ResponseGetTotalItems;
import com.thinkmobiles.easyerp.data.model.crm.opportunities.detail.ResponseGetOpportunityDetails;
import com.thinkmobiles.easyerp.data.model.crm.opportunities.list_item.OpportunityListItem;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by Lynx on 1/30/2017.
 */

public interface OpportunityService {

    @GET
    Observable<ResponseGetTotalItems<OpportunityListItem>> getOpportunities(@Url String url);

    @GET(Constants.GET_OPPORTUNITY_DETAILS)
    Observable<ResponseGetOpportunityDetails> getOpportunityDetails(@Path("OpportunityID") String OpportunityID);
}
