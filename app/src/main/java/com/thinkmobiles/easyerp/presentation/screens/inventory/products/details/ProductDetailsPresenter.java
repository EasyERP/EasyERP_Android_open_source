package com.thinkmobiles.easyerp.presentation.screens.inventory.products.details;

import com.thinkmobiles.easyerp.data.model.inventory.product.detail.ResponseGetProductDetail;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentPresenterHelper;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentView;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

/**
 * Created by samson on 13.03.17.
 */

public class ProductDetailsPresenter extends ContentPresenterHelper implements ProductDetailsContract.ProductDetailsPresenter {

    private ProductDetailsContract.ProductDetailsView view;
    private ProductDetailsContract.ProductDetailsModel model;
    private String id;

    private ResponseGetProductDetail productDetail;

    public ProductDetailsPresenter(ProductDetailsContract.ProductDetailsView view, ProductDetailsContract.ProductDetailsModel model, String id) {
        this.view = view;
        this.model = model;
        this.id = id;
        view.setPresenter(this);
    }

    @Override
    public void refresh() {
        compositeSubscription.add(model.getInventoryProductDetails(id)
                .subscribe(responseGetProductDetail -> {
                    view.showProgress(Constants.ProgressType.NONE);
                }, t -> error(t)));
    }

    @Override
    protected ContentView getView() {
        return view;
    }

    @Override
    protected boolean hasContent() {
        return productDetail != null;
    }

    @Override
    protected void retainInstance() {

    }

    private void setData(ResponseGetProductDetail response) {
        productDetail = response;
    }
}
