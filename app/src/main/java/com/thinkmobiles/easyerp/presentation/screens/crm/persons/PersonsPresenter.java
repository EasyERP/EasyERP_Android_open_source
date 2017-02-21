package com.thinkmobiles.easyerp.presentation.screens.crm.persons;

import com.thinkmobiles.easyerp.data.model.crm.common.alphabet.AlphabetItem;
import com.thinkmobiles.easyerp.data.model.crm.common.images.CustomerImageItem;
import com.thinkmobiles.easyerp.data.model.crm.persons.CommonPersonsResponse;
import com.thinkmobiles.easyerp.data.model.crm.persons.ResponseGetPersons;
import com.thinkmobiles.easyerp.data.model.crm.persons.person_item.PersonModel;
import com.thinkmobiles.easyerp.presentation.base.rules.ErrorViewHelper;
import com.thinkmobiles.easyerp.presentation.base.rules.MasterFlowSelectablePresenterHelper;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.FilterDH;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.PersonDH;
import com.thinkmobiles.easyerp.presentation.managers.ErrorManager;
import com.thinkmobiles.easyerp.presentation.utils.Constants;
import com.thinkmobiles.easyerp.presentation.utils.filter.FilterHelper;

import java.util.ArrayList;

import rx.Observable;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Lynx on 1/20/2017.
 */

public class PersonsPresenter extends MasterFlowSelectablePresenterHelper<String, PersonDH> implements PersonsContract.PersonsPresenter {

    private PersonsContract.PersonsView view;
    private PersonsContract.PersonsModel model;
    private CompositeSubscription compositeSubscription;

    private String selectedLetter = "All";
    private int currentPage = 1;
    private int totalItems;

    private ArrayList<AlphabetItem> enabledAlphabetItems = new ArrayList<>();
    private CommonPersonsResponse personsResponse;
    private FilterHelper helper;

    public PersonsPresenter(PersonsContract.PersonsView view, PersonsContract.PersonsModel model) {
        this.view = view;
        this.model = model;
        compositeSubscription = new CompositeSubscription();
        helper = new FilterHelper();

        view.setPresenter(this);
    }

    @Override
    public void selectItem(PersonDH dh, int position) {
        if (super.selectItem(dh, position, view))
            view.openPersonDetailsScreen(dh.getId());
    }

    @Override
    public void setLetter(String letter) {
        selectedLetter = letter;
        view.showProgress(Constants.ProgressType.CENTER);
        refresh();
    }

    private void loadAlphabet() {
        compositeSubscription.add(
                model.getPersonsAlphabet()
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

    private void loadMore(final int page) {
        final boolean needClear = page == 1;
        compositeSubscription.add(
                model.getPersons(helper, selectedLetter, page)
                        .flatMap(responseGetPersons -> model.getPersonImages(prepareIDsForImagesRequest(responseGetPersons)),
                                CommonPersonsResponse::new)
                        .subscribe(commonPersonsResponse -> {
                            currentPage = page;
                            totalItems = commonPersonsResponse.responseGetPersons.total;
                            saveData(commonPersonsResponse, needClear);
                            setData(commonPersonsResponse, needClear);
                        }, this::error)
        );
    }

    private void error(Throwable t) {
        if (personsResponse == null) {
            view.displayErrorState(ErrorManager.getErrorType(t));
        } else {
            view.displayErrorToast(ErrorManager.getErrorMessage(t));
        }
    }

    private void saveData(CommonPersonsResponse commonPersonsResponse, boolean needClear) {
        if (needClear) personsResponse = commonPersonsResponse;
        else if (personsResponse != null) {
            personsResponse.responseGetCustomersImages.data.addAll(commonPersonsResponse.responseGetCustomersImages.data);
            personsResponse.responseGetPersons.data.addAll(commonPersonsResponse.responseGetPersons.data);
        }
    }

    private void setData(CommonPersonsResponse commonPersonsResponse, boolean needClear) {
        view.displayPersons(prepareDataHolders(commonPersonsResponse, needClear), needClear);
        if (personsResponse.responseGetPersons.data.isEmpty()) {
            view.displayErrorState(ErrorManager.getErrorType(null));
        } else {
            view.showProgress(Constants.ProgressType.NONE);
        }
    }

    @Override
    public void refresh() {
        loadMore(1);
    }

    private ArrayList<PersonDH> prepareDataHolders(CommonPersonsResponse commonPersonsResponse, boolean needClear) {
        int position = 0;
        ArrayList<PersonDH> result = new ArrayList<>();
        for (PersonModel personModel : commonPersonsResponse.responseGetPersons.data) {
            for (CustomerImageItem imageItem : commonPersonsResponse.responseGetCustomersImages.data) {
                if (personModel.id.equalsIgnoreCase(imageItem.id)) {
                    final PersonDH personDH = new PersonDH(imageItem.imageSrc, personModel);
                    makeSelectedDHIfNeed(personDH, view, position++, needClear);
                    result.add(personDH);
                }
            }
        }
        selectFirstElementIfNeed(result, view);
        return result;
    }

    private ArrayList<String> prepareIDsForImagesRequest(ResponseGetPersons responseGetPersons) {
        ArrayList<String> personIDs = new ArrayList<>();
        if (responseGetPersons.total > 0 && responseGetPersons.data != null && responseGetPersons.data.size() > 0) {
            for (PersonModel personModel : responseGetPersons.data) {
                personIDs.add(personModel.id);
            }
        }
        return personIDs;
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
    public void subscribe() {
        if (personsResponse == null && !helper.isInitialized() && enabledAlphabetItems.isEmpty()) {
            loadFilters();
            loadAlphabet();
            getFirstPage();
        } else {
            setData(personsResponse, true);
        }
    }

    private void getFirstPage() {
        view.showProgress(Constants.ProgressType.CENTER);
        refresh();
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

    @Override
    public void unsubscribe() {
        if (compositeSubscription.hasSubscriptions())
            compositeSubscription.clear();
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
