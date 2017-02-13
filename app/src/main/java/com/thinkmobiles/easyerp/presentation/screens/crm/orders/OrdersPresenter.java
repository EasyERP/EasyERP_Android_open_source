package com.thinkmobiles.easyerp.presentation.screens.crm.orders;

import com.thinkmobiles.easyerp.data.model.crm.order.Order;
import com.thinkmobiles.easyerp.presentation.base.rules.ErrorViewHelper;
import com.thinkmobiles.easyerp.presentation.base.rules.MasterFlowSelectablePresenterHelper;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.OrderDH;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

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

    private int currentPage = 1;
    private int totalItems;
    private ArrayList<Order> orders = new ArrayList<>();

    public OrdersPresenter(OrdersContract.OrdersView view, OrdersContract.OrdersModel model) {
        this.view = view;
        this.model = model;
        this.compositeSubscription = new CompositeSubscription();

        this.view.setPresenter(this);
    }

    @Override
    public void subscribe() {
        if (orders.size() == 0) {
            view.showProgress(Constants.ProgressType.CENTER);
            refresh();
        } else view.displayOrders(prepareOrderDHs(orders, true), true);
    }

    @Override
    public void refresh() {
        loadOrders(1);
    }

    @Override
    public void loadNextPage() {
        if(view.getCountItemsNow() == totalItems) {
            return;
        }
        view.showProgress(Constants.ProgressType.BOTTOM);
        loadOrders(currentPage + 1);
    }

    @Override
    public void unsubscribe() {
        if (compositeSubscription.hasSubscriptions())
            compositeSubscription.clear();
    }

    public void loadOrders(int page) {
        final boolean needClear = page == 1;
        compositeSubscription.add(
                model.getOrders(page).subscribe(
                        responseGetOrders -> {
                            currentPage = page;
                            totalItems = responseGetOrders.total;
                            saveData(responseGetOrders.data, needClear);
                            if (orders.isEmpty()) {
                                view.displayErrorState(null, ErrorViewHelper.ErrorType.LIST_EMPTY);
                            } else {
                                view.showProgress(Constants.ProgressType.NONE);
                                view.displayOrders(prepareOrderDHs(responseGetOrders.data, needClear), needClear);
                            }
                        },
                        throwable -> {
                            if (orders.isEmpty()) {
                                view.displayErrorState(throwable.getMessage(), ErrorViewHelper.ErrorType.NETWORK);
                            } else {
                                view.displayErrorToast(throwable.getMessage());
                            }
                        }
                )
        );
    }

    private void saveData(final List<Order> orders, boolean needClear) {
        if (needClear)
            this.orders.clear();
        this.orders.addAll(orders);
    }

    @Override
    public void selectItem(OrderDH dh, int position) {
        if (super.selectItem(dh, position, view))
            view.openOrderDetailsScreen(dh.getId());
    }

    private ArrayList<OrderDH> prepareOrderDHs(final List<Order> orders, boolean needClear) {
        int position = 0;
        final ArrayList<OrderDH> result = new ArrayList<>();
        for (Order order : orders) {
            final OrderDH orderDH = new OrderDH(order);
            makeSelectedDHIfNeed(orderDH, view, position++, needClear);
            result.add(orderDH);
        }
        selectFirstElementIfNeed(result, view);
        return result;
    }
}
