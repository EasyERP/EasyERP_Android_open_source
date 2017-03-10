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
 * Created by samson on 07.03.17.
 */

public final class OrderRowVH extends RecyclerVH<OrderRowDH> {

    private final TextView tvNumber_LIOR;
    private final TextView tvName_LIOR;
    private final TextView tvSku_LIOR;
    private final TextView tvLocation_LIOR;
    private final TextView tvShipped_LIOR;
    private final TextView tvShipment_LIOR;
    private final TextView tvFollow_LIOR;


    public OrderRowVH(View itemView, @Nullable OnCardClickListener listener, int viewType) {
        super(itemView, listener, viewType);

        tvNumber_LIOR = findView(R.id.tvNumber_LIOR);
        tvName_LIOR = findView(R.id.tvName_LIOR);
        tvSku_LIOR = findView(R.id.tvSku_LIOR);
        tvLocation_LIOR = findView(R.id.tvLocation_LIOR);
        tvShipped_LIOR = findView(R.id.tvShipped_LIOR);
        tvShipment_LIOR = findView(R.id.tvShipment_LIOR);
        tvFollow_LIOR = findView(R.id.tvFollow_LIOR);
    }

    @Override
    public void bindData(OrderRowDH data) {
        OrderRow model = (OrderRow) data.getModel();
        tvNumber_LIOR.setText(String.valueOf(getAdapterPosition() + 1));
        tvName_LIOR.setText(model.description);
        if (model.product != null && model.product.info != null)
            tvSku_LIOR.setText(model.product.info.SKU);
        if (model.locationsDeliver != null)
            tvLocation_LIOR.setText(model.locationsDeliver.get(0).name);
        tvShipped_LIOR.setText(String.valueOf(model.shipped));
        tvShipment_LIOR.setText(String.valueOf(model.selectedQuantity));
        tvFollow_LIOR.setText(String.valueOf(model.quantity));
    }
}
