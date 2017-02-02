package com.thinkmobiles.easyerp.data.services;

import com.thinkmobiles.easyerp.data.model.crm.persons.images.ResponseGetCustomersImages;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import java.util.ArrayList;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Lynx on 2/2/2017.
 */

public interface CustomerService {

    @GET(Constants.GET_CUSTOMER_IMAGES)
    Observable<ResponseGetCustomersImages> getCustomerImages(@Query("ids[]") ArrayList<String> listOfIDs);

}
