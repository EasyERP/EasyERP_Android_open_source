package com.thinkmobiles.easyerp.presentation.adapters.inventory;

import com.michenko.simpleadapter.SimpleRecyclerAdapter;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.holders.data.inventory.OrderRowDH;
import com.thinkmobiles.easyerp.presentation.holders.view.inventory.OrderRowVH;

import org.androidannotations.annotations.EBean;

/**
 * Created by samson on 07.03.17.
 */
@EBean
public class OrderRowAdapter extends SimpleRecyclerAdapter<OrderRowDH, OrderRowVH> {
    @Override
    protected int getItemLayout() {
        return R.layout.view_list_item_order_row;
    }
}
