package com.thinkmobiles.easyerp.presentation.holders.data.crm;

import com.michenko.simpleadapter.RecyclerDH;
import com.thinkmobiles.easyerp.data.model.crm.leads.filter.FilterItem;

/**
 * Created by samson on 26.01.17.
 */

public class FilterDH extends RecyclerDH {

    public String id;
    public String name;
    public boolean selected;


    public FilterDH(FilterItem item, boolean selected) {
        this(item);
        this.selected = selected;
    }

    public FilterDH(FilterItem item) {
        this.id = item.id;
        this.name = item.name;
    }
}
