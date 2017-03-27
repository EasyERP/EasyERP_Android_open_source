package com.thinkmobiles.easyerp.presentation.screens.hr.dashboard.detail.charts;

import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.data.model.hr.dashboard.EmployeeCountForDashboard;
import com.thinkmobiles.easyerp.data.model.hr.dashboard.EmployeeInfoCommonResponse;
import com.thinkmobiles.easyerp.data.model.hr.dashboard.VacationStatistic;
import com.thinkmobiles.easyerp.presentation.screens.crm.dashboard.detail.charts.IChartView;

/**
 * @author Michael Soyma (Created on 3/27/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public class ColorCardsChartViewImpl implements IChartView<Pair<VacationStatistic, EmployeeCountForDashboard>> {

    @Override
    public void render(FrameLayout parent, Object data) {

        parent.removeAllViews();

        final View targetView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_chart_hr_color_cards, parent, false);
        final Pair<VacationStatistic, EmployeeCountForDashboard> prepareData = prepareData(data);

        displayHiredFiredStatistic(targetView.findViewById(R.id.llHiredFired_VCHCC), prepareData.second);
        displayVacationDays(targetView.findViewById(R.id.llVacationDays_VCHCC), prepareData.first);

        parent.addView(targetView);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Pair<VacationStatistic, EmployeeCountForDashboard> prepareData(Object data) {
        final EmployeeInfoCommonResponse employeeInfoCommonResponse = (EmployeeInfoCommonResponse) data;
        return new Pair<>(employeeInfoCommonResponse.vacationStatistic, employeeInfoCommonResponse.employeeCountForDashboard);
    }

    private void displayHiredFiredStatistic(final View rootHiredFired, final EmployeeCountForDashboard data) {
        ((TextView) rootHiredFired.findViewById(R.id.tvEmployeesValue_VCHCC)).setText(String.valueOf(data.employeeCount));
        ((TextView) rootHiredFired.findViewById(R.id.tvHiredValue_VCHCC)).setText(String.valueOf(data.hiredCount));
        ((TextView) rootHiredFired.findViewById(R.id.tvFiredValue_VCHCC)).setText(String.valueOf(data.firedCount));
    }

    private void displayVacationDays(final View rootHiredFired, final VacationStatistic data) {
        ((TextView) rootHiredFired.findViewById(R.id.tvVacationValue_VCHCC)).setText(String.valueOf(data.vacation));
        ((TextView) rootHiredFired.findViewById(R.id.tvPersonalValue_VCHCC)).setText(String.valueOf(data.personal));
        ((TextView) rootHiredFired.findViewById(R.id.tvSickValue_VCHCC)).setText(String.valueOf(data.sick));
        ((TextView) rootHiredFired.findViewById(R.id.tvEducationValue_VCHCC)).setText(String.valueOf(data.education));
    }
}
