package com.thinkmobiles.easyerp.presentation.screens.details;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.widget.Toast;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.domain.auth.UserRepository;
import com.thinkmobiles.easyerp.presentation.EasyErpApplication;
import com.thinkmobiles.easyerp.presentation.base.BaseMasterFlowActivity;
import com.thinkmobiles.easyerp.presentation.listeners.IFragmentInstance;
import com.thinkmobiles.easyerp.presentation.managers.CookieManager;
import com.thinkmobiles.easyerp.presentation.managers.GoogleAnalyticHelper;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;

/**
 * @author Alex Michenko (Created on 27.02.17).
 *         Company: Thinkmobiles
 *         Email: alex.michenko@thinkmobiles.com
 */

@EActivity(R.layout.activity_details)
public class DetailsActivity extends BaseMasterFlowActivity implements DetailsContract.DetailsView {

    private DetailsContract.DetailsPresenter presenter;

    @Extra
    protected Bundle bundle;
    @Extra
    protected String titleDetails;
    @Extra
    protected IFragmentInstance creator;

    @Bean
    protected UserRepository userRepository;
    @Bean
    protected CookieManager cookieManager;

    private ProgressDialog progressDialog;

    @AfterInject
    @Override
    public void initPresenter() {
        new DetailsPresenter(userRepository, this, cookieManager);
        progressDialog = new ProgressDialog(this, R.style.DefaultTheme_NoTitleDialogWithAnimation);
        progressDialog.setMessage(getString(R.string.dialog_logout_message));
        progressDialog.setCancelable(false);
    }

    @AfterViews
    protected void initContent() {
        presenter.subscribe();
        if (getFragmentManager().findFragmentById(contentIdLayout()) == null) {
            replaceFragmentContent(creator.getInstance(bundle), titleDetails);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.unsubscribe();
    }

    @Override
    @DrawableRes
    protected int getHomeIcon() {
        return R.drawable.ic_back;
    }

    @Override
    protected void logOut() {
        GoogleAnalyticHelper.trackClick(this, GoogleAnalyticHelper.EventType.CLICK_MENU_ITEM, "Logout");
        presenter.logOut();
    }

    @Override
    protected void onHomeMenuSelect(boolean isHamburger) {
        onBackPressed();
    }

    @Override
    protected int contentIdLayout() {
        return R.id.flContainerDetails;
    }

    @Override
    protected int contentDetailIdLayout() {
        return 0;
    }


    @Override
    public void restartApp() {
        EasyErpApplication.getInstance().restartApp();
    }

    @Override
    public void showProgress() {
        progressDialog.show();
    }

    @Override
    public void dismissProgress() {
        progressDialog.dismiss();
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void setPresenter(DetailsContract.DetailsPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public String getScreenName() {
        return "Details screen";
    }
}
