package com.thinkmobiles.easyerp.presentation.holders.data.inventory;

import com.michenko.simpleadapter.RecyclerDH;
import com.thinkmobiles.easyerp.data.model.inventory.transfers.details.TransferRowItem;

/**
 * Created by Lynx on 3/9/2017.
 */

public class TransferRowDH extends RecyclerDH {

    private TransferRowItem item;

    public TransferRowDH(TransferRowItem item) {
        this.item = item;
    }

    public TransferRowItem getItem() {
        return item;
    }
}
