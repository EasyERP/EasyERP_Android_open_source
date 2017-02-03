package com.thinkmobiles.easyerp.presentation.screens.crm.opportunities.details;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding.view.RxView;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.domain.crm.OpportunitiesRepository;
import com.thinkmobiles.easyerp.presentation.adapters.crm.HistoryAdapter;
import com.thinkmobiles.easyerp.presentation.base.BaseFragment;
import com.thinkmobiles.easyerp.presentation.base.rules.ErrorViewHelper;
import com.thinkmobiles.easyerp.presentation.custom.transformations.CropCircleTransformation;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.HistoryDH;
import com.thinkmobiles.easyerp.presentation.managers.ImageHelper;
import com.thinkmobiles.easyerp.presentation.screens.home.HomeActivity;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.DrawableRes;
import org.androidannotations.annotations.res.IntegerRes;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Created by Lynx on 2/1/2017.
 */

@EFragment(R.layout.fragment_opportunity_details)
public class OpportunityDetailsFragment extends BaseFragment<HomeActivity> implements OpportunityDetailsContract.OpportunityDetailsView {

    private OpportunityDetailsContract.OpportunityDetailsPresenter presenter;

    @FragmentArg
    protected String opportunityID;

    //region View inject
    @ViewById(R.id.llErrorLayout)
    protected View errorLayout;
    @ViewById
    protected SwipeRefreshLayout srlRefresh_FOD;
    @ViewById
    protected LinearLayout llMainContent_FOD;
    @ViewById
    protected NestedScrollView nsvContent_FOD;
    @ViewById
    protected LinearLayout llContactInfo_FOD;
    @ViewById
    protected LinearLayout llCompanyInfo_FOD;

    @ViewById
    protected TextView tvNameOpportunity_FOD;
    @ViewById
    protected EditText etCurrentStatus_FOD;
    @ViewById
    protected EditText etRevenue_FOD;
    @ViewById
    protected EditText etCloseDate_FOD;
    @ViewById
    protected EditText etAssignedTo_FOD;
    @ViewById
    protected TextView tvTags_FOD;
    @ViewById
    protected ImageView ivContactImage_FOD;
    @ViewById
    protected TextView tvContactName_FOD;
    @ViewById
    protected TextView tvContactEmail_FOD;
    @ViewById
    protected ImageView ivCompanyImage_FOD;
    @ViewById
    protected TextView tvCompanyTitleName_FOD;
    @ViewById
    protected TextView tvCompanyUrl_FOD;
    @ViewById
    protected EditText etCompanyName_FOD;
    @ViewById
    protected EditText etCompanyStreet_FOD;
    @ViewById
    protected EditText etCompanyCity_FOD;
    @ViewById
    protected EditText etCompanyState_FOD;
    @ViewById
    protected EditText etCompanyZip_FOD;
    @ViewById
    protected EditText etCompanyCountry_FOD;
    @ViewById
    protected EditText etCompanyPhone_FOD;
    @ViewById
    protected EditText etCompanyEmail_FOD;
    @ViewById
    protected TextView tvAttachments_FLD;

    @ViewById
    protected RelativeLayout btnHistory_FOD;
    @ViewById
    protected ImageView ivIconArrow_FOD;
    @ViewById
    protected View viewHistoryDivider_FOD;
    @ViewById
    protected RecyclerView rvHistory_FOD;
    //endregion

    @Bean
    protected ErrorViewHelper errorViewHelper;
    @Bean
    protected HistoryAdapter historyAdapter;
    @Bean
    protected OpportunitiesRepository opportunitiesRepository;

    @DrawableRes(R.drawable.ic_arrow_up)
    protected Drawable icArrowUp;
    @DrawableRes(R.drawable.ic_arrow_down)
    protected Drawable icArrowDown;
    @IntegerRes(android.R.integer.config_longAnimTime)
    protected int animDuration;

    private AnimatorSet set;
    private ObjectAnimator rotate;
    private ValueAnimator scale;

    @AfterInject
    @Override
    public void initPresenter() {
        new OpportunityDetailsPresenter(this, opportunitiesRepository, opportunityID);
    }

    @AfterViews
    protected void initUI() {
        errorViewHelper.init(errorLayout, v -> presenter.refresh());

        srlRefresh_FOD.setOnRefreshListener(() -> presenter.refresh());
        rvHistory_FOD.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvHistory_FOD.setAdapter(historyAdapter);
        tvAttachments_FLD.setMovementMethod(LinkMovementMethod.getInstance());

        RxView.clicks(btnHistory_FOD)
                .throttleFirst(Constants.DELAY_CLICK, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> presenter.changeNotesVisibility());
        rotate = ObjectAnimator.ofFloat(ivIconArrow_FOD, View.ROTATION, 0, 0);

        scale = new ValueAnimator();
        scale.addUpdateListener(animation -> {
            int height = (int) animation.getAnimatedValue();
            rvHistory_FOD.setVisibility(height == 0 ? View.GONE : View.VISIBLE);
            ViewGroup.LayoutParams params = rvHistory_FOD.getLayoutParams();
            params.height = height;
            rvHistory_FOD.setLayoutParams(params);
        });


        set = new AnimatorSet();
        set.setInterpolator(new FastOutSlowInInterpolator());
        set.setDuration(animDuration);
        set.playTogether(rotate, scale);

        presenter.subscribe();
    }

