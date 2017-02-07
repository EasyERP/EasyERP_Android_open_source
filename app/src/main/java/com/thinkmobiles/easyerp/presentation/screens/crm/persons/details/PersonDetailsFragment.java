package com.thinkmobiles.easyerp.presentation.screens.crm.persons.details;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
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
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding.view.RxView;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.domain.crm.PersonsRepository;
import com.thinkmobiles.easyerp.presentation.adapters.crm.AttachmentAdapter;
import com.thinkmobiles.easyerp.presentation.adapters.crm.HistoryAdapter;
import com.thinkmobiles.easyerp.presentation.adapters.crm.OpportunityAndLeadsAdapter;
import com.thinkmobiles.easyerp.presentation.adapters.crm.OpportunityPreviewAdapter;
import com.thinkmobiles.easyerp.presentation.base.BaseFragment;
import com.thinkmobiles.easyerp.presentation.base.rules.ErrorViewHelper;
import com.thinkmobiles.easyerp.presentation.custom.transformations.CropCircleTransformation;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.AttachmentDH;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.HistoryDH;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.OpportunityAndLeadDH;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.OpportunityPreviewDH;
import com.thinkmobiles.easyerp.presentation.managers.HistoryAnimationHelper;
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
import org.androidannotations.annotations.res.StringRes;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Created by Lynx on 1/24/2017.
 */

@EFragment(R.layout.fragment_person_details)
public class PersonDetailsFragment extends BaseFragment<HomeActivity> implements PersonDetailsContract.PersonDetailsView {

    private PersonDetailsContract.PersonDetailsPresenter presenter;

    @FragmentArg
    protected String personID;

    //region View Inject
    @ViewById(R.id.llErrorLayout)
    protected View errorLayout;
    @ViewById
    protected SwipeRefreshLayout srlRefresh_FPD;
    @ViewById
    protected NestedScrollView nsvContent_FPD;
    @ViewById
    protected ImageView ivPersonAvatar_FPD;
    @ViewById
    protected TextView tvPersonName_FPD;
    @ViewById
    protected TextView tvJobPosition_FPD;
    @ViewById
    protected EditText etEmail_FPD;
    @ViewById
    protected EditText etSkype_FPD;
    @ViewById
    protected EditText etPhone_FPD;
    @ViewById
    protected EditText etLinkedIn_FPD;
    @ViewById
    protected EditText etMobile_FPD;
    @ViewById
    protected EditText etFacebook_FPD;
    @ViewById
    protected EditText etDateOfBirth_FPD;
    @ViewById
    protected ImageView ivSkype_FPD;
    @ViewById
    protected ImageView ivLinkedIn_FPD;
    @ViewById
    protected ImageView ivFacebook_FPD;
    @ViewById
    protected LinearLayout llContainerSalesPurchases_FPD;
    @ViewById
    protected CheckBox cbIsCustomer_FPD;
    @ViewById
    protected CheckBox cbIsSupplier_FPD;
    @ViewById
    protected EditText etSalesTeam_FPD;
    @ViewById
    protected EditText etSalesReference_FPD;
    @ViewById
    protected EditText etSalesPerson_FPD;
    @ViewById
    protected EditText etSalesLanguage_FPD;
    @ViewById
    protected EditText etSalesImplementedBy_FPD;
    @ViewById
    protected EditText etBillingStreet_FPD;
    @ViewById
    protected EditText etBillingState_FPD;
    @ViewById
    protected EditText etBillingCity_FPD;
    @ViewById
    protected EditText etBillingCountry_FPD;
    @ViewById
    protected EditText etBillingZipcode_FPD;
    @ViewById
    protected EditText etShippingFullName_FPD;
    @ViewById
    protected EditText etShippingState_FPD;
    @ViewById
    protected EditText etShippingStreet_FPD;
    @ViewById
    protected EditText etShippingCity_FPD;
    @ViewById
    protected EditText etShippingCountry_FPD;
    @ViewById
    protected EditText etShippingZipcode_FPD;
    @ViewById
    protected LinearLayout llCompanyContainer_FPD;
    @ViewById
    protected ImageView ivCompanyImage_FPD;
    @ViewById
    protected TextView tvCompanyTitleUrl_FPD;
    @ViewById
    protected TextView tvCompanyName_FPD;
    @ViewById
    protected EditText etCompanyName_FPD;
    @ViewById
    protected EditText etCompanyState_FPD;
    @ViewById
    protected EditText etCompanyStreet_FPD;
    @ViewById
    protected EditText etCompanyZipcode_FPD;
    @ViewById
    protected EditText etCompanyCity_FPD;
    @ViewById
    protected EditText etCompanyCountry_FPD;
    @ViewById
    protected EditText etCompanyPhone_FPD;
    @ViewById
    protected EditText etCompanyEmail_FPD;
    @ViewById
    protected LinearLayout llAttachmentsContainer_FPD;
    @ViewById
    protected RecyclerView rvAttachments_FPD;
    @ViewById
    protected FrameLayout btnHistory;
    @ViewById
    protected ImageView ivIconArrow;
    @ViewById
    protected RecyclerView rvLeadsAndOpportunities_FPD;
    @ViewById
    protected RecyclerView rvHistory;

