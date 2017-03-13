package com.thinkmobiles.easyerp.data.services;

import com.thinkmobiles.easyerp.data.model.crm.common.alphabet.ResponseGetAlphabet;
import com.thinkmobiles.easyerp.data.model.crm.persons.ResponseGetPersons;
import com.thinkmobiles.easyerp.data.model.hr.employees.ResponseGetEmployees;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by Lynx on 3/13/2017.
 */

public interface EmployeesService {

    @GET(Constants.GET_EMPLOYEES_ALPHABET)
    Observable<ResponseGetAlphabet> getEmployeesAlphabet();

    @GET
    Observable<ResponseGetEmployees> getEmployees(@Url String url);

    @GET(Constants.GET_EMPLOYEES)
    Observable<ResponseGetPersons> getEmployeesByLetter(@Query("viewType") String viewType,
                                                      @Query("filter[letter][key]") String filterLetterKey,
                                                      @Query("filter[letter][value]") String filterLetterValue,
                                                      @Query("filter[letter][type]") String filterLetterType,
                                                      @Query("contentType") String contentType,
                                                      @Query("count") int count,
                                                      @Query("page") int page);

}
