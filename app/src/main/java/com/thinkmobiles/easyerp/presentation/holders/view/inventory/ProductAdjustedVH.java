package com.thinkmobiles.easyerp.presentation.holders.view.inventory;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.michenko.simpleadapter.OnCardClickListener;
import com.michenko.simpleadapter.RecyclerVH;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.holders.data.inventory.OrderRowDH;

/**
 * Created by samson on 10.03.17.
 */

public final class ProductAdjustedVH extends RecyclerVH<OrderRowDH> {

    private final TextView tvProduct_LIPA;
    private final TextView tvAdjustedBy_LIPA;

    public ProductAdjustedVH(View itemView, @Nullable OnCardClickListener listener, int viewType) {
        super(itemView, listener, viewType);
        tvProduct_LIPA = findView(R.id.tvProduct_LIPA);
        tvAdjustedBy_LIPA = findView(R.id.tvAdjustedBy_LIPA);
    }

    @Override
    public void bindData(OrderRowDH data) {
        tvProduct_LIPA.setText(data.getModel().product.name);
        tvAdjustedBy_LIPA.setText(String.valueOf(data.getModel().quantity));
    }
}
