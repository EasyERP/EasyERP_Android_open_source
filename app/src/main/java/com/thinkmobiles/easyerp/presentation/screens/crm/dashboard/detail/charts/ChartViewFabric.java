package com.thinkmobiles.easyerp.presentation.screens.crm.dashboard.detail.charts;

import com.thinkmobiles.easyerp.data.model.crm.dashboard.detail.DashboardChartType;

/**
 * Created by Asus_Dev on 1/24/2017.
 */

public final class ChartViewFabric {

    private ChartViewFabric() {}

    public static IChartView implementByChartType(final DashboardChartType chartType) {
        switch (chartType) {
            case DONUT: return new DonutChartViewImpl();
//            case OVERVIEW: return new OverviewChartViewImpl();
            case TABLE: return new TableChartViewImpl();
            case HORIZONTALBAR: return new HorizontalBarChartViewImpl();
            default : return null;
        }
    }

}
