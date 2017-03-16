package com.thinkmobiles.easyerp.data.services;

import com.thinkmobiles.easyerp.data.model.ResponseGetTotalItems;
import com.thinkmobiles.easyerp.data.model.inventory.transfers.details.ResponseGetTransferDetails;
import com.thinkmobiles.easyerp.data.model.inventory.transfers.details.TransferItem;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by Lynx on 3/7/2017.
 */

public interface TransfersService {

    @GET
    Observable<ResponseGetTotalItems<TransferItem>> getTransfers(@Url String url);

    @GET(Constants.GET_TRANSFERS_DETAILS)
    Observable<ResponseGetTransferDetails> getTransferDetails(@Path("transferID") String transferID);

}
