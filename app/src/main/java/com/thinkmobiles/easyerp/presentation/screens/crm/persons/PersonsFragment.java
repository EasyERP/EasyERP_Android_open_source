package com.thinkmobiles.easyerp.presentation.screens.crm.persons;

import android.view.View;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.data.model.crm.common.alphabet.AlphabetItem;
import com.thinkmobiles.easyerp.domain.crm.PersonsRepository;
import com.thinkmobiles.easyerp.presentation.adapters.crm.PersonsAdapter;
import com.thinkmobiles.easyerp.presentation.base.rules.ErrorViewHelper;
import com.thinkmobiles.easyerp.presentation.base.rules.MasterFlowListSelectableFragment;
import com.thinkmobiles.easyerp.presentation.custom.views.alphabet_view.AlphabetView;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.PersonDH;
import com.thinkmobiles.easyerp.presentation.screens.crm.persons.details.PersonDetailsFragment_;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

/**
 * Created by Lynx on 1/20/2017.
 */

@EFragment
public class PersonsFragment extends MasterFlowListSelectableFragment implements PersonsContract.PersonsView {

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_list_with_alphabet;
    }

    private PersonsContract.PersonsPresenter presenter;

    @ViewById
    protected AlphabetView alphabetView;

    @Bean
    protected PersonsAdapter personsAdapter;
    @Bean
    protected PersonsRepository personsRepository;

    @AfterInject
    @Override
    public void initPresenter() {
        new PersonsPresenter(this, personsRepository);
    }

    @AfterViews
    protected void initUI() {
        listRecycler.setAdapter(personsAdapter);
        personsAdapter.setOnCardClickListener((view, position, viewType) -> presenter.selectItem(personsAdapter.getItem(position), position));

        alphabetView.setListener(letter -> presenter.setLetter(letter));

        presenter.subscribe();
    }

    @Override
    protected void onLoadNextPage() {
        presenter.loadNextPage();
    }

    @Override
    protected void onRetry() {
        presenter.subscribe();
    }

    @Override
    public void onRefreshData() {
        super.onRefreshData();
        presenter.refresh();
    }

    @Override
    public void displayEnabledLetters(ArrayList<AlphabetItem> enabledAlphabetItems) {
        alphabetView.setEnabledLetters(enabledAlphabetItems);
    }

    @Override
    public void displaySelectedLetter(String selectedLetter) {
        alphabetView.selectLetterWithoutListener(selectedLetter);
    }

    @Override
    public void displayPersons(ArrayList<PersonDH> personDHs, boolean needClear) {
        alphabetView.setVisibility(View.VISIBLE);
        if (needClear) {
            personsAdapter.setListDH(personDHs);
        } else {
            personsAdapter.addListDH(personDHs);
        }
    }

    @Override
    public void displayErrorState(String msg, ErrorViewHelper.ErrorType errorType) {
        showErrorState(msg, errorType);
    }

    @Override
    public void displayErrorToast(String msg) {
        showErrorToast(msg);
    }

    @Override
    public void openPersonDetailsScreen(String personID) {
        mActivity.replaceFragmentContentDetail(PersonDetailsFragment_.builder()
                .personID(personID)
                .build());
    }

    @Override
    public void showProgress(Constants.ProgressType type) {
        showProgressBar(type);
    }

    @Override
    public void setPresenter(PersonsContract.PersonsPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public int getCountItemsNow() {
        return personsAdapter.getItemCount();
    }

    @Override
    public void changeSelectedItem(int oldPosition, int newPosition) {
        personsAdapter.replaceSelectedItem(oldPosition, newPosition);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.unsubscribe();
    }

    @Override
    public void clearSelectedItem() {
        presenter.clearSelectedInfo();
    }
}
