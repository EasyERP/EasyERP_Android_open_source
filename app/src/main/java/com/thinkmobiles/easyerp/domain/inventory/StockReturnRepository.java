package com.thinkmobiles.easyerp.domain.inventory;

import com.thinkmobiles.easyerp.data.api.Rest;
import com.thinkmobiles.easyerp.data.model.inventory.stock_returns.StockReturn;
import com.thinkmobiles.easyerp.data.services.StockReturnService;
import com.thinkmobiles.easyerp.presentation.base.NetworkRepository;
import com.thinkmobiles.easyerp.presentation.screens.inventory.stock_returns.StockReturnsListContract;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.util.List;

import rx.Observable;

/**
 * @author Michael Soyma (Created on 3/6/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
@EBean(scope = EBean.Scope.Singleton)
public class StockReturnRepository extends NetworkRepository implements StockReturnsListContract.StockReturnsListModel {

    private final StockReturnService stockReturnService;

    public StockReturnRepository() {
        this.stockReturnService = Rest.getInstance().getStockReturnService();
    }

    @Override
    public Observable<List<StockReturn>> getStockReturns(int page) {
        return getNetworkObservable(stockReturnService.getStockReturns("list", Constants.COUNT_LIST_ITEMS, "stockReturns", page));
    }
}