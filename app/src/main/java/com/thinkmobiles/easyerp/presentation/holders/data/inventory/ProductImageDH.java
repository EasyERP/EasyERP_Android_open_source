package com.thinkmobiles.easyerp.presentation.holders.data.inventory;

import com.michenko.simpleadapter.RecyclerDH;

/**
 * Created by samson on 14.03.17.
 */

public final class ProductImageDH extends RecyclerDH {

    private String url;

    public ProductImageDH(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
