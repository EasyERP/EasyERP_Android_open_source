package com.thinkmobiles.easyerp.presentation.holders.view.crm;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.michenko.simpleadapter.OnCardClickListener;
import com.michenko.simpleadapter.RecyclerVH;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.data.model.crm.order.detail.OrderProduct;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.ProductDH;
import com.thinkmobiles.easyerp.presentation.screens.crm.dashboard.detail.charts.DollarFormatter;
import com.thinkmobiles.easyerp.presentation.utils.StringUtil;

import java.text.DecimalFormat;

/**
 * @author Alex Michenko (Created on 01.02.17).
 *         Company: Thinkmobiles
 *         Email: alex.michenko@thinkmobiles.com
 */

public final class ProductVH extends RecyclerVH<ProductDH> {

    private final TextView tvNumber_LIOP;
    private final TextView tvSku_LIOP;
    private final TextView tvInfo_LIOP;
    private final TextView tvQty_LIOP;
    private final TextView tvUnitPrice_LIOP;
    private final TextView tvTaxes_LIOP;
    private final TextView tvAmount_LIOP;

    private final DecimalFormat formatter;

    public ProductVH(View itemView, @Nullable OnCardClickListener listener, int viewType) {
        super(itemView, listener, viewType);

        tvNumber_LIOP = findView(R.id.tvNumber_LIOP);
        tvSku_LIOP = findView(R.id.tvSku_LIOP);
        tvInfo_LIOP = findView(R.id.tvInfo_LIOP);
        tvQty_LIOP = findView(R.id.tvQty_LIOP);
        tvUnitPrice_LIOP = findView(R.id.tvUnitPrice_LIOP);
        tvTaxes_LIOP = findView(R.id.tvTaxes_LIOP);
        tvAmount_LIOP = findView(R.id.tvAmount_LIOP);

        formatter = new DollarFormatter().getFormat();
    }

    @Override
    public void bindData(ProductDH data) {
        OrderProduct model = data.getModel();
        String symbol = data.getSymbol();
        tvNumber_LIOP.setText(String.valueOf(getAdapterPosition() + 1));
        if (model.product != null && model.product.info != null)
            tvSku_LIOP.setText(model.product.info.SKU);
        else {
            tvSku_LIOP.setText("");
        }
        tvInfo_LIOP.setText(model.description);
        tvQty_LIOP.setText(String.valueOf(model.quantity.floatValue()));

        tvUnitPrice_LIOP.setText(StringUtil.getFormattedPriceFromCent(formatter, model.unitPrice, symbol));
        tvTaxes_LIOP.setText(StringUtil.getFormattedPriceFromCent(formatter, model.totalTaxes, symbol));
        tvAmount_LIOP.setText(StringUtil.getFormattedPriceFromCent(formatter, model.subTotal, symbol));
    }
}
