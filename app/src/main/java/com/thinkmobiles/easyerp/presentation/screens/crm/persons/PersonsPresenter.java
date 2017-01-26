package com.thinkmobiles.easyerp.presentation.screens.crm.persons;

import android.text.TextUtils;
import android.util.Log;

import com.thinkmobiles.easyerp.data.model.crm.persons.CommonPersonsResponse;
import com.thinkmobiles.easyerp.data.model.crm.persons.ResponseGetPersons;
import com.thinkmobiles.easyerp.data.model.crm.persons.images.CustomerImageItem;
import com.thinkmobiles.easyerp.data.model.crm.persons.person_item.PersonModel;
import com.thinkmobiles.easyerp.presentation.base.rules.MasterFlowSelectablePresenterHelper;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.PersonDH;

import java.util.ArrayList;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by Lynx on 1/20/2017.
 */

public class PersonsPresenter extends MasterFlowSelectablePresenterHelper<String> implements PersonsContract.PersonsPresenter {

    private PersonsContract.PersonsView view;
    private PersonsContract.PersonsModel personsModel;
    private CompositeSubscription compositeSubscription;

    private String selectedLetter;

    public PersonsPresenter(PersonsContract.PersonsView view, PersonsContract.PersonsModel personsModel) {
        this.view = view;
        this.personsModel = personsModel;
        compositeSubscription = new CompositeSubscription();

        view.setPresenter(this);
    }

    @Override
    public void setLetter(String letter) {
        selectedLetter = letter;
        if(selectedLetter.equalsIgnoreCase("All")) selectedLetter = "";
    }

    @Override
    public void loadAlphabet() {
        compositeSubscription.add(
                personsModel.getPersonsAlphabet()
                .subscribe(responseGetPersonsAlphabet -> {
                    view.displayEnabledLetters(responseGetPersonsAlphabet.data);
                }, t -> {})
        );
    }

    @Override
    public void loadMore(int page) {
        final boolean needClear = page == 1;
        if(TextUtils.isEmpty(selectedLetter)) {
            //load all
           compositeSubscription.add(
                   personsModel.getAllPersons(page)
                           .flatMap(responseGetPersons -> personsModel.getCustomerImages(prepareIDsForImagesRequest(responseGetPersons)),
                                   CommonPersonsResponse::new)
                           .subscribe(commonPersonsResponse -> {
                               view.displayPersons(prepareDataHolders(commonPersonsResponse), needClear);
                           })
           );
        } else {
            //load by letter
            compositeSubscription.add(
                    personsModel.getPersonsByLetter(selectedLetter, page)
                            .flatMap(responseGetPersons -> personsModel.getCustomerImages(prepareIDsForImagesRequest(responseGetPersons)),
                                    CommonPersonsResponse::new)
                            .subscribe(commonPersonsResponse -> {
                                view.displayPersons(prepareDataHolders(commonPersonsResponse), needClear);
                            })
            );
        }
    }

    private ArrayList<PersonDH> prepareDataHolders(CommonPersonsResponse commonPersonsResponse) {
        ArrayList<PersonDH> result = new ArrayList<>();
        for(PersonModel personModel : commonPersonsResponse.responseGetPersons.data) {
            for(CustomerImageItem imageItem : commonPersonsResponse.responseGetCustomersImages.data) {
                if(personModel.id.equalsIgnoreCase(imageItem.id)) {
                    result.add(new PersonDH(imageItem.imageSrc, personModel));
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
    public void displayPersonDetails(String personID) {
        Log.d("myLogs", "Display person details ID = " + personID);
        view.openPersonDetailsScreen(personID);
    }

    @Override
    public void subscribe() {
        if(TextUtils.isEmpty(selectedLetter))
            loadAlphabet();
        loadMore(1);
    }

    @Override
    public void unsubscribe() {
        if(compositeSubscription.hasSubscriptions())
            compositeSubscription.clear();
    }
}
