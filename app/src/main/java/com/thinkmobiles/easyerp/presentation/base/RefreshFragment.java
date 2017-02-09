package com.thinkmobiles.easyerp.presentation.base;

import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.base.rules.ErrorViewHelper;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.ColorRes;

import java.util.concurrent.TimeUnit;

/**
 * @author Alex Michenko (Created on 08.02.17).
 *         Company: Thinkmobiles
 *         Email: alex.michenko@thinkmobiles.com
 */
@EFragment
public abstract class RefreshFragment<T extends BaseMasterFlowActivity> extends BaseFragment<T> {

    @ViewById
    protected SwipeRefreshLayout srlHolderRefresh;
    @ViewById
    protected LinearLayout llHolderError;
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

    @ColorRes
    protected int colorPrimary;
    @ColorRes
    protected int colorPrimaryDark;

    protected abstract int getLayoutRes();

    protected abstract void onRetry();

    protected abstract void onRefreshSwipe();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View parent = inflater.inflate(R.layout.view_parent, container, false);
        flContent = (FrameLayout) parent.findViewById(R.id.flContent);
        View.inflate(getActivity(), getLayoutRes(), flContent);
        return parent;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        //Do nothing
    }

    @AfterViews
    protected void initHolderUI() {
        srlHolderRefresh.setColorSchemeColors(colorPrimary, colorPrimaryDark);
        srlHolderRefresh.setOnRefreshListener(this::onRefreshSwipe);
        RxView.clicks(btnHolderTry)
                .throttleFirst(Constants.DELAY_CLICK, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> onRetry());
    }

    @Override
    public void onDestroyView() {
        srlHolderRefresh.setRefreshing(false);
        super.onDestroyView();
    }

    protected void showProgressBar(Constants.ProgressType type) {
        llHolderError.setVisibility(View.GONE);
        srlHolderRefresh.setEnabled(false);
        switch (type) {
            case BOTTOM:
                pbProgressBottom.setVisibility(View.VISIBLE);
                break;
            case CENTER:
                flContent.setVisibility(View.GONE);
                pbHolderProgress.setVisibility(View.VISIBLE);
                break;
            case NONE:
                flContent.setVisibility(View.VISIBLE);
                pbHolderProgress.setVisibility(View.GONE);
                pbProgressBottom.setVisibility(View.GONE);
                srlHolderRefresh.setRefreshing(false);
                srlHolderRefresh.setEnabled(true);
                break;
        }
    }

    public void showErrorState(final String msg, final ErrorViewHelper.ErrorType errorType) {
        showProgressBar(Constants.ProgressType.NONE);
        ivHolderIcon.setImageResource(getPlaceholderIcon(errorType));
        tvHolderMessage.setText(msg);
        llHolderError.setVisibility(View.VISIBLE);
        if (errorType == ErrorViewHelper.ErrorType.LIST_EMPTY) {
            btnHolderTry.setVisibility(View.GONE);
            srlHolderRefresh.setEnabled(true);
        } else {
            btnHolderTry.setVisibility(View.VISIBLE);
            srlHolderRefresh.setEnabled(false);
        }
    }

    private
    @DrawableRes
    int getPlaceholderIcon(final ErrorViewHelper.ErrorType errorType) {
        switch (errorType) {
            case LIST_EMPTY:
                return R.drawable.ic_empty_list;
            case NETWORK:
                return R.drawable.ic_error;
        }
        return 0;
    }

}
