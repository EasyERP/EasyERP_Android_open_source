package com.thinkmobiles.easyerp.presentation.utils.filter;

import android.net.Uri;
import android.support.v4.util.Pair;
import android.util.SparseArray;
import android.view.Menu;

import com.thinkmobiles.easyerp.data.model.crm.filter.FilterInfo;
import com.thinkmobiles.easyerp.data.model.crm.filter.FilterItem;
import com.thinkmobiles.easyerp.data.model.crm.filter.ResponseFilters;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.FilterDH;
import com.thinkmobiles.easyerp.presentation.listeners.CheckFilterCallback;
import com.thinkmobiles.easyerp.presentation.listeners.VisibilityCallback;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import java.util.ArrayList;

/**
 * @author Alex Michenko (Created on 16.02.17).
 *         Company: Thinkmobiles
 *         Email: alex.michenko@thinkmobiles.com
 */

public class FilterHelper {

    private SparseArray<ArrayList<FilterDH>> filters;
    private SparseArray<String> names;
    private SparseArray<FilterTypeQuery> params;

    private final int indexSearchableFilter = 0;

    public static FilterHelper create(ResponseFilters responseFilters) {
        FilterHelper helper= new FilterHelper();
        helper.filters = new SparseArray<>();
        helper.names = new SparseArray<>();
        helper.params = new SparseArray<>();
        for (FilterInfo info : responseFilters.filters) {
            ArrayList<FilterDH> dh = new ArrayList<>();
            for (FilterItem item : info.list) {
                if (item.name != null)
                    dh.add(new FilterDH(item));
            }
            helper.filters.put(info.code, dh);
            helper.names.put(info.code, info.name);
            helper.params.put(info.code, new FilterTypeQuery(info.type, info.key));
        }
        return helper;
    }

    public boolean isInitialized() {
        return filters != null;
    }

    public void buildMenu(Menu menu) {
        for (int i = 0; i < names.size(); ++i) {
            menu.add(0, i, i, names.valueAt(i)).setCheckable(true);
        }
    }

    public void setupMenu(CheckFilterCallback callback) {
        if (filters != null)
        for (int i = 0; i < filters.size(); ++i) {
            callback.onCheckedFilter(i, params.get(i).getValues() != null);
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
        params.get(indexSearchableFilter)
                .removeAll()
                .add(filterDH.id);
        callback.onCheckedFilter(indexSearchableFilter, true);
    }

    public boolean filterByText(String text, CheckFilterCallback callback) {
        FilterTypeQuery contactQuery = params.get(indexSearchableFilter);
        contactQuery.removeAll();
        for (FilterDH dh : filters.get(indexSearchableFilter)) {
            if(dh.name.toLowerCase().contains(text)) {
                contactQuery.add(dh.id);
                dh.selected = true;
            } else {
                dh.selected = false;
            }
        }
        boolean needRequest = contactQuery.getValues() != null;
        callback.onCheckedFilter(indexSearchableFilter, needRequest);
        return needRequest;
    }

    public void filterByList(ArrayList<FilterDH> filterDHs, int position, CheckFilterCallback callback) {
        filters.put(position, filterDHs);
        FilterTypeQuery filter = params.get(position);
        filter.removeAll();
        for (FilterDH dh : filterDHs) {
            if(dh.selected) {
                filter.add(dh.id);
            }
        }
        callback.onCheckedFilter(position, filter.getValues() != null);
    }

    public void removeFilter(int position, CheckFilterCallback callback) {
        for (FilterDH dh : filters.get(position)) {
            dh.selected = false;
        }
        params.get(position).removeAll();
        callback.onCheckedFilter(position, false);
    }

    public void removeAllFilters(CheckFilterCallback callback) {
        for (int position = 0; position < filters.size(); ++position) {
            removeFilter(position, callback);
        }
    }


    public Uri.Builder createUrl(String path, String type, int page) {
        Uri.Builder builder = Uri.parse(Constants.BASE_URL).buildUpon();
        builder.appendPath(path)
                .appendQueryParameter("page", String.valueOf(page))
                .appendQueryParameter("count", String.valueOf(Constants.COUNT_LIST_ITEMS))
                .appendQueryParameter("viewType", "list")
                .appendQueryParameter("contentType", type);

        if (params != null) {
            for (int i = 0; i < params.size(); ++i) {
                FilterTypeQuery filter = params.valueAt(i);
                if (filter.getValues() != null) {
                    builder.appendQueryParameter(String.format("filter[%s][key]", filter.getType()), filter.getKey());
                    String queryName = String.format("filter[%s][value][]", filter.getType());
                    for (String value : filter.getValues()) {
                        builder.appendQueryParameter(queryName, value);
                    }
                }
            }
        }

        return builder;
    }

}
