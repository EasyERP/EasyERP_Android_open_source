package com.thinkmobiles.easyerp.presentation.holders.view.inventory;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.michenko.simpleadapter.OnCardClickListener;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.data.model.inventory.transfers.TransferItem;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectableVHHelper;
import com.thinkmobiles.easyerp.presentation.holders.data.inventory.TransferDH;
import com.thinkmobiles.easyerp.presentation.managers.DateManager;

/**
 * Created by Lynx on 3/7/2017.
 */

public class TransferVH extends SelectableVHHelper<TransferDH> {

    private TextView tvTransferName_VLIT;
    private TextView tvDate_VLIT;
    private ImageView ivPrinted_VLIT;
    private ImageView ivPacked_VLIT;
    private ImageView ivShipped_VLIT;
    private ImageView ivReceived_VLIT;
    private TextView tvWarehouseFrom_VLIT;
    private TextView tvWarehouseTo_VLIT;
    private TextView tvDescription_VLIP;

    public TransferVH(View itemView, @Nullable OnCardClickListener listener, int viewType) {
        super(itemView, listener, viewType);

        tvTransferName_VLIT = findView(R.id.tvTransferName_VLIT);
        tvDate_VLIT = findView(R.id.tvDate_VLIT);
        ivPrinted_VLIT = findView(R.id. ivPrinted_VLIT);
        ivPacked_VLIT = findView(R.id. ivPacked_VLIT);
        ivShipped_VLIT = findView(R.id. ivShipped_VLIT);
        ivReceived_VLIT = findView(R.id. ivReceived_VLIT);
        tvWarehouseFrom_VLIT = findView(R.id.tvWarehouseFrom_VLIT);
        tvWarehouseTo_VLIT = findView(R.id.tvWarehouseTo_VLIT);
        tvDescription_VLIP = findView(R.id.tvDescription_VLIP);
    }

    @Override
    public void bindData(TransferDH data) {
        super.bindData(data);
        TransferItem item = data.getItem();

        tvTransferName_VLIT.setText(item.name);
        if(item.createdBy != null && !TextUtils.isEmpty(item.createdBy.date)) {
            tvDate_VLIT.setText(new DateManager.DateConverter(item.createdBy.date)
                    .setDstPattern(DateManager.PATTERN_DATE_SIMPLE_PREVIEW)
                    .toString());
        } else {
            tvDate_VLIT.setText(null);
        }
    }
}
