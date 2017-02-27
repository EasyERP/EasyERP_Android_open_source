package com.thinkmobiles.easyerp.presentation.base.rules.master.alphabetical;

import com.thinkmobiles.easyerp.data.model.crm.common.alphabet.AlphabetItem;
import com.thinkmobiles.easyerp.presentation.base.rules.master.filterable.MasterFilterablePresenterHelper;
import com.thinkmobiles.easyerp.presentation.managers.ErrorManager;

import java.util.ArrayList;

/**
 * @author Alex Michenko (Created on 23.02.17).
 *         Company: Thinkmobiles
 *         Email: alex.michenko@thinkmobiles.com
 */

public abstract class MasterAlphabeticalPresenterHelper extends MasterFilterablePresenterHelper implements AlphabeticalPresenter {

    protected abstract AlphabeticalView getView();
    protected abstract AlphabeticalModel getModel();

    protected String selectedLetter = "All";
    protected ArrayList<AlphabetItem> enabledAlphabetItems = new ArrayList<>();

    @Override
    public void setLetter(String letter) {
        selectedLetter = letter;
        getFirstPage();
    }

    @Override
    public void subscribe() {
        if (!hasContent() && !helper.isInitialized()) {
            loadFilters();
            loadAlphabet();
            getFirstPage();
        } else {
            getView().displayEnabledLetters(enabledAlphabetItems);
            retainInstance();
        }
    }

    protected void loadAlphabet() {
        compositeSubscription.add(
                getModel().getAlphabet()
                        .subscribe(responseGetPersonsAlphabet -> {
                            enabledAlphabetItems = responseGetPersonsAlphabet.data;
                            getView().displayEnabledLetters(responseGetPersonsAlphabet.data);
                            getView().displaySelectedLetter(selectedLetter);
                        }, t -> {
                            getView().displayErrorToast(ErrorManager.getErrorType(t));
                        })
        );
    }
}
