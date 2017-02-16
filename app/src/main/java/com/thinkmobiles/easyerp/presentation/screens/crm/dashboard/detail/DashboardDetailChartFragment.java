package com.thinkmobiles.easyerp.presentation.screens.crm.dashboard.detail;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.leavjenn.smoothdaterangepicker.date.SmoothDateRangePickerFragment;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.data.model.crm.dashboard.DashboardListItem;
import com.thinkmobiles.easyerp.data.model.crm.dashboard.detail.DashboardChartType;
import com.thinkmobiles.easyerp.domain.crm.DashboardRepository;
import com.thinkmobiles.easyerp.presentation.base.rules.ErrorViewHelper;
import com.thinkmobiles.easyerp.presentation.base.rules.RefreshFragment;
import com.thinkmobiles.easyerp.presentation.managers.GoogleAnalyticHelper;
import com.thinkmobiles.easyerp.presentation.screens.crm.dashboard.detail.DashboardDetailChartContract.DashboardDetailChartView;
import com.thinkmobiles.easyerp.presentation.screens.crm.dashboard.detail.charts.ChartViewFabric;
import com.thinkmobiles.easyerp.presentation.screens.crm.dashboard.detail.charts.IChartView;
import com.thinkmobiles.easyerp.presentation.utils.AppDefaultStatesPreferences_;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * @author michael.soyma@thinkmobiles.com (Created on 1/20/2017.)
 */
