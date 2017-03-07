package com.thinkmobiles.easyerp.presentation.holders.view.inventory;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.michenko.simpleadapter.OnCardClickListener;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.data.model.inventory.stock_returns.StockReturn;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectableVHHelper;
import com.thinkmobiles.easyerp.presentation.holders.data.inventory.StockReturnDH;
import com.thinkmobiles.easyerp.presentation.managers.DateManager;
import com.thinkmobiles.easyerp.presentation.screens.crm.dashboard.detail.charts.DollarFormatter;
import com.thinkmobiles.easyerp.presentation.utils.StringUtil;

/**
 * @author Michael Soyma (Created on 3/6/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public class StockReturnVH extends SelectableVHHelper<StockReturnDH> {

    private TextView tvName_VLIST, tvOrder_VLIST, tvReceivedByDate_VLIST, tvTotal_VLIST;
    private ImageView ivStatus_VLIST;

    private final String receivedDateFormatter;

    public StockReturnVH(View itemView, @Nullable OnCardClickListener listener, int viewType) {
        super(itemView, listener, viewType);

        tvName_VLIST            = findView(R.id.tvName_VLIST);
        tvTotal_VLIST           = findView(R.id.tvTotal_VLIST);
        tvOrder_VLIST           = findView(R.id.tvOrder_VLIST);
        tvReceivedByDate_VLIST  = findView(R.id.tvReceivedByDate_VLIST);
        ivStatus_VLIST          = findView(R.id.ivStatus_VLIST);

        receivedDateFormatter = itemView.getContext().getString(R.string.pattern_received_date);
    }

    @Override
    public void bindData(StockReturnDH data) {
        super.bindData(data);
        final StockReturn stockReturn = data.getStockReturn();

        tvName_VLIST.setText(stockReturn.name);
        tvOrder_VLIST.setText(stockReturn.order.name);
        tvTotal_VLIST.setText(StringUtil.getFormattedPriceFromCent(new DollarFormatter().getFormat(),
                stockReturn.order.paymentInfo.total,
                !TextUtils.isEmpty(stockReturn.order.currency.symbol) ? stockReturn.order.currency.symbol : "$"));

        String out = String.format(receivedDateFormatter, DateManager.getDateToNow(stockReturn.status.receivedOn), DateManager.getTime(stockReturn.status.receivedOn));
        if(!TextUtils.isEmpty(stockReturn.status.receivedById.login))
            out = out + " by " + stockReturn.status.receivedById.login;
        tvReceivedByDate_VLIST.setText(out);
        ivStatus_VLIST.setImageResource(stockReturn.id == null ? R.drawable.ic_allocated_off : R.drawable.ic_allocated);
    }
}
