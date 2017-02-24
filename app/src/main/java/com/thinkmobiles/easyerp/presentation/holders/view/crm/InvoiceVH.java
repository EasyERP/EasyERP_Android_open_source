package com.thinkmobiles.easyerp.presentation.holders.view.crm;

import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.michenko.simpleadapter.OnCardClickListener;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectableVHHelper;
import com.thinkmobiles.easyerp.presentation.custom.RoundRectDrawable;
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
public final class InvoiceVH extends SelectableVHHelper<InvoiceDH> {

    private final TextView tvInvoiceNumber_VLII;
    private final TextView tvInvoiceCustomer_VLII;
    private final TextView tvInvoiceStatus_VLII;
    private final TextView tvAssignTo_VLII;
    private final TextView tvInvoiceDate_VLII;
    private final TextView tvTotalPrice_VLII;

    private final String not_assigned;
    private final String invoicedDateFormatter;

    public InvoiceVH(View itemView, @Nullable OnCardClickListener listener, int viewType) {
        super(itemView, listener, viewType);

        tvInvoiceNumber_VLII = findView(R.id.tvInvoiceNumber_VLII);
        tvInvoiceCustomer_VLII = findView(R.id.tvInvoiceCustomer_VLII);
        tvInvoiceStatus_VLII = findView(R.id.tvInvoiceStatus_VLII);
        tvAssignTo_VLII = findView(R.id.tvAssignTo_VLII);
        tvInvoiceDate_VLII = findView(R.id.tvInvoiceDate_VLII);
        tvTotalPrice_VLII = findView(R.id.tvTotalPrice_VLII);

        not_assigned = itemView.getContext().getString(R.string.not_assigned);
        invoicedDateFormatter = itemView.getContext().getString(R.string.pattern_invoiced_date);
    }

    @Override
    public void bindData(InvoiceDH data) {
        super.bindData(data);

        tvInvoiceNumber_VLII.setText(data.getInvoice().name);
        tvInvoiceCustomer_VLII.setText(TextUtils.isEmpty(data.getInvoice().supplier.name) ? not_assigned : data.getInvoice().supplier.name);
        tvInvoiceDate_VLII.setText(String.format(invoicedDateFormatter, new DateManager.DateConverter(data.getInvoice().invoiceDate).setDstPattern(DateManager.PATTERN_DATE_SIMPLE_PREVIEW).toString()));
        tvAssignTo_VLII.setText(TextUtils.isEmpty(data.getInvoice().salesPerson.name) ? not_assigned : data.getInvoice().salesPerson.name);
        tvTotalPrice_VLII.setText(StringUtil.getFormattedPriceFromCent(new DollarFormatter().getFormat(),
                data.getInvoice().paymentInfo.total,
                data.getInvoice().currency.id != null ? data.getInvoice().currency.id.symbol : "$"));

        final String workflowName = data.getInvoice().workflow.name + ((!data.getInvoice().approved && data.getInvoice().workflow.name.equals("Unpaid")) ? " / Not Approved" : "");
        tvInvoiceStatus_VLII.setText(workflowName);
        tvInvoiceStatus_VLII.setBackgroundDrawable(new RoundRectDrawable(ContextCompat.getColor(itemView.getContext(), TagHelper.getColorResIdByName(workflowName))));
    }
}
