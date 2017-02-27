package com.thinkmobiles.easyerp.presentation.screens.crm.orders;

import com.thinkmobiles.easyerp.data.model.crm.order.Order;
import com.thinkmobiles.easyerp.presentation.base.rules.master.filterable.FilterableModel;
import com.thinkmobiles.easyerp.presentation.base.rules.master.filterable.FilterableView;
import com.thinkmobiles.easyerp.presentation.base.rules.master.filterable.MasterFilterablePresenterHelper;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.OrderDH;
import com.thinkmobiles.easyerp.presentation.managers.ErrorManager;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Michael Soyma (Created on 2/1/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public class OrdersPresenter extends MasterFilterablePresenterHelper implements OrdersContract.OrdersPresenter {

    private OrdersContract.OrdersView view;
    private OrdersContract.OrdersModel model;

    private ArrayList<Order> orders = new ArrayList<>();

    public OrdersPresenter(OrdersContract.OrdersView view, OrdersContract.OrdersModel model) {
        this.view = view;
        this.model = model;

        this.view.setPresenter(this);
    }

    @Override
    protected FilterableView getView() {
        return view;
    }

    @Override
    protected FilterableModel getModel() {
        return model;
    }

    @Override
    protected void loadPage(int page) {
        final boolean needClear = page == 1;
        compositeSubscription.add(
                model.getOrders(helper, page).subscribe(
                        responseGetOrders -> {
                            currentPage = page;
                            totalItems = responseGetOrders.total;
                            saveData(responseGetOrders.data, needClear);
                            setData(responseGetOrders.data, needClear);
                        },  t -> error(t)));
    }

    @Override
    protected int getCountItems() {
        return orders.size();
    }

    @Override
    protected boolean hasContent() {
        return !orders.isEmpty();
    }

    @Override
    protected void retainInstance() {
        setData(orders, true);
    }

    @Override
    public void clickItem(int position) {
        String id = orders.get(position).id;
        if (super.selectItem(id, position))
            view.openDetailsScreen(id);
    }

    private void saveData(final List<Order> orders, boolean needClear) {
        if (needClear)
            this.orders.clear();
        this.orders.addAll(orders);
    }

    private void setData(final List<Order> payments, boolean needClear) {
        view.setDataList(prepareOrderDHs(payments, needClear), needClear);
        if (this.orders.isEmpty()) {
            view.displayErrorState(ErrorManager.getErrorType(null));
        } else {
            view.showProgress(Constants.ProgressType.NONE);
        }
    }

    private ArrayList<OrderDH> prepareOrderDHs(final List<Order> orders, boolean needClear) {
        int position = 0;
        final ArrayList<OrderDH> result = new ArrayList<>();
        for (Order order : orders) {
            final OrderDH orderDH = new OrderDH(order);
            makeSelectedDHIfNeed(orderDH, position++, needClear);
            result.add(orderDH);
        }
        selectFirstElementIfNeed(result);
        return result;
    }
}
