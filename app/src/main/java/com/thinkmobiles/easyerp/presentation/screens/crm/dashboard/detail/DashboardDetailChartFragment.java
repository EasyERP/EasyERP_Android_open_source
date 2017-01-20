package com.thinkmobiles.easyerp.presentation.screens.crm.dashboard.detail;

import android.view.View;
import android.widget.TextView;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.data.model.crm.dashboard.DashboardListItem;
import com.thinkmobiles.easyerp.domain.crm.DashboardRepository;
import com.thinkmobiles.easyerp.presentation.base.BaseFragment;
import com.thinkmobiles.easyerp.presentation.base.rules.ErrorViewHelper;
import com.thinkmobiles.easyerp.presentation.screens.crm.dashboard.detail.DashboardDetailChartContract.DashboardDetailChartView;
import com.thinkmobiles.easyerp.presentation.screens.home.HomeActivity;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

/**
 * Created by Asus_Dev on 1/20/2017.
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

    @ViewById(R.id.tvTitleDashboardDetail_FDCD)
    protected TextView titleDashboardChart;

    @ViewById(R.id.tvJson_FDCD)
    protected TextView jsonViewer;

    @Override
    protected boolean needProgress() {
        return true;
    }

    @AfterInject
    @Override
    public void initPresenter() {
        presenter = new DashboardDetailChartPresenter(this, dashboardRepository, dashboardConfigsForChart);
    }

    @AfterViews
    protected void initUI() {
        presenter.subscribe();
        errorViewHelper.init(errorLayout, view -> loadChartInfo());
        loadChartInfo();
    }

    private void loadChartInfo() {
        errorViewHelper.hideError();
        displayProgress(true);
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
    public void displayJson(String json) {
        displayProgress(false);
        jsonViewer.setText(json);
    }

    @Override
    public void displayError(String msg) {
        displayProgress(false);
        errorViewHelper.showErrorMsg(msg, ErrorViewHelper.ErrorType.NETWORK);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.unsubscribe();
    }

}
