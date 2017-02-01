package com.thinkmobiles.easyerp.presentation.custom.views.charts;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.v4.graphics.ColorUtils;
import android.util.Property;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import com.github.mikephil.charting.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author michael.soyma@thinkmobiles.com (Created on 1/25/2017.)
 */
public class TableDataView extends View {

    private static final Property<TableDataView, Float> COEFF_OFFSET =
            new Property<TableDataView, Float>(Float.class, "color") {
                @Override
                public Float get(TableDataView v) {
                    return v.getCoeffAnimOffset();
                }

                @Override
                public void set(TableDataView v, Float value) {
                    v.setCoeffAnimOffset(value);
                }
            };
    private final ObjectAnimator coeffAnimation = ObjectAnimator.ofFloat(this, COEFF_OFFSET, 0f, 1f);

    private int[] colors = new int[] {Color.BLUE};
    private int strokeColor;
    private Paint drawPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint drawStrokePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private float minWidth, maxWidth, minHeight, maxHeight;
    private float divideOffsetMin, divideOffsetMax;
    private float coeffAnimOffset = 1f;

    private List<TableItemData> data = new ArrayList<>();

    public TableDataView(Context context) {
        super(context);
        drawPaint.setStyle(Paint.Style.FILL);
        drawStrokePaint.setStyle(Paint.Style.STROKE);
        drawStrokePaint.setStrokeWidth(Utils.convertDpToPixel(4));

        textPaint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    public float getCoeffAnimOffset() {
        return coeffAnimOffset;
    }

    public void setCoeffAnimOffset(float coeffAnimOffset) {
        this.coeffAnimOffset = coeffAnimOffset;
        invalidate();
    }

    public void setColors(int[] colors) {
        this.colors = colors;
    }

    public void setStrokeColor(final int color) {
        drawStrokePaint.setColor(strokeColor = color);
    }

    public void setData(List<TableItemData> data) {
        this.data = data;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        final float calculateHG = calculateHeightGraphics();
        final float centerX = getMeasuredWidth() / 2;
        final float bottomY = getMeasuredHeight() / 2 + calculateHG / 2;
        final RectF drawRect = new RectF();

        if (data.size() == 0) {
            textPaint.setColor(ColorUtils.setAlphaComponent(Color.BLACK, Math.min((int) (255 * coeffAnimOffset), 255)));
            textPaint.setTextAlign(Paint.Align.CENTER);
            textPaint.setTextSize(maxHeight / 4);
            canvas.drawText("No data", getMeasuredWidth() / 2, getMeasuredHeight() / 2, textPaint);
        } else {
            float offsetFromBottom = (coeffAnimOffset - 1f) * calculateHG;
            float offsetFromLeft = (Math.max(coeffAnimOffset - .5f, 0f) / .5f) * minWidth / 4;
            for (int i = 0; i < data.size(); i++) {
                drawPaint.setColor(ColorUtils.setAlphaComponent(colors[i % colors.length], Math.min((int) (255 * coeffAnimOffset), 255)));
                drawStrokePaint.setColor(ColorUtils.setAlphaComponent(strokeColor, Math.min((int) (255 * coeffAnimOffset), 255)));

                final float coeff = (float) (i + 1) / data.size();
                final float newWidth = minWidth + (maxWidth - minWidth) * coeff;
                final float newHeight = minHeight + (maxHeight - minHeight) * coeff;
                final float divide = divideOffsetMin + (divideOffsetMax - divideOffsetMin) * coeff;

                drawRect.set(offsetFromLeft + centerX - newWidth / 2, bottomY - offsetFromBottom - divide - newHeight, offsetFromLeft + centerX + newWidth / 2, bottomY - offsetFromBottom - divide);
                offsetFromBottom = offsetFromBottom + divide + newHeight;

                canvas.drawRoundRect(drawRect, 10, 10, drawPaint);
                canvas.drawRoundRect(drawRect, 10, 10, drawStrokePaint);

                final TableItemData itemData = data.get(data.size() - 1 - i);

                textPaint.setColor(ColorUtils.setAlphaComponent(Color.WHITE, Math.min((int) (255 * Math.max(coeffAnimOffset - .4f, 0f) / .6f), 255)));
                textPaint.setTextAlign(Paint.Align.CENTER);

                final Rect bounds = new Rect();
                textPaint.setTextSize(newHeight / 4);

                textPaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));
                textPaint.getTextBounds(itemData.getLabel(), 0, itemData.getLabel().length(), bounds);
                canvas.drawText(itemData.getLabel(), drawRect.centerX(), drawRect.centerY() - bounds.height() / 2, textPaint);

                textPaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
                textPaint.getTextBounds(itemData.getSubLabel(), 0, itemData.getSubLabel().length(), bounds);
                canvas.drawText(itemData.getSubLabel(), drawRect.centerX(), drawRect.centerY() + newHeight / 4, textPaint);

                textPaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));
                textPaint.setTextSize(maxHeight / 6);
                textPaint.setColor(ColorUtils.setAlphaComponent(Color.GRAY, Math.min((int) (255 * Math.max(coeffAnimOffset - .7f, 0f) / .4f), 255)));
                textPaint.setTextAlign(Paint.Align.RIGHT);
                textPaint.getTextBounds(itemData.getFormattedDate(), 0, itemData.getFormattedDate().length(), bounds);

                float Y = drawRect.centerY() - bounds.height() / 4;
                for (String line : itemData.getFormattedDate().split("\n")) {
                    canvas.drawText(line, drawRect.left - divideOffsetMax, Y, textPaint);
                    Y += bounds.height() + bounds.height() / 4;
                }
            }
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        minWidth = w / 4;
        maxWidth = w / 2;
        minHeight = h / 12;
        maxHeight = h / 7;
        divideOffsetMin = h / 50;
        divideOffsetMax = h / 22;
    }

    private float calculateHeightGraphics() {
        float calculatedHeight = 0;
        for (int i = 0; i < data.size(); i++) {
            final float coeff = (float) (i + 1) / data.size();
            final float newHeight = minHeight + (maxHeight - minHeight) * coeff;
            final float divide = divideOffsetMin + (divideOffsetMax - divideOffsetMin) * coeff;
            calculatedHeight += divide + newHeight;
        }
        return calculatedHeight;
    }

    public void animateY(final int duration) {
        coeffAnimation.setInterpolator(new DecelerateInterpolator());
        coeffAnimation.setDuration(duration);
        coeffAnimation.start();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (coeffAnimation.isRunning())
            coeffAnimation.cancel();
    }

    public static class TableItemData {

        private final String formattedDate;
        private final String label;
        private final String subLabel;

        public TableItemData(String formattedDate, String label, String subLabel) {
            this.formattedDate = formattedDate;
            this.label = label;
            this.subLabel = subLabel;
        }

        public String getFormattedDate() {
            return formattedDate;
        }

        public String getLabel() {
            return label;
        }

        public String getSubLabel() {
            return subLabel;
        }
    }
}
