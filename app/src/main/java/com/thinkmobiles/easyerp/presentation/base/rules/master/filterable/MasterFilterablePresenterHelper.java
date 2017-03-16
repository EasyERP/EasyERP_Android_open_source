package com.thinkmobiles.easyerp.presentation.base.rules.master.filterable;

import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.MasterSelectablePresenterHelper;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.FilterDH;
import com.thinkmobiles.easyerp.presentation.managers.ErrorManager;
import com.thinkmobiles.easyerp.presentation.utils.filter.FilterHelper;

import java.util.ArrayList;

import rx.Observable;

/**
 * @author Alex Michenko (Created on 23.02.17).
 *         Company: Thinkmobiles
 *         Email: alex.michenko@thinkmobiles.com
 */

public abstract class MasterFilterablePresenterHelper extends MasterSelectablePresenterHelper implements FilterablePresenter {

    protected abstract FilterableView getView();
    protected abstract FilterableModel getModel();

    protected FilterHelper helper = new FilterHelper();

    @Override
    public void subscribe() {
        if (!hasContent() && !helper.isInitialized()) {
            loadFilters();
            getFirstPage();
        } else {
            retainInstance();
        }
    }

    protected void loadFilters() {
        compositeSubscription.add(getModel().getFilters()
                .flatMap(responseFilters -> Observable.just(FilterHelper.create(responseFilters)))
                .subscribe(filterHelper -> {
                    helper = filterHelper;
                    getView().createMenuFilters(helper);
                }, t -> {
                    t.printStackTrace();
                    getView().displayErrorToast(ErrorManager.getErrorType(t));
                }));
    }

    @Override
    public void filterBySearchItem(FilterDH filterDH) {
        helper.filterByItem(filterDH, (position, isVisible) -> getView().selectFilter(position, isVisible));
        getFirstPage();
    }

    @Override
    public void filterBySearchText(String name) {
        if (helper.filterByText(name, (position, isVisible) -> getView().selectFilter(position, isVisible))) {
            getFirstPage();
        }
    }

    @Override
    public void filterByList(ArrayList<FilterDH> filterDHs, int requestCode) {
        helper.filterByList(filterDHs, requestCode, (position, isVisible) -> getView().selectFilter(position, isVisible));
        getFirstPage();
    }

    @Override
    public void removeFilter(int requestCode) {
        helper.removeFilter(requestCode, (position, isVisible) -> getView().selectFilter(position, isVisible));
        getFirstPage();
    }

    @Override
    public void changeFilter(int position, String filterName) {
        getView().showFilterDialog(helper.getFilterList(position), position, filterName);
    }

    @Override
    public void buildOptionMenu() {
        getView().createMenuFilters(helper);
        helper.setupMenu((position, isVisible) -> getView().selectFilter(position, isVisible));
    }

    @Override
    public void removeAll() {
        helper.removeAllFilters((position, isVisible) -> getView().selectFilter(position, isVisible));
        getFirstPage();
    }
}
