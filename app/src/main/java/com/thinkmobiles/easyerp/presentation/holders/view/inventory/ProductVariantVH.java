package com.thinkmobiles.easyerp.presentation.holders.view.inventory;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.michenko.simpleadapter.OnCardClickListener;
import com.michenko.simpleadapter.RecyclerVH;
import com.squareup.picasso.Picasso;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.data.model.inventory.product.detail.ProductVariant;
import com.thinkmobiles.easyerp.data.model.inventory.product.detail.Variant;
import com.thinkmobiles.easyerp.presentation.holders.data.inventory.ProductVariantDH;
import com.thinkmobiles.easyerp.presentation.utils.Constants;
import com.thinkmobiles.easyerp.presentation.utils.StringUtil;

/**
 * Created by samson on 15.03.17.
 */

public class ProductVariantVH extends RecyclerVH<ProductVariantDH> {

    private final ImageView ivImage_LIPV;
    private final TextView tvName_LIPV;
    private final TextView tvSKU_LIPV;
    private final TextView tvVariant_LIPV;
    private final TextView tvChannel_LIPV;

    public ProductVariantVH(View itemView, @Nullable OnCardClickListener listener, int viewType) {
        super(itemView, listener, viewType);
        ivImage_LIPV = findView(R.id.ivImage_LIPV);
        tvName_LIPV = findView(R.id.tvName_LIPV);
        tvSKU_LIPV = findView(R.id.tvSKU_LIPV);
        tvVariant_LIPV = findView(R.id.tvVariant_LIPV);
        tvChannel_LIPV = findView(R.id.tvChannel_LIPV);
    }

    @Override
    public void bindData(ProductVariantDH data) {
        itemView.setBackgroundResource(getAdapterPosition() % 2 == 1 ? R.color.color_bg_product_details : android.R.color.white);
        itemView.setSelected(data.isSelected());
        ProductVariant model = data.getModel();
        Picasso.with(itemView.getContext())
                .load(StringUtil.getImageURL(model.imageSrc))
                .into(ivImage_LIPV);
        tvName_LIPV.setText(model.name);
        tvSKU_LIPV.setText(model.info.SKU);
        StringBuilder builder = new StringBuilder();
        for (Variant variant : model.variants.get(0)) {
            builder.append("/").append(variant.value);
        }
        if (builder.length() > 0) builder.deleteCharAt(0);
        tvVariant_LIPV.setText(builder);
        if (!model.channels.isEmpty()) {
            tvChannel_LIPV.setText(model.channels.get(0).name);
        } else {
            tvChannel_LIPV.setText(null);
        }
    }
}
