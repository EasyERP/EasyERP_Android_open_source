package com.thinkmobiles.easyerp.presentation.holders.data.inventory;

import com.thinkmobiles.easyerp.data.model.inventory.stock_returns.StockReturn;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectableDHHelper;

/**
 * @author Michael Soyma (Created on 3/6/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public class StockReturnDH extends SelectableDHHelper {

    private final StockReturn stockReturn;

    public StockReturnDH(StockReturn stockReturn) {
        this.stockReturn = stockReturn;
    }

    public StockReturn getStockReturn() {
        return stockReturn;
    }

    @Override
    public String getId() {
        return stockReturn.id;
    }
}
