package com.thinkmobiles.easyerp.presentation.base;

import android.support.annotation.CallSuper;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.thinkmobiles.easyerp.presentation.listeners.EndlessRecyclerViewScrollListener;
import com.thinkmobiles.easyerp.presentation.listeners.EndlessScrollListener;
import com.thinkmobiles.easyerp.presentation.screens.home.HomeActivity;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

/**
 * @author Alex Michenko (Created on 09.02.17).
 *         Company: Thinkmobiles
 *         Email: alex.michenko@thinkmobiles.com
 */

@EFragment
public abstract class ListRefreshFragment extends RefreshFragment<HomeActivity> {

    @ViewById
    protected RecyclerView listRecycler;

    protected EndlessScrollListener scrollListener;

    protected abstract void onLoadNextPage();

    protected void initEndlessScrollListener(LinearLayoutManager layoutManager) {
        scrollListener = new EndlessScrollListener(layoutManager, () -> {
            if (srlHolderRefresh.isRefreshing()) {
                return false;
            } else {
                onLoadNextPage();
                return true;
            }
        });
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
