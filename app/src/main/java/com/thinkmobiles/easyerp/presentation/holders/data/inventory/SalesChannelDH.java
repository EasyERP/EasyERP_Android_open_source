package com.thinkmobiles.easyerp.presentation.holders.data.inventory;

import com.michenko.simpleadapter.RecyclerDH;

/**
 * Created by samson on 14.03.17.
 */

public final class SalesChannelDH extends RecyclerDH {

    private final String url;
    private final String name;
    private final boolean published;

    public SalesChannelDH(String url, String name, boolean published) {
        this.url = url;
        this.name = name;
        this.published = published;
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

    public boolean isPublished() {
        return published;
    }
}
