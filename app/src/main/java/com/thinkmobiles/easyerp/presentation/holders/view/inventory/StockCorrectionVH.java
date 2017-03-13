package com.thinkmobiles.easyerp.presentation.holders.view.inventory;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.michenko.simpleadapter.OnCardClickListener;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.data.model.inventory.stock_correction.StockCorrection;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectableVHHelper;
import com.thinkmobiles.easyerp.presentation.holders.data.inventory.StockCorrectionDH;
import com.thinkmobiles.easyerp.presentation.managers.DateManager;

/**
 * @author Michael Soyma (Created on 3/7/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public final class StockCorrectionVH extends SelectableVHHelper<StockCorrectionDH> {

    private TextView tvReason_VLISC, tvWarehouse_VLISC, tvCreatedDate_VLISC;

    private final String createdDateFormatter;

    public StockCorrectionVH(View itemView, @Nullable OnCardClickListener listener, int viewType) {
        super(itemView, listener, viewType);

        tvReason_VLISC = findView(R.id.tvReason_VLISC);
        tvWarehouse_VLISC = findView(R.id.tvWarehouse_VLISC);
        tvCreatedDate_VLISC = findView(R.id.tvCreatedDate_VLISC);

        createdDateFormatter = itemView.getResources().getString(R.string.pattern_created_date);
    }

    @Override
    public void bindData(StockCorrectionDH data) {
        super.bindData(data);

        final StockCorrection stockCorrection = data.getStockCorrection();
        tvReason_VLISC.setText(TextUtils.isEmpty(stockCorrection.description) ? "-" : stockCorrection.description);
        tvWarehouse_VLISC.setText(stockCorrection.warehouse.name);

        String out = String.format(createdDateFormatter, DateManager.getDateToNow(stockCorrection.createdBy.date), DateManager.getTime(stockCorrection.createdBy.date));
        if(!TextUtils.isEmpty(stockCorrection.createdBy.user.login))
            out = out + " by " + stockCorrection.createdBy.user.login;
        tvCreatedDate_VLISC.setText(out);
    }
}
