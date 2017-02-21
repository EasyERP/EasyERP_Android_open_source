package com.thinkmobiles.easyerp.presentation.adapters;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.thinkmobiles.easyerp.R;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.regex.Pattern;

/**
 * Created by Lynx on 2/20/2017.
 */

@EBean
public class TutorialPagerAdapter extends PagerAdapter {

    private LayoutInflater inflater;

    @RootContext
    protected Activity activity;

    @AfterInject
    protected void initInflater() {
        inflater = LayoutInflater.from(activity);
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return bindData(position, container);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    private View bindData(int position, ViewGroup container) {
        View view = inflater.inflate(R.layout.view_tutorial_item, container, false);
        TextView tvTitle_VTI = (TextView) view.findViewById(R.id.tvTitle_VTI);
        ImageView ivTutorialImage_VTI = (ImageView) view.findViewById(R.id.ivTutorialImage_VTI);
        switch (position) {
            case 0:
                tvTitle_VTI.setText("First page");
                ivTutorialImage_VTI.setImageDrawable(ContextCompat.getDrawable(activity, R.drawable.gabe));
                break;
            case 1:
                tvTitle_VTI.setText("Second page");
                ivTutorialImage_VTI.setImageDrawable(ContextCompat.getDrawable(activity, R.drawable.gabe));
                break;
            case 2:
                tvTitle_VTI.setText("Third page");
                ivTutorialImage_VTI.setImageDrawable(ContextCompat.getDrawable(activity, R.drawable.gabe));
                break;
            case 3:
                tvTitle_VTI.setText("Fourth page");
                ivTutorialImage_VTI.setImageDrawable(ContextCompat.getDrawable(activity, R.drawable.gabe));
                break;
            case 4:
                tvTitle_VTI.setText("Fifth page");
                ivTutorialImage_VTI.setImageDrawable(ContextCompat.getDrawable(activity, R.drawable.gabe));
                break;
        }
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getItemPosition (Object object) {
        return POSITION_NONE;
    }
}
