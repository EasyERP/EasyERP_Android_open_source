package com.thinkmobiles.easyerp.presentation.screens.tutorial;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding.view.RxView;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.domain.LoginRepository;
import com.thinkmobiles.easyerp.presentation.adapters.TutorialPagerAdapter;
import com.thinkmobiles.easyerp.presentation.screens.login.LoginActivity_;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.concurrent.TimeUnit;

import me.relex.circleindicator.CircleIndicator;

/**
 * Created by Lynx on 2/21/2017.
 */


@EActivity(R.layout.activity_tutorial)
public class TutorialActivity extends AppCompatActivity implements TutorialContract.TutorialView {

    private TutorialContract.TutorialPresenter presenter;

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

    @AfterInject
    @Override
    public void initPresenter() {
        new TutorialPresenter(this, loginRepository);
    }

    @AfterViews
    protected void initUI() {
        vpTutorial_AT.setAdapter(tutorialPagerAdapter);
        ciTutorial_AT.setViewPager(vpTutorial_AT);

        RxView.clicks(btnLogin_AT)
                .throttleFirst(Constants.DELAY_CLICK, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> presenter.login());
        RxView.clicks(btnDemo_AT)
                .throttleFirst(Constants.DELAY_CLICK, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> presenter.demo());
    }

    @Override
    public void startLoginScreen() {
        LoginActivity_.intent(this)
                .flags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK)
                .start();
    }

    @Override
    public void startDemoMode() {
        Toast.makeText(this, "Demo", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(TutorialContract.TutorialPresenter presenter) {
        this.presenter = presenter;
    }
}
