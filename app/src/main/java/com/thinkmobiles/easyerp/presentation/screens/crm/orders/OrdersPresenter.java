package com.thinkmobiles.easyerp.presentation.screens.crm.orders;

import com.thinkmobiles.easyerp.data.model.crm.order.Order;
import com.thinkmobiles.easyerp.presentation.base.rules.MasterFlowSelectablePresenterHelper;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.FilterDH;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.OrderDH;
import com.thinkmobiles.easyerp.presentation.managers.ErrorManager;
import com.thinkmobiles.easyerp.presentation.utils.Constants;
import com.thinkmobiles.easyerp.presentation.utils.filter.FilterHelper;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
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
    private FilterHelper helper;

    public OrdersPresenter(OrdersContract.OrdersView view, OrdersContract.OrdersModel model) {
        this.view = view;
        this.model = model;
        this.compositeSubscription = new CompositeSubscription();
        helper = new FilterHelper();

        this.view.setPresenter(this);
    }

    @Override
    public void subscribe() {
        if (orders.isEmpty() && !helper.isInitialized()) {
            getFirstPage();
            loadFilters();
        } else {
            setData(orders, true);
        }
    }

    private void getFirstPage() {
        view.showProgress(Constants.ProgressType.CENTER);
        refresh();
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

    private void loadFilters() {
        compositeSubscription.add(model.getFilters()
                .flatMap(responseFilters -> Observable.just(FilterHelper.create(responseFilters)))
                .subscribe(filterHelper -> {
                    helper = filterHelper;
                    view.createMenuFilters(helper);
                }, t -> {
                    view.displayErrorToast(ErrorManager.getErrorMessage(t));
                }));
    }

    @Override
    public void unsubscribe() {
        if (compositeSubscription.hasSubscriptions())
            compositeSubscription.clear();
    }

    public void loadOrders(int page) {
        final boolean needClear = page == 1;
        compositeSubscription.add(
                model.getOrders(helper, page).subscribe(
                        responseGetOrders -> {
                            currentPage = page;
                            totalItems = responseGetOrders.total;
                            saveData(responseGetOrders.data, needClear);
                            setData(responseGetOrders.data, needClear);
                        },
                        throwable -> {
                            if (orders.isEmpty()) {
                                view.displayErrorState(ErrorManager.getErrorType(throwable));
                            } else {
                                view.displayErrorToast(ErrorManager.getErrorMessage(throwable));
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

    private void setData(final List<Order> payments, boolean needClear) {
        view.displayOrders(prepareOrderDHs(payments, needClear), needClear);
        if (this.orders.isEmpty()) {
            view.displayErrorState(ErrorManager.getErrorType(null));
        } else {
            view.showProgress(Constants.ProgressType.NONE);
        }
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

    @Override
    public void filterBySearchItem(FilterDH filterDH) {
        helper.filterByItem(filterDH, (position, isVisible) -> view.selectFilter(position, isVisible));
        getFirstPage();
    }

    @Override
    public void filterBySearchText(String name) {
        helper.filterByText(name, (position, isVisible) -> view.selectFilter(position, isVisible));
        getFirstPage();
    }

    @Override
    public void filterByList(ArrayList<FilterDH> filterDHs, int requestCode) {
        helper.filterByList(filterDHs, requestCode, (position, isVisible) -> view.selectFilter(position, isVisible));
        getFirstPage();
    }

    @Override
    public void removeFilter(int requestCode) {
        helper.removeFilter(requestCode, (position, isVisible) -> view.selectFilter(position, isVisible));
        getFirstPage();
    }

    @Override
    public void changeFilter(int position, String filterName) {
        view.showFilterDialog(helper.getFilterList(position), position, filterName);
    }

    @Override
    public void buildOptionMenu() {
        view.createMenuFilters(helper);
        helper.setupMenu((position, isVisible) -> view.selectFilter(position, isVisible));
    }

    @Override
    public void removeAll() {
        helper.removeAllFilters((position, isVisible) -> view.selectFilter(position, isVisible));
        getFirstPage();
    }
}
