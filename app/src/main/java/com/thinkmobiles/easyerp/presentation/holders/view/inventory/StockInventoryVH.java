package com.thinkmobiles.easyerp.presentation.holders.view.inventory;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.michenko.simpleadapter.OnCardClickListener;
import com.michenko.simpleadapter.RecyclerVH;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.holders.data.inventory.StockInventoryDH;

/**
 * Created by samson on 14.03.17.
 */

public final class StockInventoryVH extends RecyclerVH<StockInventoryDH> {

    private final TextView tvName_LISI;
    private final TextView tvStock_LISI;
    private final TextView tvFulfilled_LISI;
    private final TextView tvHand_LISI;
    private final TextView tvIncoming_LISI;
    private final TextView tvLocation_LISI;

    public StockInventoryVH(View itemView, @Nullable OnCardClickListener listener, int viewType) {
        super(itemView, listener, viewType);
        tvName_LISI = findView(R.id.tvName_LISI);
        tvStock_LISI = findView(R.id.tvStock_LISI);
        tvFulfilled_LISI = findView(R.id.tvFulfilled_LISI);
        tvHand_LISI = findView(R.id.tvHand_LISI);
        tvIncoming_LISI = findView(R.id.tvIncoming_LISI);
        tvLocation_LISI = findView(R.id.tvLocation_LISI);
    }

    @Override
    public void bindData(StockInventoryDH data) {
        itemView.setBackgroundResource(getAdapterPosition() % 2 == 0 ? R.color.color_bg_product_details : android.R.color.white);
        tvName_LISI.setText(data.getModel().warehouse);
        tvStock_LISI.setText(String.valueOf(data.getModel().onHand));
        tvFulfilled_LISI.setText(String.valueOf(data.getModel().allocated));
        tvHand_LISI.setText(String.valueOf(data.getModel().available));
        tvIncoming_LISI.setText(String.valueOf(data.getModel().incoming));
        tvLocation_LISI.setText(data.getModel().location);
    }
}
