package com.thinkmobiles.easyerp.data.services;

import com.thinkmobiles.easyerp.data.model.crm.payments.ResponseGetPayments;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @author Michael Soyma (Created on 2/2/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public interface PaymentsService {

    @GET(Constants.GET_PAYMENTS)
    Observable<ResponseGetPayments> getPayments(@Query("viewType") String viewType,
                                                @Query("page") int page,
                                                @Query("count") int count,
                                                @Query("contentType") String contentType);
}
