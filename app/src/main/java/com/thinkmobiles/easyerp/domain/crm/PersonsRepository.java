package com.thinkmobiles.easyerp.domain.crm;

import android.net.Uri;

import com.thinkmobiles.easyerp.data.api.Rest;
import com.thinkmobiles.easyerp.data.model.ResponseGetTotalItems;
import com.thinkmobiles.easyerp.data.model.crm.common.alphabet.ResponseGetAlphabet;
import com.thinkmobiles.easyerp.data.model.crm.common.images.ImageItem;
import com.thinkmobiles.easyerp.data.model.crm.filter.ResponseFilters;
import com.thinkmobiles.easyerp.data.model.crm.persons.details.ResponseGetPersonDetails;
import com.thinkmobiles.easyerp.data.model.crm.persons.person_item.PersonModel;
import com.thinkmobiles.easyerp.data.services.ImageService;
import com.thinkmobiles.easyerp.data.services.FilterService;
import com.thinkmobiles.easyerp.data.services.PersonsService;
import com.thinkmobiles.easyerp.presentation.base.NetworkRepository;
import com.thinkmobiles.easyerp.presentation.screens.crm.persons.PersonsContract;
import com.thinkmobiles.easyerp.presentation.screens.crm.persons.details.PersonDetailsContract;
import com.thinkmobiles.easyerp.presentation.utils.Constants;
import com.thinkmobiles.easyerp.presentation.utils.filter.FilterHelper;

import org.androidannotations.annotations.EBean;

import java.util.ArrayList;

import rx.Observable;

/**
 * Created by Lynx on 1/20/2017.
 */

@EBean(scope = EBean.Scope.Singleton)
public class PersonsRepository extends NetworkRepository implements PersonsContract.PersonsModel, PersonDetailsContract.PersonDetailsModel {

    private PersonsService personsService;
    private ImageService imageService;
    private FilterService filterService;

    public PersonsRepository() {
        personsService = Rest.getInstance().getPersonsService();
        imageService = Rest.getInstance().getImageService();
        filterService = Rest.getInstance().getFilterService();
    }

    @Override
    public Observable<ResponseGetAlphabet> getAlphabet() {
        return getNetworkObservable(personsService.getPersonsAlphabet("Persons"));
    }

    @Override
    public Observable<ResponseGetTotalItems<ImageItem>> getPersonImages(ArrayList<String> customerIdList) {
        return getNetworkObservable(imageService.getCustomerImages(customerIdList));
    }

    @Override
    public Observable<ResponseGetPersonDetails> getPersonDetails(String personID) {
        return getNetworkObservable(personsService.getPersonDetails(personID));
    }

    @Override
    public Observable<ResponseGetTotalItems<PersonModel>> getPersons(FilterHelper helper, String letter, int page) {
        Uri.Builder builder = helper.createUrl(Constants.GET_PERSONS, "Persons", page);
        if (!letter.equalsIgnoreCase("All")) {
            builder.appendQueryParameter("filter[letter][key]", "name.first")
                    .appendQueryParameter("filter[letter][value]", letter)
                    .appendQueryParameter("filter[letter][type]", "letter");
        }
        return getNetworkObservable(personsService.getPersons(builder.build().toString()));
    }

    @Override
    public Observable<ResponseFilters> getFilters() {
        return getNetworkObservable(filterService.getListFilters("Persons"));
    }
}
