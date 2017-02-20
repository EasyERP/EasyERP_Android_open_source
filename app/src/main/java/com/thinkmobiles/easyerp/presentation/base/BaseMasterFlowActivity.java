package com.thinkmobiles.easyerp.presentation.base;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.base.rules.MasterFlowListSelectableFragment;
import com.thinkmobiles.easyerp.presentation.screens.about.AboutActivity_;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.InstanceState;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.BooleanRes;
import org.androidannotations.annotations.res.StringRes;

/**
 * @author michael.soyma@thinkmobiles.com (Created on 1/17/2017.)
 */
@EActivity
public abstract class BaseMasterFlowActivity extends AppCompatActivity {

    @ViewById
    protected Toolbar toolbar;

    @ViewById
    protected Toolbar toolbarDetail;

    @BooleanRes
    public boolean isTablet;
    @BooleanRes
    public boolean isPortrait;
    @StringRes
    protected String app_name;

    @InstanceState
    protected String title;

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
            actionBar.setDisplayShowTitleEnabled(true);
        }
        setToolbarTitle(title);
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

    private void setToolbarTitle(final String title) {
        this.title = title;
        setTitle(TextUtils.isEmpty(this.title) ? app_name : this.title);
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

    public void replaceFragmentContent(final BaseFragment fragment, final String title) {
        setToolbarTitle(title);
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
        else {
            super.onBackPressed();
            sendEventClearSelectedItem();
        }
    }

    private void sendEventClearSelectedItem() {
        final Fragment fragment = getFragmentManager().findFragmentById(contentIdLayout());
        if (fragment != null) {
            if (fragment instanceof MasterFlowListSelectableFragment) {
                ((MasterFlowListSelectableFragment) fragment).clearSelectedItem();
            }
        }

    }

    @OptionsItem(R.id.menuAboutUs_MB)
    protected void openAboutUsScreen() {
        AboutActivity_.intent(this)
                .url("http://www.thinkmobiles.com")
                .start();
    }

    @OptionsItem(android.R.id.home)
    protected void onHomeMenuSelect() {
        onHomeMenuSelect(getFragmentManager().getBackStackEntryCount() == 0 || (isTablet && !isPortrait));
    }

    @OptionsItem(R.id.menuLogout_MB)
    protected abstract void logOut();
    protected abstract void onHomeMenuSelect(boolean isHamburger);
    protected abstract @IdRes int contentIdLayout();
    protected abstract @IdRes int contentDetailIdLayout();

}
