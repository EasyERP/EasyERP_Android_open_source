package com.thinkmobiles.easyerp.presentation.holders.view.crm;

import android.content.res.Resources;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.michenko.simpleadapter.OnCardClickListener;
import com.michenko.simpleadapter.RecyclerVH;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.data.model.crm.order.detail.OrderProduct;
import com.thinkmobiles.easyerp.presentation.base.rules.MasterFlowSelectableVHHelper;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.OrderProductDH;
import com.thinkmobiles.easyerp.presentation.screens.crm.dashboard.detail.charts.DollarFormatter;
import com.thinkmobiles.easyerp.presentation.utils.StringUtil;

import java.text.DecimalFormat;

/**
 * @author Alex Michenko (Created on 01.02.17).
 *         Company: Thinkmobiles
 *         Email: alex.michenko@thinkmobiles.com
 */

public final class OrderProductVH extends RecyclerVH<OrderProductDH> {

    private final TextView tvNumber_LIOP;
    private final TextView tvSku_LIOP;
    private final TextView tvInfo_LIOP;
    private final TextView tvQty_LIOP;
    private final TextView tvUnitPrice_LIOP;
    private final TextView tvTaxes_LIOP;
    private final TextView tvAmount_LIOP;

    private final Resources res;
    private final DecimalFormat formatter;
    private final int colorWhite;
    private final int colorBlack;

    public OrderProductVH(View itemView, @Nullable OnCardClickListener listener, int viewType) {
        super(itemView, listener, viewType);

        tvNumber_LIOP = findView(R.id.tvNumber_LIOP);
        tvSku_LIOP = findView(R.id.tvSku_LIOP);
        tvInfo_LIOP = findView(R.id.tvInfo_LIOP);
        tvQty_LIOP = findView(R.id.tvQty_LIOP);
        tvUnitPrice_LIOP = findView(R.id.tvUnitPrice_LIOP);
        tvTaxes_LIOP = findView(R.id.tvTaxes_LIOP);
        tvAmount_LIOP = findView(R.id.tvAmount_LIOP);

        res = itemView.getContext().getResources();
        int color = ContextCompat.getColor(
                itemView.getContext(),
                viewType == -1 ? R.color.bg_header_table_order_product : android.R.color.white
        );
        itemView.setBackgroundColor(color);

        colorWhite = ContextCompat.getColor(itemView.getContext(), android.R.color.white);
        colorBlack = ContextCompat.getColor(itemView.getContext(), android.R.color.black);

        formatter = new DollarFormatter().getFormat();
    }

    @Override
    public void bindData(OrderProductDH data) {
        if (data != null) {
            OrderProduct model = data.getModel();
            tvNumber_LIOP.setText(String.valueOf(getAdapterPosition()));
            if (model.product != null && model.product.info != null)
                tvSku_LIOP.setText(model.product.info.SKU);
            else {
                tvSku_LIOP.setText("");
            }
            tvInfo_LIOP.setText(model.description);
            tvQty_LIOP.setText(String.valueOf(model.quantity.floatValue()));

            tvUnitPrice_LIOP.setText(StringUtil.getFormattedPriceFromCent(formatter, model.unitPrice));
            tvTaxes_LIOP.setText(StringUtil.getFormattedPriceFromCent(formatter, model.totalTaxes));
            tvAmount_LIOP.setText(StringUtil.getFormattedPriceFromCent(formatter, model.subTotal));
        } else {
            Typeface typeface = Typeface.create("sans-serif-condensed", Typeface.BOLD);
            int colorWhite = ContextCompat.getColor(itemView.getContext(), android.R.color.white);

            tvNumber_LIOP.setTextColor(colorWhite);
            tvSku_LIOP.setTextColor(colorWhite);
            tvInfo_LIOP.setTextColor(colorWhite);
            tvQty_LIOP.setTextColor(colorWhite);
            tvUnitPrice_LIOP.setTextColor(colorWhite);
            tvTaxes_LIOP.setTextColor(colorWhite);
            tvAmount_LIOP.setTextColor(colorWhite);

            tvNumber_LIOP.setTypeface(typeface);
            tvSku_LIOP.setTypeface(typeface);
            tvInfo_LIOP.setTypeface(typeface);
            tvQty_LIOP.setTypeface(typeface);
            tvUnitPrice_LIOP.setTypeface(typeface);
            tvTaxes_LIOP.setTypeface(typeface);
            tvAmount_LIOP.setTypeface(typeface);

        }
    }
}
