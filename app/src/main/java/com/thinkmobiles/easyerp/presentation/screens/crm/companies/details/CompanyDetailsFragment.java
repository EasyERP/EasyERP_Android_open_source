package com.thinkmobiles.easyerp.presentation.screens.crm.companies.details;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding.view.RxView;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.domain.crm.CompaniesRepository;
import com.thinkmobiles.easyerp.presentation.adapters.crm.ContactsAdapter;
import com.thinkmobiles.easyerp.presentation.adapters.crm.HistoryAdapter;
import com.thinkmobiles.easyerp.presentation.adapters.crm.OpportunityAndLeadsAdapter;
import com.thinkmobiles.easyerp.presentation.base.BaseFragment;
import com.thinkmobiles.easyerp.presentation.base.rules.ErrorViewHelper;
import com.thinkmobiles.easyerp.presentation.custom.transformations.CropCircleTransformation;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.ContactDH;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.HistoryDH;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.OpportunityAndLeadDH;
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

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Created by Lynx on 2/2/2017.
 */

@EFragment(R.layout.fragment_company_details)
public class CompanyDetailsFragment extends BaseFragment<HomeActivity> implements CompanyDetailsContract.CompanyDetailsView {

    private CompanyDetailsContract.CompanyDetailsPresenter presenter;

    @FragmentArg
    protected String companyID;

    //region View inject
    @ViewById(R.id.llErrorLayout)
    protected View errorLayout;
    @ViewById
    protected SwipeRefreshLayout srlRefresh_FCD;
    @ViewById
    protected NestedScrollView nsvContent_FCD;
    @ViewById
    protected ImageView ivCompanyAvatar_FCD;
    @ViewById
    protected TextView tvCompanyName_FCD;
    @ViewById
    protected TextView tvCompanyWebsite_FCD;
    @ViewById
    protected ImageView ivCompanyFb_FCD;
    @ViewById
    protected ImageView ivCompanyLinkedIn_FCD;
    @ViewById
    protected ImageView ivCompanySkype_FCD;
    @ViewById
    protected EditText etEmail_FCD;
    @ViewById
    protected EditText etAssignedTo_FCD;
    @ViewById
    protected EditText etLinkedIn_FCD;
    @ViewById
    protected EditText etFacebook_FCD;
    @ViewById
    protected EditText etPhone_FCD;
    @ViewById
    protected EditText etMobile_FCD;
    @ViewById
    protected EditText etBillingStreet_FCD;
    @ViewById
    protected EditText etBillingCity_FCD;
    @ViewById
    protected EditText etBillingState_FCD;
    @ViewById
    protected EditText etBillingZip_FCD;
    @ViewById
    protected EditText etBillingCountry_FCD;
    @ViewById
    protected EditText etShippingFullname_FCD;
    @ViewById
    protected EditText etShippingStreet_FCD;
    @ViewById
    protected EditText etShippingCity_FCD;
    @ViewById
    protected EditText etShippingState_FCD;
    @ViewById
    protected EditText etShippingZip_FCD;
    @ViewById
    protected EditText etShippingCountry_FCD;
    @ViewById
    protected EditText etReference_FCD;
    @ViewById
    protected CheckBox cbIsCustomer_FCD;
    @ViewById
    protected CheckBox cbIsSupplier_FCD;
    @ViewById
    protected EditText etSalesTeam_FCD;
    @ViewById
    protected EditText etSalesPerson_FCD;
    @ViewById
    protected EditText etImplementedBy_FCD;
    @ViewById
    protected EditText etLanguage_FCD;
    @ViewById
    protected RecyclerView rvContacts_FCD;
    @ViewById
    protected RecyclerView rvLeadsAndOpportunities_FCD;
    @ViewById
    protected TextView tvAttachments_FCD;
    @ViewById
    protected RelativeLayout btnHistory_FLD;
    @ViewById
    protected ImageView ivIconArrow_FCD;
    @ViewById
    protected View viewHistoryDivider_FCD;
    @ViewById
    protected RecyclerView rvHistory_FCD;
    //endregion

    @DrawableRes(R.drawable.ic_arrow_up)
    protected Drawable icArrowUp;
    @DrawableRes(R.drawable.ic_arrow_down)
    protected Drawable icArrowDown;

