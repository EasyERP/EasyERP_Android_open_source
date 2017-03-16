package com.thinkmobiles.easyerp.presentation.holders.view.inventory;

import android.view.View;
import android.widget.TextView;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.data.model.inventory.product.detail.PriceList;

/**
 * Created by samson on 15.03.17.
 */

public final class PriceListVH {

    private final TextView tvPriceListName_LIPP;

    public PriceListVH(View itemView) {
        tvPriceListName_LIPP = (TextView) itemView.findViewById(R.id.tvPriceListName_LIPP);
    }

    public void bindData(PriceList data) {
        tvPriceListName_LIPP.setText(data.priceLists.name.concat(data.priceLists.cost ? " (costs)" : ""));
    }
}
