package com.thinkmobiles.easyerp.data.services;

import com.thinkmobiles.easyerp.data.model.crm.common.alphabet.ResponseGetAlphabet;
import com.thinkmobiles.easyerp.data.model.crm.companies.ResponseGetCompanies;
import com.thinkmobiles.easyerp.data.model.crm.companies.detail.ResponseGetCompanyDetails;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by Lynx on 2/2/2017.
 */

public interface CompaniesService {

    @GET(Constants.GET_COMPANIES_ALPHABET)
    Observable<ResponseGetAlphabet> getCompaniesAlphabet(@Query("contentType") String contentType);

    @GET
    Observable<ResponseGetCompanies> getCompanies(@Url String url);

    @GET(Constants.GET_COMPANY_DETAILS)
    Observable<ResponseGetCompanyDetails> getCompanyDetails(@Path("companyID") String companyID);
}
