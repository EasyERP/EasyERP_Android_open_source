package com.thinkmobiles.easyerp.presentation.adapters.inventory;

import com.michenko.simpleadapter.SimpleRecyclerAdapter;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.holders.data.inventory.OrderRowDH;
import com.thinkmobiles.easyerp.presentation.holders.view.inventory.StockReturnsOrderRowVH;

import org.androidannotations.annotations.EBean;

/**
 * Created by samson on 09.03.17.
 */
@EBean
public class StockReturnsOrderRowAdapter extends SimpleRecyclerAdapter<OrderRowDH, StockReturnsOrderRowVH> {
    @Override
    protected int getItemLayout() {
        return R.layout.view_list_item_returns_order_row;
    }
}
