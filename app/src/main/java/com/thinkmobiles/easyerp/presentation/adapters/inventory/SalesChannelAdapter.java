package com.thinkmobiles.easyerp.presentation.adapters.inventory;

import com.michenko.simpleadapter.SimpleRecyclerAdapter;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.holders.data.inventory.SalesChannelDH;
import com.thinkmobiles.easyerp.presentation.holders.view.inventory.SalesChannelVH;

import org.androidannotations.annotations.EBean;

/**
 * Created by samson on 14.03.17.
 */
@EBean
public class SalesChannelAdapter extends SimpleRecyclerAdapter<SalesChannelDH, SalesChannelVH> {
    @Override
    protected int getItemLayout() {
        return R.layout.view_list_item_product_sales_channel;
    }
}
