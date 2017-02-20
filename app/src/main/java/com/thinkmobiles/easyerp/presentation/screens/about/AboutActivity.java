package com.thinkmobiles.easyerp.presentation.screens.about;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.domain.UserRepository;
import com.thinkmobiles.easyerp.presentation.managers.CookieManager;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.BooleanRes;
import org.androidannotations.annotations.res.StringRes;

/**
 * Created by Lynx on 2/17/2017.
 */

@EActivity(R.layout.activity_about)
public class AboutActivity extends AppCompatActivity implements AboutContract.AboutView {

    private AboutContract.AboutPresenter presenter;

    @Extra
    protected String url;

    @ViewById
    protected ProgressBar pbProgress_AA;
    @ViewById
    protected WebView wvAbout_AA;
    @ViewById
    protected Toolbar toolbar;
    @ViewById
    protected Toolbar toolbarDetail;

    @StringRes(R.string.about)
    protected String about;
    @BooleanRes
    public boolean isTablet;
    @BooleanRes
    public boolean isPortrait;

    @Bean
    protected UserRepository userRepository;
    @Bean
    protected CookieManager cookieManager;

    @AfterViews
    protected void initUI() {
        initToolbar();
        wvAbout_AA.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                showProgress(false);
            }
        });
        presenter.subscribe();
    }

    @Override
    public void displayWebUrl(String url) {
        wvAbout_AA.loadUrl(url);
    }

    @Override
    public void showErrorToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress(boolean isShown) {
        pbProgress_AA.setVisibility(isShown ? View.VISIBLE : View.GONE);
    }

    @AfterInject
    @Override
    public void initPresenter() {
        new AboutPresenter(this, userRepository, cookieManager, url);
    }

    @Override
    public void setPresenter(AboutContract.AboutPresenter presenter) {
        this.presenter = presenter;
    }

    @OptionsItem(android.R.id.home)
    protected void clickBack() {
        onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(presenter != null) presenter.unsubscribe();
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);

        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayShowTitleEnabled(true);
        }
        setTitle(about);
    }
}
