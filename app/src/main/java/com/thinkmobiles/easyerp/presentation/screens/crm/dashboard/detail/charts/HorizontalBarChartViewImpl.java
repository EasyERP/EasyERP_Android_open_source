package com.thinkmobiles.easyerp.presentation.screens.crm.dashboard.detail.charts;

import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.widget.FrameLayout;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.Utils;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.data.model.crm.dashboard.detail.invoice.InvoiceItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Asus_Dev on 1/24/2017.
 */

public final class HorizontalBarChartViewImpl implements IChartView<BarData> {

    private HorizontalBarChart barChart;
    private final List<String> xLabels = new ArrayList<>();

    @Override
    public void render(FrameLayout parent, Object data) {
        parent.removeAllViews();

        barChart = new HorizontalBarChart(parent.getContext());
        barChart.setData(prepareData(data));
        barChart.setExtraOffsets(0, 0, 15, 50);

        XAxis xl = barChart.getXAxis();
        xl.setPosition(XAxis.XAxisPosition.BOTTOM);
        xl.setDrawAxisLine(true);
        xl.setDrawGridLines(false);
        xl.setLabelCount(xLabels.size());
        xl.setGranularityEnabled(true);
        xl.setValueFormatter((value, axis) -> xLabels.get((int) value));
        xl.setDrawLabels(true);

        YAxis yl = barChart.getAxisLeft();
        yl.setDrawAxisLine(true);
        yl.setDrawGridLines(true);
        yl.setAxisMinimum(0f);
        yl.setTextSize(13);
        yl.setValueFormatter(new DollarFormatter());

        YAxis yr = barChart.getAxisRight();
        yr.setDrawAxisLine(true);
        yr.setDrawGridLines(false);
        yr.setAxisMinimum(0f);
        yr.setTextSize(13);
        yr.setValueFormatter(new DollarFormatter());

        Legend legend = barChart.getLegend();
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);
        legend.setTextColor(ContextCompat.getColor(parent.getContext(), R.color.colorPrimaryDark));
        legend.setFormToTextSpace(8);
        legend.setTextSize(13);

        final Description description = barChart.getDescription();
        description.setEnabled(true);
        description.setText("Revenue");
        description.setTextSize(16);
        description.setTextColor(ContextCompat.getColor(parent.getContext(), R.color.colorPrimaryDark));
        description.setTextAlign(Paint.Align.CENTER);
        description.setPosition(
                (parent.getWidth() - parent.getPaddingLeft() - parent.getPaddingRight() ) / 2 + Utils.convertDpToPixel(38),
                parent.getHeight() - parent.getPaddingTop() - parent.getPaddingBottom() - Utils.convertDpToPixel(38));

        barChart.setFitBars(true);
        barChart.animateY(700);

        parent.addView(barChart);
    }

    @SuppressWarnings("unchecked")
    @Override
    public BarData prepareData(Object data) {
        final List<InvoiceItem> invoiceItems = (List<InvoiceItem>) data;
        final List<BarEntry> barEntries = new ArrayList<>();

        Collections.sort(invoiceItems);

        int x = 0;
        for (InvoiceItem invoiceItem: invoiceItems) {
            barEntries.add(new BarEntry(x++, (float) (invoiceItem.sum / 100d)));
            xLabels.add(invoiceItem.id);
        }

        final BarDataSet barDataSet = new BarDataSet(barEntries, "Sales Managers");
        barDataSet.setValueFormatter(new DollarFormatter());
        barDataSet.setValueTextSize(10);
        barDataSet.setDrawValues(true);
        barDataSet.setColors(generateGradientColor(invoiceItems.size()));
        barDataSet.setForm(Legend.LegendForm.CIRCLE);
        barDataSet.setFormSize(12);

        final BarData barData = new BarData(barDataSet);
        barData.setHighlightEnabled(false);

        return barData;
    }

    private int[] generateGradientColor(int size) {
        final int[] colors = new int[size];
        final int startColor = Color.parseColor("#99D6EA");
        final int endColor = Color.parseColor("#1082a7");

        int rStart = Color.red(startColor);
        int gStart = Color.green(startColor);
        int bStart = Color.blue(startColor);

        int rEnd = Color.red(endColor);
        int gEnd = Color.green(endColor);
        int bEnd = Color.blue(endColor);

        if (size <= 1)
            return new int[] {startColor, endColor};
        else {
            int redStep = (rEnd - rStart) / (size - 1);
            int greenStep = (gEnd - gStart) / (size - 1);
            int blueStep = (bEnd - bStart) / (size - 1);

            for (int i = 0; i < size; i++)
                colors[i] = Color.rgb(rStart + i * redStep, gStart + i * greenStep, bStart + i * blueStep);

            return colors;
        }
    }

}
