package com.thinkmobiles.easyerp.presentation.adapters.crm;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.thinkmobiles.easyerp.presentation.screens.about.tabs.about_app.AboutAppFragment_;
import com.thinkmobiles.easyerp.presentation.screens.about.tabs.powered_by.PoweredByFragment_;

import java.util.Arrays;

/**
 * @author Michael Soyma (Created on 2/22/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public class AboutUsPagerAdapter extends FragmentPagerAdapter {

    private static final int TAB_COUNT = 3;
    private final String[] titles;

    public AboutUsPagerAdapter(FragmentManager fm, String... titles) {
        super(fm);
        this.titles = Arrays.copyOf(titles, TAB_COUNT);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return AboutAppFragment_.builder().build();
            case 1:
                return PoweredByFragment_.builder().build();
            case 2:
                return AboutAppFragment_.builder().build();
            default: return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public int getCount() {
        return 3;
    }
}
