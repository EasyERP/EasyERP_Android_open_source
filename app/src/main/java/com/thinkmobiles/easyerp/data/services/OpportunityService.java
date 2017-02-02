package com.thinkmobiles.easyerp.data.services;

import com.thinkmobiles.easyerp.data.model.crm.opportunities.ResponseGetOpportunities;
import com.thinkmobiles.easyerp.data.model.crm.opportunities.detail.ResponseGetOpportunityDetails;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Lynx on 1/30/2017.
 */

public interface OpportunityService {
    @GET(Constants.GET_OPPORTUNITIES)
    Observable<ResponseGetOpportunities> getOpportunities(@Query("viewType") String viewType,
                                                          @Query("contentType") String contentType,
                                                          @Query("count") int count,
                                                          @Query("page") int page);

    @GET(Constants.GET_OPPORTUNITY_DETAILS)
    Observable<ResponseGetOpportunityDetails> getOpportunityDetails(@Path("OpportunityID") String OpportunityID);
}
