package com.thinkmobiles.easyerp.data.services;

import com.thinkmobiles.easyerp.data.model.ResponseGetTotalItems;
import com.thinkmobiles.easyerp.data.model.crm.filter.FilterItem;
import com.thinkmobiles.easyerp.data.model.inventory.product.Product;
import com.thinkmobiles.easyerp.data.model.inventory.product.detail.ResponseGetProductDetail;
import com.thinkmobiles.easyerp.data.model.inventory.product.detail.SalesChannel;
import com.thinkmobiles.easyerp.data.model.inventory.product.detail.StockInventory;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;
import rx.Observable;

/**
 * @author Michael Soyma (Created on 3/10/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public interface ProductService {

    @GET
    Observable<ResponseGetTotalItems<Product>> getInventoryProducts(@Url String url);

    @GET(Constants.GET_PRODUCT_DETAILS)
    Observable<ResponseGetProductDetail> getInventoryProductDetails(@Path("id") String id);

    @GET(Constants.GET_PRODUCT_STOCK_INVENTORY)
    Observable<StockInventory> getProductStockInventory(@Path("id") String id);

    @GET(Constants.GET_PRODUCT_CHANNELS)
    Observable<SalesChannel> getProductChannels();

    @GET(Constants.GET_PRODUCT_TYPES)
    Observable<ResponseGetTotalItems<FilterItem>> getProductTypes();
}
