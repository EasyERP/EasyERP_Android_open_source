package com.thinkmobiles.easyerp.presentation.holders.data.inventory;

import com.michenko.simpleadapter.RecyclerDH;
import com.thinkmobiles.easyerp.data.model.inventory.goods_out_notes.details.OrderRow;

/**
 * Created by samson on 07.03.17.
 */

public class OrderRowDH extends RecyclerDH {

    private OrderRow model;

    public OrderRowDH(OrderRow model) {
        this.model = model;
    }

    public OrderRow getModel() {
        return model;
    }

    public void setModel(OrderRow model) {
        this.model = model;
    }
}
