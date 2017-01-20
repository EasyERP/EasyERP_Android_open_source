package com.thinkmobiles.easyerp.data.services;

import com.thinkmobiles.easyerp.data.model.crm.persons.ResponseGetPersons;
import com.thinkmobiles.easyerp.data.model.crm.persons.alphabet.ResponseGetPersonsAlphabet;
import com.thinkmobiles.easyerp.data.model.crm.persons.images.ResponseGetCustomersImages;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import java.util.ArrayList;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Lynx on 1/20/2017.
 */

public interface PersonsService {

    @GET(Constants.GET_PERSONS_ALPHABET)
    Observable<ResponseGetPersonsAlphabet> getPersonsAlphabet(@Query("contentType") String contentType);

    @GET(Constants.GET_CUSTOMER_IMAGES)
    Observable<ResponseGetCustomersImages> getCustomerImages(@Query("ids[]") ArrayList<String> listOfIDs);

    @GET(Constants.GET_PERSONS)
    Observable<ResponseGetPersons> getAllPersons(@Query("viewType") String viewType,
                                                 @Query("count") int count,
                                                 @Query("contentType") String contentType,
                                                 @Query("page") int page);

    @GET(Constants.GET_PERSONS)
    Observable<ResponseGetPersons> getPersonsByLetter(@Query("viewType") String viewType,
                                                      @Query("filter[letter][key]") String filterLetterKey,
                                                      @Query("filter[letter][value]") String filterLetterValue,
                                                      @Query("filter[letter][type]") String filterLetterType,
                                                      @Query("contentType") String contentType,
                                                      @Query("count") int count,
                                                      @Query("page") int page);
}
