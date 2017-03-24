package com.thinkmobiles.easyerp.data.services;

import com.thinkmobiles.easyerp.data.model.ResponseGetTotalItems;
import com.thinkmobiles.easyerp.data.model.hr.applications.Application;
import com.thinkmobiles.easyerp.data.model.hr.applications.ResponseGetPayrollStructureTypes;
import com.thinkmobiles.easyerp.data.model.hr.employees.ResponseEmployeeDetails;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;
import rx.Observable;

/**
 * @author Michael Soyma (Created on 3/13/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public interface ApplicationService {

    @GET
    Observable<ResponseGetTotalItems<Application>> getApplications(@Url String url);

    @GET(Constants.GET_APPLICATIONS)
    Observable<ResponseEmployeeDetails> getApplicationDetails(@Query("id") String id);

    @GET(Constants.GET_PAYROLL_TYPES)
    Observable<ResponseGetPayrollStructureTypes> getPayrollStructureTypes();
}
