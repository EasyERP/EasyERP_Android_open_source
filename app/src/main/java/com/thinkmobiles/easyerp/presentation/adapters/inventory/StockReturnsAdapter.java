package com.thinkmobiles.easyerp.presentation.adapters.inventory;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectableAdapter;
import com.thinkmobiles.easyerp.presentation.holders.data.inventory.StockReturnDH;
import com.thinkmobiles.easyerp.presentation.holders.view.inventory.StockReturnVH;

import org.androidannotations.annotations.EBean;

/**
 * @author Michael Soyma (Created on 3/6/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
@EBean
public class StockReturnsAdapter extends SelectableAdapter<StockReturnDH, StockReturnVH> {
    @Override
    protected int getItemLayout() {
        return R.layout.view_chart_overview;
    }
}
