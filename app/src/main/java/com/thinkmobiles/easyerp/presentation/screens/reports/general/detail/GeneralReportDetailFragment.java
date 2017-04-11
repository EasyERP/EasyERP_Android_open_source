package com.thinkmobiles.easyerp.presentation.screens.reports.general.detail;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v4.widget.PopupWindowCompat;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.michenko.simpleadapter.SimpleRecyclerAdapter;
import com.thinkmobiles.easyerp.BuildConfig;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.domain.reports.ReportsRepository;
import com.thinkmobiles.easyerp.presentation.adapters.reports.GeneralReportsAdapter;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentFragment;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentPresenter;
import com.thinkmobiles.easyerp.presentation.base.rules.master.list.MasterListFragment;
import com.thinkmobiles.easyerp.presentation.base.rules.master.list.MasterListPresenter;
import com.thinkmobiles.easyerp.presentation.dialogs.ReportTypeDialogFragment;
import com.thinkmobiles.easyerp.presentation.dialogs.ReportTypeDialogFragment_;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.FilterDH;
import com.thinkmobiles.easyerp.presentation.managers.GoogleAnalyticHelper;
import com.thinkmobiles.easyerp.presentation.screens.home.HomeActivity;
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
public class GeneralReportDetailFragment extends MasterListFragment implements GeneralReportDetailContract.GeneralReportDetailView {

    private GeneralReportDetailContract.GeneralReportDetailPresenter presenter;

    @Bean
    protected ReportsRepository reportsRepository;
    @Bean
    protected DynamicallyPreferences dynamicallySharedPrefs;
    @Bean
    protected GeneralReportsAdapter generalReportsAdapter;

    @FragmentArg
    protected String categoryKey;
    protected MenuItem reportTypesItem;

    @AfterInject
    @Override
    public void initPresenter() {
        new GeneralReportDetailPresenter(this, reportsRepository, dynamicallySharedPrefs, categoryKey, ((HomeActivity) mActivity).userInfo);
    }

    @Override
    public void setPresenter(GeneralReportDetailContract.GeneralReportDetailPresenter presenter) {
        this.presenter = presenter;
    }

    @AfterViews
    protected void initUI() {
        GoogleAnalyticHelper.trackScreenView(this, getResources().getConfiguration());
        getAdapter().setOnCardClickListener((view, position, viewType) -> {
            switch (view.getId()) {
                case R.id.cbFavorite_VLIGR:
                    presenter.favorite(position, ((CheckBox) view).isChecked());
                    break;
                case R.id.ivDescription_VLIGR:
                    presenter.displayDescription(position, view);
                    break;
                default:
                    getPresenter().clickItem(position);
                    break;
            }
        });
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
    protected MasterListPresenter getPresenter() {
        return presenter;
    }

    @Override
    protected SimpleRecyclerAdapter getAdapter() {
        return generalReportsAdapter;
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

    @Override
    public void seeFullReport(String reportID) {
        if (reportID != null) {
            //TODO show full report review
        }
    }

    @Override
    public void showDescriptionPopUpWindow(final View anchorView, String description) {
        if (TextUtils.isEmpty(description)) {
            Toast.makeText(mActivity, R.string.description_is_empty, Toast.LENGTH_SHORT).show();
        } else {
            final View popupView = LayoutInflater.from(mActivity).inflate(R.layout.popup_window_reports_description, null, false);
            final TextView descriptionView = ((TextView) popupView.findViewById(R.id.tvDescription_PWRD));
//            descriptionView.setText("If you are in the market for a computer, there are a number of factors to consider. Will it be used for your home, your office or perhaps even your home office combo? First off, you will need to set a budget for your new purchase before deciding whether to shop for notebook or desktop computers. If you are in the market for a computer, there are a number of factors to consider. Will it be used for your home, your office or perhaps even your home office combo? First off, you will need to set a budget for your new purchase before deciding whether to shop for notebook or desktop computers. If you are in the market for a computer, there are a number of factors to consider. Will it be used for your home, your office or perhaps even your home office combo? First off, you will need to set a budget for your new purchase before deciding whether to shop for notebook or desktop computers. If you are in the market for a computer, there are a number of factors to consider. Will it be used for your home, your office or perhaps even your home office combo? First off, you will need to set a budget for your new purchase before deciding whether to shop for notebook or desktop computers. If you are in the market for a computer, there are a number of factors to consider. Will it be used for your home, your office or perhaps even your home office combo? First off, you will need to set a budget for your new purchase before deciding whether to shop for notebook or desktop computers. If you are in the market for a computer, there are a number of factors to consider. Will it be used for your home, your office or perhaps even your home office combo? First off, you will need to set a budget for your new purchase before deciding whether to shop for notebook or desktop computers. If you are in the market for a computer, there are a number of factors to consider. Will it be used for your home, your office or perhaps even your home office combo? First off, you will need to set a budget for your new purchase before deciding whether to shop for notebook or desktop computers. If you are in the market for a computer, there are a number of factors to consider. Will it be used for your home, your office or perhaps even your home office combo? First off, you will need to set a budget for your new purchase before deciding whether to shop for notebook or desktop computers.");
            descriptionView.setText(description);

            final PopupWindow popupWindowCompat = new PopupWindow(mActivity);
            popupWindowCompat.setContentView(popupView);
            popupWindowCompat.setWidth((int) getResources().getDimension(R.dimen.popup_window_description_width));
            popupWindowCompat.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                popupWindowCompat.setElevation(20f);
            popupWindowCompat.setOutsideTouchable(true);
            popupWindowCompat.setFocusable(true);
            PopupWindowCompat.showAsDropDown(popupWindowCompat, anchorView, 0, 0, Gravity.END);
        }
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
