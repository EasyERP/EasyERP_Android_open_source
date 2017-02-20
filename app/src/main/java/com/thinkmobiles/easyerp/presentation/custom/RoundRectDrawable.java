package com.thinkmobiles.easyerp.presentation.custom;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * @author Michael Soyma (Created on 2/7/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public class RoundRectDrawable extends Drawable {

    private static final int CORNER_RADIUS = 30;
    private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public RoundRectDrawable(int colorBg) {
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(colorBg);
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        final RectF rect = new RectF(0, 0, canvas.getWidth(), canvas.getHeight());
        canvas.drawRoundRect(rect, CORNER_RADIUS, CORNER_RADIUS, paint);
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