@EFragment
public class DashboardDetailChartFragment extends RefreshFragment implements DashboardDetailChartView {

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_dashboard_chart_detail;
    }

    private DashboardDetailChartContract.DashboardDetailChartPresenter presenter;

    @Bean
    protected DashboardRepository dashboardRepository;
    @Bean
    protected ErrorViewHelper errorViewHelper;

    @FragmentArg
    protected DashboardListItem dashboardConfigsForChart;

    @ViewById(R.id.flContainerChart_FDCD)
    protected FrameLayout containerChart;
    @ViewById(R.id.tvTitleDashboardDetail_FDCD)
    protected TextView titleDashboardChart;
    @ViewById(R.id.tvPreviewDateFilterDates_FDCD)
    protected TextView previewDateFilterDates;

    @Pref
    protected AppDefaultStatesPreferences_ appDefaultStatesPreferences;


    @AfterInject
    @Override
    public void initPresenter() {
        presenter = new DashboardDetailChartPresenter(this, dashboardRepository, dashboardConfigsForChart, appDefaultStatesPreferences);
    }

    @AfterViews
    protected void initUI() {
        GoogleAnalyticHelper.trackScreenView(this, getResources().getConfiguration());
        presenter.subscribe();
    }

    @Override
    protected void onRetry() {
        presenter.subscribe();
    }

    @Override
    protected void onRefreshData() {
        presenter.loadChartInfo();
    }

    @Override
    public void setPresenter(DashboardDetailChartContract.DashboardDetailChartPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public String getScreenName() {
        return "Dashboard details screen";
    }

    @Override
    public void displayHeader(String title) {
        titleDashboardChart.setText(title);
    }

    @Override
    public void displayChart(Object data, DashboardChartType chartType) {

        IChartView chartView = ChartViewFabric.implementByChartType(chartType);
        if (chartView != null)
            chartView.render(containerChart, data);
    }

    @Override
    public void displayDateFilterFromTo(String fromToDate) {
        previewDateFilterDates.setText(fromToDate);
    }

    @Override
    public void chooseCustomDateRangeFromTo(Calendar dateFrom, Calendar dateTo) {
        SmoothDateRangePickerFragment dateRangePickerFragment = SmoothDateRangePickerFragment.newInstance(
                (rpFragment, y, m, d, yTo, mTo, dTo) -> {
                    GoogleAnalyticHelper.trackClick(this,
                            GoogleAnalyticHelper.EventType.SET_CHART_PERIOD,
                            GoogleAnalyticHelper.getFromToString(y, m, d, yTo, mTo, dTo));
                    presenter.setCustomFilterRangeDateFromTo(new GregorianCalendar(y, m, d), new GregorianCalendar(yTo, mTo, dTo));
                },
                dateFrom.get(Calendar.YEAR),
                dateFrom.get(Calendar.MONTH),
                dateFrom.get(Calendar.DAY_OF_MONTH),
                dateTo.get(Calendar.YEAR),
                dateTo.get(Calendar.MONTH),
                dateTo.get(Calendar.DAY_OF_MONTH));
        dateRangePickerFragment.setAccentColor(colorPrimary);
        dateRangePickerFragment.show(getFragmentManager(), SmoothDateRangePickerFragment.class.getSimpleName());
    }

    @Override
    public void displayErrorState(String msg, ErrorViewHelper.ErrorType errorType) {
        showErrorState(msg, errorType);
    }

    @Override
    public void displayErrorToast(String msg) {
        showErrorToast(msg);
    }

    @Override
    public void showProgress(Constants.ProgressType type) {
        showProgressBar(type);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.unsubscribe();
    }

    @Override
    public int optionsMenuRes() {
        return R.menu.menu_crm_dashboard_detail;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        item.setChecked(true);
        switch (item.getItemId()) {
            case R.id.thisMonth_MCDD:
                GoogleAnalyticHelper.trackClick(this, GoogleAnalyticHelper.EventType.SET_CHART_PERIOD, "This month");
                presenter.chooseFilterType(DateFilterType.THIS_MONTH);
                break;
            case R.id.thisFinancialYear_MCDD:
                GoogleAnalyticHelper.trackClick(this, GoogleAnalyticHelper.EventType.SET_CHART_PERIOD, "This financial year");
                presenter.chooseFilterType(DateFilterType.THIS_FINANCIAL_YEAR);
                break;
            case R.id.lastMonth_MCDD:
                GoogleAnalyticHelper.trackClick(this, GoogleAnalyticHelper.EventType.SET_CHART_PERIOD, "Last month");
                presenter.chooseFilterType(DateFilterType.LAST_MONTH);
                break;
            case R.id.lastQuarter_MCDD:
                GoogleAnalyticHelper.trackClick(this, GoogleAnalyticHelper.EventType.SET_CHART_PERIOD, "Last quarter");
                presenter.chooseFilterType(DateFilterType.LAST_QUARTER);
                break;
            case R.id.lastFinancialYear_MCDD:
                GoogleAnalyticHelper.trackClick(this, GoogleAnalyticHelper.EventType.SET_CHART_PERIOD, "Last financial year");
                presenter.chooseFilterType(DateFilterType.LAST_FINANCIAL_YEAR);
                break;
            case R.id.customDates_MCDD:
                presenter.chooseFilterType(DateFilterType.CUSTOM_DATES);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void optionsMenuInitialized(Menu menu) {
        switch (presenter.getCurrentFilterType()) {
            case THIS_MONTH:
                menu.findItem(R.id.thisMonth_MCDD).setChecked(true);
                break;
            case THIS_FINANCIAL_YEAR:
                menu.findItem(R.id.thisFinancialYear_MCDD).setChecked(true);
                break;
            case LAST_MONTH:
                menu.findItem(R.id.lastMonth_MCDD).setChecked(true);
                break;
            case LAST_QUARTER:
                menu.findItem(R.id.lastQuarter_MCDD).setChecked(true);
                break;
            case LAST_FINANCIAL_YEAR:
                menu.findItem(R.id.lastFinancialYear_MCDD).setChecked(true);
                break;
            case CUSTOM_DATES:
                menu.findItem(R.id.customDates_MCDD).setChecked(true);
                break;
        }
    }

    @Override
    public boolean optionsMenuForDetail() {
        return true;
    }

}
