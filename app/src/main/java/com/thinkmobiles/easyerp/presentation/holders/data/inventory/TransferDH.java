package com.thinkmobiles.easyerp.presentation.holders.data.inventory;

import com.thinkmobiles.easyerp.data.model.inventory.transfers.details.TransferItem;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectableDHHelper;

/**
 * Created by Lynx on 3/7/2017.
 */

public final class TransferDH extends SelectableDHHelper {

    private TransferItem item;

    public TransferDH(TransferItem item) {
        this.item = item;
    }

    public TransferItem getItem() {
        return item;
    }

    @Override
    public String getId() {
        return item.id;
    }
}
