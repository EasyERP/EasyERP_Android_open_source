package com.thinkmobiles.easyerp.presentation.screens.about;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.adapters.crm.AboutUsPagerAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.ViewById;

/**
 * @author Michael Soyma (Created on 2/22/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
@EActivity(R.layout.activity_about_us)
public class AboutUsActivity extends AppCompatActivity implements AboutUsContract.AboutUsView {

    @ViewById
    protected Toolbar toolbar;
    @ViewById
    protected TabLayout tabs;
    @ViewById
    protected ViewPager vpTabs_AAU;

    @Override
    public void initPresenter() {

    }

    @Override
    public void setPresenter(AboutUsContract.AboutUsPresenter presenter) {

    }

    @AfterViews
    protected void initUI() {
        initToolbar();
        vpTabs_AAU.setAdapter(new AboutUsPagerAdapter(getSupportFragmentManager(), getString(R.string.app_name), getString(R.string.powered_by), getString(R.string.libraries)));
        tabs.setupWithViewPager(vpTabs_AAU);
    }

    @OptionsItem(android.R.id.home)
    protected void selectBack() {
        onBackPressed();
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);

        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayShowTitleEnabled(true);
        }
    }
}
