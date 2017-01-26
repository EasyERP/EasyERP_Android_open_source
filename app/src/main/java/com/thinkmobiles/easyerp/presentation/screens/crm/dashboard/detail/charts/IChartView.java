package com.thinkmobiles.easyerp.presentation.screens.crm.dashboard.detail.charts;

import android.widget.FrameLayout;

/**
 * Created by Asus_Dev on 1/24/2017.
 */

public interface IChartView<T> {
    void render(final FrameLayout parent, final Object data);
    @SuppressWarnings("unchecked")
    T prepareData(final Object data);
}
