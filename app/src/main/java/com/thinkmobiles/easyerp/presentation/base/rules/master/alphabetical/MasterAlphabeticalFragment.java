package com.thinkmobiles.easyerp.presentation.base.rules.master.alphabetical;

import android.view.View;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.data.model.crm.common.alphabet.AlphabetItem;
import com.thinkmobiles.easyerp.presentation.base.rules.master.filterable.MasterFilterableFragment;
import com.thinkmobiles.easyerp.presentation.custom.views.alphabet_view.AlphabetView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

/**
 * @author Alex Michenko (Created on 23.02.17).
 *         Company: Thinkmobiles
 *         Email: alex.michenko@thinkmobiles.com
 */

@EFragment
public abstract class MasterAlphabeticalFragment extends MasterFilterableFragment implements AlphabeticalView {

    protected abstract AlphabeticalPresenter getPresenter();

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_list_with_alphabet;
    }

    @ViewById
    protected AlphabetView alphabetView;

    @AfterViews
    protected void initAlphabet() {
        alphabetView.setListener(letter -> {
            scrollListener.reset();
            getPresenter().setLetter(letter);
        });
    }

    @Override
    public void displayEnabledLetters(ArrayList<AlphabetItem> enabledAlphabetItems) {
        alphabetView.setVisibility(View.VISIBLE);
        alphabetView.setEnabledLetters(enabledAlphabetItems);
    }

    @Override
    public void displaySelectedLetter(String selectedLetter) {
        alphabetView.selectLetterWithoutListener(selectedLetter);
    }


}
