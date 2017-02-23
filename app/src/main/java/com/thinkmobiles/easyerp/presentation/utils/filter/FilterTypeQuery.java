package com.thinkmobiles.easyerp.presentation.utils.filter;


import java.util.ArrayList;

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

}
