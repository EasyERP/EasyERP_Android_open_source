package com.thinkmobiles.easyerp.presentation.adapters.inventory;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectableAdapter;
import com.thinkmobiles.easyerp.presentation.holders.data.inventory.StockCorrectionDH;
import com.thinkmobiles.easyerp.presentation.holders.view.inventory.StockCorrectionVH;

import org.androidannotations.annotations.EBean;

/**
 * @author Michael Soyma (Created on 3/6/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
@EBean
public class StockCorrectionsAdapter extends SelectableAdapter<StockCorrectionDH, StockCorrectionVH> {
    @Override
    protected int getItemLayout() {
        return R.layout.view_list_item_stock_correction;
    }
}
