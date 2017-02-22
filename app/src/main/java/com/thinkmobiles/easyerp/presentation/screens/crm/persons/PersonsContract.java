package com.thinkmobiles.easyerp.presentation.screens.crm.persons;

import com.thinkmobiles.easyerp.data.model.crm.common.alphabet.AlphabetItem;
import com.thinkmobiles.easyerp.data.model.crm.common.alphabet.ResponseGetAlphabet;
import com.thinkmobiles.easyerp.data.model.crm.common.images.ResponseGetCustomersImages;
import com.thinkmobiles.easyerp.data.model.crm.persons.ResponseGetPersons;
import com.thinkmobiles.easyerp.presentation.base.BaseModel;
import com.thinkmobiles.easyerp.presentation.base.rules.ErrorType;
import com.thinkmobiles.easyerp.presentation.base.rules.MasterFlowSelectableBasePresenter;
import com.thinkmobiles.easyerp.presentation.base.rules.MasterFlowSelectableBaseView;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.PersonDH;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import java.util.ArrayList;

import rx.Observable;

/**
 * Created by Lynx on 1/20/2017.
 */

public interface PersonsContract {
    interface PersonsView extends MasterFlowSelectableBaseView<PersonsPresenter> {
        void displayEnabledLetters(ArrayList<AlphabetItem> enabledAlphabetItems);
        void displaySelectedLetter(String selectedLetter);
        void displayPersons(ArrayList<PersonDH> personDHs, boolean needClear);
        void openPersonDetailsScreen(String personID);

        void displayErrorState(final ErrorType errorType);
        void displayErrorToast(final String msg);
        void showProgress(Constants.ProgressType type);
    }
    interface PersonsPresenter extends MasterFlowSelectableBasePresenter<String, PersonDH> {
        void setLetter(String letter);
        void refresh();
        void loadNextPage();
    }
    interface PersonsModel extends BaseModel {
        Observable<ResponseGetAlphabet> getPersonsAlphabet();
        Observable<ResponseGetCustomersImages> getPersonImages(ArrayList<String> customerIdList);
        Observable<ResponseGetPersons> getAllPersons(int page);
        Observable<ResponseGetPersons> getPersonsByLetter(String letter, int page);
    }
}
