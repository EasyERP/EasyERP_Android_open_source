package com.thinkmobiles.easyerp.presentation.holders.data.inventory;

import com.michenko.simpleadapter.RecyclerDH;
import com.thinkmobiles.easyerp.data.model.inventory.product.detail.InventoryItem;

/**
 * Created by samson on 14.03.17.
 */

public final class StockInventoryDH extends RecyclerDH {

    private InventoryItem model;

    public StockInventoryDH(InventoryItem model) {
        this.model = model;
    }

    public InventoryItem getModel() {
        return model;
    }
}
