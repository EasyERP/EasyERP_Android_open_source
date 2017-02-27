package com.thinkmobiles.easyerp.presentation.base.rules.master.filterable;

import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectablePresenter;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.FilterDH;

import java.util.ArrayList;

/**
 * @author Alex Michenko (Created on 22.02.17).
 *         Company: Thinkmobiles
 *         Email: alex.michenko@thinkmobiles.com
 */

public interface FilterablePresenter extends SelectablePresenter {

    void filterBySearchItem(FilterDH filterDH);
    void filterBySearchText(String name);
    void filterByList(ArrayList<FilterDH> filterDHs, int requestCode);
    void removeFilter(int requestCode);

    void changeFilter(int position, String filterName);
    void buildOptionMenu();
    void removeAll();
}
