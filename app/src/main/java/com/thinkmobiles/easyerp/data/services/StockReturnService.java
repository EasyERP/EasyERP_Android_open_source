package com.thinkmobiles.easyerp.data.services;

import com.thinkmobiles.easyerp.data.model.inventory.stock_returns.StockReturn;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @author Michael Soyma (Created on 3/6/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public interface StockReturnService {

    @GET(Constants.GET_STOCK_RETURNS)
    Observable<List<StockReturn>> getStockReturns(@Query("viewType") String viewType,
                                                  @Query("count") int count,
                                                  @Query("contentType") String contentType,
                                                  @Query("page") int page);
}
