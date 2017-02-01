package com.thinkmobiles.easyerp.presentation.screens.crm.orders;

import com.thinkmobiles.easyerp.data.model.crm.orders.ResponseGetOrders;
import com.thinkmobiles.easyerp.presentation.base.BaseModel;
import com.thinkmobiles.easyerp.presentation.base.rules.ErrorViewHelper;
import com.thinkmobiles.easyerp.presentation.base.rules.MasterFlowSelectableBasePresenter;
import com.thinkmobiles.easyerp.presentation.base.rules.MasterFlowSelectableBaseView;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.OrderDH;

import java.util.ArrayList;

import rx.Observable;

/**
 * @author michael.soyma@thinkmobiles.com (Created on 1/31/2017).
 */

public interface OrdersContract {
    interface OrdersView extends MasterFlowSelectableBaseView<OrdersPresenter> {
        void displayOrders(ArrayList<OrderDH> orderDHs, boolean needClear);
        void displayError(final String msg, final ErrorViewHelper.ErrorType errorType);
        void openOrderDetailsScreen(String orderID);
    }
    interface OrdersPresenter extends MasterFlowSelectableBasePresenter<String, OrderDH> {
        void loadOrders(int page);
    }
    interface OrdersModel extends BaseModel {
        Observable<ResponseGetOrders> getOrders();
    }
}
