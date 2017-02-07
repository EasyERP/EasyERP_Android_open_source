package com.thinkmobiles.easyerp.presentation.screens.crm.companies;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.data.model.crm.common.alphabet.AlphabetItem;
import com.thinkmobiles.easyerp.domain.crm.CompaniesRepository;
import com.thinkmobiles.easyerp.presentation.adapters.crm.CompaniesAdapter;
import com.thinkmobiles.easyerp.presentation.base.rules.ErrorViewHelper;
import com.thinkmobiles.easyerp.presentation.base.rules.MasterFlowListFragment;
import com.thinkmobiles.easyerp.presentation.custom.views.alphabet_view.AlphabetListAdapter;
import com.thinkmobiles.easyerp.presentation.custom.views.alphabet_view.AlphabetView;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.CompanyDH;
import com.thinkmobiles.easyerp.presentation.listeners.EndlessRecyclerViewScrollListener;
import com.thinkmobiles.easyerp.presentation.screens.crm.companies.details.CompanyDetailsFragment_;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringRes;

import java.util.ArrayList;

/**
 * Created by Lynx on 2/2/2017.
 */

@EFragment(R.layout.fragment_companies)
public class CompaniesFragment extends MasterFlowListFragment implements CompaniesContract.CompaniesView {

    private CompaniesContract.CompaniesPresenter presenter;
    private EndlessRecyclerViewScrollListener scrollListener;

    @ViewById
    protected AlphabetView alphabetView_FC;
    @ViewById(R.id.llErrorLayout)
    protected View errorLayout;

    @StringRes(R.string.list_is_empty)
    protected String string_list_is_empty;

    @Bean
    protected AlphabetListAdapter alphabetListAdapter;
    @Bean
    protected CompaniesAdapter companiesAdapter;
    @Bean
    protected CompaniesRepository companiesRepository;
    @Bean
    protected ErrorViewHelper errorViewHelper;

    @AfterInject
    @Override
    public void initPresenter() {
        new CompaniesPresenter(this, companiesRepository);
    }

    @AfterViews
    protected void initUI() {
        alphabetView_FC.setListener(letter -> {
            presenter.setLetter(letter);
            presenter.loadMore(1);
        });
        listRecycler.setAdapter(alphabetListAdapter);
        listRecycler.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));

        errorViewHelper.init(errorLayout, view -> loadWithProgressBar());

        LinearLayoutManager llm = new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false);
        scrollListener = new EndlessRecyclerViewScrollListener(llm) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {

                displayProgress(true);
                presenter.loadMore(page);
            }
        };
        listRecycler.setLayoutManager(llm);
        listRecycler.setAdapter(companiesAdapter);
        listRecycler.addOnScrollListener(scrollListener);
        companiesAdapter.setOnCardClickListener((view, position, viewType) -> presenter.selectItem(companiesAdapter.getItem(position), position));

        loadWithProgressBar();
    }

    @Override
    public void onRefresh() {
        errorViewHelper.hideError();
        scrollListener.resetState();
        presenter.subscribe();
    }


    @Override
    public void displayEnabledLetters(ArrayList<AlphabetItem> enabledAlphabetItems) {
        alphabetView_FC.setEnabledLetters(enabledAlphabetItems);
    }

    @Override
    public void displayCompanies(ArrayList<CompanyDH> companyDHs, boolean needClear) {
        errorViewHelper.hideError();
        alphabetView_FC.setVisibility(View.VISIBLE);
        displayProgress(false);
        swipeContainer.setRefreshing(false);

        if (needClear)
            companiesAdapter.setListDH(companyDHs);
        else companiesAdapter.addListDH(companyDHs);

        if (getCountItemsNow() == 0)
            displayError(null, ErrorViewHelper.ErrorType.LIST_EMPTY);
    }

    @Override
    public void displayError(String msg, ErrorViewHelper.ErrorType errorType) {
        displayProgress(false);
        swipeContainer.setRefreshing(false);

        final String resultMsg = errorType.equals(ErrorViewHelper.ErrorType.LIST_EMPTY) ? string_list_is_empty : msg;
        if (getCountItemsNow() == 0) {
            alphabetView_FC.setVisibility(View.GONE);
            errorViewHelper.showErrorMsg(resultMsg, errorType);
        } else
            Toast.makeText(mActivity, resultMsg, Toast.LENGTH_LONG).show();
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

    private void loadWithProgressBar() {
        errorViewHelper.hideError();
        displayProgress(true);
        presenter.subscribe();
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
