package com.thinkmobiles.easyerp.presentation.screens.hr.applications.details;

import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.domain.hr.ApplicationRepository;
import com.thinkmobiles.easyerp.presentation.adapters.crm.AttachmentAdapter;
import com.thinkmobiles.easyerp.presentation.adapters.hr.SimpleNotesAdapter;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentFragment;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentPresenter;
import com.thinkmobiles.easyerp.presentation.custom.transformations.CropCircleTransformation;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.AttachmentDH;
import com.thinkmobiles.easyerp.presentation.holders.data.hr.SimpleNoteDH;
import com.thinkmobiles.easyerp.presentation.managers.GoogleAnalyticHelper;
import com.thinkmobiles.easyerp.presentation.managers.HistoryAnimationHelper;
import com.thinkmobiles.easyerp.presentation.managers.ImageHelper;
import com.thinkmobiles.easyerp.presentation.utils.Constants;
import com.thinkmobiles.easyerp.presentation.utils.IntentActionHelper;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Created by Lynx on 3/22/2017.
 */

@EFragment
public class ApplicationDetailsFragment extends ContentFragment implements ApplicationDetailsContract.ApplicationDetailsView {

    private ApplicationDetailsContract.ApplicationDetailsPresenter presenter;

    @FragmentArg
    protected String applicationID;

    //region View inject
    @ViewById
    protected NestedScrollView nsvContent_FAD;
    @ViewById
    protected ImageView ivApplicationAvatar_FAD;
    @ViewById
    protected TextView tvApplicationName_FAD;
    @ViewById
    protected TextView tvHeaderDepartment_FAD;
    @ViewById
    protected TextView tvHeaderJobPosition_FAD;
    @ViewById
    protected ImageView ivFacebook_FAD;
    @ViewById
    protected ImageView ivLinkedIn_FAD;
    @ViewById
    protected ImageView ivSkype_FAD;
    @ViewById
    protected ImageView ivEmail_FAD;
    @ViewById
    protected EditText etFirstName_FAD;
    @ViewById
    protected EditText etLastName_FAD;
    @ViewById
    protected EditText etExpectedSalary_FAD;
    @ViewById
    protected EditText etProposedSalary_FAD;
    @ViewById
    protected EditText etDob_FAD;
    @ViewById
    protected EditText etPersonalMobile_FAD;
    @ViewById
    protected EditText etPersonalEmail_FAD;
    @ViewById
    protected EditText etSkype_FAD;
    @ViewById
    protected EditText etLinkedIn_FAD;
    @ViewById
    protected EditText etFacebook_FAD;
    @ViewById
    protected EditText etWorkEmail_FAD;
    @ViewById
    protected EditText etWorkPhone_FAD;
    @ViewById
    protected EditText etSource_FAD;
    @ViewById
    protected EditText etNextActionDay_FAD;
    @ViewById
    protected EditText etJobPosition_FAD;
    @ViewById
    protected EditText etDepartment_FAD;
    @ViewById
    protected EditText etManager_FAD;
    @ViewById
    protected EditText etJobType_FAD;
    @ViewById
    protected EditText etWeeklySchedule_FAD;
    @ViewById
    protected EditText etContract_FAD;
    @ViewById
    protected EditText etScheduledPay_FAD;
    @ViewById
    protected EditText etStage_FAD;

    @ViewById
    protected EditText etGender_FAD;
    @ViewById
    protected EditText etEmploymentType_FAD;
    @ViewById
    protected EditText etMaritalStatus_FAD;
    @ViewById
    protected EditText etNationality_FAD;
    @ViewById
    protected EditText etIdentificationNumber_FAD;
    @ViewById
    protected EditText etPassportNumber_FAD;
    @ViewById
    protected EditText etBankAccountNumber_FAD;
    @ViewById
    protected EditText etOtherID_FAD;

    @ViewById
    protected EditText etStreet_FPD;
    @ViewById
    protected EditText etCity_FPD;
    @ViewById
    protected EditText etState_FPD;
    @ViewById
    protected EditText etZipcode_FPD;
    @ViewById
    protected EditText etCountry_FPD;
    @ViewById
    protected FrameLayout btnHistory;
    @ViewById
    protected ImageView ivIconArrow;

