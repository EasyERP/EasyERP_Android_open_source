package com.thinkmobiles.easyerp.presentation.screens.crm.orders;

import com.thinkmobiles.easyerp.data.model.crm.orders.Order;
import com.thinkmobiles.easyerp.presentation.base.rules.ErrorViewHelper;
import com.thinkmobiles.easyerp.presentation.base.rules.MasterFlowSelectablePresenterHelper;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.OrderDH;

import java.util.ArrayList;
import java.util.List;

import rx.subscriptions.CompositeSubscription;

/**
 * @author Michael Soyma (Created on 2/1/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public class OrdersPresenter extends MasterFlowSelectablePresenterHelper<String, OrderDH> implements OrdersContract.OrdersPresenter {

    private OrdersContract.OrdersView view;
    private OrdersContract.OrdersModel model;

    private CompositeSubscription compositeSubscription;

    public OrdersPresenter(OrdersContract.OrdersView view, OrdersContract.OrdersModel model) {
        this.view = view;
        this.model = model;
        this.compositeSubscription = new CompositeSubscription();

        this.view.setPresenter(this);
    }

    @Override
    public void subscribe() {
        loadOrders(1);
    }

    @Override
    public void unsubscribe() {
        if (compositeSubscription.hasSubscriptions()) compositeSubscription.clear();
    }

    @Override
    public void loadOrders(int page) {
        final boolean needClear = page == 1;
        compositeSubscription.add(
                model.getOrders().subscribe(
                        responseGetOrders -> view.displayOrders(prepareOrderDHs(responseGetOrders.data), needClear),
                        throwable -> view.displayError(throwable.getMessage(), ErrorViewHelper.ErrorType.NETWORK)
                )
        );
    }

    @Override
    public void selectItem(OrderDH dh, int position) {
        if (position != getSelectedItemPosition()) {
            view.changeSelectedItem(getSelectedItemPosition(), position);
            setSelectedInfo(position, dh.getId());
            view.openOrderDetailsScreen(dh.getId());
        }

    }

    private ArrayList<OrderDH> prepareOrderDHs(final List<Order> orders) {
        int position = 0;
        final ArrayList<OrderDH> result = new ArrayList<>();
        for (Order order : orders) {
            final OrderDH orderDH = new OrderDH(order);
            makeSelectedDHIfNeed(orderDH, view, position++, true);
            result.add(orderDH);
        }
        return result;
    }

}
