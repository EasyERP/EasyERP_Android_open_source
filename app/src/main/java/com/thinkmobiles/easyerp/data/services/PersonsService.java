package com.thinkmobiles.easyerp.data.services;

import com.thinkmobiles.easyerp.data.model.crm.persons.ResponseGetPersons;
import com.thinkmobiles.easyerp.data.model.crm.common.alphabet.ResponseGetAlphabet;
import com.thinkmobiles.easyerp.data.model.crm.persons.details.ResponseGetPersonDetails;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Lynx on 1/20/2017.
 */

public interface PersonsService {

    @GET(Constants.GET_PERSONS_ALPHABET)
    Observable<ResponseGetAlphabet> getPersonsAlphabet(@Query("contentType") String contentType);

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

    @GET(Constants.GET_PERSON_DETAILS)
    Observable<ResponseGetPersonDetails> getPersonDetails(@Path("PersonId") String PersonId);
}
