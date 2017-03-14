package com.thinkmobiles.easyerp.data.services;

import com.thinkmobiles.easyerp.data.model.ResponseGetTotalItems;
import com.thinkmobiles.easyerp.data.model.crm.common.alphabet.ResponseGetAlphabet;
import com.thinkmobiles.easyerp.data.model.crm.persons.details.ResponseGetPersonDetails;
import com.thinkmobiles.easyerp.data.model.crm.persons.person_item.PersonModel;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by Lynx on 1/20/2017.
 */

public interface PersonsService {

    @GET(Constants.GET_PERSONS_ALPHABET)
    Observable<ResponseGetAlphabet> getPersonsAlphabet(@Query("contentType") String contentType);

    @GET
    Observable<ResponseGetTotalItems<PersonModel>> getPersons(@Url String url);

    @GET(Constants.GET_PERSON_DETAILS)
    Observable<ResponseGetPersonDetails> getPersonDetails(@Path("PersonId") String PersonId);
}
