package com.thinkmobiles.easyerp.data.services;

import com.thinkmobiles.easyerp.data.model.ResponseGetTotalItems;
import com.thinkmobiles.easyerp.data.model.crm.payments.Payment;

import retrofit2.http.GET;
import retrofit2.http.Url;
import rx.Observable;

/**
 * @author Michael Soyma (Created on 2/2/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public interface PaymentsService {

    @GET
    Observable<ResponseGetTotalItems<Payment>> getFilteredPayments(@Url String url);
}
