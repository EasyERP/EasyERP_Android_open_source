package com.thinkmobiles.easyerp.presentation.adapters.inventory;

import com.michenko.simpleadapter.TypedRecyclerAdapter;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.holders.data.inventory.ProductVariantDH;
import com.thinkmobiles.easyerp.presentation.holders.view.inventory.ProductVariantHeaderVH;
import com.thinkmobiles.easyerp.presentation.holders.view.inventory.ProductVariantVH;

import org.androidannotations.annotations.EBean;

/**
 * Created by samson on 16.03.17.
 */
@EBean
public class ProductVariantAdapter extends TypedRecyclerAdapter<ProductVariantDH> {

    @Override
    protected void initViewTypes() {
        addType(0, R.layout.view_list_item_product_variant_header, ProductVariantHeaderVH.class);
        addType(1, R.layout.view_list_item_product_variant, ProductVariantVH.class);
    }

    @Override
    protected int getViewType(int position) {
        return position == 0 ? 0 : 1;
    }
}
