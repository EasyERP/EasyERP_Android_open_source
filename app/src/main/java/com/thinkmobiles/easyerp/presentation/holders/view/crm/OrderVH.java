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
    }

    @Override
    public void bindData(OrderDH data) {
        tvOrderName_VLIO.setText(data.getOrderItem().name);
        tvOrderStatus_VLIO.setText(buildStatusTag(data.getOrderItem().workflow.name));
        tvCustomer_VLIO.setText(buildStatusTag(data.getOrderItem().supplier.name));
        tvCreatedDate_VLIO.setText(buildStatusTag(new DateManager.DateConverter(data.getOrderItem().orderDate).setDstPattern(DateManager.PATTERN_OUT_PERSON_DOB).toString()));
        tvTotalPrice_VLIO.setText(buildStatusTag(String.format("%s %s", data.getOrderItem().currency.id.symbol, new DollarFormatter().getFormat().format(data.getOrderItem().paymentInfo.total))));

//        ivAllocated_VLIO.setImageResource(data.getOrderItem().status);
        // TODO need change status model|array - wtf ?
        ivAllocated_VLIO.setImageResource(R.drawable.ic_allocated);
        ivFulfilled_VLIO.setImageResource(R.drawable.ic_fulfilled);
        ivShipped_VLIO.setImageResource(R.drawable.ic_shipped);
    }

    private SpannableStringBuilder buildStatusTag(String status) {
        SpannableStringBuilder stringBuilder = new SpannableStringBuilder();
        stringBuilder.append(" ");
        stringBuilder.append(status.toUpperCase());
        stringBuilder.append("  ");
        RoundedBackgroundSpan tagSpan = new RoundedBackgroundSpan(itemView.getContext(), TagHelper.getColorResIdByName(status), Color.WHITE);
        stringBuilder.setSpan(tagSpan, 0, stringBuilder.length() - 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return stringBuilder;
    }

}
