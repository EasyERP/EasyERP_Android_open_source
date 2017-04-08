package com.thinkmobiles.easyerp.presentation.managers;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.view.View;
import android.widget.LinearLayout;

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
    private ValueAnimator expandAnim;

    private boolean isOpened;

    public void init(View targetArrow, View targetList, View targetContainer) {
        rotateAnim = ObjectAnimator.ofFloat(targetArrow, View.ROTATION, 0, 0);

        expandAnim = new ValueAnimator();
        expandAnim.addUpdateListener(animation -> {
            float weight = (float) animation.getAnimatedValue();
            LinearLayout.LayoutParams paramsList = (LinearLayout.LayoutParams) targetList.getLayoutParams();
            paramsList.weight = weight;
            targetList.setLayoutParams(paramsList);
            LinearLayout.LayoutParams paramsContainer = (LinearLayout.LayoutParams) targetContainer.getLayoutParams();
            paramsContainer.weight = 1 - weight;
            targetContainer.setLayoutParams(paramsContainer);
        });

        set = new AnimatorSet();
        set.setInterpolator(new FastOutSlowInInterpolator());
        set.setDuration(animDuration);
        set.playTogether(rotateAnim, expandAnim);
    }

    public void open() {
        set.setDuration(isOpened ? 0 : animDuration);
        isOpened = true;
        expandAnim.setFloatValues(0, 1);
        rotateAnim.setFloatValues(0, 180);
        set.start();
    }

    public void close() {
        set.setDuration(!isOpened ? 0 : animDuration);
        isOpened = false;
        expandAnim.setFloatValues(1, 0);
        rotateAnim.setFloatValues(180, 360);
        set.start();
    }

    public void cancel() {
        set.cancel();
    }

}
