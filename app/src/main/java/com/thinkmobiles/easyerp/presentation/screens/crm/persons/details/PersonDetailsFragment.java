package com.thinkmobiles.easyerp.presentation.screens.crm.persons.details;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.base.BaseFragment;
import com.thinkmobiles.easyerp.presentation.screens.home.HomeActivity;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;

/**
 * Created by Lynx on 1/24/2017.
 */

@EFragment(R.layout.fragment_person_details)
public class PersonDetailsFragment extends BaseFragment<HomeActivity> {

    @FragmentArg
    protected String personID;

    @Override
    protected boolean needProgress() {
        return true;
    }
}
