package com.thinkmobiles.easyerp.presentation.screens.reports.general.detail;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.domain.reports.ReportsRepository;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentFragment;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentPresenter;
import com.thinkmobiles.easyerp.presentation.dialogs.ReportTypeDialogFragment;
import com.thinkmobiles.easyerp.presentation.dialogs.ReportTypeDialogFragment_;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.FilterDH;
import com.thinkmobiles.easyerp.presentation.managers.GoogleAnalyticHelper;
import com.thinkmobiles.easyerp.presentation.utils.Constants;
import com.thinkmobiles.easyerp.presentation.utils.DynamicallyPreferences;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.OnActivityResult;

import java.util.ArrayList;

/**
 * @author Michael Soyma (Created on 4/8/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
@EFragment
public class GeneralReportDetailFragment extends ContentFragment implements GeneralReportDetailContract.GeneralReportDetailView {

    private GeneralReportDetailContract.GeneralReportDetailPresenter presenter;

    @Bean
    protected ReportsRepository reportsRepository;
    @Bean
    protected DynamicallyPreferences dynamicallySharedPrefs;

    @FragmentArg
    protected String categoryKey;
    protected MenuItem reportTypesItem;

    @AfterInject
    @Override
    public void initPresenter() {
        new GeneralReportDetailPresenter(this, reportsRepository, dynamicallySharedPrefs, categoryKey);
    }

    @Override
    public void setPresenter(GeneralReportDetailContract.GeneralReportDetailPresenter presenter) {
        this.presenter = presenter;
    }

    @AfterViews
    protected void initAnalytics() {
        GoogleAnalyticHelper.trackScreenView(this, getResources().getConfiguration());
    }

    @Override
    public String getScreenName() {
        return "Reports General detail screen";
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_general_report_details;
    }

    @Override
    protected ContentPresenter getPresenter() {
        return presenter;
    }

    @Override
    public boolean optionsMenuForDetail() {
        return true;
    }

    @Override
    public int optionsMenuRes() {
        return R.menu.menu_reports_general;
    }

    @Override
    public void optionsMenuInitialized(Menu menu) {
        reportTypesItem = menu.findItem(R.id.reportType_MRG);
        getPresenter().subscribe();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.reportType_MRG:
                presenter.chooseReportTypes();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showReportTypesDialog(ArrayList<FilterDH> listReportTypes) {
        ReportTypeDialogFragment dialogFragment = ReportTypeDialogFragment_.builder()
                .reportTypesList(listReportTypes)
                .build();
        dialogFragment.setTargetFragment(this, Constants.RequestCodes.RC_CHOOSE_REPORT_TYPES);
        dialogFragment.show(getFragmentManager(), getClass().getName());
    }

    @Override
    public void makeAvailableReportTypes() {
        reportTypesItem.setVisible(true);
    }

    @OnActivityResult(Constants.RequestCodes.RC_CHOOSE_REPORT_TYPES)
    protected void resultSelectReportTypes(int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            ArrayList<FilterDH> filterDHs = data.getParcelableArrayListExtra(Constants.KEY_REPORT_TYPES_LIST);
            presenter.filterByReportTypes(filterDHs);
        } else {
            presenter.removeAllReportTypes();
        }
    }
}
