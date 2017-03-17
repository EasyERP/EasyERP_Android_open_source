package com.thinkmobiles.easyerp.presentation.holders.data.inventory;

import com.michenko.simpleadapter.RecyclerDH;
import com.thinkmobiles.easyerp.data.model.inventory.product.detail.ProductVariant;

/**
 * Created by samson on 15.03.17.
 */

public class ProductVariantDH extends RecyclerDH {

    private ProductVariant model;
    private boolean isSelected;

    public ProductVariantDH(ProductVariant model) {
        this.model = model;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public ProductVariant getModel() {
        return model;
    }
}
