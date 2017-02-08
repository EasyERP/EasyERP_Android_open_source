package com.thinkmobiles.easyerp.presentation.screens.crm.persons;

import android.text.TextUtils;

import com.thinkmobiles.easyerp.data.model.crm.common.alphabet.AlphabetItem;
import com.thinkmobiles.easyerp.data.model.crm.common.images.CustomerImageItem;
import com.thinkmobiles.easyerp.data.model.crm.persons.CommonPersonsResponse;
import com.thinkmobiles.easyerp.data.model.crm.persons.ResponseGetPersons;
import com.thinkmobiles.easyerp.data.model.crm.persons.person_item.PersonModel;
import com.thinkmobiles.easyerp.presentation.base.rules.ErrorViewHelper;
import com.thinkmobiles.easyerp.presentation.base.rules.MasterFlowSelectablePresenterHelper;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.PersonDH;

import java.util.ArrayList;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by Lynx on 1/20/2017.
 */

public class PersonsPresenter extends MasterFlowSelectablePresenterHelper<String, PersonDH> implements PersonsContract.PersonsPresenter {

    private PersonsContract.PersonsView view;
    private PersonsContract.PersonsModel personsModel;
    private CompositeSubscription compositeSubscription;

    private String selectedLetter = "All";
    private int page = 1;

    private ArrayList<AlphabetItem> enabledAlphabetItems = new ArrayList<>();
    private CommonPersonsResponse personsResponse = null;

    public PersonsPresenter(PersonsContract.PersonsView view, PersonsContract.PersonsModel personsModel) {
        this.view = view;
        this.personsModel = personsModel;
        compositeSubscription = new CompositeSubscription();

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
    }

    @Override
    public void loadAlphabet() {
        compositeSubscription.add(
                personsModel.getPersonsAlphabet()
                .subscribe(responseGetPersonsAlphabet -> {
                    enabledAlphabetItems = responseGetPersonsAlphabet.data;
                    view.displayEnabledLetters(responseGetPersonsAlphabet.data);
                }, t -> {})
        );
    }

    @Override
    public void loadMore(int page) {
        this.page = page;
        final boolean needClear = page == 1;
        if(selectedLetter.equalsIgnoreCase("All")) {
            //load all
           compositeSubscription.add(
                   personsModel.getAllPersons(page)
                           .flatMap(responseGetPersons -> personsModel.getPersonImages(prepareIDsForImagesRequest(responseGetPersons)),
                                   CommonPersonsResponse::new)
                           .subscribe(commonPersonsResponse -> {

                               if(needClear) personsResponse = commonPersonsResponse;
                               else if(personsResponse != null) {
                                   personsResponse.responseGetCustomersImages.data.addAll(commonPersonsResponse.responseGetCustomersImages.data);
                                   personsResponse.responseGetPersons.data.addAll(commonPersonsResponse.responseGetPersons.data);
                               }

                               view.displayPersons(prepareDataHolders(commonPersonsResponse), needClear);
                           }, t -> view.displayError(t.getMessage(), ErrorViewHelper.ErrorType.NETWORK))
           );
        } else {
            //load by letter
            compositeSubscription.add(
                    personsModel.getPersonsByLetter(selectedLetter, page)
                            .flatMap(responseGetPersons -> personsModel.getPersonImages(prepareIDsForImagesRequest(responseGetPersons)),
                                    CommonPersonsResponse::new)
                            .subscribe(commonPersonsResponse -> {

                                if(needClear) personsResponse = commonPersonsResponse;
                                else if(personsResponse != null) {
                                    personsResponse.responseGetCustomersImages.data.addAll(commonPersonsResponse.responseGetCustomersImages.data);
                                    personsResponse.responseGetPersons.data.addAll(commonPersonsResponse.responseGetPersons.data);
                                }

                                view.displayPersons(prepareDataHolders(commonPersonsResponse), needClear);
                            }, t -> view.displayError(t.getMessage(), ErrorViewHelper.ErrorType.NETWORK))
            );
        }
    }

    @Override
    public void refresh() {
        loadAlphabet();
        loadMore(page);
    }

    @Override
    public int getCurrentPage() {
        return page;
    }

    private ArrayList<PersonDH> prepareDataHolders(CommonPersonsResponse commonPersonsResponse) {
        int position = 0;
        ArrayList<PersonDH> result = new ArrayList<>();
        for(PersonModel personModel : commonPersonsResponse.responseGetPersons.data) {
            for(CustomerImageItem imageItem : commonPersonsResponse.responseGetCustomersImages.data) {
                if(personModel.id.equalsIgnoreCase(imageItem.id)) {
                    final PersonDH personDH = new PersonDH(imageItem.imageSrc, personModel);
                    makeSelectedDHIfNeed(personDH, view, position++, true);
                    result.add(personDH);
                }
            }
        }
        return result;
    }

    private ArrayList<String> prepareIDsForImagesRequest(ResponseGetPersons responseGetPersons) {
        ArrayList<String> personIDs = new ArrayList<>();
        if(responseGetPersons.total > 0 && responseGetPersons.data != null && responseGetPersons.data.size() > 0) {
            for(PersonModel personModel : responseGetPersons.data) {
                personIDs.add(personModel.id);
            }
        }
        return personIDs;
    }

    @Override
    public void subscribe() {
        if(enabledAlphabetItems.isEmpty()) {
            loadAlphabet();
        } else {
            view.displayEnabledLetters(enabledAlphabetItems);
            if(!TextUtils.isEmpty(selectedLetter)) view.displaySelectedLetter(selectedLetter);
        }

        if(personsResponse == null) {
            view.showProgress(true);
            loadMore(1);
        } else {
            view.displayPersons(prepareDataHolders(personsResponse), true);
        }
    }

    @Override
    public void unsubscribe() {
        if (compositeSubscription.hasSubscriptions())
            compositeSubscription.clear();
    }
}
