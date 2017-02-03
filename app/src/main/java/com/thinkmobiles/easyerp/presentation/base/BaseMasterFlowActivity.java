package com.thinkmobiles.easyerp.presentation.base;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.EasyErpApplication;
import com.thinkmobiles.easyerp.presentation.managers.CookieManager;
import com.thinkmobiles.easyerp.presentation.screens.crm.orders.details.OrderDetailsFragment_;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.BooleanRes;

/**
 * @author michael.soyma@thinkmobiles.com (Created on 1/17/2017.)
 */
@EActivity
public abstract class BaseMasterFlowActivity extends AppCompatActivity {

    @Bean
    protected CookieManager cookieManager;

    @ViewById
    protected Toolbar toolbar;

    @ViewById
    protected Toolbar toolbarDetail;

    @BooleanRes
    public boolean isTablet;

    @BooleanRes
    public boolean isPortrait;

    @AfterInject
    protected void listenFragmentBackStack() {
        getFragmentManager().addOnBackStackChangedListener(this::syncHomeActionState);
    }

    @AfterViews
    protected void initToolbar() {
        setSupportActionBar(toolbar);

        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            syncHomeActionState();
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }
    }

    private void syncHomeActionState() {
        if (getSupportActionBar() != null)
            getSupportActionBar().setHomeAsUpIndicator(getHomeIcon());
    }

    private @DrawableRes int getHomeIcon() {
        if (getFragmentManager().getBackStackEntryCount() == 0 || (isTablet && !isPortrait))
            return R.drawable.ic_hamburger_menu;
        else return R.drawable.ic_back;
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    public Toolbar getToolbarDetail() {
        return toolbarDetail;
    }

    public void resetDetailToolbarToBase() {
        toolbarDetail.getMenu().clear();
        toolbarDetail.inflateMenu(R.menu.menu_base);
        toolbarDetail.setOnMenuItemClickListener(this::onOptionsItemSelected);
    }

    public void resetToolbar(Menu menu) {
        menu.clear();
        getMenuInflater().inflate(R.menu.menu_base, menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (isTablet && !isPortrait) {
            resetDetailToolbarToBase();
            return true;
        } else {
            getMenuInflater().inflate(R.menu.menu_base, menu);
            return true;
        }
    }

    @OptionsItem(R.id.menuLogout_MB)
    protected void logout() {
        cookieManager.clearCookie();
        EasyErpApplication.getInstance().restartApp();
    }

    public void replaceFragmentContent(final BaseFragment fragment) {
        replaceFragmentContentDetail(null);
        replaceFragment(fragment, contentIdLayout(), false);
    }

    public void replaceFragmentContentDetail(final BaseFragment fragment) {
        if (!isPortrait && getFragmentManager().getBackStackEntryCount() > 0)
            getFragmentManager().popBackStackImmediate();
        replaceFragment(fragment, contentDetailIdLayout(), true);
    }

    private void replaceFragment(final BaseFragment fragment, final int containerId, boolean withBackStack) {
        if (fragment != null) {
            final FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(containerId, fragment, fragment.getClass().getSimpleName());
            if (withBackStack)
                ft.addToBackStack(null);
            ft.commitAllowingStateLoss();
        } else {
            final Fragment targetFragmentForDelete = getFragmentManager().findFragmentById(containerId);
            if (targetFragmentForDelete != null)
                getFragmentManager().beginTransaction()
                        .remove(targetFragmentForDelete)
                        .commitAllowingStateLoss();
        }
    }

    @Override
    public void onBackPressed() {
        if (isTablet && !isPortrait && getFragmentManager().getBackStackEntryCount() > 0)
            finish();
        else super.onBackPressed();
    }

    @OptionsItem(android.R.id.home)
    protected void onHomeMenuSelect() {
        onHomeMenuSelect(getFragmentManager().getBackStackEntryCount() == 0 || (isTablet && !isPortrait));
    }

    protected abstract void onHomeMenuSelect(boolean isHamburger);
    protected abstract @IdRes int contentIdLayout();
    protected abstract @IdRes int contentDetailIdLayout();

}
