package com.thinkmobiles.easyerp.presentation.screens.hr.dashboard.detail;

import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.data.model.crm.dashboard.DashboardListItem;
import com.thinkmobiles.easyerp.data.model.crm.dashboard.detail.DashboardChartType;
import com.thinkmobiles.easyerp.domain.DomainHelper;
import com.thinkmobiles.easyerp.domain.crm.DashboardRepository;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentFragment;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentPresenter;
import com.thinkmobiles.easyerp.presentation.custom.views.drawer_menu.models.MenuConfigs;
import com.thinkmobiles.easyerp.presentation.managers.GoogleAnalyticHelper;
import com.thinkmobiles.easyerp.presentation.screens.crm.dashboard.detail.charts.ChartViewFabric;
import com.thinkmobiles.easyerp.presentation.screens.crm.dashboard.detail.charts.IChartView;
import com.thinkmobiles.easyerp.presentation.utils.AppDefaultStatesPreferences_;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;

/**
 * @author Michael Soyma (Created on 3/21/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
@EFragment
public class HRDashboardDetailChartFragment extends ContentFragment implements HRDashboardDetailChartContract.HRDashboardDetailChartView {

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_dashboard_chart_detail;
    }

    private HRDashboardDetailChartContract.HRDashboardDetailChartPresenter presenter;

    protected DashboardRepository dashboardRepository;
    @Pref
    protected AppDefaultStatesPreferences_ appDefaultStatesPreferences;

    @FragmentArg
    protected DashboardListItem dashboardConfigsForChart;
    @FragmentArg
    protected int moduleId;

    @ViewById(R.id.flContainerChart_FDCD)
    protected FrameLayout containerChart;
    @ViewById(R.id.tvTitleDashboardDetail_FDCD)
    protected TextView titleDashboardChart;
    @ViewById(R.id.tvPreviewDateFilterDates_FDCD)
    protected TextView previewYearMonth;

    @AfterInject
    @Override
    public void initPresenter() {
        dashboardRepository = DomainHelper.getDashboardRepository(moduleId);
        new HRDashboardDetailChartPresenter(this, dashboardRepository, dashboardConfigsForChart, appDefaultStatesPreferences);
    }

    @AfterViews
    protected void initAnalytics() {
        GoogleAnalyticHelper.trackScreenView(this, getResources().getConfiguration());
        getPresenter().subscribe();
    }

    @Override
    public void setPresenter(HRDashboardDetailChartContract.HRDashboardDetailChartPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public String getScreenName() {
        return String.format("%s Dashboard details screen", MenuConfigs.getModuleLabel(moduleId));
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
    public void displayYearMonth(String yearMonth) {
        previewYearMonth.setText(yearMonth);
    }

    @Override
    public void displayPickerCustomYearMonth(int year, int month) {
        //TODO open Month/Year Picker Dialog
    }

    @Override
    protected ContentPresenter getPresenter() {
        return presenter;
    }

    @Override
    public int optionsMenuRes() {
        if (dashboardConfigsForChart != null &&
                !(dashboardConfigsForChart.type.equals("reverseHorizontalBar") && dashboardConfigsForChart.dataset.equals("hrEmployeesByGender")))
            return R.menu.menu_hr_dashboard_detail;
        else return 0;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.monthYearAction_MHDD) {
            GoogleAnalyticHelper.trackClick(this, GoogleAnalyticHelper.EventType.SET_CHART_PERIOD, presenter.getYearMonthToString());
            presenter.selectYearMonthValues();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean optionsMenuForDetail() {
        return true;
    }
}
