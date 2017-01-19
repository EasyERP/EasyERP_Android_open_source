package com.thinkmobiles.easyerp.presentation.screens.leads.details;

import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.data.model.leads.details.LeadAttachment;
import com.thinkmobiles.easyerp.domain.LeadsRepository;
import com.thinkmobiles.easyerp.presentation.adapters.LeadHistoryAdapter;
import com.thinkmobiles.easyerp.presentation.base.BaseFragment;
import com.thinkmobiles.easyerp.presentation.holders.data.LeadHistoryDH;
import com.thinkmobiles.easyerp.presentation.screens.home.HomeActivity;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.DrawableRes;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


@EFragment(R.layout.fragment_lead_details)
public class LeadDetailsFragment extends BaseFragment<HomeActivity> implements LeadDetailsContract.LeadDetailsView {

    private LeadDetailsContract.LeadDetailsPresenter presenter;

    @Bean
    protected LeadsRepository leadsRepository;
    @Bean
    protected LeadHistoryAdapter historyAdapter;

    @FragmentArg
    protected String leadId;

    @ViewById
    protected SwipeRefreshLayout srlRefresh_FLD;
    @ViewById
    protected NestedScrollView nsvContent_FLD;
    @ViewById
    protected LinearLayout llWorkflowContainer_FLD;
    @ViewById
    protected TextView tvNameLead_FLD;
    @ViewById
    protected TextView tvCloseDate_FLD;
    @ViewById
    protected TextView tvAssignedTo_FLD;
    @ViewById
    protected TextView tvPriority_FLD;
    @ViewById
    protected TextView tvSource_FLD;
    @ViewById
    protected TextView tvTags_FLD;
    @ViewById
    protected TextView tvPersonName_FLD;
    @ViewById
    protected TextView tvJobPosition_FLD;
    @ViewById
    protected TextView tvDob_FLD;
    @ViewById
    protected TextView tvEmail_FLD;
    @ViewById
    protected TextView tvPhone_FLD;
    @ViewById
    protected TextView tvSkype_FLD;
    @ViewById
    protected TextView tvLinkedIn_FLD;
    @ViewById
    protected TextView tvCompanyName_FLD;
    @ViewById
    protected TextView tvCompanyAddress_FLD;
    @ViewById
    protected LinearLayout llAttachmentsContainer_FLD;
    @ViewById
    protected RelativeLayout btnHistory_FLD;
    @ViewById
    protected ImageView ivIconArrow_FLD;
    @ViewById
    protected RecyclerView rvHistory_FLD;

    @DrawableRes(R.drawable.ic_arrow_up)
    protected Drawable icArrowUp;
    @DrawableRes(R.drawable.ic_arrow_down)
    protected Drawable icArrowDown;

    @AfterInject
    protected void initFragment() {
        new LeadDetailsPresenter(this, leadsRepository, leadId);
    }

    @AfterViews
    protected void initUI() {
        srlRefresh_FLD.setOnRefreshListener(() -> presenter.refresh());
        rvHistory_FLD.setLayoutManager(new LinearLayoutManager(getContext()));
        rvHistory_FLD.setAdapter(historyAdapter);

        RxView.clicks(btnHistory_FLD)
                .throttleFirst(Constants.DELAY_CLICK, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> presenter.changeNotesVisibility());

        presenter.subscribe();
    }

    @Override
    public void onDestroyView() {
        presenter.unsubscribe();
        super.onDestroyView();
    }

    @Override
    protected boolean needProgress() {
        return true;
    }

    @Override
    public void setPresenter(LeadDetailsContract.LeadDetailsPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showProgress(boolean enable) {
        if (enable) {
            displayProgress(true);
            srlRefresh_FLD.setRefreshing(false);
        } else {
            displayProgress(false);
            srlRefresh_FLD.setRefreshing(false);
        }
    }

    @Override
    public void setNameLead(String nameLead) {
                tvNameLead_FLD.setText(nameLead);
    }

    @Override
    public void setCloseDate(String closeDate) {
        tvCloseDate_FLD.setText(closeDate);
    }

    @Override
    public void setAssignedTo(String assignedTo) {
        tvAssignedTo_FLD.setText(assignedTo);
    }

    @Override
    public void setPriority(String priority) {
        tvPriority_FLD.setText(priority);
    }

    @Override
    public void setSource(String source) {
        tvSource_FLD.setText(source);
    }

    @Override
    public void setTags(SpannableStringBuilder tags) {
        tvTags_FLD.setText(tags);
    }

    @Override
    public void setPersonName(String personName) {
        tvPersonName_FLD.setText(personName);
    }

    @Override
    public void setJobPosition(String jobPosition) {
        tvJobPosition_FLD.setText(jobPosition);
    }

    @Override
    public void setDob(String dob) {
        tvDob_FLD.setText(dob);
    }

    @Override
    public void setEmail(String email) {
        tvEmail_FLD.setText(email);
    }

    @Override
    public void setPhone(String phone) {
        tvPhone_FLD.setText(phone);
    }

    @Override
    public void setSkype(String skype) {
        tvSkype_FLD.setText(skype);
    }

    @Override
    public void setLinkedIn(String linkedIn) {
        tvLinkedIn_FLD.setText(linkedIn);
    }

    @Override
    public void setCompanyName(String companyName) {
        tvCompanyName_FLD.setText(companyName);
    }

    @Override
    public void setCompanyAddress(String companyAddress) {
        tvCompanyAddress_FLD.setText(companyAddress);
    }

    @Override
    public void setAttachments(ArrayList<LeadAttachment> attachments) {
        llAttachmentsContainer_FLD.removeAllViews();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.topMargin = (int) getResources().getDimension(R.dimen.default_padding_half);
        for(LeadAttachment attachment : attachments) {
            TextView tv = new TextView(getContext());
            tv.setLayoutParams(params);
            tv.setTextColor(ContextCompat.getColor(getContext(), android.R.color.black));
            tv.setText(attachment.name);
            llAttachmentsContainer_FLD.addView(tv);
        }
    }

    @Override
    public void showHistory(boolean enable) {
        if (enable) {
            nsvContent_FLD.setVisibility(View.GONE);
            rvHistory_FLD.setVisibility(View.VISIBLE);
            ivIconArrow_FLD.setImageDrawable(icArrowDown);
        } else {
            nsvContent_FLD.setVisibility(View.VISIBLE);
            rvHistory_FLD.setVisibility(View.GONE);
            ivIconArrow_FLD.setImageDrawable(icArrowUp);
        }
    }

    @Override
    public void setHistory(ArrayList<LeadHistoryDH> history) {
        historyAdapter.setListDH(history);
    }
}
