package com.thinkmobiles.easyerp.presentation.screens.hr.employees.details;

import com.thinkmobiles.easyerp.presentation.base.BaseModel;
import com.thinkmobiles.easyerp.presentation.base.BaseView;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentPresenter;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentView;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.AttachmentDH;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.HistoryDH;
import com.thinkmobiles.easyerp.presentation.holders.data.hr.SimpleNoteDH;

import java.util.ArrayList;

/**
 * Created by Lynx on 3/17/2017.
 */

public interface EmployeeDetailsContract {
    interface EmployeeDetailsView extends BaseView<EmployeeDetailsPresenter>, ContentView {
        void showHistory(boolean isShow);

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

        void setImage(String base64Image);
        void setHeaderFullname(String fullname);
        void setHeaderDepartment(String department);
        void setHeaderJobPosition(String jobPosition);

        void setFirstName(String firstName);
        void setLastName(String lastName);
        void setDateOfBirth(String dob);
        void setPersonalMobile(String personalMobile);
        void setPersonalEmail(String personalEmail);
        void setSkype(String skype);
        void setLinkedIn(String linkedIn);
        void setFacebook(String facebook);
        void setJobPosition(String jobPosition);
        void setDepartment(String department);
        void setManager(String manager);
        void setJobType(String jobType);
        void setSource(String source);
        void setWorkEmail(String workEmail);
        void setWorkPhone(String workPhone);

        void setGender(String gender);
        void setEmploymentType(String employmentType);
        void setMaritalStatus(String maritalStatus);
        void setNationality(String nationality);
        void setIdentificationNumber(String identificationNumber);
        void setPassportNumber(String passportNumber);
        void setBankAccountNumber(String bankAccountNumber);
        void setOrderID(String orderID);

        void setStreet(String street);
        void setCity(String city);
        void setState(String state);
        void setZip(String zip);
        void setCountry(String country);

        void setHistory(ArrayList<SimpleNoteDH> simpleNoteDHs);
        void displayAttachments(ArrayList<AttachmentDH> attachmentDHs);
        void startUrlIntent(String url);
    }
    interface EmployeeDetailsPresenter extends ContentPresenter {
        void changeNotesVisibility();
        void startAttachment(int pos);
    }
    interface EmployeeDetailsModel extends BaseModel {

    }
}
