package com.thinkmobiles.easyerp.presentation.screens.crm.orders;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.domain.crm.OrderRepository;
import com.thinkmobiles.easyerp.presentation.adapters.crm.OrdersAdapter;
import com.thinkmobiles.easyerp.presentation.base.ListRefreshFragment;
import com.thinkmobiles.easyerp.presentation.base.rules.ErrorViewHelper;
import com.thinkmobiles.easyerp.presentation.base.rules.MasterFlowListFragment;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.OrderDH;
import com.thinkmobiles.easyerp.presentation.listeners.EndlessRecyclerViewScrollListener;
import com.thinkmobiles.easyerp.presentation.screens.crm.orders.details.OrderDetailsFragment_;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringRes;

import java.util.ArrayList;

/**
 * @author Michael Soyma (Created on 2/1/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
@EFragment
public class OrdersFragment extends ListRefreshFragment implements OrdersContract.OrdersView {

    private OrdersContract.OrdersPresenter presenter;

    @Bean
    protected OrderRepository orderRepository;
    @Bean
    protected OrdersAdapter ordersAdapter;

    @AfterInject
    @Override
    public void initPresenter() {
        new OrdersPresenter(this, orderRepository);
    }

    @Override
    public void setPresenter(OrdersContract.OrdersPresenter presenter) {
        this.presenter = presenter;
    }

    @AfterViews
    protected void initUI() {
        listRecycler.setAdapter(ordersAdapter);
        ordersAdapter.setOnCardClickListener((view, position, viewType) -> presenter.selectItem(ordersAdapter.getItem(position), position));

        presenter.subscribe();
    }

    @Override
    protected void onLoadNextPage() {
        presenter.loadNextPage();
    }

    @Override
    protected void onRetry() {
        presenter.subscribe();
    }

    @Override
    public void onRefreshData() {
        super.onRefreshData();
        presenter.refresh();
    }

    @Override
    public int getCountItemsNow() {
        return ordersAdapter.getItemCount();
    }

    @Override
    public void changeSelectedItem(int oldPosition, int newPosition) {
        ordersAdapter.replaceSelectedItem(oldPosition, newPosition);
    }

    @Override
    public void displayOrders(ArrayList<OrderDH> orderDHs, boolean needClear) {
        if (needClear) {
            ordersAdapter.setListDH(orderDHs);
        } else {
            ordersAdapter.addListDH(orderDHs);
        }
    }

    @Override
    public void displayErrorState(String msg, ErrorViewHelper.ErrorType errorType) {
        showErrorState(msg, errorType);
    }

    @Override
    public void displayErrorToast(String msg) {
        showErrorToast(msg);
    }

    @Override
    public void showProgress(Constants.ProgressType type) {
        showProgressBar(type);
    }

    @Override
    public void openOrderDetailsScreen(String orderID) {
        if (orderID != null) {
            mActivity.replaceFragmentContentDetail(OrderDetailsFragment_.builder()
                    .orderId(orderID)
                    .build());
        } else {
            mActivity.replaceFragmentContentDetail(null);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.unsubscribe();
    }

    @Override
    public void clearSelectedItem() {
        presenter.clearSelectedInfo();
    }
}
