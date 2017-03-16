package com.thinkmobiles.easyerp.presentation.holders.data.inventory;

import com.thinkmobiles.easyerp.data.model.inventory.stock_correction.StockCorrection;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectableDHHelper;

/**
 * @author Michael Soyma (Created on 3/7/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public final class StockCorrectionDH extends SelectableDHHelper {

    private final StockCorrection stockCorrection;

    public StockCorrectionDH(StockCorrection stockCorrection) {
        this.stockCorrection = stockCorrection;
    }

    public StockCorrection getStockCorrection() {
        return stockCorrection;
    }

    @Override
    public String getId() {
        return stockCorrection.id;
    }
}
