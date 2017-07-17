package com.thinkmobiles.easyerp.presentation.screens.hr.employees.details;

import com.thinkmobiles.easyerp.data.model.hr.employees.ResponseEmployeeDetails;
import com.thinkmobiles.easyerp.presentation.base.BaseModel;
import com.thinkmobiles.easyerp.presentation.base.BaseView;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentPresenter;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentView;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.AttachmentDH;
import com.thinkmobiles.easyerp.presentation.holders.data.hr.EmployeeRowTransferDH;
import com.thinkmobiles.easyerp.presentation.holders.data.hr.SimpleNoteDH;

import java.util.ArrayList;

import rx.Observable;

/**
 * Created by Lynx on 3/17/2017.
 */

public interface EmployeeDetailsContract {
    interface EmployeeDetailsView extends BaseView<EmployeeDetailsPresenter>, ContentView {
        void showHistory(boolean isShow);
        void hideHistory(boolean hide);

        void showPersonalInformation(boolean isShown);
        void showAddress(boolean isShown);
        void showJobPositionList(boolean isShown);
        void showEmploymentDetailsList(boolean isShown);
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
        void displayWorkEmail(String workEmail);
        void displayWorkPhone(String workPhone);

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

        void displayJobPositionList(ArrayList<EmployeeRowTransferDH> employeeRowTransferDHs);
        void displayEmploymentDetailsList(ArrayList<EmployeeRowTransferDH> employeeRowTransferDHs);

        void displayHistory(ArrayList<SimpleNoteDH> simpleNoteDHs);
        void displayAttachments(ArrayList<AttachmentDH> attachmentDHs);
        void startUrlIntent(String url);
    }
    interface EmployeeDetailsPresenter extends ContentPresenter {
        void changeNotesVisibility();
        void startAttachment(int pos);
    }
    interface EmployeeDetailsModel extends BaseModel {
        Observable<ResponseEmployeeDetails> getEmployeeDetails(String id);
    }
}
