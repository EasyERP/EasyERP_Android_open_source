package com.thinkmobiles.easyerp.presentation.holders.view.crm;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.michenko.simpleadapter.OnCardClickListener;
import com.michenko.simpleadapter.RecyclerVH;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.custom.RoundedBackgroundSpan;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.OrderDH;
import com.thinkmobiles.easyerp.presentation.managers.DateManager;
import com.thinkmobiles.easyerp.presentation.managers.TagHelper;
import com.thinkmobiles.easyerp.presentation.screens.crm.dashboard.detail.charts.DollarFormatter;
import com.thinkmobiles.easyerp.presentation.utils.StringUtil;

/**
 * Created by Lynx on 1/16/2017.
 */

public class OrderVH extends RecyclerVH<OrderDH> {

    private TextView tvOrderName_VLIO;
    private TextView tvOrderStatus_VLIO;
    private TextView tvCustomer_VLIO;
    private TextView tvCreatedDate_VLIO;
    private TextView tvTotalPrice_VLIO;
    private ImageView ivAllocated_VLIO;
    private ImageView ivFulfilled_VLIO;
    private ImageView ivShipped_VLIO;
    private View containerItemLayout;

    public OrderVH(View itemView, @Nullable OnCardClickListener listener, int viewType) {
        super(itemView, listener, viewType);

        containerItemLayout = itemView;

        tvOrderName_VLIO = findView(R.id.tvOrderName_VLIO);
        tvOrderStatus_VLIO = findView(R.id.tvOrderStatus_VLIO);
        tvCustomer_VLIO = findView(R.id.tvCustomer_VLIO);
        tvCreatedDate_VLIO = findView(R.id.tvCreatedDate_VLIO);
        tvTotalPrice_VLIO = findView(R.id.tvTotalPrice_VLIO);

        ivAllocated_VLIO = findView(R.id.ivAllocated_VLIO);
        ivFulfilled_VLIO = findView(R.id.ivFulfilled_VLIO);
        ivShipped_VLIO = findView(R.id.ivShipped_VLIO);
    }

    @Override
    public void bindData(OrderDH data) {

        containerItemLayout.setSelected(data.isSelected());

        tvOrderName_VLIO.setText(data.getOrder().name);
        tvOrderStatus_VLIO.setText(buildStatusTag(data.getOrder().workflow.name, TagHelper.getColorResIdByName(data.getOrder().workflow.status)));
        tvCustomer_VLIO.setText(data.getOrder().supplier.name);
        tvCreatedDate_VLIO.setText(new DateManager.DateConverter(data.getOrder().orderDate).setDstPattern(DateManager.PATTERN_OUT_PERSON_DOB).toString());
        tvTotalPrice_VLIO.setText(StringUtil.getFormattedPrice(new DollarFormatter().getFormat(),
                data.getOrder().paymentInfo.total / 100,
                data.getOrder().currency.id != null ? data.getOrder().currency.id.symbol : "$"));

        switch (data.getOrder().status.allocateStatus) {
            case "NOT":
                ivAllocated_VLIO.setImageResource( R.drawable.ic_allocated_off);
                break;
            case "NOR":
                ivAllocated_VLIO.setImageResource( R.drawable.ic_allocated_middle_on);
                break;
            case "ALL":
                ivAllocated_VLIO.setImageResource( R.drawable.ic_allocated);
                break;
        }

        switch (data.getOrder().status.fulfillStatus) {
            case "NOT":
                ivFulfilled_VLIO.setImageResource( R.drawable.ic_fulfilled_off);
                break;
            case "NOR":
                ivFulfilled_VLIO.setImageResource( R.drawable.ic_fulfilled_middle_on);
                break;
            case "ALL":
                ivFulfilled_VLIO.setImageResource( R.drawable.ic_fulfilled);
                break;
        }

        switch (data.getOrder().status.shippingStatus) {
            case "NOT":
                ivShipped_VLIO.setImageResource( R.drawable.ic_shipped_off);
                break;
            case "NOR":
                ivShipped_VLIO.setImageResource( R.drawable.ic_shipped_middle_on);
                break;
            case "ALL":
                ivShipped_VLIO.setImageResource( R.drawable.ic_shipped);
                break;
        }
    }

    private SpannableStringBuilder buildStatusTag(String status, int bgColor) {
        SpannableStringBuilder stringBuilder = new SpannableStringBuilder();
        stringBuilder.append(" ");
        stringBuilder.append(status.toUpperCase());
        stringBuilder.append("  ");
        RoundedBackgroundSpan tagSpan = new RoundedBackgroundSpan(itemView.getContext(), bgColor, Color.WHITE);
        stringBuilder.setSpan(tagSpan, 0, stringBuilder.length() - 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return stringBuilder;
    }

}
