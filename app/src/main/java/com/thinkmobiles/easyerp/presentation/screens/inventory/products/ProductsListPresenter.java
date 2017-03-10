package com.thinkmobiles.easyerp.presentation.screens.inventory.products;

import com.thinkmobiles.easyerp.data.model.inventory.product.Product;
import com.thinkmobiles.easyerp.presentation.base.rules.master.filterable.FilterableModel;
import com.thinkmobiles.easyerp.presentation.base.rules.master.filterable.FilterableView;
import com.thinkmobiles.easyerp.presentation.base.rules.master.filterable.MasterFilterablePresenterHelper;
import com.thinkmobiles.easyerp.presentation.holders.data.inventory.ProductDH;
import com.thinkmobiles.easyerp.presentation.managers.ErrorManager;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Michael Soyma (Created on 3/10/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public final class ProductsListPresenter extends MasterFilterablePresenterHelper implements ProductsListContract.ProductsListPresenter {

    private ProductsListContract.ProductsListView view;
    private ProductsListContract.ProductsListModel model;

    private List<Product> products = new ArrayList<>();

    public ProductsListPresenter(ProductsListContract.ProductsListView view, ProductsListContract.ProductsListModel model) {
        this.view = view;
        this.model = model;

        this.view.setPresenter(this);
    }

    @Override
    public void clickItem(int position) {
        String id = products.get(position).id;
        if(super.selectItem(id, position))
            view.openProductDetail(id);
    }

    @Override
    protected boolean hasContent() {
        return !products.isEmpty();
    }

    @Override
    protected void retainInstance() {
        setData(products, true);
    }

    @Override
    protected void loadPage(int page) {
        final boolean needClear = page == 1;
        compositeSubscription.add(model.getFilteredProducts(helper, page).subscribe(
                responseItems -> {
                    currentPage = page;
                    totalItems = responseItems.total;
                    saveData(responseItems.data, needClear);
                    setData(responseItems.data, needClear);
                }, t -> error(t)));
    }

    @Override
    protected int getCountItems() {
        return products.size();
    }

    @Override
    protected FilterableView getView() {
        return view;
    }

    @Override
    protected FilterableModel getModel() {
        return model;
    }

    private void saveData(final List<Product> products, boolean needClear) {
        if (needClear)
            this.products.clear();
        this.products.addAll(products);
    }

    private void setData(final List<Product> products, boolean needClear) {
        view.setDataList(prepareProductDHs(products), needClear);
        if (this.products.isEmpty()) {
            view.displayErrorState(ErrorManager.getErrorType(null));
        } else {
            view.showProgress(Constants.ProgressType.NONE);
        }
    }

    private ArrayList<ProductDH> prepareProductDHs(final List<Product> products) {
        final ArrayList<ProductDH> result = new ArrayList<>();
        for (Product item : products) {
            final ProductDH productDH = new ProductDH(item);
            makeSelectedDHIfNeed(productDH, products.indexOf(item));
            result.add(productDH);
        }
        selectFirstElementIfNeed(result);
        return result;
    }
}