    @ViewById
    protected TextView tvEmptySalesANdPurchases_FPD;
    @ViewById
    protected TextView tvEmptyLeadsAndOpportunities_FPD;
    @ViewById
    protected TextView tvEmptyCompany_FPD;
    @ViewById
    protected TextView tvEmptyAttachments_FPD;
    //endregion

    @DrawableRes(R.drawable.ic_arrow_up)
    protected Drawable icArrowUp;
    @DrawableRes(R.drawable.ic_arrow_down)
    protected Drawable icArrowDown;

    @StringRes(R.string.err_not_specified)
    protected String notSpecified;

    @Bean
    protected PersonsRepository personsRepository;
    @Bean
    protected HistoryAdapter historyAdapter;
    @Bean
    protected ErrorViewHelper errorViewHelper;
    @Bean
    protected HistoryAnimationHelper animationHelper;
    @Bean
    protected OpportunityAndLeadsAdapter opportunityAndLeadsAdapter;
    @Bean
    protected AttachmentAdapter attachmentAdapter;

    @AfterViews
    protected void initUI() {
        setEmptyData();
        errorViewHelper.init(errorLayout, v -> presenter.refresh());

        srlRefresh_FPD.setOnRefreshListener(() -> presenter.refresh());
        rvHistory.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvHistory.setAdapter(historyAdapter);

        rvLeadsAndOpportunities_FPD.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        rvLeadsAndOpportunities_FPD.setAdapter(opportunityAndLeadsAdapter);

        rvAttachments_FPD.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        rvAttachments_FPD.setAdapter(attachmentAdapter);
        attachmentAdapter.setOnCardClickListener((view, position, viewType) -> presenter.startAttachment(position));

        RxView.clicks(btnHistory)
                .throttleFirst(Constants.DELAY_CLICK, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> presenter.changeNotesVisibility());

        animationHelper.init(ivIconArrow, rvHistory);

        presenter.subscribe();
    }

    @Override
    public void onDestroyView() {
        animationHelper.cancel();
        presenter.unsubscribe();
        super.onDestroyView();
    }

    @Override
    public void displayPersonAvatar(String base64Avatar) {
        ImageHelper.getBitmapFromBase64(base64Avatar, new CropCircleTransformation())
                .subscribe(ivPersonAvatar_FPD::setImageBitmap);
    }

    @Override
    public void displayPersonName(String personName) {
        tvPersonName_FPD.setText(personName);
    }

    @Override
    public void displayJobPosition(String jobPosition) {
        tvJobPosition_FPD.setText(jobPosition);
    }

    @Override
    public void displayEmail(String email) {
        etEmail_FPD.setText(email);
    }

    @Override
    public void displaySkype(String skype) {
        etSkype_FPD.setText(skype);
    }

    @Override
    public void displayLinkedIn(String linkedIn) {
        etLinkedIn_FPD.setText(linkedIn);
    }

    @Override
    public void displayFacebook(String facebook) {
        etFacebook_FPD.setText(facebook);
    }

    @Override
    public void displayPhone(String phone) {
        etPhone_FPD.setText(phone);
    }

    @Override
    public void displayMobile(String mobile) {
        etMobile_FPD.setText(mobile);
    }

    @Override
    public void displayDateOfBirth(String dateOfBirth) {
        etDateOfBirth_FPD.setText(dateOfBirth);
    }

