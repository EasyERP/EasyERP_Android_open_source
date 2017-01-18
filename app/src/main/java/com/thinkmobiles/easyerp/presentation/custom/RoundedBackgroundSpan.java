package com.thinkmobiles.easyerp.presentation.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v4.content.ContextCompat;
import android.text.style.ReplacementSpan;

/**
 * Created by Lynx on 1/16/2017.
 */

public class RoundedBackgroundSpan extends ReplacementSpan {

    private final int mPadding = 10;
    private int mBackgroundColor;
    private int mTextColor;

    public RoundedBackgroundSpan(Context context, int backgroundColor, int textColor) {
        super();
        mBackgroundColor = ContextCompat.getColor(context, backgroundColor);
        mTextColor = textColor;
    }

    @Override
    public int getSize(Paint paint, CharSequence text, int start, int end, Paint.FontMetricsInt fm) {
        return (int) (mPadding + paint.measureText(text.subSequence(start, end).toString()) + mPadding);
    }

    @Override
    public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {
        final float width = paint.measureText(text.subSequence(start, end).toString());
        final Paint.FontMetrics metrics = paint.getFontMetrics();
        final float height = metrics.bottom - metrics.top;

        RectF rect = new RectF(x, top, x + width + 2 * mPadding, top + height);
        paint.setColor(mBackgroundColor);
        canvas.drawRoundRect(rect, mPadding, mPadding, paint);
        paint.setColor(mTextColor);
        canvas.drawText(text, start, end, x + mPadding, top + height - 4 * mPadding / 5, paint);
    }
}
