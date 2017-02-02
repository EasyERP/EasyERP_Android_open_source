package com.thinkmobiles.easyerp.presentation.holders.view.crm;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.michenko.simpleadapter.OnCardClickListener;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.base.rules.MasterFlowSelectableVHHelper;
import com.thinkmobiles.easyerp.presentation.custom.RoundedBackgroundSpan;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.InvoiceDH;
import com.thinkmobiles.easyerp.presentation.managers.DateManager;
import com.thinkmobiles.easyerp.presentation.managers.TagHelper;
import com.thinkmobiles.easyerp.presentation.screens.crm.dashboard.detail.charts.DollarFormatter;
import com.thinkmobiles.easyerp.presentation.utils.StringUtil;

/**
 * @author Michael Soyma (Created on 2/2/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public final class InvoiceVH extends MasterFlowSelectableVHHelper<InvoiceDH> {

    private final TextView tvInvoiceCustomer_VLII;
    private final TextView tvInvoiceStatus_VLII;
    private final TextView tvAssignTo_VLII;
    private final TextView tvInvoiceDate_VLII;
    private final TextView tvTotalPrice_VLII;

    private final String not_assigned;

    public InvoiceVH(View itemView, @Nullable OnCardClickListener listener, int viewType) {
        super(itemView, listener, viewType);

        tvInvoiceCustomer_VLII = findView(R.id.tvInvoiceCustomer_VLII);
        tvInvoiceStatus_VLII = findView(R.id.tvInvoiceStatus_VLII);
        tvAssignTo_VLII = findView(R.id.tvAssignTo_VLII);
        tvInvoiceDate_VLII = findView(R.id.tvInvoiceDate_VLII);
        tvTotalPrice_VLII = findView(R.id.tvTotalPrice_VLII);

        not_assigned = itemView.getContext().getString(R.string.not_assigned);
    }

    @Override
    public void bindData(InvoiceDH data) {
        super.bindData(data);

        tvInvoiceCustomer_VLII.setText(TextUtils.isEmpty(data.getInvoice().supplier.name) ? not_assigned : data.getInvoice().supplier.name);
        tvInvoiceDate_VLII.setText(new DateManager.DateConverter(data.getInvoice().invoiceDate).setDstPattern(DateManager.PATTERN_DATE_SIMPLE_PREVIEW).toString());
        tvAssignTo_VLII.setText(TextUtils.isEmpty(data.getInvoice().salesPerson.name) ? not_assigned : data.getInvoice().salesPerson.name);
        tvTotalPrice_VLII.setText(StringUtil.getFormattedPriceFromCent(new DollarFormatter().getFormat(),
                data.getInvoice().paymentInfo.total,
                data.getInvoice().currency.id != null ? data.getInvoice().currency.id.symbol : "$"));
        tvInvoiceStatus_VLII.setText(buildStatusTag(data.getInvoice().workflow.name, TagHelper.getColorResIdByName(data.getInvoice().workflow.name)));

        tvInvoiceCustomer_VLII.requestLayout();
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