    @Override
    public void enableSkypeIcon(String skype) {
        ivSkype_FPD.setVisibility(View.VISIBLE);
        RxView.clicks(ivSkype_FPD)
                .throttleFirst(Constants.DELAY_CLICK, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> {
                    try {
                        Intent sky = new Intent(Intent.ACTION_VIEW);
                        sky.setData(Uri.parse("skype:" + skype));
                        getActivity().startActivity(sky);
                    } catch (ActivityNotFoundException e) {
                        Toast.makeText(getActivity(), "Skype not found", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void enableLinkedInIcon(String linkedIn) {
        ivLinkedIn_FPD.setVisibility(View.VISIBLE);
        RxView.clicks(ivLinkedIn_FPD)
                .throttleFirst(Constants.DELAY_CLICK, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.linkedin.com/profile/view?id=" + linkedIn));
                    getActivity().startActivity(intent);
                });
    }

    @Override
    public void enableFacebookIcon(String facebook) {
        ivFacebook_FPD.setVisibility(View.VISIBLE);
        RxView.clicks(ivFacebook_FPD)
                .throttleFirst(Constants.DELAY_CLICK, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(facebook));
                    getActivity().startActivity(intent);
                });
    }

    @Override
    public void displayBillingStreet(String billingStreet) {
        etBillingStreet_FPD.setText(billingStreet);
    }

    @Override
    public void displayBillingCity(String billingCity) {
        etBillingCity_FPD.setText(billingCity);
    }

    @Override
    public void displayBillingState(String billingState) {
        etBillingState_FPD.setText(billingState);
    }

    @Override
    public void displayBillingZipcode(String billingZipcode) {
        etBillingZipcode_FPD.setText(billingZipcode);
    }

    @Override
    public void displayBillingCountry(String billingCountry) {
        etBillingCountry_FPD.setText(billingCountry);
    }

    @Override
    public void displayShippingFullName(String shippingFirstName) {
        etShippingFullName_FPD.setText(shippingFirstName);
    }

    @Override
    public void displayShippingStreet(String shippingStreet) {
        etShippingStreet_FPD.setText(shippingStreet);
    }

    @Override
    public void displayShippingCity(String shippingCity) {
        etShippingCity_FPD.setText(shippingCity);
    }

    @Override
    public void displayShippingState(String shippingState) {
        etShippingState_FPD.setText(shippingState);
    }

    @Override
    public void displayShippingZipcode(String shippingZipcode) {
        etShippingZipcode_FPD.setText(shippingZipcode);
    }

    @Override
    public void displayShippingCountry(String shippingCountry) {
        etShippingCountry_FPD.setText(shippingCountry);
    }

    @Override
    public void showSalesPurchasesInfo(boolean isShow) {
        llContainerSalesPurchases_FPD.setVisibility(isShow ? View.VISIBLE : View.GONE);
        if(!isShow) tvEmptySalesANdPurchases_FPD.setVisibility(View.VISIBLE);
    }

    @Override
    public void showLeadsAndOpportunities(boolean isShown) {
        if(!isShown) tvEmptyLeadsAndOpportunities_FPD.setVisibility(View.VISIBLE);
    }

    @Override
    public void showAttachments(boolean isShown) {
        if(!isShown) tvEmptyAttachments_FPD.setVisibility(View.VISIBLE);
    }

    @Override
    public void displayError(String msg) {
        if (msg == null) errorViewHelper.hideError();
        else {
            srlRefresh_FPD.setRefreshing(false);
            displayProgress(false);
            srlRefresh_FPD.setVisibility(View.GONE);
            errorViewHelper.showErrorMsg(msg, ErrorViewHelper.ErrorType.NETWORK);
        }
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showJobPosition(boolean isShown) {
        tvJobPosition_FPD.setVisibility(isShown ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showCompany(boolean isShown) {
        tvCompanyName_FPD.setVisibility(isShown ? View.VISIBLE : View.GONE);
    }

    @Override
    public void displayIsCustomer(boolean isCustomer) {
        cbIsCustomer_FPD.setChecked(isCustomer);
    }

    @Override
    public void displayIsSupplier(boolean isSupplier) {
        cbIsSupplier_FPD.setChecked(isSupplier);
    }

    @Override
    public void displaySalesTeam(String salesTeam) {
        etSalesTeam_FPD.setText(salesTeam);
    }

    @Override
    public void displaySalesPerson(String salesPerson) {
        etSalesPerson_FPD.setText(salesPerson);
    }

    @Override
    public void displaySalesImplementedBy(String implementedBy) {
        etSalesImplementedBy_FPD.setText(implementedBy);
    }

    @Override
    public void displaySalesReference(String reference) {
        etSalesReference_FPD.setText(reference);
    }

    @Override
    public void displaySalesLanguage(String language) {
        etSalesLanguage_FPD.setText(language);
    }

    @Override
    public void showCompanyInfo(boolean isShow) {
        llCompanyContainer_FPD.setVisibility(isShow ? View.VISIBLE : View.GONE);
        if(!isShow) tvEmptyCompany_FPD.setVisibility(View.VISIBLE);
    }

    @Override
    public void displayCompanyImage(String base64CompanyImage) {
        ImageHelper.getBitmapFromBase64(base64CompanyImage)
                .subscribe(ivCompanyImage_FPD::setImageBitmap);
    }

    @Override
    public void displayCompanyUrl(String companyUrl) {
        tvCompanyTitleUrl_FPD.setText(companyUrl);
        String url;
        if(!companyUrl.startsWith("http://")) url = "http://" + companyUrl;
        else url = companyUrl;
        RxView.clicks(tvCompanyTitleUrl_FPD)
                .throttleFirst(Constants.DELAY_CLICK, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> startUrlIntent(url));
    }

    @Override
    public void displayCompanyName(String companyName) {
        tvCompanyName_FPD.setText(companyName);
        etCompanyName_FPD.setText(companyName);
    }

    @Override
    public void displayCompanyStreet(String companyStreet) {
        etCompanyStreet_FPD.setText(companyStreet);
    }

    @Override
    public void displayCompanyCity(String companyCity) {
        etCompanyCity_FPD.setText(companyCity);
    }

    @Override
    public void displayCompanyState(String companyState) {
        etCompanyState_FPD.setText(companyState);
    }

    @Override
    public void displayCompanyZipcode(String companyZipcode) {
        etCompanyZipcode_FPD.setText(companyZipcode);
    }

    @Override
    public void displayCompanyCountry(String companyCountry) {
        etCompanyCountry_FPD.setText(companyCountry);
    }

    @Override
    public void displayCompanyPhone(String companyPhone) {
        etCompanyPhone_FPD.setText(companyPhone);
    }

    @Override
    public void displayCompanyEmail(String companyEmail) {
        etCompanyEmail_FPD.setText(companyEmail);
    }

    @Override
    public void displayLeadAndOpportunity(ArrayList<OpportunityAndLeadDH> opportunityAndLeadDHs) {
        opportunityAndLeadsAdapter.setListDH(opportunityAndLeadDHs);
    }

    @Override
    public void displayAttachments(ArrayList<AttachmentDH> attachmentDHs) {
        attachmentAdapter.setListDH(attachmentDHs);
    }

    @Override
    public void showProgress(boolean enable) {
        if (enable) {
            displayProgress(true);
            srlRefresh_FPD.setVisibility(View.GONE);
            srlRefresh_FPD.setRefreshing(false);
        } else {
            displayProgress(false);
            srlRefresh_FPD.setVisibility(View.VISIBLE);
            srlRefresh_FPD.setRefreshing(false);
        }
    }

    @Override
    public void showHistory(boolean enable) {
        if (enable && rvHistory.getVisibility() == View.GONE) {
            animationHelper.forward(nsvContent_FPD.getHeight());
        }
        if (!enable && rvHistory.getVisibility() == View.VISIBLE)
            animationHelper.backward(rvHistory.getHeight());
    }

    @Override
    public void displayHistory(ArrayList<HistoryDH> historyDHs) {
        historyAdapter.setListDH(historyDHs);
    }

    @Override
    public void startUrlIntent(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }

    @AfterInject
    @Override
    public void initPresenter() {
        new PersonDetailsPresenter(this, personsRepository, personID);
    }

    @Override
    public void setPresenter(PersonDetailsContract.PersonDetailsPresenter presenter) {
        this.presenter = presenter;
    }

    private void setEmptyData() {
        displayPersonName(notSpecified);
        displayJobPosition(notSpecified);
        displayEmail(notSpecified);
        displaySkype(notSpecified);
        displayLinkedIn(notSpecified);
        displayFacebook(notSpecified);
        displayPhone(notSpecified);
        displayMobile(notSpecified);
        displayDateOfBirth(notSpecified);
        displayBillingStreet(notSpecified);
        displayBillingCity(notSpecified);
        displayBillingState(notSpecified);
        displayBillingZipcode(notSpecified);
        displayBillingCountry(notSpecified);
        displayShippingFullName(notSpecified);
        displayShippingStreet(notSpecified);
        displayShippingCity(notSpecified);
        displayShippingState(notSpecified);
        displayShippingZipcode(notSpecified);
        displayShippingCountry(notSpecified);
        displaySalesTeam(notSpecified);
        displaySalesPerson(notSpecified);
        displaySalesImplementedBy(notSpecified);
        displaySalesReference(notSpecified);
        displaySalesLanguage(notSpecified);
        displayCompanyName(notSpecified);
        displayCompanyStreet(notSpecified);
        displayCompanyCity(notSpecified);
        displayCompanyState(notSpecified);
        displayCompanyZipcode(notSpecified);
        displayCompanyCountry(notSpecified);
        displayCompanyPhone(notSpecified);
        displayCompanyEmail(notSpecified);
    }
}
