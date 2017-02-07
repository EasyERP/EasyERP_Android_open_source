package com.thinkmobiles.easyerp.presentation.managers;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.view.View;
import android.view.ViewGroup;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.res.IntegerRes;

/**
 * @author Alex Michenko (Created on 03.02.17).
 *         Company: Thinkmobiles
 *         Email: alex.michenko@thinkmobiles.com
 */

@EBean
public class HistoryAnimationHelper {

    @IntegerRes(android.R.integer.config_longAnimTime)
    protected int animDuration;

    private AnimatorSet set;
    private ObjectAnimator rotateAnim;
    private ValueAnimator slideAnim;

    public void init(View targetArrow, View targetList) {
        rotateAnim = ObjectAnimator.ofFloat(targetArrow, View.ROTATION, 0, 0);

        slideAnim = new ValueAnimator();
        slideAnim.addUpdateListener(animation -> {
            int height = (int) animation.getAnimatedValue();
            targetList.setVisibility(height == 0 ? View.GONE : View.VISIBLE);
            ViewGroup.LayoutParams params = targetList.getLayoutParams();
            params.height = height;
            targetList.setLayoutParams(params);
        });

        set = new AnimatorSet();
        set.setInterpolator(new FastOutSlowInInterpolator());
        set.setDuration(animDuration);
        set.playTogether(rotateAnim, slideAnim);
    }

    public void forward(int heightFrom) {
        slideAnim.setIntValues(0, heightFrom);
        rotateAnim.setFloatValues(0, 180);
        set.start();
    }

    public void backward(int heightFrom) {
        slideAnim.setIntValues(heightFrom, 0);
        rotateAnim.setFloatValues(180, 360);
        set.start();
    }

    public void cancel() {
        set.cancel();
    }

}
