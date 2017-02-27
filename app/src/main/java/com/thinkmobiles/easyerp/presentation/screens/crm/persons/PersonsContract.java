package com.thinkmobiles.easyerp.presentation.screens.crm.persons;

import com.thinkmobiles.easyerp.data.model.crm.common.images.ResponseGetCustomersImages;
import com.thinkmobiles.easyerp.data.model.crm.persons.ResponseGetPersons;
import com.thinkmobiles.easyerp.presentation.base.BaseView;
import com.thinkmobiles.easyerp.presentation.base.rules.master.alphabetical.AlphabeticalModel;
import com.thinkmobiles.easyerp.presentation.base.rules.master.alphabetical.AlphabeticalPresenter;
import com.thinkmobiles.easyerp.presentation.base.rules.master.alphabetical.AlphabeticalView;
import com.thinkmobiles.easyerp.presentation.utils.filter.FilterHelper;

import java.util.ArrayList;

import rx.Observable;

/**
 * Created by Lynx on 1/20/2017.
 */

public interface PersonsContract {
    interface PersonsView extends BaseView<PersonsPresenter>, AlphabeticalView {
        void openDetailsScreen(String personID);
    }
    interface PersonsPresenter extends AlphabeticalPresenter {
    }
    interface PersonsModel extends AlphabeticalModel {
        Observable<ResponseGetCustomersImages> getPersonImages(ArrayList<String> customerIdList);
        Observable<ResponseGetPersons> getPersons(FilterHelper helper, String letter, int page);
    }
}
