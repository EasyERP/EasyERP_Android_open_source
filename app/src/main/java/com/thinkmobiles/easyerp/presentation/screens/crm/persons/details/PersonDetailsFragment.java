package com.thinkmobiles.easyerp.presentation.screens.crm.persons.details;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding.view.RxView;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.domain.crm.PersonsRepository;
import com.thinkmobiles.easyerp.presentation.adapters.crm.LeadHistoryAdapter;
import com.thinkmobiles.easyerp.presentation.adapters.crm.OpportunityAdapter;
import com.thinkmobiles.easyerp.presentation.base.BaseFragment;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.HistoryDH;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.OpportunityDH;
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
 * Created by Lynx on 1/24/2017.
 */

@EFragment(R.layout.fragment_person_details)
public class PersonDetailsFragment extends BaseFragment<HomeActivity> implements PersonDetailsContract.PersonDetailsView {

    private PersonDetailsContract.PersonDetailsPresenter presenter;

    @FragmentArg
    protected String personID;

    //region View Inject
    @ViewById
    protected SwipeRefreshLayout srlRefresh_FPD;
    @ViewById
    protected NestedScrollView nsvContent_FPD;
    @ViewById
    protected TextView tvAboutName_FPD;
    @ViewById
    protected ImageView ivPersonAvatar_FPD;
    @ViewById
    protected EditText etFirstName_FPD;
    @ViewById
    protected EditText etLastName_FPD;
    @ViewById
    protected EditText etJobPosition_FPD;
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
    protected TextView tvCompanyTitleName_FPD;
    @ViewById
    protected TextView tvCompanyTitleUrl_FPD;
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
    protected RecyclerView rvOpportunities_FPD;
    @ViewById
    protected LinearLayout llAttachmentsContainer_FPD;
    @ViewById
    protected TextView tvAttachments_FPD;
    @ViewById
    protected RelativeLayout btnHistory_FPD;
    @ViewById
    protected ImageView ivIconArrow_FPD;
    @ViewById
    protected RecyclerView rvHistory_FPD;
    //endregion

    @DrawableRes(R.drawable.ic_arrow_up)
    protected Drawable icArrowUp;
    @DrawableRes(R.drawable.ic_arrow_down)
    protected Drawable icArrowDown;

    @Bean
    protected PersonsRepository personsRepository;
    @Bean
    protected LeadHistoryAdapter historyAdapter;
    @Bean
    protected OpportunityAdapter opportunityAdapter;

    @AfterViews
    protected void initUI() {
        srlRefresh_FPD.setOnRefreshListener(() -> presenter.refresh());
        rvHistory_FPD.setLayoutManager(new LinearLayoutManager(getContext()));
        rvHistory_FPD.setAdapter(historyAdapter);
        rvOpportunities_FPD.setLayoutManager(new LinearLayoutManager(getContext()));
        rvOpportunities_FPD.setAdapter(opportunityAdapter);

        RxView.clicks(btnHistory_FPD)
                .throttleFirst(Constants.DELAY_CLICK, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> presenter.changeNotesVisibility());

        presenter.subscribe();
    }

    @Override
    protected boolean needProgress() {
        return true;
    }

    @Override
    public void displayPersonAvatar(String base64Avatar) {
        ImageHelper.getBitmapFromBase64(base64Avatar)
                .subscribe(ivPersonAvatar_FPD::setImageBitmap);
    }

    @Override
    public void displayFirstName(String firstName) {
        etFirstName_FPD.setText(firstName);
    }

    @Override
    public void displayLastName(String lastName) {
        etLastName_FPD.setText(lastName);
    }

    @Override
    public void displayJobPosition(String jobPosition) {
        etJobPosition_FPD.setText(jobPosition);
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
    }

    @Override
    public void displayPersonAboutName(String name) {
        tvAboutName_FPD.setText(name);
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
    }

    @Override
    public void displayCompanyImage(String base64CompanyImage) {
        ImageHelper.getBitmapFromBase64(base64CompanyImage)
                .subscribe(ivCompanyImage_FPD::setImageBitmap);
    }

    @Override
    public void displayCompanyNameTitle(String companyName) {
        tvCompanyTitleName_FPD.setText(companyName);
    }

    @Override
    public void displayCompanyUrl(String companyUrl) {
        tvCompanyTitleUrl_FPD.setMovementMethod(LinkMovementMethod.getInstance());
        tvCompanyTitleUrl_FPD.setText(Html.fromHtml(companyUrl));
    }

    @Override
    public void displayCompanyName(String companyName) {
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
    public void displayOpportunities(ArrayList<OpportunityDH> opportunityDHs) {
        opportunityAdapter.setListDH(opportunityDHs);
    }

    @Override
    public void displayAttachments(String attachments) {
        tvAttachments_FPD.setMovementMethod(LinkMovementMethod.getInstance());
        tvAttachments_FPD.setText(Html.fromHtml(attachments));
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
    public void showHistory(boolean isShow) {
        if (isShow) {
            rvHistory_FPD.setVisibility(View.VISIBLE);
            nsvContent_FPD.setVisibility(View.GONE);
            ivIconArrow_FPD.setImageDrawable(icArrowDown);
        } else {
            nsvContent_FPD.setVisibility(View.VISIBLE);
            rvHistory_FPD.setVisibility(View.GONE);
            ivIconArrow_FPD.setImageDrawable(icArrowUp);
        }
    }

    @Override
    public void displayHistory(ArrayList<HistoryDH> historyDHs) {
        historyAdapter.setListDH(historyDHs);
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
}
