package com.thinkmobiles.easyerp.presentation.base;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.MenuRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.thinkmobiles.easyerp.R;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.SystemService;

/**
 * Created by Lynx on 1/13/2017.
 */

@EFragment
public abstract class BaseFragment<T extends BaseMasterFlowActivity> extends Fragment {

    protected T mActivity;
    private View progressView;

    @SystemService
    protected InputMethodManager inputMethodManager;

    @Override
    public void onAttach(Activity context) {
        super.onAttach(context);
        try {
            mActivity = (T) context;
        } catch (ClassCastException e) {
            throw new RuntimeException("This fragment should have activity instance");
        }
    }

    @AfterInject
    protected void initFragmentConfigs() {
        setRetainInstance(true);
        setHasOptionsMenu(true);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (needProgress() && (view instanceof FrameLayout || view instanceof RelativeLayout)) {
            progressView = LayoutInflater.from(mActivity).inflate(R.layout.view_progress, (ViewGroup) view, false);
            ((ViewGroup) view).addView(progressView);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        hideKeyboard();
    }

    public void displayProgress(boolean isShow) {
        progressView.setVisibility(isShow ? View.VISIBLE : View.GONE);
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

    protected abstract boolean needProgress();

    protected void hideKeyboard() {
        if(getView() != null) {
            inputMethodManager.hideSoftInputFromWindow(getView().getWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);
        }
    }
}
