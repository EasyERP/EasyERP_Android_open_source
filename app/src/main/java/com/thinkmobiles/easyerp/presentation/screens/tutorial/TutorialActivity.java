package com.thinkmobiles.easyerp.presentation.screens.tutorial;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.adapters.TutorialPagerAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

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

    @AfterViews
    protected void initUI() {
        vpTutorial_AT.setAdapter(tutorialPagerAdapter);
        ciTutorial_AT.setViewPager(vpTutorial_AT);
    }

    @Override
    public void startLoginScreen() {

    }

    @Override
    public void startDemoMode() {

    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void setPresenter(TutorialContract.TutorialPresenter presenter) {
        this.presenter = presenter;
    }
}
