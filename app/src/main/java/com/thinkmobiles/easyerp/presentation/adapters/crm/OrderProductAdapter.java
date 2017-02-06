package com.thinkmobiles.easyerp.presentation.adapters.crm;

import com.michenko.simpleadapter.SimpleRecyclerAdapter;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.OrderProductDH;
import com.thinkmobiles.easyerp.presentation.holders.view.crm.OrderProductVH;

import org.androidannotations.annotations.EBean;

/**
 * @author Alex Michenko (Created on 01.02.17).
 *         Company: Thinkmobiles
 *         Email: alex.michenko@thinkmobiles.com
 */

@EBean
public class OrderProductAdapter extends SimpleRecyclerAdapter<OrderProductDH, OrderProductVH> {

    @Override
    protected int getItemLayout() {
        return R.layout.view_list_item_order_product;
    }

    @Override
    public int getItemViewType(int position) {
        return --position;
    }
}
