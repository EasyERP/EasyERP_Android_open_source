package com.thinkmobiles.easyerp.domain.crm;

import com.thinkmobiles.easyerp.data.api.Rest;
import com.thinkmobiles.easyerp.data.model.crm.common.alphabet.ResponseGetAlphabet;
import com.thinkmobiles.easyerp.data.model.crm.common.images.ResponseGetCustomersImages;
import com.thinkmobiles.easyerp.data.model.crm.persons.ResponseGetPersons;
import com.thinkmobiles.easyerp.data.model.crm.persons.details.ResponseGetPersonDetails;
import com.thinkmobiles.easyerp.data.services.CustomerService;
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
    private CustomerService customerService;

    public PersonsRepository() {
        personsService = Rest.getInstance().getPersonsService();
        customerService = Rest.getInstance().getCustomerService();
    }

    private <T> Observable<T> getNetworkObservable(Observable<T> observable) {
        return observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread());
    }

    public Observable<ResponseGetAlphabet> getPersonsAlphabet() {
        return getNetworkObservable(personsService.getPersonsAlphabet("Persons"));
    }

    public Observable<ResponseGetCustomersImages> getPersonImages(ArrayList<String> customerIdList) {
        return getNetworkObservable(customerService.getCustomerImages(customerIdList));
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
