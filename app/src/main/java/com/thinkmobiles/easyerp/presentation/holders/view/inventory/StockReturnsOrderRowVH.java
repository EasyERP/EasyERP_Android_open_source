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

    private final TextView tvNumber_LISROR;
    private final TextView tvName_LISROR;
    private final TextView tvSku_LISROR;
    private final TextView tvCost_LISROR;
    private final TextView tvNote_LISROR;
    private final TextView tvWarehouse_LISROR;
    private final TextView tvQty_LISROR;

    public StockReturnsOrderRowVH(View itemView, @Nullable OnCardClickListener listener, int viewType) {
        super(itemView, listener, viewType);

        tvNumber_LISROR = findView(R.id.tvNumber_LISROR);
        tvName_LISROR = findView(R.id.tvName_LISROR);
        tvSku_LISROR = findView(R.id.tvSku_LISROR);
        tvCost_LISROR = findView(R.id.tvCost_LISROR);
        tvNote_LISROR = findView(R.id.tvNote_LISROR);
        tvWarehouse_LISROR = findView(R.id.tvWarehouse_LISROR);
        tvQty_LISROR = findView(R.id.tvQty_LISROR);
    }

    @Override
    public void bindData(OrderRowDH data) {
        OrderRow model = data.getModel();
        tvNumber_LISROR.setText(String.valueOf(getAdapterPosition() + 1));
        if (model.product != null && model.product.info != null) {
            tvName_LISROR.setText(model.product.name);
            tvSku_LISROR.setText(model.product.info.SKU);
        }
        tvCost_LISROR.setText(String.valueOf(model.cost));
        if (model.goodsOutNote != null) {
            tvNote_LISROR.setText(model.goodsOutNote.name);
        }
        if (model.warehouse != null) {
            tvWarehouse_LISROR.setText(String.valueOf(model.warehouse.name));
        }
        tvQty_LISROR.setText(String.valueOf(model.quantity));
    }
}
