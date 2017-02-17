package com.thinkmobiles.easyerp.presentation.base.rules;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.adapters.crm.SearchAdapter;
import com.thinkmobiles.easyerp.presentation.dialogs.FilterDialogFragment;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.FilterDH;
import com.thinkmobiles.easyerp.presentation.listeners.EndlessScrollListener;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import org.androidannotations.annotations.AfterTextChange;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

/**
 * @author Alex Michenko (Created on 09.02.17).
 *         Company: Thinkmobiles
 *         Email: alex.michenko@thinkmobiles.com
 */

@EFragment
public abstract class ListRefreshFragment extends RefreshFragment {

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_list_with_search;
    }

    @ViewById
    protected RecyclerView listRecycler;
    @ViewById
    protected AppCompatAutoCompleteTextView actSearch;
    @Bean
    protected SearchAdapter searchAdapter;

    protected EndlessScrollListener scrollListener;

    protected abstract void onLoadNextPage();

    @AfterViews
    protected void initList() {
        LinearLayoutManager recyclerLayoutManager = new LinearLayoutManager(getActivity());

        scrollListener = new EndlessScrollListener(recyclerLayoutManager, () -> {
            if (srlHolderRefresh.isRefreshing()) {
                return false;
            } else {
                onLoadNextPage();
                return true;
            }
        });

        listRecycler.setLayoutManager(recyclerLayoutManager);
        listRecycler.addOnScrollListener(scrollListener);

        actSearch.setAdapter(searchAdapter);
    }

    @AfterTextChange(R.id.actSearch)
    protected void afterSearchChanged(Editable editable) {
        if (editable.length() > 1) {
            searchAdapter.getFilter().filter(editable.toString());
        }
    }

    @CallSuper
    protected void onRefreshData() {
        scrollListener.reset();
    }

    @Override
    protected void showErrorToast(String message) {
        scrollListener.reset();
        super.showErrorToast(message);
    }

    protected void showDialogFiltering(ArrayList<FilterDH> filterDHs, int requestCode, String filterName) {
        actSearch.clearFocus();
        listRecycler.requestFocus();
        FilterDialogFragment dialogFragment = new FilterDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(Constants.KEY_FILTER_LIST, filterDHs);
        bundle.putString(Constants.KEY_FILTER_NAME, filterName);
        dialogFragment.setArguments(bundle);
        dialogFragment.setTargetFragment(this, requestCode);
        dialogFragment.show(getFragmentManager(), getClass().getName());
    }
}
