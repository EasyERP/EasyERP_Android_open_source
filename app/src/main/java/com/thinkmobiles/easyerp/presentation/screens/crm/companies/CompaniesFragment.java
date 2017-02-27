package com.thinkmobiles.easyerp.presentation.screens.crm.companies;

import com.thinkmobiles.easyerp.domain.crm.CompaniesRepository;
import com.thinkmobiles.easyerp.presentation.adapters.crm.CompaniesAdapter;
import com.thinkmobiles.easyerp.presentation.base.rules.master.alphabetical.MasterAlphabeticalFragment;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectableAdapter;
import com.thinkmobiles.easyerp.presentation.managers.GoogleAnalyticHelper;
import com.thinkmobiles.easyerp.presentation.screens.crm.companies.details.CompanyDetailsFragment_;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;

/**
 * Created by Lynx on 2/2/2017.
 */

@EFragment
public class CompaniesFragment extends MasterAlphabeticalFragment implements CompaniesContract.CompaniesView {

    private CompaniesContract.CompaniesPresenter presenter;

    @Bean
    protected CompaniesAdapter companiesAdapter;
    @Bean
    protected CompaniesRepository companiesRepository;

    @AfterInject
    @Override
    public void initPresenter() {
        new CompaniesPresenter(this, companiesRepository);
    }

    @AfterViews
    protected void initAnalytics() {
        GoogleAnalyticHelper.trackScreenView(this, getResources().getConfiguration());
    }

    @Override
    public void openDetailsScreen(String companyID) {
        if (companyID != null) {
            mActivity.replaceFragmentContentDetail(CompanyDetailsFragment_.builder()
                    .companyID(companyID)
                    .build());
        } else {
            mActivity.replaceFragmentContentDetail(null);
        }
    }

    @Override
    public CompaniesContract.CompaniesPresenter getPresenter() {
        return presenter;
    }

    @Override
    public void setPresenter(CompaniesContract.CompaniesPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public String getScreenName() {
        return "Company list screen";
    }

    @Override
    protected SelectableAdapter getAdapter() {
        return companiesAdapter;
    }
}
