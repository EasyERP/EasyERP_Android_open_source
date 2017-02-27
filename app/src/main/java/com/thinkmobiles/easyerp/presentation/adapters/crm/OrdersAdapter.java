package com.thinkmobiles.easyerp.presentation.adapters.crm;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectableAdapter;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.OrderDH;
import com.thinkmobiles.easyerp.presentation.holders.view.crm.OrderVH;

import org.androidannotations.annotations.EBean;

/**
 * Created by Lynx on 1/16/2017.
 */

@EBean
public class OrdersAdapter extends SelectableAdapter<OrderDH, OrderVH> {

    @Override
    protected int getItemLayout() {
        return R.layout.view_list_item_order;
    }

}
