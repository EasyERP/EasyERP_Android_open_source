package com.thinkmobiles.easyerp.presentation.utils.filter;


import java.util.ArrayList;

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

    public String getKey() {
        return key;
    }

    public String getTypeFilter() {
        return typeFilter;
    }

    public ArrayList<String> getValues() {
        return values;
    }
}
