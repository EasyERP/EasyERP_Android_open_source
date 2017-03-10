package com.thinkmobiles.easyerp.presentation.adapters.inventory;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectableAdapter;
import com.thinkmobiles.easyerp.presentation.holders.data.inventory.ProductDH;
import com.thinkmobiles.easyerp.presentation.holders.view.inventory.ProductVH;

import org.androidannotations.annotations.EBean;

/**
 * @author Michael Soyma (Created on 3/10/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
@EBean
public class ProductsAdapter extends SelectableAdapter<ProductDH, ProductVH> {
    @Override
    protected int getItemLayout() {
        return R.layout.view_list_item_inventory_product;
    }
}
