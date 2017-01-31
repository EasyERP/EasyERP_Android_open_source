package com.thinkmobiles.easyerp.presentation.holders.data.crm;

import com.thinkmobiles.easyerp.data.model.crm.dashboard.detail.order.OrderItem;
import com.thinkmobiles.easyerp.data.model.crm.leads.LeadItem;
import com.thinkmobiles.easyerp.presentation.base.rules.MasterFlowSelectableDHHelper;

/**
 * Created by Lynx on 1/16/2017.
 */

public class OrderDH extends MasterFlowSelectableDHHelper<String> {
    private OrderItem orderItem;

    public OrderDH(OrderItem orderItem) {
        this.orderItem = orderItem;
    }

    public OrderItem getOrderItem() {
        return orderItem;
    }

    @Override
    public String getId() {
        return orderItem.id;
    }

}
