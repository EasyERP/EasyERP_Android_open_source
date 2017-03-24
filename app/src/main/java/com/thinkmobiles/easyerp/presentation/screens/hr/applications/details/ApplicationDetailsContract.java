package com.thinkmobiles.easyerp.presentation.screens.hr.applications.details;

import com.thinkmobiles.easyerp.data.model.hr.employees.ResponseEmployeeDetails;
import com.thinkmobiles.easyerp.presentation.base.BaseModel;
import com.thinkmobiles.easyerp.presentation.base.BaseView;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentPresenter;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentView;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.AttachmentDH;
import com.thinkmobiles.easyerp.presentation.holders.data.hr.SimpleNoteDH;

import java.util.ArrayList;

import rx.Observable;

/**
 * Created by Lynx on 3/22/2017.
 */

public interface ApplicationDetailsContract {
    interface ApplicationDetailsView extends BaseView<ApplicationDetailsPresenter>, ContentView {
        void showHistory(boolean isShow);

        void showPersonalInformation(boolean isShown);
        void showAddress(boolean isShown);
        void showAttachments(boolean isShown);

        void enableSkypeIcon(final String uriPath);
        void enableLinkedInIcon(final String uriPath);
        void enableFacebookIcon(final String uriPath);
        void enableEmailIcon(final String email);

        void enablePersonalMobileActionClick(final String phone);
        void enableWorkPhoneActionClick(final String phone);
        void enablePersonalEmailActionClick(final String email);
        void enableWorkEmailActionClick(final String email);
        void enableSourceActionClick(final String url);

        void displayEmployeeImage(String base64Image);
        void displayHeaderFullname(String fullname);
        void displayHeaderDepartment(String department);
        void displayHeaderJobPosition(String jobPosition);

        void displayFirstName(String firstName);
        void displayLastName(String lastName);
        void displayExpectedSalary(String expectedSalary);
        void displayProposedSalary(String proposedSalary);
        void displayDateOfBirth(String dob);
        void displayPersonalMobile(String personalMobile);
        void displayPersonalEmail(String personalEmail);
        void displaySkype(String skype);
        void displayLinkedIn(String linkedIn);
        void displayFacebook(String facebook);
        void displayJobPosition(String jobPosition);
        void displayDepartment(String department);
        void displayManager(String manager);
        void displayJobType(String jobType);
        void displaySource(String source);
        void displayNextActionDate(String nextActionDate);
        void displayWorkEmail(String workEmail);
        void displayWorkPhone(String workPhone);
        void displayWeeklyScheduler(String weeklyScheduler);
        void displayContract(String contract);
        void displayScheduledPay(String scheduledPay);
        void displayStage(String stage);

        void displayGender(String gender);
        void displayEmploymentType(String employmentType);
        void displayMaritalStatus(String maritalStatus);
        void displayNationality(String nationality);
        void displayIdentificationNumber(String identificationNumber);
        void displayPassportNumber(String passportNumber);
        void displayBankAccountNumber(String bankAccountNumber);
        void displayOtherID(String orderID);

        void displayStreet(String street);
        void displayCity(String city);
        void displayState(String state);
        void displayZip(String zip);
        void displayCountry(String country);

        void displayHistory(ArrayList<SimpleNoteDH> simpleNoteDHs);
        void displayAttachments(ArrayList<AttachmentDH> attachmentDHs);
        void startUrlIntent(String url);
    }
    interface ApplicationDetailsPresenter extends ContentPresenter {
        void changeNotesVisibility();
        void startAttachment(int pos);
    }
    interface ApplicationDetailsModel extends BaseModel {
        Observable<ResponseEmployeeDetails> getApplicationDetails(String id);
    }
}
