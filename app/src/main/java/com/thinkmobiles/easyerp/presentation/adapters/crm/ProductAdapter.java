package com.thinkmobiles.easyerp.presentation.adapters.crm;

import com.michenko.simpleadapter.SimpleRecyclerAdapter;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.ProductDH;
import com.thinkmobiles.easyerp.presentation.holders.view.crm.ProductVH;

import org.androidannotations.annotations.EBean;

/**
 * @author Alex Michenko (Created on 01.02.17).
 *         Company: Thinkmobiles
 *         Email: alex.michenko@thinkmobiles.com
 */

@EBean
public class ProductAdapter extends SimpleRecyclerAdapter<ProductDH, ProductVH> {

    @Override
    protected int getItemLayout() {
        return R.layout.view_list_item_order_product;
    }

}
