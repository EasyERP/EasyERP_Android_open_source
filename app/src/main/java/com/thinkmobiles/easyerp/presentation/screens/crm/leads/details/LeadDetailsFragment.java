package com.thinkmobiles.easyerp.presentation.screens.crm.leads.details;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.ColorUtils;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ismaeltoe.FlowLayout;
import com.jakewharton.rxbinding.view.RxView;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.data.model.crm.leads.TagItem;
import com.thinkmobiles.easyerp.domain.crm.LeadsRepository;
import com.thinkmobiles.easyerp.presentation.adapters.crm.AttachmentAdapter;
import com.thinkmobiles.easyerp.presentation.adapters.crm.HistoryAdapter;
import com.thinkmobiles.easyerp.presentation.base.BaseFragment;
import com.thinkmobiles.easyerp.presentation.base.rules.ErrorViewHelper;
import com.thinkmobiles.easyerp.presentation.custom.RoundRectDrawable;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.AttachmentDH;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.HistoryDH;
import com.thinkmobiles.easyerp.presentation.managers.HistoryAnimationHelper;
import com.thinkmobiles.easyerp.presentation.managers.TagHelper;
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
    protected HistoryAdapter historyAdapter;
    @Bean
    protected ErrorViewHelper errorViewHelper;
    @Bean
    protected HistoryAnimationHelper animationHelper;
    @Bean
    protected AttachmentAdapter attachmentAdapter;

    @FragmentArg
    protected String leadId;

    //region Views inject
    @ViewById(R.id.llErrorLayout)
    protected View errorLayout;
    @ViewById
    protected LinearLayout llMainContent_FLD;
    @ViewById
    protected SwipeRefreshLayout srlRefresh_FLD;
    @ViewById
    protected NestedScrollView nsvContent_FLD;
    @ViewById
    protected TextView tvNameLead_FLD;
    @ViewById
    protected TextView tvCurrentStatus_FLD;
    @ViewById
    protected TextView tvCloseDate_FLD;
    @ViewById
    protected TextView tvAssignedTo_FLD;
    @ViewById
    protected TextView tvPriority_FLD;
    @ViewById
    protected TextView tvSource_FLD;
    @ViewById
    protected FlowLayout flowLayoutTags_FLD;
    @ViewById
    protected TextView tvPersonName_FLD;
    @ViewById
    protected TextView tvFirstName_FLD;
    @ViewById
    protected TextView tvLastName_FLD;
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
    protected TextView tvFacebook_FLD;
    @ViewById
    protected TextView tvCompanyName_FLD;
    @ViewById
    protected TextView tvCompanyStreet_FLD;
    @ViewById
    protected TextView tvCompanyCity_FLD;
    @ViewById
    protected TextView tvCompanyState_FLD;
    @ViewById
    protected TextView tvCompanyZipcode_FLD;
    @ViewById
    protected TextView tvCompanyCountry_FLD;
    @ViewById
    protected FrameLayout btnHistory;
    @ViewById
    protected ImageView ivIconArrow;
    @ViewById
    protected RecyclerView rvHistory;
    @ViewById
    protected RecyclerView rvAttachments_FLD;

    @ViewById
    protected TextView tvEmptyContacts_FLD;
    @ViewById
    protected TextView tvEmptyCompany_FLD;
    @ViewById
    protected TextView tvEmptyAttachments_FLD;
    @ViewById
    protected LinearLayout llContainerContacts_FLD;
    @ViewById
    protected LinearLayout llContainerCompanyInfo_FLD;
    //endregion

    @DrawableRes(R.drawable.ic_arrow_up)
    protected Drawable icArrowUp;
    @DrawableRes(R.drawable.ic_arrow_down)
    protected Drawable icArrowDown;

    @AfterViews
    protected void initUI() {
        errorViewHelper.init(errorLayout, v -> presenter.refresh());

        srlRefresh_FLD.setOnRefreshListener(() -> presenter.refresh());
        rvHistory.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvHistory.setAdapter(historyAdapter);

        rvAttachments_FLD.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        rvAttachments_FLD.setAdapter(attachmentAdapter);
        attachmentAdapter.setOnCardClickListener((view, position, viewType) -> presenter.startAttachment(position));

        RxView.clicks(btnHistory)
                .throttleFirst(Constants.DELAY_CLICK, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> presenter.changeNotesVisibility());

        animationHelper.init(ivIconArrow, rvHistory);

        presenter.subscribe();
    }

    public boolean optionsMenuForDetail() {
        return true;
    }

    @Override
    public void onDestroyView() {
        animationHelper.cancel();
        presenter.unsubscribe();
        super.onDestroyView();
    }

    @AfterInject
    @Override
    public void initPresenter() {
        new LeadDetailsPresenter(this, leadsRepository, leadId);
    }

    @Override
    public void setPresenter(LeadDetailsContract.LeadDetailsPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showProgress(boolean enable) {
        if(enable) {
            displayProgress(true);
            srlRefresh_FLD.setVisibility(View.GONE);
            srlRefresh_FLD.setRefreshing(false);
        } else {
            displayProgress(false);
            srlRefresh_FLD.setVisibility(View.VISIBLE);
            srlRefresh_FLD.setRefreshing(false);
        }
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
    public void setTags(ArrayList<TagItem> tags) {
        flowLayoutTags_FLD.removeAllViews();
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        for(TagItem tagItem : tags) {
            TextView tvTag = (TextView) inflater.inflate(R.layout.text_view_tag, flowLayoutTags_FLD, false);
            tvTag.setBackground(new RoundRectDrawable(ColorUtils.setAlphaComponent(ContextCompat.getColor(getActivity(), TagHelper.getColorResIdByName(tagItem.color)), 200)));
            tvTag.setText(tagItem.name);
            flowLayoutTags_FLD.addView(tvTag);
        }
    }

    @Override
    public void setPersonName(String personName) {
        tvPersonName_FLD.setText(personName);
    }

    @Override
    public void setFirstName(String firstName) {
        tvFirstName_FLD.setText(firstName);
    }

    @Override
    public void setLastName(String lastName) {
        tvLastName_FLD.setText(lastName);
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
    public void setTvFacebook(String tvFacebook) {
        tvFacebook_FLD.setText(tvFacebook);
    }

    @Override
    public void setCompanyName(String companyName) {
        tvCompanyName_FLD.setText(companyName);
    }

    @Override
    public void setCompanyStreet(String companyStreet) {
        tvCompanyStreet_FLD.setText(companyStreet);
    }

    @Override
    public void setCompanyCity(String companyCity) {
        tvCompanyCity_FLD.setText(companyCity);
    }

    @Override
    public void setCompanyState(String companyState) {
        tvCompanyState_FLD.setText(companyState);
    }

    @Override
    public void setCompanyZipcode(String companyZipcode) {
        tvCompanyZipcode_FLD.setText(companyZipcode);
    }

    @Override
    public void setCompanyCountry(String companyCountry) {
        tvCompanyCountry_FLD.setText(companyCountry);
    }

    @Override
    public void showHistory(boolean enable) {

        if (enable && rvHistory.getVisibility() == View.GONE) {
            animationHelper.forward(nsvContent_FLD.getHeight());
        }
        if (!enable && rvHistory.getVisibility() == View.VISIBLE)
            animationHelper.backward(rvHistory.getHeight());
    }

    @Override
    public void showError(String errMsg) {
        if(errMsg == null) {
            errorViewHelper.hideError();
        } else {
            errorViewHelper.showErrorMsg(errMsg, ErrorViewHelper.ErrorType.NETWORK);
        }
        llMainContent_FLD.setVisibility(errMsg == null ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showContacts(boolean isShown) {
        llContainerContacts_FLD.setVisibility(isShown ? View.VISIBLE : View.GONE);
        tvEmptyContacts_FLD.setVisibility(isShown ? View.GONE : View.VISIBLE);
    }

    @Override
    public void showCompany(boolean isShown) {
        llContainerCompanyInfo_FLD.setVisibility(isShown ? View.VISIBLE : View.GONE);
        tvEmptyCompany_FLD.setVisibility(isShown ? View.GONE : View.VISIBLE);
    }

    @Override
    public void showAttachments(boolean isShown) {
        if(!isShown) tvEmptyAttachments_FLD.setVisibility(View.VISIBLE);
    }

    @Override
    public void setLeadName(String leadName) {
        tvNameLead_FLD.setText(leadName);
    }

    @Override
    public void setCurrentStatus(String currentStatus) {
        tvCurrentStatus_FLD.setText(currentStatus);
    }

    @Override
    public void setHistory(ArrayList<HistoryDH> history) {
        historyAdapter.setListDH(history);
    }

    @Override
    public void displayAttachments(ArrayList<AttachmentDH> attachmentDHs) {
        attachmentAdapter.setListDH(attachmentDHs);
    }

    @Override
    public void startUrlIntent(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }
}
