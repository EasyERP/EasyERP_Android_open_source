package com.thinkmobiles.easyerp.data.services;

import com.thinkmobiles.easyerp.data.model.crm.opportunities.ResponseGetOpportunities;
import com.thinkmobiles.easyerp.data.model.crm.opportunities.detail.ResponseGetOpportunityDetails;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import java.util.List;
import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by Lynx on 1/30/2017.
 */

public interface OpportunityService {

    @GET
    Observable<ResponseGetOpportunities> getOpportunities(@Url String url);

    @GET(Constants.GET_OPPORTUNITY_DETAILS)
    Observable<ResponseGetOpportunityDetails> getOpportunityDetails(@Path("OpportunityID") String OpportunityID);
}
