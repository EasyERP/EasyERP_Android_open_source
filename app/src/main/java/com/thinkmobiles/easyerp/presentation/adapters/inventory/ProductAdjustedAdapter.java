package com.thinkmobiles.easyerp.presentation.adapters.inventory;

import com.michenko.simpleadapter.SimpleRecyclerAdapter;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.holders.data.inventory.OrderRowDH;
import com.thinkmobiles.easyerp.presentation.holders.view.inventory.ProductAdjustedVH;

import org.androidannotations.annotations.EBean;

/**
 * Created by samson on 10.03.17.
 */
@EBean
public class ProductAdjustedAdapter extends SimpleRecyclerAdapter<OrderRowDH, ProductAdjustedVH> {
    @Override
    protected int getItemLayout() {
        return R.layout.view_list_item_product_adjusted;
    }
}
