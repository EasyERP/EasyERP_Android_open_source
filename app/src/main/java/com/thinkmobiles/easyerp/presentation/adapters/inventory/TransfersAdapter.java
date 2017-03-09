package com.thinkmobiles.easyerp.presentation.adapters.inventory;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectableAdapter;
import com.thinkmobiles.easyerp.presentation.holders.data.inventory.TransferDH;
import com.thinkmobiles.easyerp.presentation.holders.view.inventory.TransferVH;

import org.androidannotations.annotations.EBean;

/**
 * Created by Lynx on 3/7/2017.
 */

@EBean
public class TransfersAdapter extends SelectableAdapter<TransferDH, TransferVH> {
    @Override
    protected int getItemLayout() {
        return R.layout.view_list_item_transfer;
    }
}
