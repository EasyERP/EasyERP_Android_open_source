package com.thinkmobiles.easyerp.data.services;

import com.thinkmobiles.easyerp.data.model.crm.leads.ResponseGetLeads;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.ResponseGetLeadDetails;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.ResponseGetLeadWorkflow;
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
 * Created by Lynx on 1/16/2017.
 */

public interface LeadService {

    @GET
    Observable<ResponseGetLeads> getLeads(@Url String url);

    @GET(Constants.GET_LEADS + "/{leadId}")
    Observable<ResponseGetLeadDetails> getLeadDetails(@Path("leadId") String leadId);
}
