package com.thinkmobiles.easyerp.presentation.holders.view.crm;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.michenko.simpleadapter.OnCardClickListener;
import com.michenko.simpleadapter.RecyclerVH;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.data.model.crm.invoice.detail.InvoicePayment;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.InvoicePaymentDH;
import com.thinkmobiles.easyerp.presentation.managers.DateManager;
import com.thinkmobiles.easyerp.presentation.screens.crm.dashboard.detail.charts.DollarFormatter;
import com.thinkmobiles.easyerp.presentation.utils.StringUtil;

import java.text.Format;

/**
 * @author Alex Michenko (Created on 07.02.17).
 *         Company: Thinkmobiles
 *         Email: alex.michenko@thinkmobiles.com
 */

public final class InvoicePaymentVH extends RecyclerVH<InvoicePaymentDH> {

    private final TextView tvPaymentName_LIIP;
    private final TextView tvPaymentDate_LIIP;
    private final TextView tvPaymentAmount_LIIP;

    private Format formatter;

    public InvoicePaymentVH(View itemView, @Nullable OnCardClickListener listener, int viewType) {
        super(itemView, listener, viewType);

        tvPaymentName_LIIP = findView(R.id.tvPaymentName_LIIP);
        tvPaymentDate_LIIP = findView(R.id.tvPaymentDate_LIIP);
        tvPaymentAmount_LIIP = findView(R.id.tvPaymentAmount_LIIP);

        formatter = new DollarFormatter().getFormat();
    }

    @Override
    public void bindData(InvoicePaymentDH data) {
        InvoicePayment model = data.getModel();
        String symbol = model.currency.id != null ? model.currency.id.symbol : "S";

        tvPaymentName_LIIP.setText(model.name);
        tvPaymentDate_LIIP.setText(DateManager.convert(model.date).setDstPattern(DateManager.PATTERN_DATE_SIMPLE_PREVIEW).toString());
        tvPaymentAmount_LIIP.setText(StringUtil.getFormattedPriceFromCent(formatter, model.paidAmount, symbol));

    }
}
