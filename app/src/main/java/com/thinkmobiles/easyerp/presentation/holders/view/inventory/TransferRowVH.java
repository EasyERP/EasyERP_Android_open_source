package com.thinkmobiles.easyerp.presentation.holders.view.inventory;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.michenko.simpleadapter.OnCardClickListener;
import com.michenko.simpleadapter.RecyclerVH;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.data.model.inventory.transfers.details.TransferRowItem;
import com.thinkmobiles.easyerp.presentation.holders.data.inventory.TransferRowDH;

/**
 * Created by Lynx on 3/9/2017.
 */

public class TransferRowVH extends RecyclerVH<TransferRowDH> {

    private TextView tvNumber_VLITR;
    private TextView tvName_VLITR;
    private TextView tvSku_VLITR;
    private TextView tvShip_VLITR;
    private TextView tvLocationsFrom_VLITR;
    private TextView tvLocationsTo_VLITR;

    public TransferRowVH(View itemView, @Nullable OnCardClickListener listener, int viewType) {
        super(itemView, listener, viewType);

        tvNumber_VLITR = findView(R.id.tvNumber_VLITR);
        tvName_VLITR = findView(R.id.tvName_VLITR);
        tvSku_VLITR = findView(R.id.tvSku_VLITR);
        tvShip_VLITR = findView(R.id.tvShip_VLITR);
        tvLocationsFrom_VLITR = findView(R.id.tvLocationsFrom_VLITR);
        tvLocationsTo_VLITR = findView(R.id.tvLocationsTo_VLITR);
    }

    @Override
    public void bindData(TransferRowDH data) {
        TransferRowItem item = data.getItem();
        tvNumber_VLITR.setText(String.valueOf(getAdapterPosition() + 1));
        if(item.product != null && !TextUtils.isEmpty(item.product.name)) {
        tvName_VLITR.setText(item.product.name);
        } else {
            tvName_VLITR.setText(null);
        }
        if(item.product != null && item.product.info != null && !TextUtils.isEmpty(item.product.info.SKU)) {
            tvSku_VLITR.setText(item.product.info.SKU);
        } else {
            tvSku_VLITR.setText(null);
        }
        tvShip_VLITR.setText(String.valueOf(item.quantity));
        if(item.locationsReceived != null && !item.locationsReceived.isEmpty() && item.locationsReceived.get(0).location != null && !TextUtils.isEmpty(item.locationsReceived.get(0).location.name)) {
            tvLocationsFrom_VLITR.setText(item.locationsReceived.get(0).location.name);
        } else {
            tvLocationsFrom_VLITR.setText(null);
        }
        if(item.locationsDeliver != null && !item.locationsDeliver.isEmpty() && !TextUtils.isEmpty(item.locationsDeliver.get(0).name)) {
            tvLocationsTo_VLITR.setText(item.locationsDeliver.get(0).name);
        } else {
            tvLocationsTo_VLITR.setText(null);
        }
    }
}
