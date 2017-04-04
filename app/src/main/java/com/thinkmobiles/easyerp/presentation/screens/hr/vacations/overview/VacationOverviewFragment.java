package com.thinkmobiles.easyerp.presentation.screens.hr.vacations.overview;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentFragment;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentPresenter;
import com.thinkmobiles.easyerp.presentation.listeners.IFragmentInstance;

import org.androidannotations.annotations.EFragment;

/**
 * Created by Alex Michenko on 29.03.2017.
 */

@EFragment
public class VacationOverviewFragment extends ContentFragment {


    public static IFragmentInstance getCreator() {
        return (IFragmentInstance) args -> VacationOverviewFragment_.builder().arg(args).build();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_vacation_overview;
    }

    @Override
    protected ContentPresenter getPresenter() {
        return new VacationOverviewPresenter();
    }
}
