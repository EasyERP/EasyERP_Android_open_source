package com.thinkmobiles.easyerp.presentation.base;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import org.androidannotations.annotations.EFragment;

/**
 * @author Michael Soyma (Created on 2/22/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
@EFragment
public abstract class BaseSupportFragment<T extends AppCompatActivity> extends Fragment {

    protected T mActivity;

    @Override
    public void onAttach(Activity context) {
        super.onAttach(context);
        try {
            mActivity = (T) context;
        } catch (ClassCastException e) {
            throw new RuntimeException("This fragment should have mActivity instance");
        }
    }
}
