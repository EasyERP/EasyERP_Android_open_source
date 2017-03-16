package com.thinkmobiles.easyerp.domain.inventory;

import com.thinkmobiles.easyerp.data.api.Rest;
import com.thinkmobiles.easyerp.data.model.ResponseGetTotalItems;
import com.thinkmobiles.easyerp.data.model.inventory.stock_correction.StockCorrection;
import com.thinkmobiles.easyerp.data.model.inventory.stock_correction.details.ResponseGetStockCorrectionDetails;
import com.thinkmobiles.easyerp.data.services.StockService;
import com.thinkmobiles.easyerp.data.services.UserService;
import com.thinkmobiles.easyerp.presentation.base.NetworkRepository;
import com.thinkmobiles.easyerp.presentation.screens.inventory.stock_corrections.StockCorrectionsListContract;
import com.thinkmobiles.easyerp.presentation.screens.inventory.stock_corrections.details.StockCorrectionsDetailsContract;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import org.androidannotations.annotations.EBean;

import rx.Observable;

/**
 * @author Michael Soyma (Created on 3/6/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
@EBean(scope = EBean.Scope.Singleton)
public class StockCorrectionRepository extends NetworkRepository implements StockCorrectionsListContract.StockCorrectionsListModel,
        StockCorrectionsDetailsContract.StockCorrectionsDetailsModel {

    private final StockService stockService;
    private final UserService userService;

    public StockCorrectionRepository() {
        this.stockService = Rest.getInstance().getStockService();
        this.userService = Rest.getInstance().getUserService();
    }

    @Override
    public Observable<ResponseGetTotalItems<StockCorrection>> getStockCorrections(int page) {
        return getNetworkObservable(stockService.getStockCorrection("list", Constants.COUNT_LIST_ITEMS, "stockCorrections", page));
    }

    @Override
    public Observable<ResponseGetStockCorrectionDetails> getStockCorrectionDetails(String id) {
        return getNetworkObservable(stockService.getStockCorrectionDetails(id));
    }
}