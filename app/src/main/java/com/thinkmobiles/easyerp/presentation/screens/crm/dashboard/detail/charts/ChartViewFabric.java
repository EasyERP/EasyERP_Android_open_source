package com.thinkmobiles.easyerp.presentation.screens.crm.dashboard.detail.charts;

import com.thinkmobiles.easyerp.data.model.crm.dashboard.detail.DashboardChartType;
import com.thinkmobiles.easyerp.presentation.screens.hr.dashboard.detail.charts.ColorCardsChartViewImpl;
import com.thinkmobiles.easyerp.presentation.screens.hr.dashboard.detail.charts.HRHorizontalBarChartViewImpl;
import com.thinkmobiles.easyerp.presentation.screens.hr.dashboard.detail.charts.ReverseBarChartViewImpl;

/**
 * @author michael.soyma@thinkmobiles.com (Created on 1/24/2017.)
 */

public final class ChartViewFabric {

    private ChartViewFabric() {}

    public static IChartView implementByChartType(final DashboardChartType chartType) {
        switch (chartType) {
            case DONUT: return new DonutChartViewImpl();
            case OVERVIEW: return new OverViewChartViewImpl();
            case TABLE: return new TableChartViewImpl();
            case HORIZONTALBAR: return new HorizontalBarChartViewImpl();
            default : return null;
        }
    }

    public static IChartView implementForHRByChartType(final DashboardChartType chartType, final String dataSet) {
        switch (chartType) {
            case HORIZONTALBAR: return new HRHorizontalBarChartViewImpl(dataSet);
            case REVERSEHORIZONTALBAR: return new ReverseBarChartViewImpl();
            case COLORCARDSVIEW: return new ColorCardsChartViewImpl();
            default : return null;
        }
    }
}
