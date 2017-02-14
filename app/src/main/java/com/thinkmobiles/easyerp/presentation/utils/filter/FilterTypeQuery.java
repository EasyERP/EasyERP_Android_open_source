package com.thinkmobiles.easyerp.presentation.utils.filter;


import com.thinkmobiles.easyerp.presentation.holders.data.crm.FilterDH;
import com.thinkmobiles.easyerp.presentation.listeners.VisibilityCallback;
import com.thinkmobiles.easyerp.presentation.screens.crm.leads.LeadsPresenter;

import java.util.ArrayList;
import java.util.Map;

public class FilterTypeQuery {

    private String key;
    private String typeFilter;
    private ArrayList<String> values;

    public FilterTypeQuery(String key, String typeFilter) {
        this.key = key;
        this.typeFilter = typeFilter;
    }

    public FilterTypeQuery add(String value) {
        if (values == null) {
            values = new ArrayList<>();
        }
        values.add(value);
        return this;
    }

    public FilterTypeQuery removeAll() {
        values = null;
        return this;
    }

    public ArrayList<String> getValues() {
        return values;
    }

    public void setList(ArrayList<FilterDH> filterDHs, VisibilityCallback callback) {
        values = null;
        for (FilterDH dh : filterDHs) {
            if(dh.selected) {
                add(dh.id);
            }
        }
        callback.setVisibility(values != null);
    }

    public ArrayList<String> save(final Map<String, String> map) {
        if (values != null) {
            map.put(String.format("filter[%s][key]", key), typeFilter);
        }
        return values;
    }

}
