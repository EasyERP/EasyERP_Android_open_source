package com.thinkmobiles.easyerp.presentation.holders.view.inventory;

import android.support.annotation.Nullable;
import android.view.View;

import com.michenko.simpleadapter.OnCardClickListener;
import com.michenko.simpleadapter.RecyclerVH;
import com.thinkmobiles.easyerp.presentation.holders.data.inventory.ProductVariantDH;

/**
 * Created by samson on 16.03.17.
 */

public class ProductVariantHeaderVH extends RecyclerVH<ProductVariantDH> {

    public ProductVariantHeaderVH(View itemView, @Nullable OnCardClickListener listener, int viewType) {
        super(itemView, listener, viewType);
    }

    @Override
    public void bindData(ProductVariantDH data) {

    }
}
