package com.thinkmobiles.easyerp.presentation.screens.crm.persons;

import com.thinkmobiles.easyerp.data.model.crm.common.images.CustomerImageItem;
import com.thinkmobiles.easyerp.data.model.crm.persons.CommonPersonsResponse;
import com.thinkmobiles.easyerp.data.model.crm.persons.ResponseGetPersons;
import com.thinkmobiles.easyerp.data.model.crm.persons.person_item.PersonModel;
import com.thinkmobiles.easyerp.presentation.base.rules.master.alphabetical.AlphabeticalModel;
import com.thinkmobiles.easyerp.presentation.base.rules.master.alphabetical.AlphabeticalView;
import com.thinkmobiles.easyerp.presentation.base.rules.master.alphabetical.MasterAlphabeticalPresenterHelper;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.PersonDH;
import com.thinkmobiles.easyerp.presentation.managers.ErrorManager;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import java.util.ArrayList;

/**
 * Created by Lynx on 1/20/2017.
 */

public class PersonsPresenter extends MasterAlphabeticalPresenterHelper implements PersonsContract.PersonsPresenter {

    private PersonsContract.PersonsView view;
    private PersonsContract.PersonsModel model;

    private CommonPersonsResponse personsResponse;

    public PersonsPresenter(PersonsContract.PersonsView view, PersonsContract.PersonsModel model) {
        this.view = view;
        this.model = model;

        view.setPresenter(this);
    }

    @Override
    protected AlphabeticalView getView() {
        return view;
    }

    @Override
    protected AlphabeticalModel getModel() {
        return model;
    }

    @Override
    protected void loadPage(int page) {
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
                        },  t -> error(t))
        );
    }

    @Override
    protected int getCountItems() {
        return personsResponse.responseGetPersons.data.size();
    }

    @Override
    protected boolean hasContent() {
        return personsResponse != null;
    }

    @Override
    protected void retainInstance() {
        setData(personsResponse, true);
    }

    @Override
    public void clickItem(int position) {
        String id = personsResponse.responseGetPersons.data.get(position)._id;
        if (super.selectItem(id, position))
            view.openDetailsScreen(id);
    }

    private void saveData(CommonPersonsResponse commonPersonsResponse, boolean needClear) {
        if (needClear) personsResponse = commonPersonsResponse;
        else if (personsResponse != null) {
            personsResponse.responseGetCustomersImages.data.addAll(commonPersonsResponse.responseGetCustomersImages.data);
            personsResponse.responseGetPersons.data.addAll(commonPersonsResponse.responseGetPersons.data);
        }
    }

    private void setData(CommonPersonsResponse commonPersonsResponse, boolean needClear) {
        view.displaySelectedLetter(selectedLetter);
        view.setDataList(prepareDataHolders(commonPersonsResponse, needClear), needClear);
        if (personsResponse.responseGetPersons.data.isEmpty()) {
            view.displayErrorState(ErrorManager.getErrorType(null));
        } else {
            view.showProgress(Constants.ProgressType.NONE);
        }
    }

    private ArrayList<PersonDH> prepareDataHolders(CommonPersonsResponse commonPersonsResponse, boolean needClear) {
        int position = 0;
        ArrayList<PersonDH> result = new ArrayList<>();
        for (PersonModel personModel : commonPersonsResponse.responseGetPersons.data) {
            for (CustomerImageItem imageItem : commonPersonsResponse.responseGetCustomersImages.data) {
                if (personModel.id.equalsIgnoreCase(imageItem.id)) {
                    final PersonDH personDH = new PersonDH(imageItem.imageSrc, personModel);
                    makeSelectedDHIfNeed(personDH, position++, needClear);
                    result.add(personDH);
                }
            }
        }
        selectFirstElementIfNeed(result);
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
}
