package com.thinkmobiles.easyerp.presentation.holders.view.inventory;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.michenko.simpleadapter.OnCardClickListener;
import com.michenko.simpleadapter.RecyclerVH;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.data.model.inventory.goods_out_notes.details.OrderRow;
import com.thinkmobiles.easyerp.presentation.holders.data.inventory.OrderRowDH;

/**
 * Created by samson on 09.03.17.
 */

public final class StockReturnsOrderRowVH extends RecyclerVH<OrderRowDH> {

    private final TextView tvNumber_LIOR;
    private final TextView tvName_LIOR;
    private final TextView tvSku_LIOR;
    private final TextView tvCost_LIOR;
    private final TextView tvNote_LIOR;
    private final TextView tvWarehouse_LIOR;
    private final TextView tvQty_LIOR;

    public StockReturnsOrderRowVH(View itemView, @Nullable OnCardClickListener listener, int viewType) {
        super(itemView, listener, viewType);

        tvNumber_LIOR = findView(R.id.tvNumber_LIOR);
        tvName_LIOR = findView(R.id.tvName_LIOR);
        tvSku_LIOR = findView(R.id.tvSku_LIOR);
        tvCost_LIOR = findView(R.id.tvCost_LIOR);
        tvNote_LIOR = findView(R.id.tvNote_LIOR);
        tvWarehouse_LIOR = findView(R.id.tvWarehouse_LIOR);
        tvQty_LIOR = findView(R.id.tvQty_LIOR);
    }

    @Override
    public void bindData(OrderRowDH data) {
        OrderRow model = data.getModel();
        tvNumber_LIOR.setText(String.valueOf(getAdapterPosition() + 1));
        if (model.product != null && model.product.info != null) {
            tvName_LIOR.setText(model.product.name);
            tvSku_LIOR.setText(model.product.info.SKU);
        }
        tvCost_LIOR.setText(String.valueOf(model.cost));
        if (model.goodsOutNote != null) {
            tvNote_LIOR.setText(model.goodsOutNote.name);
        }
        if (model.warehouse != null) {
            tvWarehouse_LIOR.setText(String.valueOf(model.warehouse.name));
        }
        tvQty_LIOR.setText(String.valueOf(model.quantity));
    }
}
