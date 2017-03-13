package com.thinkmobiles.easyerp.data.services;

import com.thinkmobiles.easyerp.data.model.crm.common.images.ImageItem;
import com.thinkmobiles.easyerp.data.model.crm.common.images.ResponseGetImages;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import java.util.ArrayList;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Lynx on 2/2/2017.
 */

public interface ImageService {

    @GET(Constants.GET_CUSTOMER_IMAGES)
    Observable<ResponseGetImages> getCustomerImages(@Query("ids[]") ArrayList<String> listOfIDs);

    @GET(Constants.GET_EMPLOYEES_IMAGES)
    Observable<ResponseGetImages> getEmployeesImages(@Query("ids[]") ArrayList<String> listOfIDs);

}
