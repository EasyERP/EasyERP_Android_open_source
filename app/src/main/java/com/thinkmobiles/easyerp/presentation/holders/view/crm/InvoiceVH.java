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

/**
 * @author Michael Soyma (Created on 2/2/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public class InvoiceVH extends MasterFlowSelectableVHHelper<InvoiceDH> {

    private TextView tvInvoiceCustomer_VLII;
    private TextView tvInvoiceStatus_VLII;
    private TextView tvAssignTo_VLII;
    private TextView tvInvoiceDate_VLII;
    private TextView tvTotalPrice_VLII;

    private String noData;

    public InvoiceVH(View itemView, @Nullable OnCardClickListener listener, int viewType) {
        super(itemView, listener, viewType);

        tvInvoiceCustomer_VLII = findView(R.id.tvInvoiceCustomer_VLII);
        tvInvoiceStatus_VLII = findView(R.id.tvInvoiceStatus_VLII);
        tvAssignTo_VLII = findView(R.id.tvAssignTo_VLII);
        tvInvoiceDate_VLII = findView(R.id.tvInvoiceDate_VLII);
        tvTotalPrice_VLII = findView(R.id.tvTotalPrice_VLII);

        noData = itemView.getContext().getString(R.string.no_data);
    }

    @Override
    public void bindData(InvoiceDH data) {
        super.bindData(data);

        tvInvoiceCustomer_VLII.setText(TextUtils.isEmpty(data.getInvoice().supplier.name) ? noData : data.getInvoice().supplier.name);
        tvInvoiceDate_VLII.setText(new DateManager.DateConverter(data.getInvoice().invoiceDate).setDstPattern(DateManager.PATTERN_OUT_PERSON_DOB).toString());
        tvAssignTo_VLII.setText(TextUtils.isEmpty(data.getInvoice().salesPerson.name) ? noData : data.getInvoice().salesPerson.name);
        tvTotalPrice_VLII.setText(String.format("%s %s",
                data.getInvoice().currency.id != null ? data.getInvoice().currency.id.symbol : "$",
                new DollarFormatter().getFormat().format(data.getInvoice().paymentInfo.total)));
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
