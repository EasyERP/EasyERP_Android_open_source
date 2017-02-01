package com.thinkmobiles.easyerp.presentation.holders.data.crm;

import com.thinkmobiles.easyerp.data.model.crm.orders.Order;
import com.thinkmobiles.easyerp.presentation.base.rules.MasterFlowSelectableDHHelper;

/**
 * Created by Lynx on 1/16/2017.
 */

public class OrderDH extends MasterFlowSelectableDHHelper<String> {
    private Order order;

    public OrderDH(Order order) {
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }

    @Override
    public String getId() {
        return order.id;
    }

}
