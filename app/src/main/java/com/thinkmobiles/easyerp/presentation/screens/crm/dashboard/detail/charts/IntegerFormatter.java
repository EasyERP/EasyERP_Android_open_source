package com.thinkmobiles.easyerp.presentation.screens.crm.dashboard.detail.charts;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

public class IntegerFormatter implements IValueFormatter, IAxisValueFormatter {

    protected DecimalFormat mFormat;

    public IntegerFormatter() {
        mFormat = (DecimalFormat) NumberFormat.getInstance(Locale.US);
//        DecimalFormatSymbols symbols = mFormat.getDecimalFormatSymbols();
//        symbols.setGroupingSeparator(' ');
//        mFormat.setRoundingMode(RoundingMode.HALF_EVEN);
//        mFormat.setMaximumFractionDigits(2);
//        mFormat.setDecimalFormatSymbols(symbols);
    }

    // IValueFormatter
    @Override
    public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
        return mFormat.format(value);
    }

    // IAxisValueFormatter
    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        return mFormat.format(value);
    }

    public DecimalFormat getFormat() {
        return mFormat;
    }

    public int getDecimalDigits() {
        return 1;
    }
}
