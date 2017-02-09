package com.thinkmobiles.easyerp.presentation.base;

import android.support.v7.widget.RecyclerView;

import com.thinkmobiles.easyerp.presentation.screens.home.HomeActivity;

import org.androidannotations.annotations.ViewById;

/**
 * @author Alex Michenko (Created on 09.02.17).
 *         Company: Thinkmobiles
 *         Email: alex.michenko@thinkmobiles.com
 */

public abstract class ListRefreshFragment extends RefreshFragment<HomeActivity> {


    @ViewById
    protected RecyclerView listRecycler;


    public boolean withItemSelecting() {
        return mActivity.isTablet && !mActivity.isPortrait;
    }
    public abstract void clearSelectedItem();
}
