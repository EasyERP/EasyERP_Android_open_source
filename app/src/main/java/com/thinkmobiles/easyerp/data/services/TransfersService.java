package com.thinkmobiles.easyerp.data.services;

import com.thinkmobiles.easyerp.data.model.inventory.transfers.ResponseGetTransferDetails;
import com.thinkmobiles.easyerp.data.model.inventory.transfers.ResponseGetTransfers;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by Lynx on 3/7/2017.
 */

public interface TransfersService {

    @GET
    Observable<ResponseGetTransfers> getTransfers(@Url String url);

    @GET(Constants.GET_TRANSFERS_DETAILS)
    Observable<ResponseGetTransferDetails> getTransferDetails(@Part("transferID") String transferID);

}
