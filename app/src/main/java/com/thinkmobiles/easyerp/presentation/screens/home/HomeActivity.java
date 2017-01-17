package com.thinkmobiles.easyerp.presentation.screens.home;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.screens.leads.LeadsFragment_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by Lynx on 1/13/2017.
 */

@EActivity(R.layout.activity_home)
public class HomeActivity extends AppCompatActivity {

    @ViewById
    protected Toolbar toolbar_LT;

    @AfterViews
    protected void initUI() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.flFragmentContainer_AH, LeadsFragment_.builder().build())
                .commit();
    }

}
