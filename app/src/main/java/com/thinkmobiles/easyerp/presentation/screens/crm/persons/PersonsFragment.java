package com.thinkmobiles.easyerp.presentation.screens.crm.persons;

import com.thinkmobiles.easyerp.presentation.base.rules.SimpleListWithRefreshFragment;

import org.androidannotations.annotations.EFragment;

/**
 * Created by Lynx on 1/20/2017.
 */

@EFragment
public class PersonsFragment extends SimpleListWithRefreshFragment {
    @Override
    public void onRefresh() {

    }

    @Override
    protected boolean needProgress() {
        return true;
    }
}
