package com.thinkmobiles.easyerp.domain.crm;

import com.thinkmobiles.easyerp.data.api.Rest;
import com.thinkmobiles.easyerp.data.model.crm.persons.ResponseGetPersons;
import com.thinkmobiles.easyerp.data.model.crm.persons.alphabet.ResponseGetPersonsAlphabet;
import com.thinkmobiles.easyerp.data.model.crm.persons.details.ResponseGetPersonDetails;
import com.thinkmobiles.easyerp.data.model.crm.persons.images.ResponseGetCustomersImages;
import com.thinkmobiles.easyerp.data.services.PersonsService;
import com.thinkmobiles.easyerp.presentation.screens.crm.persons.PersonsContract;
import com.thinkmobiles.easyerp.presentation.screens.crm.persons.details.PersonDetailsContract;

import org.androidannotations.annotations.EBean;

import java.util.ArrayList;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Lynx on 1/20/2017.
 */

@EBean(scope = EBean.Scope.Singleton)
public class PersonsRepository implements PersonsContract.PersonsModel, PersonDetailsContract.PersonDetailsModel {

    private PersonsService personsService;

    public PersonsRepository() {
        personsService = Rest.getInstance().getPersonsService();
    }

    private <T> Observable<T> getNetworkObservable(Observable<T> observable) {
        return observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread());
    }

    public Observable<ResponseGetPersonsAlphabet> getPersonsAlphabet() {
        return getNetworkObservable(personsService.getPersonsAlphabet("Persons"));
    }

    public Observable<ResponseGetCustomersImages> getCustomerImages(ArrayList<String> customerIdList) {
        return getNetworkObservable(personsService.getCustomerImages(customerIdList));
    }

    public Observable<ResponseGetPersons> getAllPersons(int page) {
        return getNetworkObservable(personsService.getAllPersons("list", 50, "Persons", page));
    }

    public Observable<ResponseGetPersons> getPersonsByLetter(String letter, int page) {
        return getNetworkObservable(personsService.getPersonsByLetter("list", "name.first", letter, "letter", "Persons", 50, page));
    }

    public Observable<ResponseGetPersonDetails> getPersonDetails(String personID) {
        return getNetworkObservable(personsService.getPersonDetails(personID));
    }
}
