package com.thinkmobiles.easyerp.presentation.screens.crm.companies;

import android.view.View;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.data.model.crm.common.alphabet.AlphabetItem;
import com.thinkmobiles.easyerp.domain.crm.CompaniesRepository;
import com.thinkmobiles.easyerp.presentation.adapters.crm.CompaniesAdapter;
import com.thinkmobiles.easyerp.presentation.base.rules.ErrorType;
import com.thinkmobiles.easyerp.presentation.base.rules.MasterFlowListSelectableFragment;
import com.thinkmobiles.easyerp.presentation.custom.views.alphabet_view.AlphabetView;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.CompanyDH;
import com.thinkmobiles.easyerp.presentation.screens.crm.companies.details.CompanyDetailsFragment_;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

/**
 * Created by Lynx on 2/2/2017.
 */

@EFragment
public class CompaniesFragment extends MasterFlowListSelectableFragment implements CompaniesContract.CompaniesView {

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_list_with_alphabet;
    }

    private CompaniesContract.CompaniesPresenter presenter;

    @ViewById
    protected AlphabetView alphabetView;

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
    protected void initUI() {
        listRecycler.setAdapter(companiesAdapter);
        companiesAdapter.setOnCardClickListener((view, position, viewType) -> presenter.selectItem(companiesAdapter.getItem(position), position));

        alphabetView.setListener(letter -> presenter.setLetter(letter));

        presenter.subscribe();
    }

    @Override
    public void onRefreshData() {
        super.onRefreshData();
        presenter.refresh();
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
    public void showProgress(Constants.ProgressType type) {
        showProgressBar(type);
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
    public void displayCompanies(ArrayList<CompanyDH> companyDHs, boolean needClear) {
        alphabetView.setVisibility(View.VISIBLE);
        if (needClear){
            companiesAdapter.setListDH(companyDHs);
        } else {
            companiesAdapter.addListDH(companyDHs);
        }
    }

    @Override
    public void displayErrorState(ErrorType errorType) {
        showErrorState(errorType);
    }

    @Override
    public void displayErrorToast(String msg) {
        showErrorToast(msg);
    }

    @Override
    public void openCompanyDetailsScreen(String companyID) {
        if (companyID != null) {
            mActivity.replaceFragmentContentDetail(CompanyDetailsFragment_.builder()
                    .companyID(companyID)
                    .build());
        } else {
            mActivity.replaceFragmentContentDetail(null);
        }
    }

    @Override
    public int getCountItemsNow() {
        return companiesAdapter.getItemCount();
    }

    @Override
    public void changeSelectedItem(int oldPosition, int newPosition) {
        companiesAdapter.replaceSelectedItem(oldPosition, newPosition);
    }

    @Override
    public void setPresenter(CompaniesContract.CompaniesPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(presenter != null) presenter.unsubscribe();
    }

    @Override
    public void clearSelectedItem() {
        presenter.clearSelectedInfo();
    }
}
