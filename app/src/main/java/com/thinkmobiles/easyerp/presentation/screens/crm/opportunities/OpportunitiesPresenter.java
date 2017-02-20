package com.thinkmobiles.easyerp.presentation.screens.crm.opportunities;

import com.thinkmobiles.easyerp.data.model.crm.opportunities.list_item.OpportunityListItem;
import com.thinkmobiles.easyerp.presentation.base.rules.ErrorViewHelper;
import com.thinkmobiles.easyerp.presentation.base.rules.MasterFlowSelectablePresenterHelper;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.FilterDH;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.OpportunityDH;
import com.thinkmobiles.easyerp.presentation.utils.Constants;
import com.thinkmobiles.easyerp.presentation.utils.filter.FilterHelper;

import java.util.ArrayList;

import rx.Observable;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Lynx on 1/30/2017.
 */

public class OpportunitiesPresenter extends MasterFlowSelectablePresenterHelper<String, OpportunityDH> implements OpportunitiesContract.OpportunitiesPresenter {

    private OpportunitiesContract.OpportunitiesView view;
    private OpportunitiesContract.OpportunitiesModel model;
    private CompositeSubscription compositeSubscription;

    private int currentPage = 1;
    private int totalItems = Integer.MAX_VALUE;
    private ArrayList<OpportunityListItem> opportunityItems = new ArrayList<>();
    private FilterHelper helper;

    public OpportunitiesPresenter(OpportunitiesContract.OpportunitiesView view, OpportunitiesContract.OpportunitiesModel model) {
        this.view = view;
        this.model = model;
        compositeSubscription = new CompositeSubscription();
        helper = new FilterHelper();

        view.setPresenter(this);
    }

    private void loadOpportunities(int page) {
        final boolean needClear = page == 1;
        compositeSubscription.add(
                model.getOpportunities(helper, page)
                        .subscribe(responseGetOpportunities -> {
                                    currentPage = page;
                                    totalItems = responseGetOpportunities.total;
                                    saveData(responseGetOpportunities.data, needClear);
                                    setData(responseGetOpportunities.data, needClear);
                                },
                                t -> {
                                    if (opportunityItems.isEmpty()) {
                                        view.displayErrorState(t.getMessage(), ErrorViewHelper.ErrorType.NETWORK);
                                    } else {
                                        view.displayErrorToast(t.getMessage());
                                    }
                                }));
    }

    private void loadFilters() {
        compositeSubscription.add(model.getOpportunityFilters()
                .flatMap(responseFilters -> Observable.just(FilterHelper.create(responseFilters)))
                .subscribe(filterHelper -> {
                    helper = filterHelper;
                    view.createMenuFilters(helper);
                }, t -> {
                    view.displayErrorToast(t.getMessage());
                }));
    }

    private void saveData(final ArrayList<OpportunityListItem> list, boolean needClear) {
        if (needClear)
            this.opportunityItems.clear();
        this.opportunityItems.addAll(list);
    }


    private void setData(final ArrayList<OpportunityListItem> list, boolean needClear) {
        if (opportunityItems.isEmpty()) {
            view.displayErrorState(null, ErrorViewHelper.ErrorType.LIST_EMPTY);
        } else {
            view.showProgress(Constants.ProgressType.NONE);
            view.displayOpportunities(prepareOpportunitiesDHs(list, needClear), needClear);
        }
    }

    @Override
    public void subscribe() {
        if (opportunityItems.isEmpty() && !helper.isInitialized()) {
            getFirstPage();
            loadFilters();
        } else {
            setData(opportunityItems, true);
        }
    }

    private void getFirstPage() {
        view.showProgress(Constants.ProgressType.CENTER);
        refresh();
    }

    @Override
    public void refresh() {
        loadOpportunities(1);
    }

    @Override
    public void loadNextPage() {
        if(view.getCountItemsNow() == totalItems) {
            return;
        }
        view.showProgress(Constants.ProgressType.BOTTOM);
        loadOpportunities(currentPage + 1);
    }

    @Override
    public void unsubscribe() {
        if (compositeSubscription.hasSubscriptions())
            compositeSubscription.clear();
    }

    private ArrayList<OpportunityDH> prepareOpportunitiesDHs(ArrayList<OpportunityListItem> list, boolean needClear) {
        int position = 0;
        final ArrayList<OpportunityDH> result = new ArrayList<>();
        for (OpportunityListItem opportunityItem : list) {
            final OpportunityDH opportunityDH = new OpportunityDH(opportunityItem);
            makeSelectedDHIfNeed(opportunityDH, view, position++, needClear);
            result.add(opportunityDH);
        }
        selectFirstElementIfNeed(result, view);
        return result;
    }

    @Override
    public void selectItem(OpportunityDH dh, int position) {
        if (super.selectItem(dh, position, view))
            view.openOpportunityDetailsScreen(dh.getId());
    }

    @Override
    public void filterBySearchItem(FilterDH filterDH) {
        helper.filterByItem(filterDH, (position, isVisible) -> view.selectFilter(position, isVisible));
        getFirstPage();
    }

    @Override
    public void filterBySearchText(String name) {
        helper.filterByText(name, (position, isVisible) -> view.selectFilter(position, isVisible));
        getFirstPage();
    }

    @Override
    public void filterByList(ArrayList<FilterDH> filterDHs, int requestCode) {
        helper.filterByList(filterDHs, requestCode, (position, isVisible) -> view.selectFilter(position, isVisible));
        getFirstPage();
    }

    @Override
    public void removeFilter(int requestCode) {
        helper.removeFilter(requestCode, (position, isVisible) -> view.selectFilter(position, isVisible));
        getFirstPage();
    }

    @Override
    public void changeFilter(int position, String filterName) {
        view.showFilterDialog(helper.getFilterList(position), position, filterName);
    }

    @Override
    public void buildOptionMenu() {
        view.createMenuFilters(helper);
        helper.setupMenu((position, isVisible) -> view.selectFilter(position, isVisible));
    }

    @Override
    public void removeAll() {
        helper.removeAllFilters((position, isVisible) -> view.selectFilter(position, isVisible));
        getFirstPage();
    }
}
