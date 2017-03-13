package com.thinkmobiles.easyerp.presentation.screens.inventory.products;

import com.thinkmobiles.easyerp.domain.inventory.ProductRepository;
import com.thinkmobiles.easyerp.presentation.adapters.inventory.ProductsAdapter;
import com.thinkmobiles.easyerp.presentation.base.rules.master.filterable.FilterablePresenter;
import com.thinkmobiles.easyerp.presentation.base.rules.master.filterable.MasterFilterableFragment;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectableAdapter;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;

/**
 * @author Michael Soyma (Created on 3/10/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
@EFragment
public class ProductsListFragment extends MasterFilterableFragment implements ProductsListContract.ProductsListView {

    private ProductsListContract.ProductsListPresenter presenter;

    @Bean
    protected ProductRepository productRepository;
    @Bean
    protected ProductsAdapter productsAdapter;

    @AfterInject
    @Override
    public void initPresenter() {
        new ProductsListPresenter(this, productRepository);
    }

    @Override
    public void setPresenter(ProductsListContract.ProductsListPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public String getScreenName() {
        return "Inventory Products list screen";
    }

    @Override
    protected SelectableAdapter getAdapter() {
        return productsAdapter;
    }

    @Override
    public void openProductDetail(String id) {
        if (id != null) {
            //TODO open Inventory Product detail
        } else {
            mActivity.replaceFragmentContentDetail(null);
        }
    }

    @Override
    protected FilterablePresenter getPresenter() {
        return presenter;
    }
}
