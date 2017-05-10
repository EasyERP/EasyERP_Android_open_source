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
public abstract class BaseMasterFlowFragment<T extends BaseMasterFlowDelegate> extends Fragment {

    private T masterFlowDelegate;
    private Activity activity;

    protected BaseMasterFlowDelegate getMasterDelegate() {
        return masterFlowDelegate;
    }

    protected Activity contextActivity() {
        return activity;
    }

    @SystemService
    protected InputMethodManager inputMethodManager;

    @Override
    public void onAttach(Activity context) {
        super.onAttach(context);
        try {
            masterFlowDelegate = (T) context;
            activity = context;
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
            if (masterFlowDelegate.isTablet() && !masterFlowDelegate.isPortrait() && optionsMenuForDetail()) {
                masterFlowDelegate.getToolbarDetail().inflateMenu(optionsMenuRes());
                masterFlowDelegate.getToolbarDetail().setOnMenuItemClickListener(this::onOptionsItemSelected);
                optionsMenuInitialized(masterFlowDelegate.getToolbarDetail().getMenu());
            } else {
                inflater.inflate(optionsMenuRes(), menu);
                optionsMenuInitialized(menu);
            }
        } else {
            if (masterFlowDelegate.isPortrait()) {
                masterFlowDelegate.resetToolbar(menu);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (masterFlowDelegate.isTablet() && !masterFlowDelegate.isPortrait() && optionsMenuForDetail()) {
            masterFlowDelegate.onOptionsItemSelected(item);
            return true;
        } else return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (masterFlowDelegate.isTablet() && !masterFlowDelegate.isPortrait() && optionsMenuForDetail()) {
            masterFlowDelegate.resetDetailToolbarToBase();
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
