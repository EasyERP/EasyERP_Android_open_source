package com.thinkmobiles.easyerp.presentation.screens.crm.opportunities.details;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.ColorUtils;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ismaeltoe.FlowLayout;
import com.jakewharton.rxbinding.view.RxView;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.data.model.crm.leads.TagItem;
import com.thinkmobiles.easyerp.domain.crm.OpportunitiesRepository;
import com.thinkmobiles.easyerp.presentation.adapters.crm.AttachmentAdapter;
import com.thinkmobiles.easyerp.presentation.adapters.crm.ContactAdapter;
import com.thinkmobiles.easyerp.presentation.adapters.crm.HistoryAdapter;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentFragment;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentPresenter;
import com.thinkmobiles.easyerp.presentation.custom.RoundRectDrawable;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.AttachmentDH;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.ContactDH;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.HistoryDH;
import com.thinkmobiles.easyerp.presentation.managers.HistoryAnimationHelper;
import com.thinkmobiles.easyerp.presentation.managers.ImageHelper;
import com.thinkmobiles.easyerp.presentation.managers.TagHelper;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Created by Lynx on 2/1/2017.
 */

@EFragment
public class OpportunityDetailsFragment extends ContentFragment implements OpportunityDetailsContract.OpportunityDetailsView {

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_opportunity_details;
    }

    @Override
    protected ContentPresenter getPresenter() {
        return presenter;
    }

    private OpportunityDetailsContract.OpportunityDetailsPresenter presenter;

    @FragmentArg
    protected String opportunityID;

    @ViewById
    protected LinearLayout llMainContent_FOD;
    @ViewById
    protected NestedScrollView nsvContent_FOD;

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
    protected FlowLayout flowLayoutTags_FOD;
    @ViewById
    protected ImageView ivCompanyImage_FOD;
    @ViewById
    protected TextView tvCompanyTitleUrl_FOD;
    @ViewById
    protected EditText etCompanyName_FOD;
    @ViewById
    protected EditText etCompanyStreet_FOD;
    @ViewById
    protected EditText etCompanyCity_FOD;
    @ViewById
    protected EditText etCompanyState_FOD;
    @ViewById
    protected EditText etCompanyZipcode_FOD;
    @ViewById
    protected EditText etCompanyPhone_FOD;
    @ViewById
    protected EditText etCompanyCountry_FOD;
    @ViewById
    protected EditText etCompanyEmail_FOD;
    @ViewById
    protected RecyclerView rvContacts_FOD;
    @ViewById
    protected RecyclerView rvAttachments_FOD;

    @ViewById
    protected TextView tvEmptyContact_FOD;
    @ViewById
    protected FrameLayout flContainerContacts_FOD;
    @ViewById
    protected TextView tvEmptyCompany_FOD;
    @ViewById
    protected LinearLayout llCompanyContainer_FOD;
    @ViewById
    protected TextView tvEmptyAttachments_FOD;

    @ViewById
    protected FrameLayout btnHistory;
    @ViewById
    protected ImageView ivIconArrow;
    @ViewById
    protected RecyclerView rvHistory;
    //endregion

    @Bean
    protected HistoryAdapter historyAdapter;
    @Bean
    protected OpportunitiesRepository opportunitiesRepository;
    @Bean
    protected HistoryAnimationHelper animationHelper;
    @Bean
    protected AttachmentAdapter attachmentAdapter;
    @Bean
    protected ContactAdapter contactAdapter;

    @AfterInject
    @Override
    public void initPresenter() {
        new OpportunityDetailsPresenter(this, opportunitiesRepository, opportunityID);
    }

    @AfterViews
    protected void initUI() {
        rvHistory.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvHistory.setAdapter(historyAdapter);

        rvContacts_FOD.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        rvContacts_FOD.setAdapter(contactAdapter);

        rvAttachments_FOD.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        rvAttachments_FOD.setAdapter(attachmentAdapter);
        attachmentAdapter.setOnCardClickListener((view, position, viewType) -> presenter.startAttachment(position));

        RxView.clicks(btnHistory)
                .throttleFirst(Constants.DELAY_CLICK, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> presenter.changeNotesVisibility());

        animationHelper.init(ivIconArrow, rvHistory);
    }

    @Override
    public void showHistory(boolean enable) {
        if (enable && rvHistory.getVisibility() == View.GONE) {
            animationHelper.forward(nsvContent_FOD.getHeight());
        }
        if (!enable && rvHistory.getVisibility() == View.VISIBLE)
            animationHelper.backward(rvHistory.getHeight());
    }

    @Override
    public void showContact(boolean isShown) {
        flContainerContacts_FOD.setVisibility(isShown ? View.VISIBLE : View.GONE);
        tvEmptyContact_FOD.setVisibility(isShown ? View.GONE : View.VISIBLE);
    }

    @Override
    public void showCompany(boolean isShown) {
        llCompanyContainer_FOD.setVisibility(isShown ? View.VISIBLE : View.GONE);
        tvEmptyCompany_FOD.setVisibility(isShown ? View.GONE : View.VISIBLE);
    }

    @Override
    public void showAttachments(boolean isShown) {
        if(!isShown) tvEmptyAttachments_FOD.setVisibility(View.VISIBLE);
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
    public void displayContacts(ArrayList<ContactDH> contactDHs) {
        contactAdapter.setListDH(contactDHs);
    }

    @Override
    public void displayCompanyImage(String companyImageBase64) {
        ImageHelper.getBitmapFromBase64(companyImageBase64)
                .subscribe(ivCompanyImage_FOD::setImageBitmap);
    }

    @Override
    public void displayCompanyUrl(String companyUrl) {
        tvCompanyTitleUrl_FOD.setText(companyUrl);
        String url;
        if(!companyUrl.startsWith("http://")) url = "http://" + companyUrl;
        else url = companyUrl;
        RxView.clicks(tvCompanyTitleUrl_FOD)
                .throttleFirst(Constants.DELAY_CLICK, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> startUrlIntent(url));
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
        etCompanyZipcode_FOD.setText(companyZip);
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
    public void setTags(ArrayList<TagItem> tags) {
        flowLayoutTags_FOD.removeAllViews();
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        for(TagItem tagItem : tags) {
            TextView tvTag = (TextView) inflater.inflate(R.layout.view_text_tag, flowLayoutTags_FOD, false);
            tvTag.setBackground(new RoundRectDrawable(ColorUtils.setAlphaComponent(ContextCompat.getColor(getActivity(), TagHelper.getColorResIdByName(tagItem.color)), 150)));
            tvTag.setText(tagItem.name);
            flowLayoutTags_FOD.addView(tvTag);
        }
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

    @Override
    public void displayHistory(ArrayList<HistoryDH> history) {
        historyAdapter.setListDH(history);
    }

    @Override
    public boolean optionsMenuForDetail() {
        return true;
    }

    @Override
    public void setPresenter(OpportunityDetailsContract.OpportunityDetailsPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onDestroyView() {
        animationHelper.cancel();
        presenter.unsubscribe();
        super.onDestroyView();
    }
}
