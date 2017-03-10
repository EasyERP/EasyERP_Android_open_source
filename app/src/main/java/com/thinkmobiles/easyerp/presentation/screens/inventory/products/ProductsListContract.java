package com.thinkmobiles.easyerp.presentation.screens.inventory.products;

import com.thinkmobiles.easyerp.data.model.ResponseGetTotalItems;
import com.thinkmobiles.easyerp.data.model.inventory.product.Product;
import com.thinkmobiles.easyerp.presentation.base.BaseModel;
import com.thinkmobiles.easyerp.presentation.base.BaseView;
import com.thinkmobiles.easyerp.presentation.base.rules.master.filterable.FilterableModel;
import com.thinkmobiles.easyerp.presentation.base.rules.master.filterable.FilterablePresenter;
import com.thinkmobiles.easyerp.presentation.base.rules.master.filterable.FilterableView;
import com.thinkmobiles.easyerp.presentation.utils.filter.FilterHelper;

import rx.Observable;

/**
 * @author Michael Soyma (Created on 3/10/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public interface ProductsListContract {
    interface ProductsListView extends BaseView<ProductsListPresenter>, FilterableView {
        void openProductDetail(final String id);
    }
    interface ProductsListPresenter extends FilterablePresenter {

    }
    interface ProductsListModel extends BaseModel, FilterableModel {
        Observable<ResponseGetTotalItems<Product>> getFilteredProducts(final FilterHelper query, final int page);
    }
}
