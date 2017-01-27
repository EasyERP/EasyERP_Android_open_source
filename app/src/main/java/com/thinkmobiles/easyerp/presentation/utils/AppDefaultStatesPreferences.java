package com.thinkmobiles.easyerp.presentation.utils;

import org.androidannotations.annotations.sharedpreferences.DefaultInt;
import org.androidannotations.annotations.sharedpreferences.DefaultLong;
import org.androidannotations.annotations.sharedpreferences.SharedPref;

/**
 * Created by Lynx on 1/13/2017.
 */

@SharedPref(value = SharedPref.Scope.APPLICATION_DEFAULT)
public interface AppDefaultStatesPreferences {

    @DefaultInt(0)
    int defaultDateFilterTypeForCRMDashboardCharts();

    @DefaultLong(-1)
    long customDateFilterFromForCRMDashboardCharts();

    @DefaultLong(-1)
    long customDateFilterToForCRMDashboardCharts();

}
