package com.thinkmobiles.easyerp.presentation.holders.view.crm;

import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.michenko.simpleadapter.OnCardClickListener;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.base.rules.MasterFlowSelectableVHHelper;
import com.thinkmobiles.easyerp.presentation.custom.RoundRectDrawable;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.OrderDH;
import com.thinkmobiles.easyerp.presentation.managers.DateManager;
import com.thinkmobiles.easyerp.presentation.managers.TagHelper;
import com.thinkmobiles.easyerp.presentation.screens.crm.dashboard.detail.charts.DollarFormatter;
import com.thinkmobiles.easyerp.presentation.utils.StringUtil;

/**
 * @author Michael Soyma (Created on 1/31/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public final class OrderVH extends MasterFlowSelectableVHHelper<OrderDH> {

    private final TextView tvOrderName_VLIO;
    private final TextView tvOrderStatus_VLIO;
    private final TextView tvCustomer_VLIO;
    private final TextView tvCreatedDate_VLIO;
    private final TextView tvTotalPrice_VLIO;
    private final ImageView ivAllocated_VLIO;
    private final ImageView ivFulfilled_VLIO;
    private final ImageView ivShipped_VLIO;

    private final String not_assigned;

    public OrderVH(View itemView, @Nullable OnCardClickListener listener, int viewType) {
        super(itemView, listener, viewType);

        tvOrderName_VLIO = findView(R.id.tvOrderName_VLIO);
        tvOrderStatus_VLIO = findView(R.id.tvOrderStatus_VLIO);
        tvCustomer_VLIO = findView(R.id.tvCustomer_VLIO);
        tvCreatedDate_VLIO = findView(R.id.tvCreatedDate_VLIO);
        tvTotalPrice_VLIO = findView(R.id.tvTotalPrice_VLIO);

        ivAllocated_VLIO = findView(R.id.ivAllocated_VLIO);
        ivFulfilled_VLIO = findView(R.id.ivFulfilled_VLIO);
        ivShipped_VLIO = findView(R.id.ivShipped_VLIO);

        not_assigned = itemView.getContext().getString(R.string.not_assigned);
    }

    @Override
    public void bindData(OrderDH data) {
        super.bindData(data);

        tvOrderName_VLIO.setText(data.getOrder().name);
        tvOrderStatus_VLIO.setText(data.getOrder().workflow.name.toUpperCase());
        tvOrderStatus_VLIO.setBackgroundDrawable(
                new RoundRectDrawable(ContextCompat.getColor(itemView.getContext(), TagHelper.getColorResIdByName(data.getOrder().workflow.status))));

        tvCustomer_VLIO.setText(TextUtils.isEmpty(data.getOrder().supplier.name) ? not_assigned : data.getOrder().supplier.name);
        tvCreatedDate_VLIO.setText(new DateManager.DateConverter(data.getOrder().orderDate).setDstPattern(DateManager.PATTERN_DATE_SIMPLE_PREVIEW).toString());
        tvTotalPrice_VLIO.setText(StringUtil.getFormattedPriceFromCent(new DollarFormatter().getFormat(),
                data.getOrder().paymentInfo.total,
                data.getOrder().currency.id != null ? data.getOrder().currency.id.symbol : "$"));

        tvOrderName_VLIO.requestLayout();

        switch (data.getOrder().status.allocateStatus) {
            case "NOT":
                ivAllocated_VLIO.setImageResource(R.drawable.ic_allocated_off);
                break;
            case "NOR":
                ivAllocated_VLIO.setImageResource(R.drawable.ic_allocated_middle_on);
                break;
            case "ALL":
                ivAllocated_VLIO.setImageResource(R.drawable.ic_allocated);
                break;
        }

        switch (data.getOrder().status.fulfillStatus) {
            case "NOT":
                ivFulfilled_VLIO.setImageResource(R.drawable.ic_fulfilled_off);
                break;
            case "NOR":
                ivFulfilled_VLIO.setImageResource(R.drawable.ic_fulfilled_middle_on);
                break;
            case "ALL":
                ivFulfilled_VLIO.setImageResource(R.drawable.ic_fulfilled);
                break;
        }

        switch (data.getOrder().status.shippingStatus) {
            case "NOT":
                ivShipped_VLIO.setImageResource(R.drawable.ic_shipped_off);
                break;
            case "NOR":
                ivShipped_VLIO.setImageResource(R.drawable.ic_shipped_middle_on);
                break;
            case "ALL":
                ivShipped_VLIO.setImageResource(R.drawable.ic_shipped);
                break;
        }
    }
}
