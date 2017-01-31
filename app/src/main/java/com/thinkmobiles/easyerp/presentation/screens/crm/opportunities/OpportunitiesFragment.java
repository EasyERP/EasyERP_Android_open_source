package com.thinkmobiles.easyerp.presentation.screens.crm.opportunities;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.base.rules.SimpleListWithRefreshFragment;

import org.androidannotations.annotations.EFragment;

/**
 * Created by Lynx on 1/30/2017.
 */

@EFragment(R.layout.fragment_simple_list_with_swipe_refresh)
public class OpportunitiesFragment extends SimpleListWithRefreshFragment {
    @Override
    public void onRefresh() {

    }

    @Override
    protected boolean needProgress() {
        return true;
    }
}
