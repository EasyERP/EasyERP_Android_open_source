package com.thinkmobiles.easyerp.presentation.screens.inventory.products.details;

import android.text.TextUtils;

import com.thinkmobiles.easyerp.data.model.crm.common.images.ImageItem;
import com.thinkmobiles.easyerp.data.model.crm.filter.FilterItem;
import com.thinkmobiles.easyerp.data.model.inventory.product.detail.ChannelResult;
import com.thinkmobiles.easyerp.data.model.inventory.product.detail.InventoryItem;
import com.thinkmobiles.easyerp.data.model.inventory.product.detail.ProductVariant;
import com.thinkmobiles.easyerp.data.model.inventory.product.detail.ResponseGetProductDetail;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentPresenterHelper;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentView;
import com.thinkmobiles.easyerp.presentation.holders.data.inventory.ProductImageDH;
import com.thinkmobiles.easyerp.presentation.holders.data.inventory.ProductVariantDH;
import com.thinkmobiles.easyerp.presentation.holders.data.inventory.SalesChannelDH;
import com.thinkmobiles.easyerp.presentation.holders.data.inventory.StockInventoryDH;
import com.thinkmobiles.easyerp.presentation.utils.Constants;
import com.thinkmobiles.easyerp.presentation.utils.StringUtil;

import java.util.ArrayList;

/**
 * Created by samson on 13.03.17.
 */

public class ProductDetailsPresenter extends ContentPresenterHelper implements ProductDetailsContract.ProductDetailsPresenter {

    private ProductDetailsContract.ProductDetailsView view;
    private ProductDetailsContract.ProductDetailsModel model;
    private String id;

    private ResponseGetProductDetail productDetail;
    private int selectedVariant;
    private int priceListIndex;
    private boolean variantsIsVisible;

    public ProductDetailsPresenter(ProductDetailsContract.ProductDetailsView view, ProductDetailsContract.ProductDetailsModel model, String id) {
        this.view = view;
        this.model = model;
        this.id = id;
        view.setPresenter(this);
    }

    @Override
    public void refresh() {
        compositeSubscription.add(model.getInventoryProductDetails(id)
                .flatMap(responseGetProductDetail -> model.getProductStockInventory(responseGetProductDetail, selectedVariant))
                .subscribe(responseGetProductDetail -> {
                    view.showProgress(Constants.ProgressType.NONE);
                    setData(responseGetProductDetail);
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
        setData(productDetail);
    }

    @Override
    public void changeVisibilityVariants() {
        variantsIsVisible = !variantsIsVisible;
        view.showVariants(variantsIsVisible);
    }

    @Override
    public void changePriceList(int position) {
        priceListIndex = position;
        view.setPrices(productDetail.variantsArray.get(selectedVariant).priceList.get(priceListIndex).prices);
    }

    @Override
    public void changeProductVariant(int position) {
        if (position != 0 && selectedVariant != position - 1) {
            selectedVariant = position - 1;
            changeVisibilityVariants();
            view.showProgress(Constants.ProgressType.CENTER);
            compositeSubscription.add(model.getProductStockInventory(productDetail, selectedVariant)
                    .subscribe(responseGetProductDetail -> {
                        view.showProgress(Constants.ProgressType.NONE);
                        setData(responseGetProductDetail);
                    }, t -> error(t)));
        }
    }

    @Override
    public void openGallery(int startPosition) {
        view.openGallery(startPosition, productDetail.variantsArray.get(selectedVariant).name, productDetail.images);
    }

    private void setData(ResponseGetProductDetail response) {
        productDetail = response;
        ProductVariant variant = response.variantsArray.get(selectedVariant);
        view.setProductImage(StringUtil.getImageURL(variant.imageSrc));
        view.setProductName(variant.name);
        ArrayList<String> actions = new ArrayList<>();
        if (variant.canBeSold) actions.add("Sold");
        if (variant.canBeExpensed) actions.add("Expensed");
        if (variant.canBePurchased) actions.add("Purchased");
        view.setActions(TextUtils.join(", ", actions));
        view.setProductType(getProductType(variant.info.productType, response.productTypes));
        view.setCategories(variant.categories);
        view.setDescription(TextUtils.isEmpty(variant.info.description) ? "No description" : variant.info.description);
        view.setSKU(variant.info.SKU);
        view.setUPC(variant.info.UPC);
        view.setISBN(variant.info.ISBN);
        view.setEAN(variant.info.EAN);
        view.setWeight(String.valueOf(variant.inventory.weight));
        view.setStockInventory(prepareInventory(response.stockInventory.data));
        view.setSalesChannels(prepareSalesChannel(response.channels.result));
        view.setImages(prepareImages(response.images));
        view.setPriceLists(variant.priceList);
        changePriceList(priceListIndex);
        view.setVariants(prepareVariants(response.variantsArray));
    }

    private String getProductType(String id, ArrayList<FilterItem> types) {
        String res = "Not specified";
        for (FilterItem item : types) {
            if (item.id.equals(id)) {
                res = item.name;
                break;
            }
        }
        return res;
    }

    private ArrayList<ProductImageDH> prepareImages(ArrayList<ImageItem> list) {
        ArrayList<ProductImageDH> dhs = new ArrayList<>();
        for (ImageItem item : list) {
            dhs.add(new ProductImageDH(Constants.BASE_URL.concat(item.imageSrc)));
        }
        return dhs;
    }

    private ArrayList<ProductVariantDH> prepareVariants(ArrayList<ProductVariant> list) {
        ArrayList<ProductVariantDH> dhs = new ArrayList<>();
        dhs.add(null);
        for (ProductVariant item : list) {
            dhs.add(new ProductVariantDH(item));
        }
        dhs.get(selectedVariant + 1).setSelected(true);
        return dhs;
    }

    private ArrayList<StockInventoryDH> prepareInventory(ArrayList<InventoryItem> list) {
        ArrayList<StockInventoryDH> dhs = new ArrayList<>();
        for (InventoryItem item : list) {
            dhs.add(new StockInventoryDH(item));
        }
        return dhs;
    }

    private ArrayList<SalesChannelDH> prepareSalesChannel(ArrayList<ChannelResult> all) {
        ArrayList<SalesChannelDH> dhs = new ArrayList<>();
        for (ChannelResult item : all) {
            dhs.add(new SalesChannelDH(null, item.channelName, item.isPublished));
        }
        return dhs;
    }
}
