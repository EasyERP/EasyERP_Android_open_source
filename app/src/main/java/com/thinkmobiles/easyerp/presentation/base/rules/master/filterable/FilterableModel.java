package com.thinkmobiles.easyerp.presentation.base.rules.master.filterable;

import com.thinkmobiles.easyerp.data.model.crm.filter.ResponseFilters;

import rx.Observable;

/**
 * @author Alex Michenko (Created on 23.02.17).
 *         Company: Thinkmobiles
 *         Email: alex.michenko@thinkmobiles.com
 */

public interface FilterableModel {
    Observable<ResponseFilters> getFilters();
}
