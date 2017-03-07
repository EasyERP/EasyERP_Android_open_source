package com.thinkmobiles.easyerp.data.services;

import com.thinkmobiles.easyerp.data.model.inventory.transfers.ResponseGetTransfers;

import retrofit2.http.GET;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by Lynx on 3/7/2017.
 */

public interface TransactionsService {

    @GET
    Observable<ResponseGetTransfers> getTransactions(@Url String url);

}
