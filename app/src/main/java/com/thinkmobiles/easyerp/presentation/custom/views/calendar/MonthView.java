package com.thinkmobiles.easyerp.presentation.custom.views.calendar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.thinkmobiles.easyerp.R;

import java.util.ArrayList;
import java.util.Arrays;

import static com.thinkmobiles.easyerp.presentation.custom.views.calendar.CalendarManager.CENTER;
import static com.thinkmobiles.easyerp.presentation.custom.views.calendar.CalendarManager.CIRCLE;
import static com.thinkmobiles.easyerp.presentation.custom.views.calendar.CalendarManager.LEFT;
import static com.thinkmobiles.easyerp.presentation.custom.views.calendar.CalendarManager.NONE;
import static com.thinkmobiles.easyerp.presentation.custom.views.calendar.CalendarManager.RIGHT;


/**
 * Created by Alex Michenko on 21.03.2017.
 */

public class MonthView extends View implements GestureDetector.OnGestureListener {


    private String monthTitle = "Title";
    private ArrayList<String> dayNames = new ArrayList<>(Arrays.asList("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"));

    private Paint paintTitle;
    private Paint paintLine;
    private Paint paintWeek;
    private Paint paintDay;
    private Paint paintMonth;
    private Paint paintSelector;
    private Rect rectText;

    private int colorBackground;
    private int colorMonthName;
    private int colorHeaderLine;
    private int colorDayName;
    private int colorWeekend;
    private int colorDay;
    private int colorSelectedDay;
    private float textMonthNameSize;
    private float textDayNameSize;
    private float textDaySize;
    private boolean visibleHeaderLine;
    private boolean selectWeekend;
    private Typeface regularBold, regular;

    private float radiusCircle;
    private float mPadding;
    private float stepV;
    private float stepH;
    private float stepV2;
    private float stepH2;
    private float yLine;

    private ArrayList<DayDH> days = new ArrayList<>();

    private GestureDetector gestureDetector;

    public MonthView(Context context) {
        this(context, null);
    }

