package com.thinkmobiles.easyerp.domain.inventory;

import com.thinkmobiles.easyerp.data.api.Rest;
import com.thinkmobiles.easyerp.data.model.ResponseGetTotalItems;
import com.thinkmobiles.easyerp.data.model.crm.filter.ResponseFilters;
import com.thinkmobiles.easyerp.data.model.inventory.product.Product;
import com.thinkmobiles.easyerp.data.services.FilterService;
import com.thinkmobiles.easyerp.data.services.ProductService;
import com.thinkmobiles.easyerp.presentation.base.NetworkRepository;
import com.thinkmobiles.easyerp.presentation.screens.inventory.products.ProductsListContract;
import com.thinkmobiles.easyerp.presentation.utils.Constants;
import com.thinkmobiles.easyerp.presentation.utils.filter.FilterHelper;

import org.androidannotations.annotations.EBean;

import rx.Observable;

/**
 * @author Michael Soyma (Created on 3/10/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
@EBean(scope = EBean.Scope.Singleton)
public class ProductRepository extends NetworkRepository implements ProductsListContract.ProductsListModel {

    private final ProductService productService;
    private final FilterService filterService;

    public ProductRepository() {
        productService = Rest.getInstance().getProductService();
        filterService = Rest.getInstance().getFilterService();
    }

    @Override
    public Observable<ResponseFilters> getFilters() {
        return getNetworkObservable(filterService.getListFilters("Products"));
    }

    @Override
    public Observable<ResponseGetTotalItems<Product>> getFilteredProducts(FilterHelper query, int page) {
        return getNetworkObservable(productService.getInventoryProducts(query
                .createUrl(Constants.GET_INVENTORY_PRODUCTS, "Products", page)
                .build()
                .toString()
        ));
    }
}
