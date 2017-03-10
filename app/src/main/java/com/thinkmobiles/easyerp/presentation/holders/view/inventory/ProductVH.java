package com.thinkmobiles.easyerp.presentation.holders.view.inventory;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.michenko.simpleadapter.OnCardClickListener;
import com.squareup.picasso.Picasso;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.data.model.inventory.product.Product;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectableVHHelper;
import com.thinkmobiles.easyerp.presentation.holders.data.inventory.ProductDH;
import com.thinkmobiles.easyerp.presentation.utils.StringUtil;

/**
 * @author Michael Soyma (Created on 3/10/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public final class ProductVH extends SelectableVHHelper<ProductDH> {

    private TextView tvProductName_VLIIP, tvProductType_VLIIP, tvProductSKU_VLIIP, tvProductCategory_VLIIP;
    private ImageView ivProductImage_VLIIP;

    public ProductVH(View itemView, @Nullable OnCardClickListener listener, int viewType) {
        super(itemView, listener, viewType);

        tvProductName_VLIIP = findView(R.id.tvProductName_VLIIP);
        tvProductType_VLIIP = findView(R.id.tvProductType_VLIIP);
        tvProductSKU_VLIIP = findView(R.id.tvProductSKU_VLIIP);
        tvProductCategory_VLIIP = findView(R.id.tvProductCategory_VLIIP);
        ivProductImage_VLIIP = findView(R.id.ivProductImage_VLIIP);
    }

    @Override
    public void bindData(ProductDH data) {
        super.bindData(data);

        final Product product = data.getProduct();
        tvProductName_VLIIP.setText(product.name);
        tvProductType_VLIIP.setText(product.ProductTypesName);
        tvProductSKU_VLIIP.setText(
                String.format("%s (%s %s)",
                        product.info.SKU,
                        product.variantsCount.count,
                        itemView.getResources().getQuantityString(R.plurals.variants, product.variantsCount.count)));
        tvProductCategory_VLIIP.setText(TextUtils.join(" • ", product.ProductCategories));

        Picasso.with(itemView.getContext())
                .load(StringUtil.getImageURL(product.imageSrc))
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .into(ivProductImage_VLIIP);
    }
}
