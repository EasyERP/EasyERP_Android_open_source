package com.thinkmobiles.easyerp.presentation.screens.crm.orders.details;

import com.thinkmobiles.easyerp.data.model.crm.orders.detail.ResponseGerOrderDetails;
import com.thinkmobiles.easyerp.presentation.base.BaseModel;


import rx.Observable;


public interface OrderDetailsContract {


    interface OrderDetailsModel extends BaseModel {
        Observable<ResponseGerOrderDetails> getOrderDetails(String orderId);
    }
}
