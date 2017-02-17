package com.thinkmobiles.easyerp.presentation.utils.filter;


import com.thinkmobiles.easyerp.presentation.holders.data.crm.FilterDH;
import com.thinkmobiles.easyerp.presentation.listeners.VisibilityCallback;

import java.util.ArrayList;
import java.util.Map;

public class FilterTypeQuery {

    private String type;
    private String key;
    private ArrayList<String> values;

    public FilterTypeQuery(String type, String key) {
        this.type = type;
        this.key = key;
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

    public String getType() {
        return type;
    }

    public String getKey() {
        return key;
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
            map.put(String.format("filter[%s][key]", type), key);
        }
        return values;
    }

}
