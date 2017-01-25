package com.thinkmobiles.easyerp.presentation.custom.views.charts;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.view.View;

import com.github.mikephil.charting.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Asus_Dev on 1/25/2017.
 */
public class TableDataView extends View {

    private int[] colors = new int[] {Color.BLUE};
    private Paint drawPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint drawStrokePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private float minWidth, maxWidth, minHeight, maxHeight;
    private float divideOffsetMin, divideOffsetMax;

    private List<TableItemData> data = new ArrayList<>();

    public TableDataView(Context context) {
        super(context);
        drawPaint.setStyle(Paint.Style.FILL);
        drawStrokePaint.setStyle(Paint.Style.STROKE);
        drawStrokePaint.setStrokeWidth(Utils.convertDpToPixel(2));

        textPaint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    public void setColors(int[] colors) {
        this.colors = colors;
    }

    public void setStrokeColor(final int color) {
        drawStrokePaint.setColor(color);
    }

    public void setData(List<TableItemData> data) {
        this.data = data;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        final float centerX = getMeasuredWidth() / 2;
        final float bottomY = getMeasuredHeight() - getPaddingBottom();
        final RectF drawRect = new RectF();

        float offsetFromBottom = 0f;
        for (int i = 0; i < data.size(); i++) {
            drawPaint.setColor(colors[i % colors.length]);

            final float coeff = (float) (i + 1) / data.size();
            final float newWidth = minWidth + (maxWidth - minWidth) * coeff;
            final float newHeight = minHeight + (maxHeight - minHeight) * coeff;
            final float divide = divideOffsetMin + (divideOffsetMax - divideOffsetMin) * coeff;

            drawRect.set(centerX - newWidth / 2, bottomY - offsetFromBottom - divide - newHeight, centerX + newWidth / 2, bottomY - offsetFromBottom - divide);
            offsetFromBottom = offsetFromBottom + divide + newHeight;

            canvas.drawRect(drawRect, drawPaint);
            canvas.drawRect(drawRect, drawStrokePaint);

            final TableItemData itemData = data.get(data.size() - 1 - i);

            textPaint.setColor(Color.BLACK);
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
            textPaint.setTextSize(maxHeight / 5);
            textPaint.setColor(Color.GRAY);
            textPaint.setTextAlign(Paint.Align.RIGHT);
            textPaint.getTextBounds(itemData.getFormattedDate(), 0, itemData.getFormattedDate().length(), bounds);
            canvas.drawText(itemData.getFormattedDate(), drawRect.left - divideOffsetMax, drawRect.centerY() + bounds.height() / 2, textPaint);
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
