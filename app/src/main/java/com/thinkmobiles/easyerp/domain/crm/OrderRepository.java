package com.thinkmobiles.easyerp.domain.crm;

import com.thinkmobiles.easyerp.data.api.Rest;
import com.thinkmobiles.easyerp.data.model.ResponseGetTotalItems;
import com.thinkmobiles.easyerp.data.model.crm.filter.ResponseFilters;
import com.thinkmobiles.easyerp.data.model.crm.order.Order;
import com.thinkmobiles.easyerp.data.model.crm.order.detail.ResponseGetOrderDetails;
import com.thinkmobiles.easyerp.data.model.user.organization.ResponseGetOrganizationSettings;
import com.thinkmobiles.easyerp.data.services.FilterService;
import com.thinkmobiles.easyerp.data.services.OrderService;
import com.thinkmobiles.easyerp.data.services.UserService;
import com.thinkmobiles.easyerp.presentation.base.NetworkRepository;
import com.thinkmobiles.easyerp.presentation.screens.crm.orders.OrdersContract;
import com.thinkmobiles.easyerp.presentation.screens.crm.orders.details.OrderDetailsContract;
import com.thinkmobiles.easyerp.presentation.utils.filter.FilterHelper;

import rx.Observable;

/**
 * Created by Lynx on 1/16/2017.
 */

public class OrderRepository extends NetworkRepository implements OrdersContract.OrdersModel, OrderDetailsContract.OrderDetailsModel {

    private OrderService orderService;
    private UserService userService;
    private FilterService filterService;

    private String path;
    private String contentType;

    public OrderRepository(String path, String contentType) {
        this.path = path;
        this.contentType = contentType;
        orderService = Rest.getInstance().getOrderService();
        userService = Rest.getInstance().getUserService();
        filterService = Rest.getInstance().getFilterService();
    }

    @Override
    public Observable<ResponseGetTotalItems<Order>> getOrders(FilterHelper query, final int page) {
        return getNetworkObservable(orderService.getOrders(query
                .createUrl(path, contentType, page)
                .build()
                .toString()
        ));
    }

    @Override
    public Observable<ResponseFilters> getFilters() {
        return getNetworkObservable(filterService.getListFilters(contentType));
    }

    @Override
    public Observable<ResponseGetOrderDetails> getOrderDetails(String orderId) {
        return getNetworkObservable(orderService.getOrderDetails(orderId));
    }

    @Override
    public Observable<ResponseGetOrganizationSettings> getOrganizationSettings() {
        return getNetworkObservable(userService.getOrganizationSettings());
    }
}