package com.thinkmobiles.easyerp.presentation.holders.view.crm;

import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.michenko.simpleadapter.OnCardClickListener;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectableVHHelper;
import com.thinkmobiles.easyerp.presentation.custom.RoundRectDrawable;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.PaymentDH;
import com.thinkmobiles.easyerp.presentation.managers.ColorHelper;
import com.thinkmobiles.easyerp.presentation.managers.DateManager;
import com.thinkmobiles.easyerp.presentation.screens.crm.dashboard.detail.charts.DollarFormatter;
import com.thinkmobiles.easyerp.presentation.utils.StringUtil;

/**
 * @author Michael Soyma (Created on 2/2/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public final class PaymentVH extends SelectableVHHelper<PaymentDH> {

    private final TextView tvSourceDocument_VLIP;
    private final TextView tvPaymentCompany_VLIP;
    private final TextView tvPaymentType_VLIP;
    private final TextView tvAssignTo_VLIP;
    private final TextView tvPaymentDate_VLIP;
    private final TextView tvPaid_VLIP;

    private final String not_assigned;
    private final String no_data;
    private final String paymentDateFormatter;

    public PaymentVH(View itemView, @Nullable OnCardClickListener listener, int viewType) {
        super(itemView, listener, viewType);

        tvSourceDocument_VLIP = findView(R.id.tvSourceDocument_VLIP);
        tvPaymentCompany_VLIP = findView(R.id.tvPaymentCompany_VLIP);
        tvPaymentType_VLIP = findView(R.id.tvPaymentType_VLIP);
        tvAssignTo_VLIP = findView(R.id.tvAssignTo_VLIP);
        tvPaymentDate_VLIP = findView(R.id.tvPaymentDate_VLIP);
        tvPaid_VLIP = findView(R.id.tvPaid_VLIP);

        not_assigned = itemView.getContext().getString(R.string.not_assigned);
        no_data = itemView.getContext().getString(R.string.no_data);
        paymentDateFormatter = itemView.getContext().getString(R.string.pattern_payment_date);
    }

    @Override
    public void bindData(PaymentDH data) {
        super.bindData(data);

        tvSourceDocument_VLIP.setText(data.getPayment().name);
        tvPaymentCompany_VLIP.setText(data.getPayment().supplier == null ? no_data : data.getPayment().supplier.name.getFullName());
        tvPaymentDate_VLIP.setText(String.format(paymentDateFormatter, new DateManager.DateConverter(data.getPayment().date).setDstPattern(DateManager.PATTERN_DATE_MONTH_PREVIEW).toString()));
        //TODO: NullPointerException
        tvAssignTo_VLIP.setText(data.getPayment().assigned == null || data.getPayment().assigned.name == null
                ? not_assigned : data.getPayment().assigned.name.getFullName());
        tvPaid_VLIP.setText(StringUtil.getFormattedPriceFromCent(new DollarFormatter().getFormat(),
                (data.getPayment().refund ? -1 : 1) * data.getPayment().paidAmount,
                data.getPayment().currency.id != null ? data.getPayment().currency.symbol : "$"));

        final String paymentType = data.getPayment().refund ? "Refund" : "Payment";
        tvPaymentType_VLIP.setText(paymentType);
        tvPaymentType_VLIP.setBackgroundDrawable(new RoundRectDrawable(ContextCompat.getColor(itemView.getContext(), ColorHelper.getColorResIdByName(paymentType))));
    }
}
