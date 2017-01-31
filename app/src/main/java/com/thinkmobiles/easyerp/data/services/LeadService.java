package com.thinkmobiles.easyerp.data.services;

import com.thinkmobiles.easyerp.data.model.crm.leads.ResponseGetLeads;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.ResponseGetLeadDetails;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.ResponseGetLeadWorkflow;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by Lynx on 1/16/2017.
 */

public interface LeadService {

    @GET(Constants.GET_LEADS)
    Observable<ResponseGetLeads> getLeads(@Query("viewType") String viewType,
                                          @Query("page") int page,
                                          @Query("count") int count,
                                          @Query("contentType") String contentType);

    @GET(Constants.GET_LEADS)
    Observable<ResponseGetLeads> getFilteredLeads(
            @QueryMap Map<String, String> keys,
            @Query("filter[contactName][value][]") List<String> contactName,
            @Query("filter[source][value][]") List<String> source,
            @Query("filter[workflow][value][]") List<String> workflow,
            @Query("filter[salesPerson][value][]") List<String> salesPerson,
            @Query("filter[createdBy][value][]") List<String> createdBy,
            @Query("page") int page,
            @Query("count") int count
    );

    @GET(Constants.GET_LEADS + "/{leadId}")
    Observable<ResponseGetLeadDetails> getLeadDetails(@Path("leadId") String leadId);

    @GET("workflows/")
    Observable<ResponseGetLeadWorkflow> getLeadWorkflow(@Query("id") String workflowId);

}
