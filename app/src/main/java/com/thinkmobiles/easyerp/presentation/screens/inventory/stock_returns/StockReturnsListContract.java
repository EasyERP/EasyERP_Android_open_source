package com.thinkmobiles.easyerp.presentation.screens.inventory.stock_returns;

import com.thinkmobiles.easyerp.data.model.inventory.stock_returns.StockReturn;
import com.thinkmobiles.easyerp.presentation.base.BaseModel;
import com.thinkmobiles.easyerp.presentation.base.BaseView;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectablePresenter;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectableView;

import java.util.List;

import rx.Observable;

/**
 * @author Michael Soyma (Created on 3/6/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public interface StockReturnsListContract {
    interface StockReturnsListView extends BaseView<StockReturnsListPresenter>, SelectableView {
        void openStockReturnDetail(final String id);
    }
    interface StockReturnsListPresenter extends SelectablePresenter {

    }
    interface StockReturnsListModel extends BaseModel {
        Observable<List<StockReturn>> getStockReturns(final int page);
    }
}
