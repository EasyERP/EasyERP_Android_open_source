package com.thinkmobiles.easyerp.presentation.screens.hr.dashboard.detail.charts;

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
import com.thinkmobiles.easyerp.data.model.hr.dashboard.EmployeeGenderDepartmentInfo;
import com.thinkmobiles.easyerp.presentation.managers.ColorGenerateManager;
import com.thinkmobiles.easyerp.presentation.screens.crm.dashboard.detail.charts.DollarFormatter;
import com.thinkmobiles.easyerp.presentation.screens.crm.dashboard.detail.charts.IChartView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Michael Soyma (Created on 3/23/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public final class ReverseBarChartViewImpl implements IChartView<BarData> {

    private HorizontalBarChart barChart;
    private final List<String> xLabels = new ArrayList<>();
    private int axisLimit = 0;

    @Override
    public void render(FrameLayout parent, Object data) {
        parent.removeAllViews();

        barChart = new HorizontalBarChart(parent.getContext());
        barChart.setData(prepareData(data));
        barChart.setExtraOffsets(0, 0, 15, 10);

        XAxis xl = barChart.getXAxis();
        xl.setPosition(XAxis.XAxisPosition.BOTTOM);
        xl.setDrawAxisLine(false);
        xl.setDrawGridLines(false);
        xl.setLabelCount(xLabels.size());
        xl.setGranularityEnabled(true);
        xl.setValueFormatter((value, axis) -> xLabels.get((int) value));
        xl.setDrawLabels(true);

        YAxis yl = barChart.getAxisLeft();
        yl.setDrawZeroLine(true);
        yl.setAxisLineColor(Color.TRANSPARENT);
        yl.setDrawGridLines(false);
        yl.setLabelCount(12, false);
        yl.setValueFormatter((value, axis) -> String.valueOf(Math.abs((int) value)));
        yl.setTextSize(12);

        YAxis yr = barChart.getAxisRight();
        yr.setDrawZeroLine(true);
        yr.setAxisLineColor(Color.TRANSPARENT);
        yr.setDrawGridLines(false);
        yr.setLabelCount(12, false);
        yr.setValueFormatter((value, axis) -> String.valueOf(Math.abs((int) value)));
        yr.setTextSize(12);

        Legend legend = barChart.getLegend();
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);
        legend.setTextColor(ContextCompat.getColor(parent.getContext(), R.color.color_text_black));
        legend.setTypeface(Typeface.create("sans-serif-condensed", Typeface.NORMAL));
        legend.setFormToTextSpace(8);
        legend.setTextSize(14);
        legend.setEnabled(true);

        final Description description = barChart.getDescription();
        description.setEnabled(true);
        description.setText("Count");
        description.setTextSize(14);
        description.setTextColor(ContextCompat.getColor(parent.getContext(), R.color.color_text_black));
        description.setEnabled(false);

        final FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(parent.getLayoutParams());
        final int margin = (int) parent.getResources().getDimension(R.dimen.default_padding_half);
        lp.setMargins(margin, margin, margin, margin);

        barChart.invalidate();
        barChart.setLayoutParams(lp);
        barChart.animateY(700);
        parent.addView(barChart);
    }

    @SuppressWarnings("unchecked")
    @Override
    public BarData prepareData(Object data) {
        final List<EmployeeGenderDepartmentInfo> gendersCounts = (List<EmployeeGenderDepartmentInfo>) data;
        final List<BarEntry> barEntries = new ArrayList<>();

        int x = 0;
        for (EmployeeGenderDepartmentInfo department : gendersCounts) {
            axisLimit = Math.max(axisLimit, Math.max(department.femaleCount, department.maleCount));
            barEntries.add(new BarEntry(x++, new float[] {-department.femaleCount, department.maleCount}));
            xLabels.add(TextUtils.isEmpty(department.id) ? "Not Assigned" : String.format("%s (%s)", department.id, department.employeesCount));
        }

        final BarDataSet barDataSet = new BarDataSet(barEntries, null);
        barDataSet.setDrawValues(true);
        barDataSet.setValueTextSize(10);
        barDataSet.setColors(Color.parseColor("#30cfb0"), Color.parseColor("#3498db"));
        barDataSet.setForm(Legend.LegendForm.CIRCLE);
        barDataSet.setFormSize(12);
        barDataSet.setValueFormatter((value, entry, dataSetIndex, viewPortHandler) -> {if (value == 0) return ""; else return String.valueOf(Math.abs((int) value));});
        barDataSet.setStackLabels(new String[]{"Female", "Male"});

        final BarData barData = new BarData(barDataSet);
        barData.setHighlightEnabled(false);

        return barData;
    }
}