    @Bean
    protected ErrorViewHelper errorViewHelper;
    @Bean
    protected HistoryAdapter historyAdapter;
    @Bean
    protected ContactsAdapter contactsAdapter;
    @Bean
    protected OpportunityAndLeadsAdapter opportunityAndLeadsAdapter;
    @Bean
    protected CompaniesRepository companiesRepository;

    @AfterInject
    @Override
    public void initPresenter() {
        new CompanyDetailsPresenter(this, companiesRepository, companyID);
    }

    @AfterViews
    protected void initUI() {
        errorViewHelper.init(errorLayout, v -> presenter.refresh());

        srlRefresh_FCD.setOnRefreshListener(() -> presenter.refresh());
        rvHistory_FCD.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvHistory_FCD.setAdapter(historyAdapter);

        rvContacts_FCD.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvContacts_FCD.setAdapter(contactsAdapter);

        rvLeadsAndOpportunities_FCD.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        rvLeadsAndOpportunities_FCD.setAdapter(opportunityAndLeadsAdapter);

        RxView.clicks(btnHistory_FLD)
                .throttleFirst(Constants.DELAY_CLICK, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> presenter.changeNotesVisibility());

        presenter.subscribe();
    }

    //region Set data
    @Override
    protected boolean needProgress() {
        return true;
    }

    @Override
    public void showProgress(boolean enable) {
        if (enable) {
            displayProgress(true);
            srlRefresh_FCD.setVisibility(View.GONE);
            srlRefresh_FCD.setRefreshing(false);
        } else {
            displayProgress(false);
            srlRefresh_FCD.setVisibility(View.VISIBLE);
            srlRefresh_FCD.setRefreshing(false);
        }
    }

    @Override
    public void showHistory(boolean isShow) {
        if(isShow) {
            btnHistory_FLD.setBackgroundColor(ContextCompat.getColor(getActivity(), (android.R.color.white)));
            nsvContent_FCD.setVisibility(View.GONE);
            rvHistory_FCD.setVisibility(View.VISIBLE);
            viewHistoryDivider_FCD.setVisibility(View.VISIBLE);
            ivIconArrow_FCD.setImageDrawable(icArrowDown);
        } else {
            btnHistory_FLD.setBackgroundColor(ContextCompat.getColor(getActivity(), (R.color.color_grey_transparent)));
            rvHistory_FCD.setVisibility(View.GONE);
            nsvContent_FCD.setVisibility(View.VISIBLE);
            viewHistoryDivider_FCD.setVisibility(View.GONE);
            ivIconArrow_FCD.setImageDrawable(icArrowUp);
        }
    }

    @Override
    public void displayError(String msg) {
        if(msg == null) errorViewHelper.hideError();
        else {
            srlRefresh_FCD.setRefreshing(false);
            displayProgress(false);
            srlRefresh_FCD.setVisibility(View.GONE);
            errorViewHelper.showErrorMsg(msg, ErrorViewHelper.ErrorType.NETWORK);
        }
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void displayCompanyImage(String base64Image) {
        ImageHelper.getBitmapFromBase64(base64Image, new CropCircleTransformation())
                .subscribe(ivCompanyAvatar_FCD::setImageBitmap);
    }

    @Override
    public void displayCompanyName(String companyName) {
        tvCompanyName_FCD.setText(companyName);
    }

    @Override
    public void displayCompanyUrl(String companyUrl) {
        tvCompanyWebsite_FCD.setMovementMethod(LinkMovementMethod.getInstance());
        tvCompanyWebsite_FCD.setText(Html.fromHtml(companyUrl));
    }

    @Override
    public void enableFacebookButton(String url) {
        ivCompanyFb_FCD.setVisibility(View.VISIBLE);
        RxView.clicks(ivCompanyFb_FCD)
                .throttleFirst(Constants.DELAY_CLICK, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    getActivity().startActivity(intent);
                });
    }

