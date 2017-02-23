package com.thinkmobiles.easyerp.presentation.utils.filter;

import com.thinkmobiles.easyerp.data.model.crm.filter.FilterItem;

import java.util.Comparator;

/**
 * @author Alex Michenko (Created on 15.02.17).
 *         Company: Thinkmobiles
 *         Email: alex.michenko@thinkmobiles.com
 */

public class FilterComparator implements Comparator<FilterItem> {
    @Override
    public int compare(FilterItem o1, FilterItem o2) {
        if (o1.name != null && o2.name != null) {
            return o1.name.compareToIgnoreCase(o2.name);
        } else if (o1.name != null) {
            return -1;
        } else {
            return 1;
        }
    }
}
