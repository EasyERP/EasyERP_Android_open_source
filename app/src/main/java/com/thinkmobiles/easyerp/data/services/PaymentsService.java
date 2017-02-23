package com.thinkmobiles.easyerp.data.services;

import com.thinkmobiles.easyerp.data.model.crm.payments.ResponseGetPayments;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import java.util.List;
import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;

/**
 * @author Michael Soyma (Created on 2/2/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public interface PaymentsService {

    @GET
    Observable<ResponseGetPayments> getFilteredPayments(@Url String url);
}
