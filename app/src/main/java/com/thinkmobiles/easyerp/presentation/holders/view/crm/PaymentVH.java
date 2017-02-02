package com.thinkmobiles.easyerp.presentation.holders.view.crm;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.view.View;
import android.widget.TextView;

import com.michenko.simpleadapter.OnCardClickListener;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.base.rules.MasterFlowSelectableVHHelper;
import com.thinkmobiles.easyerp.presentation.custom.RoundedBackgroundSpan;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.PaymentDH;
import com.thinkmobiles.easyerp.presentation.managers.DateManager;
import com.thinkmobiles.easyerp.presentation.managers.TagHelper;
import com.thinkmobiles.easyerp.presentation.screens.crm.dashboard.detail.charts.DollarFormatter;
import com.thinkmobiles.easyerp.presentation.utils.StringUtil;

/**
 * @author Michael Soyma (Created on 2/2/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public final class PaymentVH extends MasterFlowSelectableVHHelper<PaymentDH> {

    private final TextView tvPaymentCompany_VLIP;
    private final TextView tvPaymentType_VLIP;
    private final TextView tvAssignTo_VLIP;
    private final TextView tvPaymentDate_VLIP;
    private final TextView tvPaid_VLIP;

    private final String not_assigned;
    private final String no_data;

    public PaymentVH(View itemView, @Nullable OnCardClickListener listener, int viewType) {
        super(itemView, listener, viewType);

        tvPaymentCompany_VLIP = findView(R.id.tvPaymentCompany_VLIP);
        tvPaymentType_VLIP = findView(R.id.tvPaymentType_VLIP);
        tvAssignTo_VLIP = findView(R.id.tvAssignTo_VLIP);
        tvPaymentDate_VLIP = findView(R.id.tvPaymentDate_VLIP);
        tvPaid_VLIP = findView(R.id.tvPaid_VLIP);

        not_assigned = itemView.getContext().getString(R.string.not_assigned);
        no_data = itemView.getContext().getString(R.string.no_data);
    }

    @Override
    public void bindData(PaymentDH data) {
        super.bindData(data);

        tvPaymentCompany_VLIP.setText(data.getPayment().supplier == null ? no_data : data.getPayment().supplier.name.getFullName());
        tvPaymentDate_VLIP.setText(new DateManager.DateConverter(data.getPayment().date).setDstPattern(DateManager.PATTERN_DATE_SIMPLE_PREVIEW).toString());
        tvAssignTo_VLIP.setText(data.getPayment().assigned == null ? not_assigned : data.getPayment().assigned.name.getFullName());
        tvPaid_VLIP.setText(StringUtil.getFormattedPriceFromCent(new DollarFormatter().getFormat(),
                (data.getPayment().refund ? -1 : 1) * data.getPayment().paidAmount,
                data.getPayment().currency.id != null ? data.getPayment().currency.symbol : "$"));
        tvPaymentType_VLIP.setText(buildStatusTag(data.getPayment().refund ? "Refund" : "Payment", TagHelper.getColorResIdByName("")));

        tvPaymentCompany_VLIP.requestLayout();
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
