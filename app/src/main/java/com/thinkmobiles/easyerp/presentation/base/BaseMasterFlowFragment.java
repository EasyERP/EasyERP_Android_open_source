package com.thinkmobiles.easyerp.presentation.base;

import android.app.Activity;
import android.app.Fragment;
import android.support.annotation.MenuRes;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.SystemService;

/**
 * Created by Lynx on 1/13/2017.
 */

@EFragment
public abstract class BaseMasterFlowFragment<T extends BaseMasterFlowActivity> extends Fragment {

    protected T mActivity;

    @SystemService
    protected InputMethodManager inputMethodManager;

    @Override
    public void onAttach(Activity context) {
        super.onAttach(context);
        try {
            mActivity = (T) context;
        } catch (ClassCastException e) {
            throw new RuntimeException("This fragment should have Activity instance");
        }
    }

    @AfterInject
    protected void initFragmentConfigs() {
        setRetainInstance(true);
        setHasOptionsMenu(true);
    }

    @Override
    public void onStop() {
        super.onStop();
        hideKeyboard();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if (optionsMenuRes() != 0) {
            if (mActivity.isTablet && !mActivity.isPortrait && optionsMenuForDetail()) {
                mActivity.getToolbarDetail().inflateMenu(optionsMenuRes());
                mActivity.getToolbarDetail().setOnMenuItemClickListener(this::onOptionsItemSelected);
                optionsMenuInitialized(mActivity.getToolbarDetail().getMenu());
            } else {
                inflater.inflate(optionsMenuRes(), menu);
                optionsMenuInitialized(menu);
            }
        } else {
            if (mActivity.isPortrait) {
                mActivity.resetToolbar(menu);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mActivity.isTablet && !mActivity.isPortrait && optionsMenuForDetail()) {
            mActivity.onOptionsItemSelected(item);
            return true;
        } else return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mActivity.isTablet && !mActivity.isPortrait && optionsMenuForDetail()) {
            mActivity.resetDetailToolbarToBase();
        }
    }

    public boolean optionsMenuForDetail() { return false; }
    public @MenuRes int optionsMenuRes() { return 0; }
    public void optionsMenuInitialized(final Menu menu) {}

    protected void hideKeyboard() {
        if(getView() != null) {
            inputMethodManager.hideSoftInputFromWindow(getView().getWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);
        }
    }
}
