package com.thinkmobiles.easyerp.presentation.holders.view.inventory;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.michenko.simpleadapter.OnCardClickListener;
import com.michenko.simpleadapter.RecyclerVH;
import com.squareup.picasso.Picasso;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.holders.data.inventory.ProductImageDH;

/**
 * Created by samson on 14.03.17.
 */

public final class ProductImageVH extends RecyclerVH<ProductImageDH> {

    private final ImageView ivProductImage_LISI;

    public ProductImageVH(View itemView, @Nullable OnCardClickListener listener, int viewType) {
        super(itemView, listener, viewType);
        ivProductImage_LISI = findView(R.id.ivProductImage_LISI);
    }

    @Override
    public void bindData(ProductImageDH data) {
        Picasso.with(ivProductImage_LISI.getContext())
                .load(data.getUrl())
                .placeholder(R.drawable.placeholder)
                .into(ivProductImage_LISI);
    }
}
