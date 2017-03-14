package com.thinkmobiles.easyerp.presentation.holders.data.inventory;

import com.michenko.simpleadapter.RecyclerDH;
import com.thinkmobiles.easyerp.data.model.inventory.BaseOrderRow;
import com.thinkmobiles.easyerp.data.model.inventory.goods_out_notes.details.OrderRow;

/**
 * Created by samson on 07.03.17.
 */

public final class OrderRowDH extends RecyclerDH {

    private BaseOrderRow model;

    public OrderRowDH(BaseOrderRow model) {
        this.model = model;
    }

    public BaseOrderRow getModel() {
        return model;
    }

    public void setModel(BaseOrderRow model) {
        this.model = model;
    }
}
