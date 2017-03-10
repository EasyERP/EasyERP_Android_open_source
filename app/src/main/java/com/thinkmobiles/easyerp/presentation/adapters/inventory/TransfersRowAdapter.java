package com.thinkmobiles.easyerp.presentation.adapters.inventory;

import com.michenko.simpleadapter.SimpleRecyclerAdapter;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.holders.data.inventory.TransferRowDH;
import com.thinkmobiles.easyerp.presentation.holders.view.inventory.TransferRowVH;

import org.androidannotations.annotations.EBean;

/**
 * Created by Lynx on 3/10/2017.
 */

@EBean
public class TransfersRowAdapter extends SimpleRecyclerAdapter<TransferRowDH, TransferRowVH> {
    @Override
    protected int getItemLayout() {
        return R.layout.view_list_item_transfer_row;
    }
}
