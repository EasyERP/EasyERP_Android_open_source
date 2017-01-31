package com.thinkmobiles.easyerp.domain.crm;

import com.thinkmobiles.easyerp.data.api.Rest;
import com.thinkmobiles.easyerp.data.model.crm.dashboard.detail.order.OrderItem;
import com.thinkmobiles.easyerp.data.model.crm.orders.detail.ResponseGerOrderDetails;
import com.thinkmobiles.easyerp.data.services.OrderService;
import com.thinkmobiles.easyerp.presentation.screens.crm.orders.OrdersContract;
import com.thinkmobiles.easyerp.presentation.screens.crm.orders.details.OrderDetailsContract;

import org.androidannotations.annotations.EBean;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Lynx on 1/16/2017.
 */

@EBean(scope = EBean.Scope.Singleton)
public class OrderRepository implements OrdersContract.OrdersModel, OrderDetailsContract.OrderDetailsModel {

    private OrderService orderService;

    public OrderRepository() {
        orderService = Rest.getInstance().getOrderService();
    }

    private <T> Observable<T> getNetworkObservable(Observable<T> observable) {
        return observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread());
    }

    @Override
    public Observable<List<OrderItem>> getOrders() {
        return getNetworkObservable(orderService.getOrder());
    }

    @Override
    public Observable<ResponseGerOrderDetails> getOrderDetails(String orderId) {
        return getNetworkObservable(orderService.getOrderDetails(orderId));
    }

}