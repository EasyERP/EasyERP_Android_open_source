package com.thinkmobiles.easyerp.presentation.screens.crm.persons;

import com.thinkmobiles.easyerp.domain.crm.PersonsRepository;
import com.thinkmobiles.easyerp.presentation.adapters.crm.PersonsAdapter;
import com.thinkmobiles.easyerp.presentation.base.rules.master.alphabetical.AlphabeticalPresenter;
import com.thinkmobiles.easyerp.presentation.base.rules.master.alphabetical.MasterAlphabeticalFragment;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectableAdapter;
import com.thinkmobiles.easyerp.presentation.managers.GoogleAnalyticHelper;
import com.thinkmobiles.easyerp.presentation.screens.crm.persons.details.PersonDetailsFragment_;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;

/**
 * Created by Lynx on 1/20/2017.
 */

@EFragment
public class PersonsFragment extends MasterAlphabeticalFragment implements PersonsContract.PersonsView {

    private PersonsContract.PersonsPresenter presenter;

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
        GoogleAnalyticHelper.trackScreenView(this, getResources().getConfiguration());

        alphabetView.setListener(letter -> {
            GoogleAnalyticHelper.trackClick(this, GoogleAnalyticHelper.EventType.SET_FILTER, "Letter " + letter);
            presenter.setLetter(letter);
        });
    }

    @Override
    protected AlphabeticalPresenter getPresenter() {
        return presenter;
    }

    @Override
    protected SelectableAdapter getAdapter() {
        return personsAdapter;
    }

    @Override
    public void openDetailsScreen(String personID) {
        if (personID != null) {
            getMasterDelegate().replaceFragmentContentDetail(PersonDetailsFragment_.builder()
                    .personID(personID)
                    .build());
        } else {
            getMasterDelegate().replaceFragmentContentDetail(null);
        }
    }

    @Override
    public void setPresenter(PersonsContract.PersonsPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public String getScreenName() {
        return "Person list screen";
    }

}
