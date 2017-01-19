package com.thinkmobiles.easyerp.presentation.base;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.thinkmobiles.easyerp.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by Asus_Dev on 1/17/2017.
 */
@EActivity
public abstract class BaseMasterFlowActivity extends AppCompatActivity {

    @ViewById
    protected Toolbar toolbar;

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

    public void replaceFragmentContent(final BaseFragment fragment) {
        replaceFragment(fragment, contentIdLayout());
    }

    public void replaceFragmentContentDetail(final BaseFragment fragment) {
        replaceFragment(fragment, contentDetailIdLayout());
    }

    private void replaceFragment(final BaseFragment fragment, final int containerId) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(containerId, fragment, fragment.getClass().getSimpleName())
                    .commitAllowingStateLoss();
        } else {
            final Fragment targetFragmentForDelete = getSupportFragmentManager().findFragmentById(containerId);
            if (targetFragmentForDelete != null)
                getSupportFragmentManager().beginTransaction()
                        .remove(targetFragmentForDelete)
                        .commitAllowingStateLoss();
        }
    }

    protected abstract
    @IdRes
    int contentIdLayout();

    protected abstract
    @IdRes
    int contentDetailIdLayout();

}
