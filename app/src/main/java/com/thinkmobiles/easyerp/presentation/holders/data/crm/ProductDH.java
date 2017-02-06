package com.thinkmobiles.easyerp.presentation.holders.data.crm;

import com.michenko.simpleadapter.RecyclerDH;
import com.thinkmobiles.easyerp.data.model.crm.order.detail.OrderProduct;

/**
 * @author Alex Michenko (Created on 01.02.17).
 *         Company: Thinkmobiles
 *         Email: alex.michenko@thinkmobiles.com
 */

public final class ProductDH extends RecyclerDH {

    private final OrderProduct model;
    private final String symbol;

    public ProductDH(OrderProduct model, String symbol) {
        this.model = model;
        this.symbol = symbol;
    }

    public OrderProduct getModel() {
        return model;
    }

    public String getSymbol() {
        return symbol;
    }
}
