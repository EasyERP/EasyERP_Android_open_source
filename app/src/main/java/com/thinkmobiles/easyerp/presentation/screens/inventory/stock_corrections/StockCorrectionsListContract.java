package com.thinkmobiles.easyerp.presentation.screens.inventory.stock_corrections;

import com.thinkmobiles.easyerp.data.model.ResponseGetTotalItems;
import com.thinkmobiles.easyerp.data.model.inventory.stock_correction.StockCorrection;
import com.thinkmobiles.easyerp.presentation.base.BaseView;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectablePresenter;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectableView;

import rx.Observable;

/**
 * @author Michael Soyma (Created on 3/7/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public interface StockCorrectionsListContract {
    interface StockCorrectionsListView extends BaseView<StockCorrectionsListPresenter>, SelectableView {
        void openStockCorrectionDetail(final String id);
    }
    interface StockCorrectionsListPresenter extends SelectablePresenter {

    }
    interface StockCorrectionsListModel {
        Observable<ResponseGetTotalItems<StockCorrection>> getStockCorrections(final int page);
    }
}
