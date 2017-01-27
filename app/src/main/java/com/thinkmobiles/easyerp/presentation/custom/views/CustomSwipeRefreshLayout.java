package com.thinkmobiles.easyerp.presentation.custom.views;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

/**
 * Created by Asus_Dev on 1/25/2017.
 */

public class CustomSwipeRefreshLayout extends SwipeRefreshLayout {

    private int mTouchSlop;
    private float mPrevY;
    // Indicate if we've already declined the move event
    private boolean mDeclined;

    public CustomSwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPrevY = MotionEvent.obtain(event).getY();
                break;

            case MotionEvent.ACTION_MOVE:
                final float eventY = event.getY();
                float yDiff = Math.abs(eventY - mPrevY);

                if (mDeclined || yDiff > mTouchSlop) {
                    mDeclined = true;
                    return false;
                }
        }

        return super.onInterceptTouchEvent(event);
    }

}
