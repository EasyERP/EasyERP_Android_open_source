package com.thinkmobiles.easyerp.presentation.holders.data.crm;

import com.michenko.simpleadapter.RecyclerDH;
import com.thinkmobiles.easyerp.data.model.crm.order.detail.OrderProduct;

/**
 * @author Alex Michenko (Created on 01.02.17).
 *         Company: Thinkmobiles
 *         Email: alex.michenko@thinkmobiles.com
 */

public final class OrderProductDH extends RecyclerDH {

    private final OrderProduct model;

    public OrderProductDH(OrderProduct model) {
        this.model = model;
    }

    public OrderProduct getModel() {
        return model;
    }
}