    public MonthView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MonthView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        gestureDetector = new GestureDetector(context, this);
        initAttrs(attrs);
        initDays();
    }

    private void initAttrs(AttributeSet attrs) {
        float sp = getContext().getResources().getDisplayMetrics().scaledDensity;
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.MonthView, 0, 0);
        try {
            colorBackground = ta.getColor(R.styleable.MonthView_colorBackground, Color.BLACK);
            colorMonthName = ta.getColor(R.styleable.MonthView_colorMonthName, Color.BLACK);
            colorHeaderLine = ta.getColor(R.styleable.MonthView_colorHeaderLine, Color.BLACK);
            colorDayName = ta.getColor(R.styleable.MonthView_colorDayName, Color.BLACK);
            colorDay = ta.getColor(R.styleable.MonthView_colorDay, Color.BLACK);
            colorWeekend = ta.getColor(R.styleable.MonthView_colorWeekend, Color.BLACK);
            colorSelectedDay = ta.getColor(R.styleable.MonthView_colorSelectedDay, Color.WHITE);

            textMonthNameSize = ta.getDimension(R.styleable.MonthView_textMonthNameSize, 20 * sp);
            textDayNameSize = ta.getDimension(R.styleable.MonthView_textDayNameSize, 16 * sp);
            textDaySize = ta.getDimension(R.styleable.MonthView_textDaySize, 14 * sp);

            visibleHeaderLine = ta.getBoolean(R.styleable.MonthView_visibleHeaderLine, false);
            selectWeekend = ta.getBoolean(R.styleable.MonthView_selectWeekend, false);
        } finally {
            ta.recycle();
        }
    }


    private void initDays() {
        float dp = getContext().getResources().getDisplayMetrics().density;
        mPadding = 16 * dp;
        rectText = new Rect();
        regularBold = Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD);
        regular = Typeface.create(Typeface.SANS_SERIF, Typeface.NORMAL);

        paintMonth = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintMonth.setColor(colorBackground);
        paintMonth.setStyle(Paint.Style.FILL);

        paintSelector = new Paint(paintMonth);

        paintLine = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintLine.setStyle(Paint.Style.STROKE);
        paintLine.setColor(colorHeaderLine);

        paintTitle = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintTitle.setColor(colorMonthName);
        paintTitle.setStyle(Paint.Style.FILL);
        paintTitle.setStrokeWidth(1 * dp);
        paintTitle.setTextSize(textMonthNameSize);
        paintTitle.setTextAlign(Paint.Align.CENTER);
        paintTitle.setTypeface(regularBold);
        paintTitle.getTextBounds(monthTitle, 0, monthTitle.length(), rectText);
        yLine = 2 * mPadding + rectText.height();

        paintWeek = new Paint(paintTitle);
        paintWeek.setColor(colorDayName);
        paintWeek.setTextSize(textDayNameSize);
        paintWeek.setTypeface(regularBold);

        paintDay = new Paint(paintTitle);
        paintDay.setColor(colorDay);
        paintDay.setTextSize(textDaySize);
        paintDay.setTypeface(regular);

    }

    public void setDays(ArrayList<DayDH> days) {
        this.days = days;
    }

    public void setMonthTitle(String monthTitle) {
        this.monthTitle = monthTitle;
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasuredWidth(), getMeasuredWidth());
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        stepH = (w - 2 * mPadding) / 7f;
        stepV = (h - yLine - mPadding) / 7f;
        stepH2 = stepH / 2f;
        stepV2 = stepV / 2f;
        radiusCircle = stepH * 0.375f;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawBackground(canvas);
        drawTitle(canvas);
        if (visibleHeaderLine) drawTitleLine(canvas);
        drawWeekDays(canvas);

        drawMonthDays(canvas);
    }

    private void drawBackground(Canvas canvas) {
        canvas.drawRect(0, 0, getWidth(), getWidth(), paintMonth);
    }

    private void drawTitle(Canvas canvas) {
        paintTitle.getTextBounds(monthTitle, 0, monthTitle.length(), rectText);
        canvas.drawText(monthTitle, getWidth() / 2, yLine - mPadding, paintTitle);
    }

    private void drawTitleLine(Canvas canvas) {
        canvas.drawLine(0, yLine, getWidth(), yLine, paintLine);
    }

    private void drawWeekDays(Canvas canvas) {
        for (int i = 0; i < dayNames.size(); i++) {
            String d = dayNames.get(i);
            paintWeek.getTextBounds(d, 0, d.length(), rectText);
            paintWeek.setColor(selectWeekend && i > 4 ? colorWeekend : colorDayName);
            canvas.drawText(d, i * stepH + stepH2 + mPadding, yLine + stepV2 + rectText.height() / 2, paintWeek);
        }
    }

    private void drawMonthDays(Canvas canvas) {
        for (int i = 0, week = 0, day = 0; i < days.size(); i++) {
            DayDH dayDH = days.get(i);
            float x = mPadding + stepH2 + day * stepH;
            float y = yLine + stepV2 + (week + 1) * stepV;
            drawDayBg(canvas, x, y, dayDH.getShapeType(), dayDH.getShapeColor());

            String d = dayDH.getDay();
            paintDay.getTextBounds(d, 0, d.length(), rectText);
            int color = selectWeekend && day > 4 ? colorWeekend : colorDay;
            paintDay.setColor(dayDH.getShapeType() == NONE ? color : colorSelectedDay);
            paintDay.setFakeBoldText(dayDH.getShapeType() != NONE);
            canvas.drawText(d, x, y + rectText.height() / 2, paintDay);

            ++day;
            if (day == 7) {
                ++week;
                day = 0;
            }
        }
    }

    private void drawDayBg(Canvas canvas, float cX, float cY, @CalendarManager.Shape int shapeType, int color) {
        paintSelector.setColor(color);
        switch (shapeType) {
            case CIRCLE:
                canvas.drawCircle(cX, cY, radiusCircle, paintSelector);
                break;
            case LEFT:
                canvas.drawCircle(cX, cY, radiusCircle, paintSelector);
                canvas.drawRect(cX, cY - radiusCircle, cX + stepH2, cY + radiusCircle, paintSelector);
                break;
            case CENTER:
                canvas.drawRect(cX - stepH2, cY - radiusCircle, cX + stepH2, cY + radiusCircle, paintSelector);
                break;
            case RIGHT:
                canvas.drawCircle(cX, cY, radiusCircle, paintSelector);
                canvas.drawRect(cX - stepH2, cY - radiusCircle, cX, cY + radiusCircle, paintSelector);
                break;
            case NONE:
            default:
                break;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }


    @Override
    public boolean onDown(MotionEvent e) {
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }
}
