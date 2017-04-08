package com.thinkmobiles.easyerp.presentation.screens.crm.dashboard.detail.charts;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.widget.FrameLayout;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.data.model.crm.invoice.Invoice;
import com.thinkmobiles.easyerp.presentation.managers.ColorGenerateManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author michael.soyma@thinkmobiles.com (Created on 1/24/2017.)
 */

public final class HorizontalBarChartViewImpl implements IChartView<BarData> {

    private HorizontalBarChart barChart;
    private final List<String> xLabels = new ArrayList<>();

    @Override
    public void render(FrameLayout parent, Object data) {
        parent.removeAllViews();

        barChart = new HorizontalBarChart(parent.getContext());
        barChart.setData(prepareData(data));
        barChart.setExtraOffsets(0, 0, 15, 0);

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
        legend.setTextColor(ContextCompat.getColor(parent.getContext(), R.color.color_text_black));
        legend.setTypeface(Typeface.create("sans-serif-condensed", Typeface.NORMAL));
        legend.setFormToTextSpace(8);
        legend.setTextSize(14);
        legend.setEnabled(false);

        final Description description = barChart.getDescription();
        description.setEnabled(true);
        description.setText("Revenue");
        description.setTextSize(16);
        description.setTextColor(ContextCompat.getColor(parent.getContext(), R.color.color_text_black));

        barChart.setFitBars(true);

        final FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(parent.getLayoutParams());
        final int margin = (int) parent.getResources().getDimension(R.dimen.default_padding_half);
        lp.setMargins(margin, margin, margin, margin);

        barChart.setLayoutParams(lp);
        barChart.animateY(700);
        parent.addView(barChart);
    }

    @SuppressWarnings("unchecked")
    @Override
    public BarData prepareData(Object data) {
        final List<Invoice> invoices = (List<Invoice>) data;
        final List<BarEntry> barEntries = new ArrayList<>();

        Collections.sort(invoices);

        int x = 0;
        for (Invoice invoice : invoices) {
            barEntries.add(new BarEntry(x++, (float) (invoice.sum / 100d)));
            xLabels.add(TextUtils.isEmpty(invoice.id) ? "Not Assigned" : invoice.id);
        }

        final BarDataSet barDataSet = new BarDataSet(barEntries, "Sales Managers");
        barDataSet.setValueFormatter(new DollarFormatter());
        barDataSet.setValueTextSize(10);
        barDataSet.setDrawValues(true);
        barDataSet.setColors(ColorGenerateManager.generateGradientBetweenColors(invoices.size(), Color.parseColor("#30cfb0"), Color.parseColor("#3498db")));
        barDataSet.setForm(Legend.LegendForm.CIRCLE);
        barDataSet.setFormSize(12);

        final BarData barData = new BarData(barDataSet);
        barData.setHighlightEnabled(false);

        return barData;
    }
}
