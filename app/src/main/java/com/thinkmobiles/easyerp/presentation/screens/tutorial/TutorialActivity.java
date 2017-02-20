package com.thinkmobiles.easyerp.presentation.screens.tutorial;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.thinkmobiles.easyerp.R;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by Lynx on 2/20/2017.
 */

@EActivity(R.layout.activity_tutorial)
public class TutorialActivity extends AppCompatActivity {

    @ViewById
    protected ViewPager vpTutorial_AT;
    @ViewById
    protected TextView btnLogin_AT;
    @ViewById
    protected TextView btnDemo_AT;

}
