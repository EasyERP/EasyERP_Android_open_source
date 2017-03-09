package com.thinkmobiles.easyerp.domain.inventory;

import com.thinkmobiles.easyerp.data.api.Rest;
import com.thinkmobiles.easyerp.data.model.ResponseGetTotalItems;
import com.thinkmobiles.easyerp.data.model.inventory.stock_correction.StockCorrection;
import com.thinkmobiles.easyerp.data.services.StockService;
import com.thinkmobiles.easyerp.presentation.base.NetworkRepository;
import com.thinkmobiles.easyerp.presentation.screens.inventory.stock_corrections.StockCorrectionsListContract;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import org.androidannotations.annotations.EBean;

import rx.Observable;

/**
 * @author Michael Soyma (Created on 3/6/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
@EBean(scope = EBean.Scope.Singleton)
public class StockCorrectionRepository extends NetworkRepository implements StockCorrectionsListContract.StockCorrectionsListModel {

    private final StockService stockService;

    public StockCorrectionRepository() {
        this.stockService = Rest.getInstance().getStockService();
    }

    @Override
    public Observable<ResponseGetTotalItems<StockCorrection>> getStockCorrections(int page) {
        return getNetworkObservable(stockService.getStockCorrection("list", Constants.COUNT_LIST_ITEMS, "stockCorrections", page));
    }
}