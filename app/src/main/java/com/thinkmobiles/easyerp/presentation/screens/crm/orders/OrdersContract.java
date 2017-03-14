package com.thinkmobiles.easyerp.presentation.screens.crm.orders;

import com.thinkmobiles.easyerp.data.model.ResponseGetTotalItems;
import com.thinkmobiles.easyerp.data.model.crm.order.Order;
import com.thinkmobiles.easyerp.presentation.base.BaseView;
import com.thinkmobiles.easyerp.presentation.base.rules.master.filterable.FilterableModel;
import com.thinkmobiles.easyerp.presentation.base.rules.master.filterable.FilterablePresenter;
import com.thinkmobiles.easyerp.presentation.base.rules.master.filterable.FilterableView;
import com.thinkmobiles.easyerp.presentation.utils.filter.FilterHelper;

import rx.Observable;

/**
 * @author michael.soyma@thinkmobiles.com (Created on 1/31/2017).
 */

public interface OrdersContract {
    interface OrdersView extends BaseView<OrdersPresenter>, FilterableView {
        void openDetailsScreen(String orderID);
    }
    interface OrdersPresenter extends FilterablePresenter {}
    interface OrdersModel extends FilterableModel {
        Observable<ResponseGetTotalItems<Order>> getOrders(FilterHelper query, int page);
    }
}
