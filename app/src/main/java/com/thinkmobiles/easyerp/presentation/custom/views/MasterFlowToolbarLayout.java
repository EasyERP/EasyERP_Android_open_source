package com.thinkmobiles.easyerp.presentation.custom.views;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

/**
 * @author michael.soyma@thinkmobiles.com (Created on 1/27/2017.)
 */

public class MasterFlowToolbarLayout extends LinearLayout {

    public MasterFlowToolbarLayout(Context context) {
        super(context);
    }

    public MasterFlowToolbarLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MasterFlowToolbarLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (getChildCount() == 2) {
            final View childFirst = getChildAt(0); final float weightFirst = ((LayoutParams) childFirst.getLayoutParams()).weight;
            final View childSecond = getChildAt(1); final float weightSecond = ((LayoutParams) childSecond.getLayoutParams()).weight;
            final int widthFirst = (int) ((weightFirst/(weightFirst + weightSecond)) * (getMeasuredWidth() - getMinimumWidth())) + getMinimumWidth();

            childFirst.measure(
                    MeasureSpec.makeMeasureSpec(widthFirst, MeasureSpec.EXACTLY),
                    MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(heightMeasureSpec), MeasureSpec.EXACTLY));
            childSecond.measure(
                    MeasureSpec.makeMeasureSpec(getMeasuredWidth() - widthFirst, MeasureSpec.EXACTLY),
                    MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(heightMeasureSpec), MeasureSpec.EXACTLY));
        }
    }

}
