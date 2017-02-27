package com.thinkmobiles.easyerp.presentation.screens.tutorial;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding.view.RxView;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.data.model.user.UserInfo;
import com.thinkmobiles.easyerp.domain.LoginRepository;
import com.thinkmobiles.easyerp.domain.UserRepository;
import com.thinkmobiles.easyerp.presentation.adapters.TutorialPagerAdapter;
import com.thinkmobiles.easyerp.presentation.custom.transformations.ZoomOutSlideTransformer;
import com.thinkmobiles.easyerp.presentation.managers.CookieManager;
import com.thinkmobiles.easyerp.presentation.managers.GoogleAnalyticHelper;
import com.thinkmobiles.easyerp.presentation.screens.home.HomeActivity_;
import com.thinkmobiles.easyerp.presentation.screens.login.LoginActivity_;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import java.util.concurrent.TimeUnit;

import me.relex.circleindicator.CircleIndicator;

/**
 * Created by Lynx on 2/21/2017.
 */


@EActivity(R.layout.activity_tutorial)
public class TutorialActivity extends AppCompatActivity implements TutorialContract.TutorialView {

    private TutorialContract.TutorialPresenter presenter;
    private ProgressDialog progressDialog;

    @Extra
    protected boolean isPreview;

    @ViewById
    protected ViewPager vpTutorial_AT;
    @ViewById
    protected TextView btnLogin_AT;
    @ViewById
    protected TextView btnDemo_AT;
    @ViewById
    protected CircleIndicator ciTutorial_AT;

    @Bean
    protected TutorialPagerAdapter tutorialPagerAdapter;
    @Bean
    protected LoginRepository loginRepository;
    @Bean
    protected CookieManager cookieManager;
    @Bean
    protected UserRepository userRepository;

    @AfterInject
    @Override
    public void initPresenter() {
        new TutorialPresenter(this, loginRepository, cookieManager, userRepository, isPreview);
    }

    @AfterViews
    protected void initUI() {
        GoogleAnalyticHelper.trackScreenView(this, getResources().getConfiguration());

        vpTutorial_AT.setAdapter(tutorialPagerAdapter);
        ciTutorial_AT.setViewPager(vpTutorial_AT);
        vpTutorial_AT.setPageTransformer(true, new ZoomOutSlideTransformer());

        btnLogin_AT.setVisibility(isPreview ? View.INVISIBLE : View.VISIBLE);
        btnDemo_AT.setVisibility(isPreview ? View.INVISIBLE : View.VISIBLE);

        RxView.clicks(btnLogin_AT)
                .throttleFirst(Constants.DELAY_CLICK, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> {
                    GoogleAnalyticHelper.trackClick(this, GoogleAnalyticHelper.EventType.CLICK_BUTTON, "Login");
                    presenter.login();
                });
        RxView.clicks(btnDemo_AT)
                .throttleFirst(Constants.DELAY_CLICK, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> {
                    GoogleAnalyticHelper.trackClick(this, GoogleAnalyticHelper.EventType.CLICK_BUTTON, "Demo");
                    presenter.demo();
                });
    }

    @Override
    public void showProgress(String msg) {
        progressDialog = new ProgressDialog(this, R.style.DefaultTheme_NoTitleDialogWithAnimation);
        progressDialog.setCancelable(false);
        progressDialog.setMessage(msg);
        progressDialog.show();
    }

    @Override
    public void dismissProgress() {
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    @Override
    public void showInfoToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void startLoginScreen() {
        LoginActivity_.intent(this)
                .flags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK)
                .start();
    }

    @Override
    public void startHomeScreen(UserInfo userInfo) {
        HomeActivity_.intent(this)
                .userInfo(userInfo)
                .flags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK)
                .start();
    }

    @Override
    public void setPresenter(TutorialContract.TutorialPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public String getScreenName() {
        return "Tutorial screen";
    }
}
