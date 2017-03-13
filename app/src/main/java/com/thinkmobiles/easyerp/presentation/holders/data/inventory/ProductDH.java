package com.thinkmobiles.easyerp.presentation.holders.data.inventory;

import com.thinkmobiles.easyerp.data.model.inventory.product.Product;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectableDHHelper;

/**
 * @author Michael Soyma (Created on 3/10/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public final class ProductDH extends SelectableDHHelper {

    private final Product product;

    public ProductDH(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    @Override
    public String getId() {
        return product.id;
    }
}
