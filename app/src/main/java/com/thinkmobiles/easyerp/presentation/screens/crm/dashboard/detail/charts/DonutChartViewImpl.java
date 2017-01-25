package com.thinkmobiles.easyerp.presentation.screens.crm.dashboard.detail.charts;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.StyleSpan;
import android.widget.FrameLayout;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.data.model.crm.dashboard.detail.invoice.InvoiceItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Asus_Dev on 1/24/2017.
 */

public final class DonutChartViewImpl implements IChartView<PieData> {

    private static int[] colors = new int[] {
            Color.parseColor("#4db7da"),
            Color.parseColor("#e36028"),
            Color.parseColor("#ffb128"),
            Color.parseColor("#51a87a"),
            Color.parseColor("#47d2d3"),
            Color.parseColor("#e485b2"),
            Color.parseColor("#f5964c")};

    private Double totalRevenue = 0d;
    private PieChart pieChart;

    @Override
    public void render(FrameLayout parent, Object data) {
        parent.removeAllViews();

        pieChart = new PieChart(parent.getContext());
        pieChart.setData(prepareData(data));
        pieChart.setExtraOffsets(15, 15, 15, 15);

        pieChart.setDrawCenterText(true);
        pieChart.setCenterTextSize(26);
        pieChart.setCenterTextColor(ContextCompat.getColor(parent.getContext(), R.color.colorPrimary));
        pieChart.setCenterText(generateCenterSpannableText());

        pieChart.setEntryLabelTextSize(15);
        pieChart.setDragDecelerationFrictionCoef(0.95f);

        final Description description = pieChart.getDescription();
        description.setEnabled(false);
        description.setTextSize(15);
        description.setTextColor(ContextCompat.getColor(parent.getContext(), R.color.colorPrimary));
        description.setTextAlign(Paint.Align.LEFT);
        description.setTypeface(Typeface.DEFAULT_BOLD);
        description.setPosition(0, 28);

        Legend legend = pieChart.getLegend();
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);
        legend.setTextColor(ContextCompat.getColor(parent.getContext(), R.color.colorPrimaryDark));
        legend.setXEntrySpace(16);
        legend.setTextSize(13);

        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                pieChart.getDescription().setText(String.format("%s: %s", ((PieEntry) e).getLabel(), new DollarFormatter().getFormattedValue(e.getY(), null)));
                pieChart.getDescription().setEnabled(true);
            }

            @Override
            public void onNothingSelected() {
                pieChart.getDescription().setEnabled(false);
            }
        });

        pieChart.animateY(700, Easing.EasingOption.EaseInOutQuad);

        parent.addView(pieChart);
    }

    @SuppressWarnings("unchecked")
    @Override
    public PieData prepareData(Object data) {
        final List<InvoiceItem> invoiceItems = (List<InvoiceItem>) data;
        final List<PieEntry> pieEntries = new ArrayList<>();

        totalRevenue = 0d;
        for (InvoiceItem invoiceItem: invoiceItems) {
            final Double sum = invoiceItem.sum / 100d;
            totalRevenue += sum;
            pieEntries.add(new PieEntry(sum.floatValue(), TextUtils.isEmpty(invoiceItem.id) ? "Unknown" : invoiceItem.id));
        }

        final PieDataSet pieDataSet = new PieDataSet(pieEntries, null);
        pieDataSet.setValueTextSize(15);
        pieDataSet.setColors(colors);
        pieDataSet.setValueFormatter(new DollarFormatter());
        pieDataSet.setForm(Legend.LegendForm.CIRCLE);
        pieDataSet.setFormSize(13);
        pieDataSet.setSelectionShift(14);

        if (pieDataSet.getEntryCount() >= 8) {
            pieDataSet.setDrawValues(false);
            pieChart.setDrawEntryLabels(false);
            pieChart.getLegend().setEnabled(false);
        }

        return new PieData(pieDataSet);
    }

    private SpannableString generateCenterSpannableText() {
        SpannableString s = new SpannableString(String.format("Total\n$ %s", new DollarFormatter().getFormat().format(totalRevenue)));
        s.setSpan(new StyleSpan(Typeface.BOLD), 6, s.length(), 0);
        return s;
    }

}