    @Override
    protected boolean needProgress() {
        return true;
    }

    @Override
    public void showProgress(boolean enable) {
        if(enable) {
            displayProgress(true);
            srlRefresh_FOD.setVisibility(View.GONE);
            srlRefresh_FOD.setRefreshing(false);
        } else {
            displayProgress(false);
            srlRefresh_FOD.setVisibility(View.VISIBLE);
            srlRefresh_FOD.setRefreshing(false);
        }
    }

    @Override
    public void showHistory(boolean enable) {
        if (enable) {
            scale.setIntValues(0, nsvContent_FOD.getHeight());
            rotate.setFloatValues((float)rotate.getAnimatedValue(), 180);
        } else {
            scale.setIntValues(rvHistory_FOD.getHeight(), 0);
            rotate.setFloatValues((float)rotate.getAnimatedValue(), 0);
        }
        if (enable && rvHistory_FOD.getVisibility() == View.GONE
                || !enable && rvHistory_FOD.getVisibility() == View.VISIBLE)
            set.start();
    }

    @Override
    public void showError(String errMsg) {
        if(errMsg == null) {
            errorViewHelper.hideError();
        } else {
            errorViewHelper.showErrorMsg(errMsg, ErrorViewHelper.ErrorType.NETWORK);
        }
        llMainContent_FOD.setVisibility(errMsg == null ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void displayContactInfo(boolean isVisible) {
        llContactInfo_FOD.setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }

    @Override
    public void displayCompanyInfo(boolean isVisible) {
        llCompanyInfo_FOD.setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }

    @Override
    public void displayOpportunityName(String opportunityName) {
        tvNameOpportunity_FOD.setText(opportunityName);
    }

    @Override
    public void displayStatus(String opportunityStatus) {
        etCurrentStatus_FOD.setText(opportunityStatus);
    }

    @Override
    public void displayRevenue(String revenue) {
        etRevenue_FOD.setText(revenue);
    }

    @Override
    public void displayCloseDate(String closeDate) {
        etCloseDate_FOD.setText(closeDate);
    }

    @Override
    public void displayAssignedTo(String assignedTo) {
        etAssignedTo_FOD.setText(assignedTo);
    }

    @Override
    public void displayContactImage(String contactImageBase64) {
        ImageHelper.getBitmapFromBase64(contactImageBase64, new CropCircleTransformation())
                .subscribe(ivContactImage_FOD::setImageBitmap);
    }

    @Override
    public void displayContactFullName(String contactFullname) {
        tvContactName_FOD.setText(contactFullname);
    }

    @Override
    public void displayContactEmail(String contactEmail) {
        tvContactEmail_FOD.setText(contactEmail);
    }

    @Override
    public void displayCompanyImage(String companyImageBase64) {
        ImageHelper.getBitmapFromBase64(companyImageBase64)
                .subscribe(ivCompanyImage_FOD::setImageBitmap);
    }

    @Override
    public void displayCompanyTitleName(String companyTitleName) {
        tvCompanyTitleName_FOD.setText(companyTitleName);
    }

    @Override
    public void displayCompanyUrl(String companyUrl) {
        tvCompanyUrl_FOD.setMovementMethod(LinkMovementMethod.getInstance());
        tvCompanyUrl_FOD.setText(Html.fromHtml(companyUrl));
    }

    @Override
    public void displayCompanyName(String companyName) {
        etCompanyName_FOD.setText(companyName);
    }

    @Override
    public void displayCompanyStreet(String companyStreet) {
        etCompanyStreet_FOD.setText(companyStreet);
    }

    @Override
    public void displayCompanyCity(String companyCity) {
        etCompanyCity_FOD.setText(companyCity);
    }

    @Override
    public void displayCompanyState(String companyState) {
        etCompanyState_FOD.setText(companyState);
    }

    @Override
    public void displayCompanyZip(String companyZip) {
        etCompanyZip_FOD.setText(companyZip);
    }

    @Override
    public void displayCompanyCountry(String companyCountry) {
        etCompanyCountry_FOD.setText(companyCountry);
    }

    @Override
    public void displayCompanyPhone(String companyPhone) {
        etCompanyPhone_FOD.setText(companyPhone);
    }

    @Override
    public void displayCompanyEmail(String companyEmail) {
        etCompanyEmail_FOD.setText(companyEmail);
    }

    @Override
    public void setAttachments(String attachments) {
        tvAttachments_FLD.setText(Html.fromHtml(attachments));
    }

    @Override
    public void displayTags(SpannableStringBuilder tags) {
        tvTags_FOD.setText(tags);
    }

    @Override
    public void displayHistory(ArrayList<HistoryDH> history) {
        historyAdapter.setListDH(history);
    }

    @Override
    public void setPresenter(OpportunityDetailsContract.OpportunityDetailsPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onDestroyView() {
        set.cancel();
        presenter.unsubscribe();
        super.onDestroyView();
    }
}
