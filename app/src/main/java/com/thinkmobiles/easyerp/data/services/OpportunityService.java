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
import rx.Observable;

/**
 * Created by Lynx on 1/30/2017.
 */

public interface OpportunityService {

    @GET(Constants.GET_LEADS)
    Observable<ResponseGetOpportunities> getFilteredOpportunities(
            @QueryMap Map<String, String> keys,
            @Query("filter[customer][value][]") List<String> customer,
            @Query("filter[name][value][]") List<String> name,
            @Query("filter[workflow][value][]") List<String> workflow,
            @Query("filter[salesPerson][value][]") List<String> salesPerson,
            @Query("page") int page,
            @Query("count") int count
    );

    @GET(Constants.GET_OPPORTUNITY_DETAILS)
    Observable<ResponseGetOpportunityDetails> getOpportunityDetails(@Path("OpportunityID") String OpportunityID);
}
