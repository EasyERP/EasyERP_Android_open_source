package com.thinkmobiles.easyerp.presentation.base.rules;

import android.support.annotation.CallSuper;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.listeners.EndlessScrollListener;
import com.thinkmobiles.easyerp.presentation.screens.home.HomeActivity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

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

    public boolean withItemSelecting() {
        return mActivity.isTablet && !mActivity.isPortrait;
    }
    public abstract void clearSelectedItem();
}
