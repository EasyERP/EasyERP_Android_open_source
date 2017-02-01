package com.thinkmobiles.easyerp.presentation.base.rules;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.thinkmobiles.easyerp.presentation.base.BaseFragment;
import com.thinkmobiles.easyerp.presentation.screens.home.HomeActivity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.ColorRes;

/**
 * @author michael.soyma@thinkmobiles.com (Created on 1/18/2017.)
 */
@EFragment
public abstract class SimpleListWithRefreshFragment extends BaseFragment<HomeActivity> implements SwipeRefreshLayout.OnRefreshListener {

    @ViewById
    protected SwipeRefreshLayout swipeContainer;
    @ViewById
    protected RecyclerView listRecycler;

    @ColorRes
    protected int colorPrimary;
    @ColorRes
    protected int colorPrimaryDark;

    @AfterViews
    protected void defaulInit() {
        swipeContainer.setColorSchemeColors(colorPrimary, colorPrimaryDark);
        swipeContainer.setOnRefreshListener(this);
    }

}
