package com.thinkmobiles.easyerp.domain.inventory;

import com.thinkmobiles.easyerp.data.api.Rest;
import com.thinkmobiles.easyerp.data.model.inventory.stock_returns.StockReturn;
import com.thinkmobiles.easyerp.data.model.inventory.stock_returns.details.ResponseGetStockReturnsDetails;
import com.thinkmobiles.easyerp.data.model.user.organization.ResponseGetOrganizationSettings;
import com.thinkmobiles.easyerp.data.services.StockService;
import com.thinkmobiles.easyerp.data.services.UserService;
import com.thinkmobiles.easyerp.presentation.base.NetworkRepository;
import com.thinkmobiles.easyerp.presentation.screens.inventory.stock_returns.StockReturnsListContract;
import com.thinkmobiles.easyerp.presentation.screens.inventory.stock_returns.details.StockReturnsDetailsContract;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import org.androidannotations.annotations.EBean;

import java.util.List;

import rx.Observable;

/**
 * @author Michael Soyma (Created on 3/6/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
@EBean(scope = EBean.Scope.Singleton)
public class StockReturnRepository extends NetworkRepository implements StockReturnsListContract.StockReturnsListModel,
        StockReturnsDetailsContract.StockReturnsDetailsModel {

    private final StockService stockService;
    private final UserService userService;

    public StockReturnRepository() {
        this.stockService = Rest.getInstance().getStockService();
        this.userService = Rest.getInstance().getUserService();
    }

    @Override
    public Observable<List<StockReturn>> getStockReturns(int page) {
        return getNetworkObservable(stockService.getStockReturns("list", Constants.COUNT_LIST_ITEMS, "stockReturns", page));
    }

    @Override
    public Observable<ResponseGetStockReturnsDetails> getStockReturnsDetails(String id) {
        return getNetworkObservable(stockService.getStockReturnsDetails(id));
    }

    @Override
    public Observable<ResponseGetOrganizationSettings> getOrganizationSettings() {
        return getNetworkObservable(userService.getOrganizationSettings());
    }
}