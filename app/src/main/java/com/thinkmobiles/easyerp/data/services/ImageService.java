package com.thinkmobiles.easyerp.data.services;

import com.thinkmobiles.easyerp.data.model.ResponseGetTotalItems;
import com.thinkmobiles.easyerp.data.model.crm.common.images.ImageItem;
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
    Observable<ResponseGetTotalItems<ImageItem>> getCustomerImages(@Query("ids[]") ArrayList<String> listOfIDs);

    @GET(Constants.GET_EMPLOYEES_IMAGES)
    Observable<ResponseGetTotalItems<ImageItem>> getEmployeesImages(@Query("ids[]") ArrayList<String> listOfIDs);

}
