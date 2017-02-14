package com.thinkmobiles.easyerp.presentation.custom;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * @author Michael Soyma (Created on 2/14/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public class CircleStrokeDrawable extends Drawable {

    private static final int STROKE_WIDTH = 2;
    private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public CircleStrokeDrawable(final Resources res, int colorBg) {
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(colorBg);
        paint.setStrokeWidth(STROKE_WIDTH * res.getDisplayMetrics().density);
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        final int size = Math.min(canvas.getWidth(), canvas.getHeight());
        canvas.drawCircle(size / 2, size / 2, size / 2 - paint.getStrokeWidth(), paint);
    }

    @Override
    public void setAlpha(@IntRange(from = 0, to = 255) int i) {

    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSPARENT;
    }
}
