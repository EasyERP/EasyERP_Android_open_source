package com.thinkmobiles.easyerp.presentation.screens.crm.orders;

import com.thinkmobiles.easyerp.data.model.crm.dashboard.detail.order.OrderItem;
import com.thinkmobiles.easyerp.presentation.base.BaseModel;
import com.thinkmobiles.easyerp.presentation.base.rules.ErrorViewHelper;
import com.thinkmobiles.easyerp.presentation.base.rules.MasterFlowSelectableBasePresenter;
import com.thinkmobiles.easyerp.presentation.base.rules.MasterFlowSelectableBaseView;

import java.util.List;

import rx.Observable;

/**
 * @author michael.soyma@thinkmobiles.com (Created on 1/31/2017).
 */

public interface OrdersContract {
    interface OrdersView extends MasterFlowSelectableBaseView<OrdersPresenter> {
        void displayOrders(List<OrderItem> orderDHs, boolean needClear);
        void displayError(final String msg, final ErrorViewHelper.ErrorType errorType);
        void openOrderDetailsScreen(String orderID);
    }
    interface OrdersPresenter extends MasterFlowSelectableBasePresenter<String> {
        void loadOrders(int page);
        void displayOrderDetail(String orderId);
    }
    interface OrdersModel extends BaseModel {
        Observable<List<OrderItem>> getOrders();
    }
}
