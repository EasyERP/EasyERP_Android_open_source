package com.thinkmobiles.easyerp.data.services;

import com.thinkmobiles.easyerp.data.model.crm.filter.ResponseGetFilters;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by samson on 24.01.17.
 */

public interface FilterService {

    @GET(Constants.GET_FILTER + "/{filterParam}")
    Observable<ResponseGetFilters> getLeadFilters(@Path("filterParam") String filterParam);
}
