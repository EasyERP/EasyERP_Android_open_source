package com.thinkmobiles.easyerp.presentation.screens.crm.persons;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.data.model.crm.persons.alphabet.AlphabetItem;
import com.thinkmobiles.easyerp.domain.crm.PersonsRepository;
import com.thinkmobiles.easyerp.presentation.adapters.crm.PersonsAdapter;
import com.thinkmobiles.easyerp.presentation.base.rules.ErrorViewHelper;
import com.thinkmobiles.easyerp.presentation.base.rules.SimpleListWithRefreshFragment;
import com.thinkmobiles.easyerp.presentation.custom.views.alphabet_view.AlphabetListAdapter;
import com.thinkmobiles.easyerp.presentation.custom.views.alphabet_view.AlphabetView;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.PersonDH;
import com.thinkmobiles.easyerp.presentation.listeners.EndlessRecyclerViewScrollListener;
import com.thinkmobiles.easyerp.presentation.screens.crm.persons.details.PersonDetailsFragment_;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringRes;

import java.util.ArrayList;

/**
 * Created by Lynx on 1/20/2017.
 */

@EFragment(R.layout.fragment_persons)
public class PersonsFragment extends SimpleListWithRefreshFragment implements PersonsContract.PersonsView {

    private PersonsContract.PersonsPresenter presenter;
    private EndlessRecyclerViewScrollListener scrollListener;

    @ViewById
    protected AlphabetView alphabetView_FP;
    @StringRes(R.string.list_is_empty)
    protected String string_list_is_empty;

    @ViewById(R.id.llErrorLayout)
    protected View errorLayout;

    @Bean
    protected AlphabetListAdapter alphabetListAdapter;
    @Bean
    protected PersonsAdapter personsAdapter;
    @Bean
    protected PersonsRepository personsRepository;
    @Bean
    protected ErrorViewHelper errorViewHelper;

    @AfterInject
    @Override
    public void initPresenter() {
        new PersonsPresenter(this, personsRepository);
    }

    @AfterViews
    protected void initUI() {
        alphabetView_FP.setListener(letter -> {
            presenter.setLetter(letter);
            presenter.loadMore(1);
        });
        listRecycler.setAdapter(alphabetListAdapter);
        listRecycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        errorViewHelper.init(errorLayout, view -> loadWithProgressBar());

        LinearLayoutManager llm = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        scrollListener = new EndlessRecyclerViewScrollListener(llm) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                displayProgress(true);
                presenter.loadMore(page);
            }
        };
        listRecycler.setLayoutManager(llm);
        listRecycler.setAdapter(personsAdapter);
        listRecycler.addOnScrollListener(scrollListener);
        personsAdapter.setOnCardClickListener((view, position, viewType) -> {
            if (position != presenter.getSelectedItemPosition()) {
                final PersonDH itemDH = personsAdapter.getItem(position);
                personsAdapter.replaceSelectedItem(presenter.getSelectedItemPosition(), position);
                presenter.setSelectedInfo(position, itemDH.getId());
                presenter.displayPersonDetails(itemDH.getPersonModel().id);
            }
        });

        loadWithProgressBar();

    }

    private void loadWithProgressBar() {
        errorViewHelper.hideError();
        displayProgress(true);
        presenter.subscribe();
    }

    @Override
    public void onRefresh() {
        errorViewHelper.hideError();
        scrollListener.resetState();
        presenter.setSelectedInfo(-1, presenter.getSelectedItemId());
        presenter.subscribe();
    }

    @Override
    protected boolean needProgress() {
        return true;
    }

    @Override
    public void displayEnabledLetters(ArrayList<AlphabetItem> enabledAlphabetItems) {
        alphabetView_FP.setEnabledLetters(enabledAlphabetItems);
    }

    @Override
    public void displayPersons(ArrayList<PersonDH> personDHs, boolean needClear) {
        errorViewHelper.hideError();
        displayProgress(false);
        swipeContainer.setRefreshing(false);

        if (needClear)
            personsAdapter.setListDH(personDHs);
        else personsAdapter.addListDH(personDHs);

        if (getCountItemsNow() == 0)
            displayError(null, ErrorViewHelper.ErrorType.LIST_EMPTY);
    }

    @Override
    public void displayError(String msg, ErrorViewHelper.ErrorType errorType) {
        displayProgress(false);
        swipeContainer.setRefreshing(false);

        final String resultMsg = errorType.equals(ErrorViewHelper.ErrorType.LIST_EMPTY) ? string_list_is_empty : msg;
        if (getCountItemsNow() == 0)
            errorViewHelper.showErrorMsg(resultMsg, errorType);
        else Toast.makeText(getContext(), resultMsg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void openPersonDetailsScreen(String personID) {
        mActivity.replaceFragmentContentDetail(PersonDetailsFragment_.builder()
                .personID(personID)
                .build());
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
    public void onDestroyView() {
        super.onDestroyView();
        if(presenter != null) presenter.unsubscribe();
    }
}