    @Override
    public void enableLinkedInButton(String url) {
        ivCompanyLinkedIn_FCD.setVisibility(View.VISIBLE);
        RxView.clicks(ivCompanyLinkedIn_FCD)
                .throttleFirst(Constants.DELAY_CLICK, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.linkedin.com/profile/view?id=" + url));
                    getActivity().startActivity(intent);
                });
    }

    @Override
    public void enableSkypeButton(String url) {
        ivCompanySkype_FCD.setVisibility(View.VISIBLE);
        RxView.clicks(ivCompanySkype_FCD)
                .throttleFirst(Constants.DELAY_CLICK, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> {
                    try {
                        Intent sky = new Intent(Intent.ACTION_VIEW);
                        sky.setData(Uri.parse("skype:" + url));
                        getActivity().startActivity(sky);
                    } catch (ActivityNotFoundException e) {
                        Toast.makeText(getActivity(), "Skype not found", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void displayEmail(String email) {
        etEmail_FCD.setText(email);
    }

    @Override
    public void displayAssignedTo(String assignedTo) {
        etAssignedTo_FCD.setText(assignedTo);
    }

    @Override
    public void displayLinkedIn(String linkedIn) {
        etLinkedIn_FCD.setText(linkedIn);
    }

    @Override
    public void displayFacebook(String facebook) {
        etFacebook_FCD.setText(facebook);
    }

    @Override
    public void displayPhone(String phone) {
        etPhone_FCD.setText(phone);
    }

    @Override
    public void displayMobile(String mobile) {
        etMobile_FCD.setText(mobile);
    }

    @Override
    public void displayBillingStreet(String billingStreet) {
        etBillingStreet_FCD.setText(billingStreet);
    }

    @Override
    public void displayBillingCity(String billingCity) {
        etBillingCity_FCD.setText(billingCity);
    }

    @Override
    public void displayBillingState(String billingState) {
        etBillingState_FCD.setText(billingState);
    }

    @Override
    public void displayBillingZip(String billingZip) {
        etBillingZip_FCD.setText(billingZip);
    }

    @Override
    public void displayBillingCountry(String billingCountry) {
        etBillingCountry_FCD.setText(billingCountry);
    }

    @Override
    public void displayShippingFullName(String shippingFullName) {
        etShippingFullname_FCD.setText(shippingFullName);
    }

    @Override
    public void displayShippingStreet(String shippingStreet) {
        etShippingStreet_FCD.setText(shippingStreet);
    }

    @Override
    public void displayShippingCity(String shippingCity) {
        etShippingCity_FCD.setText(shippingCity);
    }

    @Override
    public void displayShippingState(String shippingState) {
        etShippingState_FCD.setText(shippingState);
    }

    @Override
    public void displayShippingZip(String shippingZip) {
        etShippingZip_FCD.setText(shippingZip);
    }

    @Override
    public void displayShippingCountry(String shippingCountry) {
        etShippingCountry_FCD.setText(shippingCountry);
    }

    @Override
    public void displaySalesReference(String salesReference) {
        etReference_FCD.setText(salesReference);
    }

    @Override
    public void displaySalesIsCustomer(boolean isCustomer) {
        cbIsCustomer_FCD.setChecked(isCustomer);
    }

    @Override
    public void displaySalesIsSupplier(boolean isSupplier) {
        cbIsSupplier_FCD.setChecked(isSupplier);
    }

    @Override
    public void displaySalesTeam(String salesTeam) {
        etSalesTeam_FCD.setText(salesTeam);
    }

    @Override
    public void displaySalesPerson(String salesPerson) {
        etSalesPerson_FCD.setText(salesPerson);
    }

    @Override
    public void displaySalesImplementedBy(String salesImplementedBy) {
        etImplementedBy_FCD.setText(salesImplementedBy);
    }

    @Override
    public void displaySalesLanguage(String salesLanguage) {
        etLanguage_FCD.setText(salesLanguage);
    }

    @Override
    public void displayContacts(ArrayList<ContactDH> contactDHs) {
        contactsAdapter.setListDH(contactDHs);
    }

    @Override
    public void displayLeadAndOpportunity(ArrayList<OpportunityAndLeadDH> opportunityAndLeadDHs) {
        opportunityAndLeadsAdapter.setListDH(opportunityAndLeadDHs);
    }

    @Override
    public void displayAttachments(String attachments) {
        tvAttachments_FCD.setMovementMethod(LinkMovementMethod.getInstance());
        tvAttachments_FCD.setText(Html.fromHtml(attachments));
    }

    @Override
    public void displayHistory(ArrayList<HistoryDH> historyDHs) {
        historyAdapter.setListDH(historyDHs);
    }
    //endregion


    @Override
    public void setPresenter(CompanyDetailsContract.CompanyDetailsPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(presenter != null) presenter.unsubscribe();
    }
}
