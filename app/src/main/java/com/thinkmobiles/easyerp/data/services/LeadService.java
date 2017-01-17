package com.thinkmobiles.easyerp.data.services;

import com.thinkmobiles.easyerp.data.model.leads.ResponseGetLeads;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import retrofit2.http.GET;
import retrofit2.http.Query;
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

}
