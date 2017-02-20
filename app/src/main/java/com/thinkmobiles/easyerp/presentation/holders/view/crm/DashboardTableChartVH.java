package com.thinkmobiles.easyerp.presentation.holders.view.crm;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.michenko.simpleadapter.OnCardClickListener;
import com.michenko.simpleadapter.RecyclerVH;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.DashboardTableChartDH;
import com.thinkmobiles.easyerp.presentation.managers.DateManager;
import com.thinkmobiles.easyerp.presentation.screens.crm.dashboard.detail.charts.DollarFormatter;
import com.thinkmobiles.easyerp.presentation.utils.StringUtil;

import java.text.DecimalFormat;

/**
 * @author Michael Soyma (Created on 2/14/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public final class DashboardTableChartVH extends RecyclerVH<DashboardTableChartDH> {

    private final TextView tvTitle_VCOTI, tvTransactionDate_VCOTI, tvTotalValue_VCOTI;
    private final String not_assigned;
    private final DecimalFormat totalFormat;

    public DashboardTableChartVH(View itemView, @Nullable OnCardClickListener listener, int viewType) {
        super(itemView, listener, viewType);
        tvTitle_VCOTI = findView(R.id.tvTitle_VCOTI);
        tvTransactionDate_VCOTI = findView(R.id.tvTransactionDate_VCOTI);
        tvTotalValue_VCOTI = findView(R.id.tvTotalValue_VCOTI);

        totalFormat = new DollarFormatter().getFormat();
        not_assigned = itemView.getResources().getString(R.string.not_assigned);
    }

    @Override
    public void bindData(DashboardTableChartDH data) {
        tvTitle_VCOTI.setText(TextUtils.isEmpty(data.getInvoice().supplier.name) ? not_assigned : data.getInvoice().supplier.name);
        tvTransactionDate_VCOTI.setText(new DateManager.DateConverter(data.getInvoice().invoiceDate).setDstPattern(DateManager.PATTERN_DASHBOARD_DAY_VIEW).toString());
        tvTotalValue_VCOTI.setText(StringUtil.getFormattedPriceFromCent(totalFormat, data.getInvoice().paymentInfo.total, data.getInvoice().currency.id.symbol));
    }
}
