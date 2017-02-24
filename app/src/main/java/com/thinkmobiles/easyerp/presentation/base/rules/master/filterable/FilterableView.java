package com.thinkmobiles.easyerp.presentation.base.rules.master.filterable;

import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectableView;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.FilterDH;
import com.thinkmobiles.easyerp.presentation.utils.filter.FilterHelper;

import java.util.ArrayList;

/**
 * @author Alex Michenko (Created on 23.02.17).
 *         Company: Thinkmobiles
 *         Email: alex.michenko@thinkmobiles.com
 */

public interface FilterableView extends SelectableView {
    void createMenuFilters(FilterHelper helper);
    void selectFilter(int id, boolean isSelected);

    void showFilterDialog(ArrayList<FilterDH> filterDHs, int requestCode, String filterName);
}
