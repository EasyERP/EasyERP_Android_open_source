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
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.data.model.ResponseGetTotalItems;
import com.thinkmobiles.easyerp.data.model.hr.dashboard.DepartmentSalary;
import com.thinkmobiles.easyerp.presentation.screens.crm.dashboard.detail.charts.DollarFormatter;
import com.thinkmobiles.easyerp.presentation.screens.crm.dashboard.detail.charts.IChartView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Michael Soyma (Created on 3/22/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public final class HRHorizontalBarChartViewImpl implements IChartView<BarData> {

    private HorizontalBarChart barChart;
    private final List<String> xLabels = new ArrayList<>();
    private String mDescription, mBarDataSetLabel, mDataSet;
    private IValueFormatter valueFormatter;
    private IAxisValueFormatter axisValueFormatter;

    public HRHorizontalBarChartViewImpl(final String dateSet) {
        this.mDataSet = dateSet;

        switch (this.mDataSet) {
            case "hrEmployeesSalary":
                this.mDescription = "Count";
                this.mBarDataSetLabel = "Salary";
                this.valueFormatter = null;
                this.axisValueFormatter = null;
                break;
            case "hrEmployeesDepartment":
                this.mDescription = "Salary";
                this.mBarDataSetLabel = "Department";
                this.valueFormatter = new DollarFormatter();
                this.axisValueFormatter = new DollarFormatter();
                break;
        }
    }

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
        yl.setValueFormatter(axisValueFormatter);

        YAxis yr = barChart.getAxisRight();
        yr.setDrawAxisLine(true);
        yr.setDrawGridLines(false);
        yr.setAxisMinimum(0f);
        yr.setTextSize(13);
        yr.setValueFormatter(axisValueFormatter);

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
        description.setText(mDescription);
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
        final List<BarEntry> barEntries = new ArrayList<>();

        int x = 0;
        switch (mDataSet) {
            case "hrEmployeesSalary":
                xLabels.add("<$250");
                xLabels.add("$250-500");
                xLabels.add("$500-750");
                xLabels.add("$750-1000");
                xLabels.add("$1000-1250");
                xLabels.add("$1250-1500");
                xLabels.add("$1500-1750");
                xLabels.add("$1750-2000");
                xLabels.add("$2000-2250");
                xLabels.add(">=$2250");
                final int[] counts = new int[10];
                for (Integer value: ((ResponseGetTotalItems<Integer>) data).data) {
                    if (value < 250)
                        counts[0]++;
                    else if (value >= 250 && value < 500)
                        counts[1]++;
                    else if (value >= 500 && value < 750)
                        counts[2]++;
                    else if (value >= 750 && value < 1000)
                        counts[3]++;
                    else if (value >= 1000 && value < 1250)
                        counts[4]++;
                    else if (value >= 1250 && value < 1500)
                        counts[5]++;
                    else if (value >= 1500 && value < 1750)
                        counts[6]++;
                    else if (value >= 1750 && value < 2000)
                        counts[7]++;
                    else if (value >= 2000 && value < 2250)
                        counts[8]++;
                    else if (value >= 2250)
                        counts[9]++;
                }
                for (int count: counts)
                    barEntries.add(new BarEntry(x++, count));
                break;
            case "hrEmployeesDepartment":
                for (DepartmentSalary value: ((ResponseGetTotalItems<DepartmentSalary>) data).data) {
                    barEntries.add(new BarEntry(x++, value.salary));
                    xLabels.add(TextUtils.isEmpty(value.getId()) ? "Not Assigned" : value.getId());
                }
                break;
        }

        final BarDataSet barDataSet = new BarDataSet(barEntries, mBarDataSetLabel);
        barDataSet.setValueFormatter(valueFormatter);
        barDataSet.setValueTextSize(10);
        barDataSet.setDrawValues(true);
        barDataSet.setColors(Color.parseColor("#3498db"));
        barDataSet.setForm(Legend.LegendForm.CIRCLE);
        barDataSet.setFormSize(12);

        final BarData barData = new BarData(barDataSet);
        barData.setHighlightEnabled(false);

        return barData;
    }
}
