package com.thinkmobiles.easyerp.presentation.adapters;

import android.app.Activity;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
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

/**
 * Created by Lynx on 2/20/2017.
 */

@EBean
public class TutorialPagerAdapter extends PagerAdapter {

    private LayoutInflater inflater;

    private static final int TUTORIAL_SCREEN_COUNT = 10;
    private @StringRes int[] titles = new int[TUTORIAL_SCREEN_COUNT];
    private @DrawableRes int[] screens = new int[TUTORIAL_SCREEN_COUNT];

    @RootContext
    protected Activity activity;

    @AfterInject
    protected void initInflater() {
        inflater = LayoutInflater.from(activity);
    }

    @AfterInject
    protected void initData() {
        titles[0] = R.string.tutorial_1;
        titles[1] = R.string.tutorial_2;
        titles[2] = R.string.tutorial_3;
        titles[3] = R.string.tutorial_4;
        titles[4] = R.string.tutorial_5;
        titles[5] = R.string.tutorial_6;
        titles[6] = R.string.tutorial_7;
        titles[7] = R.string.tutorial_8;
        titles[8] = R.string.tutorial_9;
        titles[9] = R.string.tutorial_10;

        screens[0] = R.drawable.tutorial1;
        screens[1] = R.drawable.tutorial2;
        screens[2] = R.drawable.tutorial3;
        screens[3] = R.drawable.tutorial4;
        screens[4] = R.drawable.tutorial5;
        screens[5] = R.drawable.tutorial6;
        screens[6] = R.drawable.tutorial7;
        screens[7] = R.drawable.tutorial8;
        screens[8] = R.drawable.tutorial9;
        screens[9] = R.drawable.tutorial10;
    }

    @Override
    public int getCount() {
        return TUTORIAL_SCREEN_COUNT;
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

        TextView title = (TextView) view.findViewById(R.id.tvTitle_VTI);
        ImageView screen = (ImageView) view.findViewById(R.id.ivTutorialImage_VTI);

        title.setText(titles[position]);
        screen.setImageDrawable(ContextCompat.getDrawable(activity, screens[position]));

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
