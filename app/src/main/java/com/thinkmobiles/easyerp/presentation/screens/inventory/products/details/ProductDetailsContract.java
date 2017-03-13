package com.thinkmobiles.easyerp.presentation.screens.inventory.products.details;

import com.thinkmobiles.easyerp.data.model.inventory.product.detail.ResponseGetProductDetail;
import com.thinkmobiles.easyerp.presentation.base.BaseModel;
import com.thinkmobiles.easyerp.presentation.base.BaseView;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentPresenter;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentView;

import rx.Observable;

/**
 * Created by samson on 13.03.17.
 */

public interface ProductDetailsContract {

    interface ProductDetailsView extends ContentView, BaseView<ProductDetailsPresenter> {

    }

    interface ProductDetailsPresenter extends ContentPresenter {

    }

    interface ProductDetailsModel extends BaseModel {
        Observable<ResponseGetProductDetail> getInventoryProductDetails(String id);
        Observable<ResponseGetProductDetail> getProductStockInventory(ResponseGetProductDetail details);
    }
}
