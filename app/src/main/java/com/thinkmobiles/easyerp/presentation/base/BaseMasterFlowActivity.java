package com.thinkmobiles.easyerp.presentation.base;

import android.app.Fragment;
import android.support.annotation.IdRes;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.EasyErpApplication;
import com.thinkmobiles.easyerp.presentation.managers.CookieManager;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
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

    @AfterViews
    protected void initToolbar() {
        setSupportActionBar(toolbar);

        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_hamburger_menu);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (isTablet) {
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
        replaceFragment(fragment, contentIdLayout());
    }

    public void replaceFragmentContentDetail(final BaseFragment fragment) {
        replaceFragment(fragment, contentDetailIdLayout());
    }

    private void replaceFragment(final BaseFragment fragment, final int containerId) {
        if (fragment != null) {
            getFragmentManager().beginTransaction()
                    .replace(containerId, fragment, fragment.getClass().getSimpleName())
                    .commitAllowingStateLoss();
        } else {
            final Fragment targetFragmentForDelete = getFragmentManager().findFragmentById(containerId);
            if (targetFragmentForDelete != null)
                getFragmentManager().beginTransaction()
                        .remove(targetFragmentForDelete)
                        .commitAllowingStateLoss();
        }
    }

    protected abstract @IdRes int contentIdLayout();
    protected abstract @IdRes int contentDetailIdLayout();

}
