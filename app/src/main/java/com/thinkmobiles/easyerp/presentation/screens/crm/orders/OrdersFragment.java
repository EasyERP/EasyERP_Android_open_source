package com.thinkmobiles.easyerp.presentation.screens.crm.orders;

import com.thinkmobiles.easyerp.domain.crm.OrderRepository;
import com.thinkmobiles.easyerp.presentation.adapters.crm.OrdersAdapter;
import com.thinkmobiles.easyerp.presentation.base.rules.master.filterable.FilterablePresenter;
import com.thinkmobiles.easyerp.presentation.base.rules.master.filterable.MasterFilterableFragment;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectableAdapter;
import com.thinkmobiles.easyerp.presentation.base.rules.ErrorType;
import com.thinkmobiles.easyerp.presentation.base.rules.MasterFlowListSelectableFragment;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.FilterDH;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.OrderDH;
import com.thinkmobiles.easyerp.presentation.managers.GoogleAnalyticHelper;
import com.thinkmobiles.easyerp.presentation.screens.crm.orders.details.OrderDetailsFragment_;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;

/**
 * @author Michael Soyma (Created on 2/1/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
@EFragment
public class OrdersFragment extends MasterFilterableFragment implements OrdersContract.OrdersView {

    private OrdersContract.OrdersPresenter presenter;

    @Bean
    protected OrderRepository orderRepository;
    @Bean
    protected OrdersAdapter ordersAdapter;

    @AfterViews
    protected void initAnalytics() {
        GoogleAnalyticHelper.trackScreenView(this, getResources().getConfiguration());
    }

    @AfterInject
    @Override
    public void initPresenter() {
        new OrdersPresenter(this, orderRepository);
    }

    @Override
    public void setPresenter(OrdersContract.OrdersPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    protected FilterablePresenter getPresenter() {
        return presenter;
    }

    @Override
    protected SelectableAdapter getAdapter() {
        return ordersAdapter;
    }

    @Override
    public void openDetailsScreen(String orderID) {
        if (orderID != null) {
            mActivity.replaceFragmentContentDetail(OrderDetailsFragment_.builder()
                    .orderId(orderID)
                    .build());
        } else {
            mActivity.replaceFragmentContentDetail(null);
        }
    }

    @Override
    public String getScreenName() {
        return "Order list screen";
    }
}
