package com.thinkmobiles.easyerp.presentation.screens.inventory.products.details;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.domain.inventory.ProductRepository;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentFragment;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentPresenter;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;

/**
 * Created by samson on 13.03.17.
 */

@EFragment
public class ProductDetailsFragment extends ContentFragment implements ProductDetailsContract.ProductDetailsView {

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_product_details;
    }

    private ProductDetailsContract.ProductDetailsPresenter presenter;

    @FragmentArg
    protected String id;

    @Bean
    protected ProductRepository productRepository;

    @AfterInject
    @Override
    public void initPresenter() {
        new ProductDetailsPresenter(this, productRepository, id);
    }

    @AfterViews
    protected void initUI() {

    }

    @Override
    public ProductDetailsContract.ProductDetailsPresenter getPresenter() {
        return presenter;
    }

    @Override
    public void setPresenter(ProductDetailsContract.ProductDetailsPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public String getScreenName() {
        return "Product details screen";
    }

}
