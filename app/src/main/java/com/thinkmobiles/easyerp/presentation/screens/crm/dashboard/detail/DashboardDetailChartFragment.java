package com.thinkmobiles.easyerp.presentation.screens.crm.dashboard.detail;

import android.support.v4.widget.SwipeRefreshLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.leavjenn.smoothdaterangepicker.date.SmoothDateRangePickerFragment;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.data.model.crm.dashboard.DashboardListItem;
import com.thinkmobiles.easyerp.data.model.crm.dashboard.detail.DashboardChartType;
import com.thinkmobiles.easyerp.domain.crm.DashboardRepository;
import com.thinkmobiles.easyerp.presentation.base.BaseFragment;
import com.thinkmobiles.easyerp.presentation.base.rules.ErrorViewHelper;
import com.thinkmobiles.easyerp.presentation.screens.crm.dashboard.detail.DashboardDetailChartContract.DashboardDetailChartView;
import com.thinkmobiles.easyerp.presentation.screens.crm.dashboard.detail.charts.ChartViewFabric;
import com.thinkmobiles.easyerp.presentation.screens.crm.dashboard.detail.charts.IChartView;
import com.thinkmobiles.easyerp.presentation.screens.home.HomeActivity;
import com.thinkmobiles.easyerp.presentation.utils.AppDefaultStatesPreferences_;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.OptionsMenuItem;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.ColorRes;
import org.androidannotations.annotations.sharedpreferences.Pref;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * @author michael.soyma@thinkmobiles.com (Created on 1/20/2017.)
 */
@EFragment(R.layout.fragment_dashboard_chart_detail)
public class DashboardDetailChartFragment extends BaseFragment<HomeActivity> implements DashboardDetailChartView {

    private DashboardDetailChartContract.DashboardDetailChartPresenter presenter;

    @Bean
    protected DashboardRepository dashboardRepository;
    @Bean
    protected ErrorViewHelper errorViewHelper;

    @FragmentArg
    protected DashboardListItem dashboardConfigsForChart;

    @ViewById(R.id.llErrorLayout)
    protected View errorLayout;
    @ViewById
    protected SwipeRefreshLayout swipeContainer;
    @ViewById(R.id.flContainerChart_FDCD)
    protected FrameLayout containerChart;
    @ViewById(R.id.tvTitleDashboardDetail_FDCD)
    protected TextView titleDashboardChart;
    @ViewById(R.id.tvPreviewDateFilterDates_FDCD)
    protected TextView previewDateFilterDates;

    @ColorRes
    protected int colorPrimary;
    @ColorRes
    protected int colorPrimaryDark;

    @Pref
    protected AppDefaultStatesPreferences_ appDefaultStatesPreferences;

    @Override
    protected boolean needProgress() {
        return true;
    }

    @AfterInject
    @Override
    public void initPresenter() {
        presenter = new DashboardDetailChartPresenter(this, dashboardRepository, dashboardConfigsForChart, appDefaultStatesPreferences);
    }

    @AfterViews
    protected void initUI() {
        presenter.subscribe();

        swipeContainer.setColorSchemeColors(colorPrimary, colorPrimaryDark);
        swipeContainer.setOnRefreshListener(() -> loadChartInfo(false));
        errorViewHelper.init(errorLayout, view -> loadChartInfo(true));

        loadChartInfo(true);
    }

    private void loadChartInfo(boolean withProgress) {
        errorViewHelper.hideError();
        if (withProgress) {
            containerChart.setVisibility(View.INVISIBLE);
            displayProgress(true);
        }
        presenter.loadChartInfo();
    }

    @Override
    public void setPresenter(DashboardDetailChartContract.DashboardDetailChartPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void displayHeader(String title) {
        titleDashboardChart.setText(title);
    }

    @Override
    public void displayChart(Object data, DashboardChartType chartType) {
        swipeContainer.setRefreshing(false);
        displayProgress(false);
        containerChart.setVisibility(View.VISIBLE);

        IChartView chartView = ChartViewFabric.implementByChartType(chartType);
        if (chartView != null)
            chartView.render(containerChart, data);
    }

    @Override
    public void displayError(String msg) {
        swipeContainer.setRefreshing(false);
        displayProgress(false);

        if (containerChart.getVisibility() == View.VISIBLE)
            Toast.makeText(mActivity, msg, Toast.LENGTH_LONG).show();
        else errorViewHelper.showErrorMsg(msg, ErrorViewHelper.ErrorType.NETWORK);
    }

    @Override
    public void displayDateFilterFromTo(String fromToDate) {
        previewDateFilterDates.setText(fromToDate);
    }

    @Override
    public void chooseCustomDateRangeFromTo(Calendar dateFrom, Calendar dateTo) {
        SmoothDateRangePickerFragment dateRangePickerFragment = SmoothDateRangePickerFragment.newInstance(
                (rpFragment, y, m, d, yTo, mTo, dTo) -> presenter.setCustomFilterRangeDateFromTo(new GregorianCalendar(y, m, d), new GregorianCalendar(yTo, mTo, dTo)),
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
    public void reloadData() {
        loadChartInfo(true);
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
                presenter.chooseFilterType(DateFilterType.THIS_MONTH);
                break;
            case R.id.thisFinancialYear_MCDD:
                presenter.chooseFilterType(DateFilterType.THIS_FINANCIAL_YEAR);
                break;
            case R.id.lastMonth_MCDD:
                presenter.chooseFilterType(DateFilterType.LAST_MONTH);
                break;
            case R.id.lastQuarter_MCDD:
                presenter.chooseFilterType(DateFilterType.LAST_QUARTER);
                break;
            case R.id.lastFinancialYear_MCDD:
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