    @ViewById
    protected RecyclerView rvAttachments_FAD;
    @ViewById
    protected RecyclerView rvHistory;

    @ViewById
    protected LinearLayout llContainerPersonalInformation_FAD;
    @ViewById
    protected LinearLayout llAddressContainer_FAD;

    @ViewById
    protected TextView tvEmptyPersonalInformation_FAD;
    @ViewById
    protected TextView tvEmptyAddress_FAD;
    @ViewById
    protected TextView tvEmptyAttachments_FAD;

    //endregion

    @Bean
    protected ApplicationRepository applicationRepository;
    @Bean
    protected AttachmentAdapter attachmentAdapter;
    @Bean
    protected SimpleNotesAdapter simpleNotesAdapter;
    @Bean
    protected HistoryAnimationHelper animationHelper;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_application_details;
    }

    @Override
    protected ContentPresenter getPresenter() {
        return presenter;
    }

    @AfterInject
    @Override
    public void initPresenter() {
        new ApplicationDetailsPresenter(this, applicationRepository, applicationID);
    }

    @AfterViews
    protected void initUI() {

    }

    @Override
    public void setPresenter(ApplicationDetailsContract.ApplicationDetailsPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public String getScreenName() {
        return "Application Details Screen";
    }

    @Override
    public void showHistory(boolean isShow) {
        if (isShow) {
            GoogleAnalyticHelper.trackClick(this, GoogleAnalyticHelper.EventType.CLICK_BUTTON, "History");
            animationHelper.open();
        } else {
            animationHelper.close();
        }
    }

    @Override
    public void showPersonalInformation(boolean isShown) {
        llContainerPersonalInformation_FAD.setVisibility(isShown ? View.VISIBLE : View.GONE);
        tvEmptyPersonalInformation_FAD.setVisibility(isShown ? View.GONE : View.VISIBLE);
    }

    @Override
    public void showAddress(boolean isShown) {
        llAddressContainer_FAD.setVisibility(isShown ? View.VISIBLE : View.GONE);
        tvEmptyAddress_FAD.setVisibility(isShown ? View.GONE : View.VISIBLE);
    }

    @Override
    public void showAttachments(boolean isShown) {
        tvEmptyAttachments_FAD.setVisibility(isShown ? View.GONE : View.VISIBLE);
    }

    @Override
    public void enableSkypeIcon(String uriPath) {
        ivSkype_FAD.setVisibility(TextUtils.isEmpty(uriPath) ? View.GONE : View.VISIBLE);
        RxView.clicks(ivSkype_FAD)
                .throttleFirst(Constants.DELAY_CLICK, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> {
                    GoogleAnalyticHelper.trackClick(this, GoogleAnalyticHelper.EventType.CLICK_SOCIAL_BUTTON, "Skype");
                    IntentActionHelper.callViewIntent(mActivity, uriPath, null);
                });
    }

    @Override
    public void enableLinkedInIcon(String uriPath) {
        ivLinkedIn_FAD.setVisibility(TextUtils.isEmpty(uriPath) ? View.GONE : View.VISIBLE);
        RxView.clicks(ivLinkedIn_FAD)
                .throttleFirst(Constants.DELAY_CLICK, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> {
                    GoogleAnalyticHelper.trackClick(this, GoogleAnalyticHelper.EventType.CLICK_SOCIAL_BUTTON, "Linked In");
                    IntentActionHelper.callViewIntent(mActivity, uriPath, null);
                });
    }

    @Override
    public void enableFacebookIcon(String uriPath) {
        ivFacebook_FAD.setVisibility(TextUtils.isEmpty(uriPath) ? View.GONE : View.VISIBLE);
        RxView.clicks(ivFacebook_FAD)
                .throttleFirst(Constants.DELAY_CLICK, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> {
                    GoogleAnalyticHelper.trackClick(this, GoogleAnalyticHelper.EventType.CLICK_SOCIAL_BUTTON, "Facebook");
                    IntentActionHelper.callViewIntent(mActivity, uriPath, null);
                });
    }

    @Override
    public void enableEmailIcon(String email) {
        ivEmail_FAD.setVisibility(TextUtils.isEmpty(email) ? View.GONE : View.VISIBLE);
        RxView.clicks(ivEmail_FAD)
                .throttleFirst(Constants.DELAY_CLICK, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> {
                    GoogleAnalyticHelper.trackClick(this, GoogleAnalyticHelper.EventType.CLICK_SOCIAL_BUTTON, "Email");
                    IntentActionHelper.callSendEmailIntent(mActivity, email, null);
                });
    }

    @Override
    public void enablePersonalMobileActionClick(String phone) {
        RxView.clicks(etPersonalMobile_FAD)
                .throttleFirst(Constants.DELAY_CLICK, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> {
                    if (phone != null) {
                        GoogleAnalyticHelper.trackClick(this, GoogleAnalyticHelper.EventType.CLICK_URL, "Personal Mobile");
                        IntentActionHelper.callDialIntent(mActivity, phone);
                    }
                });
    }

    @Override
    public void enableWorkPhoneActionClick(String phone) {
        RxView.clicks(etWorkPhone_FAD)
                .throttleFirst(Constants.DELAY_CLICK, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> {
                    if (phone != null) {
                        GoogleAnalyticHelper.trackClick(this, GoogleAnalyticHelper.EventType.CLICK_URL, "Work Phone");
                        IntentActionHelper.callDialIntent(mActivity, phone);
                    }
                });
    }

    @Override
    public void enablePersonalEmailActionClick(String email) {
        RxView.clicks(etPersonalEmail_FAD)
                .throttleFirst(Constants.DELAY_CLICK, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> {
                    if (email != null) {
                        GoogleAnalyticHelper.trackClick(this, GoogleAnalyticHelper.EventType.CLICK_URL, "Personal Email");
                        IntentActionHelper.callSendEmailIntent(mActivity, email, null);
                    }
                });
    }

    @Override
    public void enableWorkEmailActionClick(String email) {
        RxView.clicks(etWorkEmail_FAD)
                .throttleFirst(Constants.DELAY_CLICK, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> {
                    if (email != null) {
                        GoogleAnalyticHelper.trackClick(this, GoogleAnalyticHelper.EventType.CLICK_URL, "Work Email");
                        IntentActionHelper.callSendEmailIntent(mActivity, email, null);
                    }
                });
    }

    @Override
    public void enableSourceActionClick(String url) {
        RxView.clicks(etSource_FAD)
                .throttleFirst(Constants.DELAY_CLICK, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> {
                    if (url != null) {
                        GoogleAnalyticHelper.trackClick(this, GoogleAnalyticHelper.EventType.CLICK_URL, "");
                        if (!url.startsWith("http:")) {
                            startUrlIntent("http:" + url);
                        } else
                            startUrlIntent(url);
                    }
                });
    }

    @Override
    public void displayEmployeeImage(String base64Image) {
        if (!TextUtils.isEmpty(base64Image)) {
            ImageHelper.getBitmapFromBase64(base64Image, new CropCircleTransformation())
                    .subscribe(ivApplicationAvatar_FAD::setImageBitmap);
        }
    }

    @Override
    public void displayHeaderFullname(String fullname) {
        tvApplicationName_FAD.setText(fullname);
    }

    @Override
    public void displayHeaderDepartment(String department) {
        tvHeaderDepartment_FAD.setText(department);
    }

    @Override
    public void displayHeaderJobPosition(String jobPosition) {
        tvHeaderJobPosition_FAD.setText(jobPosition);
    }

    @Override
    public void displayFirstName(String firstName) {
        etFirstName_FAD.setText(firstName);
    }

    @Override
    public void displayLastName(String lastName) {
        etLastName_FAD.setText(lastName);
    }

    @Override
    public void displayExpectedSalary(String expectedSalary) {
        etExpectedSalary_FAD.setText(expectedSalary);
    }

    @Override
    public void displayProposedSalary(String proposedSalary) {
        etProposedSalary_FAD.setText(proposedSalary);
    }

    @Override
    public void displayDateOfBirth(String dob) {
        etDob_FAD.setText(dob);
    }

    @Override
    public void displayPersonalMobile(String personalMobile) {
        etPersonalMobile_FAD.setText(personalMobile);
    }

    @Override
    public void displayPersonalEmail(String personalEmail) {
        etPersonalEmail_FAD.setText(personalEmail);
    }

    @Override
    public void displaySkype(String skype) {
        etSkype_FAD.setText(skype);
    }

    @Override
    public void displayLinkedIn(String linkedIn) {
        etLinkedIn_FAD.setText(linkedIn);
    }

    @Override
    public void displayFacebook(String facebook) {
        etFacebook_FAD.setText(facebook);
    }

    @Override
    public void displayJobPosition(String jobPosition) {
        etJobPosition_FAD.setText(jobPosition);
    }

    @Override
    public void displayDepartment(String department) {
        etDepartment_FAD.setText(department);
    }

    @Override
    public void displayManager(String manager) {
        etManager_FAD.setText(manager);
    }

    @Override
    public void displayJobType(String jobType) {
        etJobType_FAD.setText(jobType);
    }

    @Override
    public void displaySource(String source) {
        etSource_FAD.setText(source);
    }

    @Override
    public void displayNextActionDate(String nextActionDate) {
        etNextActionDay_FAD.setText(nextActionDate);
    }

    @Override
    public void displayWorkEmail(String workEmail) {
        etWorkEmail_FAD.setText(workEmail);
    }

    @Override
    public void displayWorkPhone(String workPhone) {
        etWorkPhone_FAD.setText(workPhone);
    }

    @Override
    public void displayWeeklyScheduler(String weeklyScheduler) {
        etWeeklySchedule_FAD.setText(weeklyScheduler);
    }

    @Override
    public void displayContract(String contract) {
        etContract_FAD.setText(contract);
    }

    @Override
    public void displayScheduledPay(String scheduledPay) {
        etScheduledPay_FAD.setText(scheduledPay);
    }

    @Override
    public void displayStage(String stage) {
        etStage_FAD.setText(stage);
    }

    @Override
    public void displayGender(String gender) {
        etGender_FAD.setText(gender);
    }

    @Override
    public void displayEmploymentType(String employmentType) {
        etEmploymentType_FAD.setText(employmentType);
    }

    @Override
    public void displayMaritalStatus(String maritalStatus) {
        etMaritalStatus_FAD.setText(maritalStatus);
    }

    @Override
    public void displayNationality(String nationality) {
        etNationality_FAD.setText(nationality);
    }

    @Override
    public void displayIdentificationNumber(String identificationNumber) {
        etIdentificationNumber_FAD.setText(identificationNumber);
    }

    @Override
    public void displayPassportNumber(String passportNumber) {
        etPassportNumber_FAD.setText(passportNumber);
    }

    @Override
    public void displayBankAccountNumber(String bankAccountNumber) {
        etBankAccountNumber_FAD.setText(bankAccountNumber);
    }

    @Override
    public void displayOtherID(String otherID) {
        etOtherID_FAD.setText(otherID);
    }

    @Override
    public void displayStreet(String street) {
        etStreet_FPD.setText(street);
    }

    @Override
    public void displayCity(String city) {
        etCity_FPD.setText(city);
    }

    @Override
    public void displayState(String state) {
        etState_FPD.setText(state);
    }

    @Override
    public void displayZip(String zip) {
        etZipcode_FPD.setText(zip);
    }

    @Override
    public void displayCountry(String country) {
        etCountry_FPD.setText(country);
    }

    @Override
    public void displayHistory(ArrayList<SimpleNoteDH> simpleNoteDHs) {
        simpleNotesAdapter.setListDH(simpleNoteDHs);
    }

    @Override
    public void displayAttachments(ArrayList<AttachmentDH> attachmentDHs) {
        attachmentAdapter.setListDH(attachmentDHs);
    }

    @Override
    public void startUrlIntent(String url) {
        IntentActionHelper.callViewIntent(mActivity, url, null);
    }
}
