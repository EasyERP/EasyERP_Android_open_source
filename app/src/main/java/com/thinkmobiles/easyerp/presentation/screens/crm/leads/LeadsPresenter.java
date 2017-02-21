package com.thinkmobiles.easyerp.presentation.screens.crm.leads;

import com.thinkmobiles.easyerp.data.model.crm.leads.LeadItem;
import com.thinkmobiles.easyerp.presentation.base.rules.MasterFlowSelectablePresenterHelper;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.FilterDH;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.LeadDH;
import com.thinkmobiles.easyerp.presentation.managers.ErrorManager;
import com.thinkmobiles.easyerp.presentation.utils.Constants;
import com.thinkmobiles.easyerp.presentation.utils.filter.FilterHelper;

import java.util.ArrayList;

import rx.Observable;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Lynx on 1/16/2017.
 */

public class LeadsPresenter extends MasterFlowSelectablePresenterHelper<String, LeadDH> implements LeadsContract.LeadsPresenter {

    private LeadsContract.LeadsView view;
    private LeadsContract.LeadsModel model;
    private CompositeSubscription compositeSubscription;
    private int totalItems = Integer.MAX_VALUE;

    private ArrayList<LeadItem> leadItems = new ArrayList<>();
    private int currentPage = 1;
    private FilterHelper helper;

    public LeadsPresenter(LeadsContract.LeadsView view, LeadsContract.LeadsModel model) {
        this.view = view;
        this.model = model;
        compositeSubscription = new CompositeSubscription();
        helper = new FilterHelper();

        view.setPresenter(this);
    }

    @Override
    public void loadNextPage() {
        if(view.getCountItemsNow() == totalItems) {
            return;
        }
        view.showProgress(Constants.ProgressType.BOTTOM);
        loadLeads(currentPage + 1);
    }

    private void getFirstPage() {
        view.showProgress(Constants.ProgressType.CENTER);
        refresh();
    }

    private void loadLeads(int page) {
        final boolean needClear = page == 1;
        compositeSubscription.add(
                model.getFilteredLeads(helper, page)
                        .subscribe(
                                responseGetLeads -> {
                                    currentPage = page;
                                    totalItems = responseGetLeads.total;
                                    saveData(responseGetLeads.data, needClear);
                                    setData(responseGetLeads.data, needClear);
                                }, t -> {
                                    if (leadItems.isEmpty()) {
                                        view.displayErrorState(ErrorManager.getErrorType(t));
                                    } else {
                                        view.displayErrorToast(ErrorManager.getErrorMessage(t));
                                    }
                                }
                        ));
    }

    private void saveData(ArrayList<LeadItem> leadItems, boolean needClear) {
        if(needClear)
            this.leadItems.clear();
        this.leadItems.addAll(leadItems);
    }

    private void setData(ArrayList<LeadItem> leadItems, boolean needClear) {
        view.displayLeads(prepareLeadDHs(leadItems, needClear), needClear);
        if(this.leadItems.isEmpty()) {
            view.displayErrorState(ErrorManager.getErrorType(null));
        } else {
            view.showProgress(Constants.ProgressType.NONE);
        }
    }

    private void loadFilters() {
        compositeSubscription.add(model.getFilters()
                .flatMap(responseFilters -> Observable.just(FilterHelper.create(responseFilters)))
                .subscribe(filterHelper -> {
                    helper = filterHelper;
                    view.createMenuFilters(helper);
                }, t -> {
                    view.displayErrorState(ErrorManager.getErrorType(t));
                }));
    }

    @Override
    public void selectItem(LeadDH dh, int position) {
        if(super.selectItem(dh, position, view))
            view.openLeadDetailsScreen(dh.getId());
    }

    @Override
    public void subscribe() {
        if (leadItems.isEmpty() && !helper.isInitialized()) {
            getFirstPage();
            loadFilters();
        } else {
            setData(leadItems, true);
        }
    }

    @Override
    public void refresh() {
        loadLeads(1);
    }

    @Override
    public void unsubscribe() {
        if(compositeSubscription.hasSubscriptions())
            compositeSubscription.clear();
    }

    private ArrayList<LeadDH> prepareLeadDHs(ArrayList<LeadItem> leadItems, boolean needClear) {
        int position = 0;
        final ArrayList<LeadDH> result = new ArrayList<>();
        for (LeadItem leadItem : leadItems) {
            final LeadDH leadDH = new LeadDH(leadItem);
            makeSelectedDHIfNeed(leadDH, view, position++, needClear);
            result.add(leadDH);
        }
        selectFirstElementIfNeed(result, view);
        return result;
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
