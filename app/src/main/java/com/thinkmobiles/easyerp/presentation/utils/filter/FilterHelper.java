package com.thinkmobiles.easyerp.presentation.utils.filter;

import android.support.v4.util.Pair;
import android.util.SparseArray;
import android.view.Menu;

import com.thinkmobiles.easyerp.data.model.crm.filter.FilterInfo;
import com.thinkmobiles.easyerp.data.model.crm.filter.FilterItem;
import com.thinkmobiles.easyerp.data.model.crm.filter.ResponseFilters;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.FilterDH;
import com.thinkmobiles.easyerp.presentation.listeners.CheckFilterCallback;
import com.thinkmobiles.easyerp.presentation.listeners.VisibilityCallback;

import java.util.ArrayList;

/**
 * @author Alex Michenko (Created on 16.02.17).
 *         Company: Thinkmobiles
 *         Email: alex.michenko@thinkmobiles.com
 */

public class FilterHelper {

    private SparseArray<ArrayList<FilterDH>> filters;
    private SparseArray<String> names;
    private FilterQuery queryBuilder;

    private final int indexSearchableFilter = 0;

    public static FilterHelper create(ResponseFilters responseFilters) {
        FilterHelper helper= new FilterHelper();
        helper.filters = new SparseArray<>();
        helper.names = new SparseArray<>();
        helper.queryBuilder = new FilterQuery();
        for (FilterInfo info : responseFilters.filters) {
            ArrayList<FilterDH> dh = new ArrayList<>();
            for (FilterItem item : info.list) {
                if (item.name != null)
                    dh.add(new FilterDH(item));
            }
            helper.filters.put(info.code, dh);
            helper.names.put(info.code, info.name);
            helper.queryBuilder.putFilter(info.code, info.type, info.key);
        }
        return helper;
    }

    public boolean isInitialized() {
        return filters != null;
    }

    public FilterQuery getFilterBuilder() {
        return queryBuilder;
    }

    public void buildMenu(Menu menu) {
        for (int i = 0; i < names.size(); ++i) {
            menu.add(0, i, i, names.valueAt(i)).setCheckable(true);
        }
    }

    public void setupMenu(CheckFilterCallback callback) {
        if (filters != null)
        for (int i = 0; i < filters.size(); ++i) {
            callback.onCheckedFilter(i, queryBuilder.forFilter(i).getValues() != null);
        }
    }

    public ArrayList<FilterDH> getSearchableFilterList() {
        return getFilterList(indexSearchableFilter);
    }

    public ArrayList<FilterDH> getFilterList(int index) {
        return filters.get(index);
    }

    public void filterByItem(FilterDH filterDH, CheckFilterCallback callback) {
        for (FilterDH dh : filters.get(indexSearchableFilter)) {
            dh.selected = dh.equals(filterDH);
        }
        queryBuilder.forFilter(indexSearchableFilter)
                .removeAll()
                .add(filterDH.id);
        callback.onCheckedFilter(indexSearchableFilter, true);
    }

    public void filterByText(String text, CheckFilterCallback callback) {
        FilterTypeQuery contactQuery = queryBuilder.forFilter(indexSearchableFilter);
        contactQuery.removeAll();
        for (FilterDH dh : filters.get(indexSearchableFilter)) {
            if(dh.name.toLowerCase().contains(text)) {
                contactQuery.add(dh.id);
                dh.selected = true;
            } else {
                dh.selected = false;
            }
        }
        callback.onCheckedFilter(indexSearchableFilter, true);
    }

    public void filterByList(ArrayList<FilterDH> filterDHs, int code, CheckFilterCallback callback) {
        filters.put(code, filterDHs);
        FilterTypeQuery filter = queryBuilder.forFilter(code);
        filter.removeAll();
        for (FilterDH dh : filterDHs) {
            if(dh.selected) {
                filter.add(dh.id);
            }
        }
        callback.onCheckedFilter(code, filter.getValues() != null);
    }

    public void removeFilter(int code, CheckFilterCallback callback) {
        ArrayList<FilterDH> filterDHs = filters.get(code);
        for (FilterDH dh : filterDHs) {
            dh.selected = false;
        }
        queryBuilder.forFilter(code).removeAll();
        callback.onCheckedFilter(code, false);
    }

    public void removeAllFilters(CheckFilterCallback callback) {
        for (int position = 0; position < filters.size(); ++position) {
            for (FilterDH dh : filters.get(position)) {
                dh.selected = false;
            }
            queryBuilder.forFilter(position).removeAll();
            callback.onCheckedFilter(position, false);
        }
    }

}
