package com.thinkmobiles.easyerp.presentation.screens.inventory.products.details;

import com.thinkmobiles.easyerp.data.model.crm.filter.FilterItem;
import com.thinkmobiles.easyerp.data.model.inventory.product.detail.Price;
import com.thinkmobiles.easyerp.data.model.inventory.product.detail.PriceList;
import com.thinkmobiles.easyerp.data.model.inventory.product.detail.ResponseGetProductDetail;
import com.thinkmobiles.easyerp.presentation.base.BaseModel;
import com.thinkmobiles.easyerp.presentation.base.BaseView;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentPresenter;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentView;
import com.thinkmobiles.easyerp.presentation.holders.data.inventory.ProductImageDH;
import com.thinkmobiles.easyerp.presentation.holders.data.inventory.ProductVariantDH;
import com.thinkmobiles.easyerp.presentation.holders.data.inventory.SalesChannelDH;
import com.thinkmobiles.easyerp.presentation.holders.data.inventory.StockInventoryDH;

import java.util.ArrayList;

import rx.Observable;

/**
 * Created by samson on 13.03.17.
 */

public interface ProductDetailsContract {

    interface ProductDetailsView extends ContentView, BaseView<ProductDetailsPresenter> {
        void setProductImage(String url);
        void setProductName(String productName);
        void setActions(String actions);
        void setCategories(ArrayList<FilterItem> categories);
        void setProductType(String productType);
        void setDescription(String description);
        void setSKU(String sKU);
        void setUPC(String uPC);
        void setISBN(String iSBN);
        void setEAN(String eAN);
        void setWeight(String weight);
        void setStockInventory(ArrayList<StockInventoryDH> inventory);
        void setSalesChannels(ArrayList<SalesChannelDH> channels);
        void setImages(ArrayList<ProductImageDH> images);
        void setPriceLists(ArrayList<PriceList> prices);
        void setPrices(ArrayList<Price> prices);
        void setVariants(ArrayList<ProductVariantDH> variants);

        void showVariants(boolean enable);
    }

    interface ProductDetailsPresenter extends ContentPresenter {
        void changeVisibilityVariants();
        void changePriceList(int position);
        void changeProductVariant(int position);
    }

    interface ProductDetailsModel extends BaseModel {
        Observable<ResponseGetProductDetail> getInventoryProductDetails(String id);
        Observable<ResponseGetProductDetail> getProductStockInventory(ResponseGetProductDetail details, int variant);
    }
}
