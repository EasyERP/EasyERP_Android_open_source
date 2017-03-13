package com.thinkmobiles.easyerp.data.services;

import com.thinkmobiles.easyerp.data.model.ResponseGetTotalItems;
import com.thinkmobiles.easyerp.data.model.inventory.product.Product;

import retrofit2.http.GET;
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
}
