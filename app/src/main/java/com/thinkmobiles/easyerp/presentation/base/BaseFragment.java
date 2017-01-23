package com.thinkmobiles.easyerp.presentation.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.thinkmobiles.easyerp.R;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.SystemService;

/**
 * Created by Lynx on 1/13/2017.
 */

@EFragment
public abstract class BaseFragment<T extends Activity> extends Fragment {

    protected T mActivity;
    private View progressView;

    @SystemService
    protected InputMethodManager inputMethodManager;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mActivity = (T) context;
        } catch (ClassCastException e) {
            throw new RuntimeException("This fragment should have activity instance");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (needProgress() && (view instanceof FrameLayout || view instanceof RelativeLayout)) {
            progressView = LayoutInflater.from(getContext()).inflate(R.layout.view_progress, (ViewGroup) view, false);
            ((ViewGroup) view).addView(progressView);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if(getView() != null) {
            inputMethodManager.hideSoftInputFromWindow(getView().getWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);
        }
    }

    public void displayProgress(boolean isShow) {
        progressView.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }

    protected abstract boolean needProgress();
}
