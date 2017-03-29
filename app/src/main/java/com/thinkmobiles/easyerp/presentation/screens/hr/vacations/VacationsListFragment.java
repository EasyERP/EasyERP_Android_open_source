package com.thinkmobiles.easyerp.presentation.screens.hr.vacations;

import android.widget.Toast;

import com.thinkmobiles.easyerp.domain.hr.VacationsRepository;
import com.thinkmobiles.easyerp.presentation.adapters.hr.VacationsListAdapter;
import com.thinkmobiles.easyerp.presentation.base.rules.master.alphabetical.AlphabeticalPresenter;
import com.thinkmobiles.easyerp.presentation.base.rules.master.alphabetical.MasterAlphabeticalFragment;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectableAdapter;
import com.thinkmobiles.easyerp.presentation.managers.GoogleAnalyticHelper;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;

import java.util.Locale;

/**
 * Created by Lynx on 3/29/2017.
 */

@EFragment
public class VacationsListFragment extends MasterAlphabeticalFragment implements VacationsListContract.VacationsListView {

    private VacationsListContract.VacationsListPresenter presenter;

    @Bean
    protected VacationsListAdapter vacationsListAdapter;
    @Bean
    protected VacationsRepository vacationsRepository;

    @AfterInject
    @Override
    public void initPresenter() {
        new VacationsListPresenter(this, vacationsRepository);
    }

    @Override
    protected void initList() {
        GoogleAnalyticHelper.trackScreenView(this, getResources().getConfiguration());
        alphabetView.setForYears(true);
        super.initList();
    }

    @Override
    public void setPresenter(VacationsListContract.VacationsListPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public String getScreenName() {
        return "Vacations list screen";
    }

    @Override
    protected SelectableAdapter getAdapter() {
        return vacationsListAdapter;
    }

    @Override
    public void openDetailsScreen(int year, int month) {
        Toast.makeText(getActivity(), String.format(Locale.US, "Open vacation details :: year = %d month = %d", year, month), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected VacationsListContract.VacationsListPresenter getPresenter() {
        return presenter;
    }
}
