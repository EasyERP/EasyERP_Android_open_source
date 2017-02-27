package com.thinkmobiles.easyerp.presentation.base.rules.content;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding.view.RxView;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.base.BaseMasterFlowFragment;
import com.thinkmobiles.easyerp.presentation.managers.ErrorManager;
import com.thinkmobiles.easyerp.presentation.screens.home.HomeActivity;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.ColorRes;

import java.util.concurrent.TimeUnit;

/**
 * @author Alex Michenko (Created on 22.02.17).
 *         Company: Thinkmobiles
 *         Email: alex.michenko@thinkmobiles.com
 */

@EFragment
public abstract class ContentFragment extends BaseMasterFlowFragment<HomeActivity> implements ContentView {

    @ViewById
    protected SwipeRefreshLayout srlHolderRefresh;
    @ViewById
    protected View llHolderError;
    @ViewById
    protected ImageView ivHolderIcon;
    @ViewById
    protected TextView tvHolderMessage;
    @ViewById
    protected Button btnHolderTry;
    @ViewById
    protected ProgressBar pbHolderProgress;
    @ViewById
    protected ProgressBar pbProgressBottom;

    protected FrameLayout flContent;

    private Toast toast;

    @ColorRes
    protected int colorPrimary;
    @ColorRes
    protected int colorPrimaryDark;

    protected abstract int getLayoutRes();
    protected abstract ContentPresenter getPresenter();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        toast = Toast.makeText(getActivity(), null, Toast.LENGTH_SHORT);
        View parent = inflater.inflate(R.layout.view_parent_refresh_for_fragments, container, false);
        flContent = (FrameLayout) parent.findViewById(R.id.flContent);
        View.inflate(getActivity(), getLayoutRes(), flContent);
        return parent;
    }

    @AfterViews
    protected void initHolderUI() {
        srlHolderRefresh.setColorSchemeColors(colorPrimary, colorPrimaryDark);
        srlHolderRefresh.setOnRefreshListener(this::onRefreshData);
        RxView.clicks(btnHolderTry)
                .throttleFirst(Constants.DELAY_CLICK, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> getPresenter().subscribe());

        getPresenter().subscribe();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getPresenter().unsubscribe();
    }

    protected void onRefreshData() {
        getPresenter().refresh();
    }

    @Override
    public void showProgress(Constants.ProgressType type) {
        llHolderError.setVisibility(View.GONE);
        switch (type) {
            case BOTTOM:
                srlHolderRefresh.setEnabled(false);
                pbProgressBottom.setVisibility(View.VISIBLE);
                break;
            case CENTER:
                srlHolderRefresh.setEnabled(false);
                getHiddenView().setVisibility(View.GONE);
                pbHolderProgress.setVisibility(View.VISIBLE);
                break;
            case NONE:
                getHiddenView().setVisibility(View.VISIBLE);
                pbHolderProgress.setVisibility(View.GONE);
                pbProgressBottom.setVisibility(View.GONE);
                srlHolderRefresh.setRefreshing(false);
                srlHolderRefresh.setEnabled(true);
                break;
        }
    }

    protected View getHiddenView() {
        return flContent;
    }

    @Override
    public void displayErrorState(final Constants.ErrorType errorType) {
        showErrorState(errorType);
    }

    protected boolean showErrorState(final Constants.ErrorType errorType) {
        showProgress(Constants.ProgressType.NONE);
        getHiddenView().setVisibility(View.GONE);
        llHolderError.setVisibility(View.VISIBLE);
        ivHolderIcon.setImageResource(ErrorManager.getErrorIcon(errorType));
        tvHolderMessage.setText(ErrorManager.getErrorMessage(errorType));
        srlHolderRefresh.setEnabled(false);
        switch (errorType) {
            case UNKNOWN:
            case NETWORK:
                btnHolderTry.setVisibility(View.VISIBLE);
                return true;
            default:
                return false;
        }
    }

    @Override
    public void displayErrorToast(Constants.ErrorType errorType) {
        showProgress(Constants.ProgressType.NONE);
        toast.setText(ErrorManager.getErrorMessage(errorType));
        toast.show();
    }
}
