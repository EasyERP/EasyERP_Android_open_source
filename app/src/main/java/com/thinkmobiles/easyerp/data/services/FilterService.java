package com.thinkmobiles.easyerp.data.services;

import com.thinkmobiles.easyerp.data.model.crm.leads.filter.ResponseGetLeadsFilters;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by samson on 24.01.17.
 */

public interface FilterService {

    @GET("filter/{filterParam}")
    Observable<ResponseGetLeadsFilters> getLeadFilters(@Path("filterParam") String filterParam);
}
