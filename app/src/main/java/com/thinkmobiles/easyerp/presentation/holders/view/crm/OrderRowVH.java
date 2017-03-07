package com.thinkmobiles.easyerp.presentation.holders.view.crm;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.michenko.simpleadapter.OnCardClickListener;
import com.michenko.simpleadapter.RecyclerVH;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.data.model.inventory.goods_out_notes.details.OrderRow;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.OrderRowDH;

/**
 * Created by samson on 07.03.17.
 */

public class OrderRowVH extends RecyclerVH<OrderRowDH> {

    private TextView tvNumber_LIOR;
    private TextView tvName_LIOR;
    private TextView tvSku_LIOR;
    private TextView tvLocation_LIOR;
    private TextView tvShipped_LIOR;
    private TextView tvShipment_LIOR;
    private TextView tvFollow_LIOR;


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
        OrderRow model = data.getModel();
        tvNumber_LIOR.setText(String.valueOf(getAdapterPosition() + 1));
        tvName_LIOR.setText(model.description);
        if (model.product != null && model.product.info != null)
            tvSku_LIOR.setText(model.product.info.SKU);
        if (model.locationDeliver != null)
            tvLocation_LIOR.setText(model.locationDeliver.get(0).name);
        tvShipped_LIOR.setText(String.valueOf(model.shipped));
        tvShipment_LIOR.setText(null);
        tvFollow_LIOR.setText(model.quantity);
    }
}
