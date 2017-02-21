package com.thinkmobiles.easyerp.presentation.screens.crm.companies;

import android.text.TextUtils;

import com.thinkmobiles.easyerp.data.model.crm.common.alphabet.AlphabetItem;
import com.thinkmobiles.easyerp.data.model.crm.common.images.CustomerImageItem;
import com.thinkmobiles.easyerp.data.model.crm.companies.CommonCompaniesResponse;
import com.thinkmobiles.easyerp.data.model.crm.companies.CompanyListItem;
import com.thinkmobiles.easyerp.data.model.crm.companies.ResponseGetCompanies;
import com.thinkmobiles.easyerp.presentation.base.rules.ErrorViewHelper;
import com.thinkmobiles.easyerp.presentation.base.rules.MasterFlowSelectablePresenterHelper;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.CompanyDH;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.FilterDH;
import com.thinkmobiles.easyerp.presentation.utils.Constants;
import com.thinkmobiles.easyerp.presentation.utils.filter.FilterHelper;

import java.util.ArrayList;

import rx.Observable;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Lynx on 2/2/2017.
 */

public class CompaniesPresenter extends MasterFlowSelectablePresenterHelper<String, CompanyDH> implements CompaniesContract.CompaniesPresenter {

    private CompaniesContract.CompaniesView view;
    private CompaniesContract.CompaniesModel model;
    private CompositeSubscription compositeSubscription;

    private String selectedLetter = "All";
    private int currentPage = 1;
    private int totalItems;

    private ArrayList<AlphabetItem> enabledAlphabetItems = new ArrayList<>();
    private CommonCompaniesResponse companiesResponse = null;
    private FilterHelper helper;

    public CompaniesPresenter(CompaniesContract.CompaniesView view, CompaniesContract.CompaniesModel model) {
        this.view = view;
        this.model = model;
        compositeSubscription = new CompositeSubscription();
        helper = new FilterHelper();

        view.setPresenter(this);
    }

    @Override
    public void selectItem(CompanyDH dh, int position) {
        if (super.selectItem(dh, position, view))
            view.openCompanyDetailsScreen(dh.getId());
    }

    @Override
    public void setLetter(String letter) {
        selectedLetter = letter;
        view.showProgress(Constants.ProgressType.CENTER);
        refresh();
    }

    @Override
    public void subscribe() {
        if (companiesResponse == null && !helper.isInitialized() && enabledAlphabetItems.isEmpty()) {
            loadFilters();
            loadAlphabet();
            getFirstPage();
        } else {
            setData(companiesResponse, true);
        }
    }

    private void getFirstPage() {
        view.showProgress(Constants.ProgressType.CENTER);
        refresh();
    }

    @Override
    public void refresh() {
        loadMore(1);
    }

    @Override
    public void loadNextPage() {
        if (view.getCountItemsNow() == totalItems) {
            return;
        }
        view.showProgress(Constants.ProgressType.BOTTOM);
        loadMore(currentPage + 1);
    }

    @Override
    public void unsubscribe() {
        if (compositeSubscription.hasSubscriptions())
            compositeSubscription.clear();
    }


    private void loadAlphabet() {
        compositeSubscription.add(
                model.getCompaniesAlphabet()
                        .subscribe(responseGetPersonsAlphabet -> {
                            enabledAlphabetItems = responseGetPersonsAlphabet.data;
                            view.displayEnabledLetters(responseGetPersonsAlphabet.data);
                            view.displaySelectedLetter(selectedLetter);
                        }, t -> {
                            t.printStackTrace();
                            view.displayErrorToast(t.getMessage());
                        })
        );
    }

    private void loadFilters() {
        compositeSubscription.add(model.getFilters()
                .flatMap(responseFilters -> Observable.just(FilterHelper.create(responseFilters)))
                .subscribe(filterHelper -> {
                    helper = filterHelper;
                    view.createMenuFilters(helper);
                }, t -> {
                    view.displayErrorToast(t.getMessage());
                }));
    }

    private void loadMore(int page) {
        final boolean needClear = page == 1;
        compositeSubscription.add(
                model.getCompanies(helper, selectedLetter, page)
                        .flatMap(responseGetCompanies -> model.getCompanyImages(prepareIDsForImagesRequest(responseGetCompanies)),
                                CommonCompaniesResponse::new)
                        .subscribe(commonCompaniesResponse -> {
                            currentPage = page;
                            totalItems = commonCompaniesResponse.responseGetCompanies.total;
                            saveData(commonCompaniesResponse, needClear);
                            setData(commonCompaniesResponse, needClear);
                        }, this::error)
        );
    }

    private void error(Throwable t) {
        if (companiesResponse == null) {
            view.displayErrorState(t.getMessage(), ErrorViewHelper.ErrorType.NETWORK);
        } else {
            view.displayErrorToast(t.getMessage());
        }
    }

    private void saveData(CommonCompaniesResponse commonPersonsResponse, boolean needClear) {
        if (needClear) companiesResponse = commonPersonsResponse;
        else if (companiesResponse != null) {
            companiesResponse.responseGetCustomersImages.data.addAll(commonPersonsResponse.responseGetCustomersImages.data);
            companiesResponse.responseGetCompanies.data.addAll(commonPersonsResponse.responseGetCompanies.data);
        }
    }

    private void setData(CommonCompaniesResponse commonPersonsResponse, boolean needClear) {
        if (companiesResponse.responseGetCompanies.data.isEmpty()) {
            view.displayErrorState(null, ErrorViewHelper.ErrorType.LIST_EMPTY);
        } else {
            view.showProgress(Constants.ProgressType.NONE);
            view.displayCompanies(prepareDataHolders(commonPersonsResponse, needClear), needClear);
        }
    }

    private ArrayList<CompanyDH> prepareDataHolders(CommonCompaniesResponse commonCompaniesResponse, boolean needClear) {
        int position = 0;
        ArrayList<CompanyDH> result = new ArrayList<>();
        for (CompanyListItem companyListItem : commonCompaniesResponse.responseGetCompanies.data) {
            for (CustomerImageItem imageItem : commonCompaniesResponse.responseGetCustomersImages.data) {
                if (companyListItem.id.equalsIgnoreCase(imageItem.id)) {
                    final CompanyDH companyDH = new CompanyDH(companyListItem, imageItem.imageSrc);
                    makeSelectedDHIfNeed(companyDH, view, position++, needClear);
                    result.add(companyDH);
                }
            }
        }
        selectFirstElementIfNeed(result, view);
        return result;
    }

    private ArrayList<String> prepareIDsForImagesRequest(ResponseGetCompanies responseGetCompanies) {
        ArrayList<String> companyIDs = new ArrayList<>();
        if (responseGetCompanies.total > 0 && responseGetCompanies.data != null && responseGetCompanies.data.size() > 0) {
            for (CompanyListItem companyListItem : responseGetCompanies.data) {
                companyIDs.add(companyListItem.id);
            }
        }
        return companyIDs;
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
