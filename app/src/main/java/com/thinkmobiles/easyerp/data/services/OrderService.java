package com.thinkmobiles.easyerp.data.services;

import com.thinkmobiles.easyerp.data.model.crm.dashboard.detail.order.OrderItem;
import com.thinkmobiles.easyerp.data.model.crm.order.ResponseGetOrders;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @author michael.soyma@thinkmobiles.com (Created on 1/16/2017.)
 */

public interface OrderService {

    @GET(Constants.GET_ORDER)
    Observable<ResponseGetOrders> getOrder(@Query("viewType") String viewType,
                                           @Query("page") int page,
                                           @Query("count") int count,
                                           @Query("contentType") String contentType);

    @GET(Constants.GET_ORDER_BY_WORKFLOWS)
    Observable<List<OrderItem>> getOrderByWorkflows(
            @Query("filter[date][value][0]") final String filterDateValueFrom,
            @Query("filter[date][value][1]") final String filterDateValueTo,
            @Query("forSales") final boolean forSales);

}
