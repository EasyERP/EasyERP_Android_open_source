package com.thinkmobiles.easyerp.presentation.holders.data.inventory;

import com.thinkmobiles.easyerp.data.model.inventory.goods_out_notes.GoodOutNoteItem;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectableDHHelper;

/**
 * Created by Lynx on 3/7/2017.
 */

public class GoodOutNoteDH extends SelectableDHHelper {

    private GoodOutNoteItem item;

    public GoodOutNoteDH(GoodOutNoteItem item) {
        this.item = item;
    }

    public GoodOutNoteItem getItem() {
        return item;
    }

    @Override
    public String getId() {
        return item._id;
    }
}
