package com.thinkmobiles.easyerp.domain.crm;

import com.thinkmobiles.easyerp.data.api.Rest;
import com.thinkmobiles.easyerp.data.model.crm.orders.ResponseGetOrders;
import com.thinkmobiles.easyerp.data.services.OrderService;
import com.thinkmobiles.easyerp.presentation.base.NetworkRepository;
import com.thinkmobiles.easyerp.presentation.screens.crm.orders.OrdersContract;

import org.androidannotations.annotations.EBean;

import rx.Observable;

/**
 * Created by Lynx on 1/16/2017.
 */

@EBean(scope = EBean.Scope.Singleton)
public class OrderRepository extends NetworkRepository implements OrdersContract.OrdersModel {

    private OrderService orderService;

    public OrderRepository() {
        orderService = Rest.getInstance().getOrderService();
    }

    @Override
    public Observable<ResponseGetOrders> getOrders(final int page) {
        return getNetworkObservable(orderService.getOrder(
                "list",
                page,
                50,
                "order"));
    }

}