package com.thinkmobiles.easyerp.presentation.screens.crm.orders;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.domain.crm.OrderRepository;
import com.thinkmobiles.easyerp.presentation.adapters.crm.OrdersAdapter;
import com.thinkmobiles.easyerp.presentation.base.rules.ErrorViewHelper;
import com.thinkmobiles.easyerp.presentation.base.rules.MasterFlowListFragment;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.OrderDH;
import com.thinkmobiles.easyerp.presentation.listeners.EndlessRecyclerViewScrollListener;
import com.thinkmobiles.easyerp.presentation.screens.crm.orders.details.OrderDetailsFragment_;

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
@EFragment(R.layout.fragment_simple_list_with_swipe_refresh)
public class OrdersFragment extends MasterFlowListFragment implements OrdersContract.OrdersView {

    private OrdersContract.OrdersPresenter presenter;
    private EndlessRecyclerViewScrollListener scrollListener;
    private LinearLayoutManager recyclerLayoutManager;

    @Bean
    protected OrderRepository orderRepository;
    @Bean
    protected OrdersAdapter ordersAdapter;
    @Bean
    protected ErrorViewHelper errorViewHelper;

    @StringRes(R.string.list_is_empty)
    protected String string_list_is_empty;

    @ViewById(R.id.llErrorLayout)
    protected View errorLayout;

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
        errorViewHelper.init(errorLayout, view -> retryLoadWithProgressBar());
        recyclerLayoutManager = new LinearLayoutManager(mActivity);
        scrollListener = new EndlessRecyclerViewScrollListener(recyclerLayoutManager, presenter.getCurrentPage()) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                displayProgress(true);
                presenter.loadOrders(page);
            }
        };
        listRecycler.setLayoutManager(recyclerLayoutManager);
        listRecycler.setAdapter(ordersAdapter);
        listRecycler.addOnScrollListener(scrollListener);
        ordersAdapter.setOnCardClickListener((view, position, viewType) -> presenter.selectItem(ordersAdapter.getItem(position), position));

        presenter.subscribe();
    }

    private void retryLoadWithProgressBar() {
        showProgress(true);
        presenter.loadOrders(1);
    }

    @Override
    public void onRefresh() {
        errorViewHelper.hideError();
        scrollListener.resetState();
        presenter.subscribe();
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
        showProgress(false);
        swipeContainer.setRefreshing(false);

        if (needClear)
            ordersAdapter.setListDH(orderDHs);
        else ordersAdapter.addListDH(orderDHs);

        if (getCountItemsNow() == 0)
            displayError(null, ErrorViewHelper.ErrorType.LIST_EMPTY);
    }

    @Override
    public void displayError(String msg, ErrorViewHelper.ErrorType errorType) {
        displayProgress(false);
        swipeContainer.setRefreshing(false);

        final String resultMsg = errorType.equals(ErrorViewHelper.ErrorType.LIST_EMPTY) ? string_list_is_empty : msg;
        if (getCountItemsNow() == 0)
            errorViewHelper.showErrorMsg(resultMsg, errorType);
        else Toast.makeText(mActivity, resultMsg, Toast.LENGTH_LONG).show();
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
    public void showProgress(boolean isShow) {
        errorViewHelper.hideError();
        displayProgress(isShow);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.unsubscribe();
    }
}
