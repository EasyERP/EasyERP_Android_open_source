package com.thinkmobiles.easyerp.presentation.screens.hr.applications.details;

import android.text.TextUtils;

import com.thinkmobiles.easyerp.data.model.crm.leads.detail.AttachmentItem;
import com.thinkmobiles.easyerp.data.model.hr.employees.ResponseEmployeeDetails;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentPresenterHelper;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentView;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.AttachmentDH;
import com.thinkmobiles.easyerp.presentation.holders.data.hr.SimpleNoteDH;
import com.thinkmobiles.easyerp.presentation.managers.DateManager;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

/**
 * Created by Lynx on 3/22/2017.
 */

public class ApplicationDetailsPresenter extends ContentPresenterHelper implements ApplicationDetailsContract.ApplicationDetailsPresenter {

    private ApplicationDetailsContract.ApplicationDetailsView view;
    private ApplicationDetailsContract.ApplicationDetailsModel model;
    private String applicationID;

    private ResponseEmployeeDetails currentEApplication;
    private boolean isVisibleHistory;

    public ApplicationDetailsPresenter(ApplicationDetailsContract.ApplicationDetailsView view, ApplicationDetailsContract.ApplicationDetailsModel model, String applicationID) {
        this.view = view;
        this.model = model;
        this.applicationID = applicationID;

        view.setPresenter(this);
    }

    @Override
    protected ContentView getView() {
        return view;
    }

    @Override
    protected boolean hasContent() {
        return currentEApplication != null;
    }

    @Override
    protected void retainInstance() {
        setData(currentEApplication);
    }

    @Override
    public void refresh() {
        compositeSubscription.add(model.getApplicationDetails(applicationID)
        .subscribe(responseEmployeeDetails -> {
            currentEApplication = responseEmployeeDetails;
            view.showProgress(Constants.ProgressType.NONE);
            setData(currentEApplication);
        }, t -> error(t)));
    }

    @Override
    public void changeNotesVisibility() {
        isVisibleHistory = !isVisibleHistory;
        view.showHistory(isVisibleHistory);
    }

    @Override
    public void startAttachment(int pos) {
        String url = String.format("%sdownload/%s", Constants.BASE_URL, currentEApplication.attachments.get(pos).shortPath);
        view.startUrlIntent(url);
    }

    private void setData(ResponseEmployeeDetails data) {
        setHeaderInfo(data);
        setMainInformation(data);
        setPersonalInformation(data);
        setAddress(data);
        setAttachments(data);
        setHistory(data);
    }

    private void setHeaderInfo(ResponseEmployeeDetails info) {
        if(!TextUtils.isEmpty(info.imageSrc)) view.displayEmployeeImage(info.imageSrc);
        view.displayHeaderFullname(info.fullName);
        if(info.department != null && !TextUtils.isEmpty(info.department.name)) {
            view.displayHeaderDepartment(info.department.name);
        } else {
            view.displayHeaderDepartment(null);
        }
        if(info.jobPosition != null && !TextUtils.isEmpty(info.jobPosition.name)) {
            view.displayHeaderJobPosition(info.jobPosition.name);
        } else {
            view.displayHeaderJobPosition(null);
        }
        if(info.social != null && !TextUtils.isEmpty(info.social.linkedIn)) {
            view.enableLinkedInIcon(info.social.linkedIn);
        } else {
            view.enableLinkedInIcon(null);
        }
        if(info.social != null && !TextUtils.isEmpty(info.social.facebook)) {
            view.enableFacebookIcon(info.social.facebook);
        } else {
            view.enableFacebookIcon(null);
        }
        view.enableSkypeIcon(info.skype);
        view.enableEmailIcon(info.personalEmail);
    }

    private void setMainInformation(ResponseEmployeeDetails info) {
        view.displayFirstName(info.name != null && !TextUtils.isEmpty(info.name.first) ? info.name.first : null);
        view.displayLastName(info.name != null && !TextUtils.isEmpty(info.name.last) ? info.name.last : null);
        view.displayExpectedSalary(String.format(Locale.US, "$ %d", info.expectedSalary));
        view.displayProposedSalary(String.format(Locale.US, "$ %d", info.proposedSalary));
        if(!TextUtils.isEmpty(info.dateBirth)) {
            view.displayDateOfBirth(DateManager.convert(info.dateBirth)
                    .setDstPattern(DateManager.PATTERN_DATE_SIMPLE_PREVIEW)
                    .toString());
        } else {
            view.displayDateOfBirth(null);
        }
        if(!TextUtils.isEmpty(info.nextAction)) {
            view.displayNextActionDate(DateManager.convert(info.nextAction)
                    .setDstPattern(DateManager.PATTERN_DATE_SIMPLE_PREVIEW)
                    .toString());
        } else {
            view.displayNextActionDate(null);
        }
        view.displayPersonalMobile(info.workPhones != null && !TextUtils.isEmpty(info.workPhones.mobile) ? info.workPhones.mobile : null);
        view.displayPersonalEmail(!TextUtils.isEmpty(info.personalEmail) ? info.personalEmail : null);
        view.displaySkype(!TextUtils.isEmpty(info.skype) ? info.skype : null);
        view.displayLinkedIn(info.social != null && !TextUtils.isEmpty(info.social.linkedIn) ? info.social.linkedIn : null);
        view.displayFacebook(info.social != null && !TextUtils.isEmpty(info.social.facebook) ? info.social.facebook : null);
        view.displayJobPosition(info.jobPosition != null && !TextUtils.isEmpty(info.jobPosition.name) ? info.jobPosition.name : null);
        view.displayDepartment(info.department != null && !TextUtils.isEmpty(info.department.name) ? info.department.name : null);
        view.displayManager(info.manager != null && !TextUtils.isEmpty(info.manager.fullName) ? info.manager.fullName : null);
        view.displayJobType(!TextUtils.isEmpty(info.jobType) ? info.jobType : null);
        view.displaySource(!TextUtils.isEmpty(info.source) ? info.source : null);
        view.displayWorkEmail(!TextUtils.isEmpty(info.workEmail) ? info.workEmail : null);
        view.displayWorkPhone(info.workPhones != null && !TextUtils.isEmpty(info.workPhones.phone) ? info.workPhones.phone : null);

        if(info.workPhones != null && !TextUtils.isEmpty(info.workPhones.mobile))
            view.enablePersonalMobileActionClick(info.workPhones.mobile);
        else
            view.enablePersonalMobileActionClick(null);
        if(info.workPhones != null && !TextUtils.isEmpty(info.workPhones.mobile))
            view.enableWorkPhoneActionClick(info.workPhones.mobile);
        else
            view.enableWorkPhoneActionClick(null);
        if(!TextUtils.isEmpty(info.personalEmail))
            view.enablePersonalEmailActionClick(info.personalEmail);
        else
            view.enablePersonalEmailActionClick(null);
        if(!TextUtils.isEmpty(info.workEmail))
            view.enableWorkEmailActionClick(info.workEmail);
        else
            view.enableWorkEmailActionClick(null);
        if(!TextUtils.isEmpty(info.source))
            view.enableSourceActionClick(info.source);
        else
            view.enableSourceActionClick(null);

        view.displayWeeklyScheduler(info.weeklyScheduler != null && !TextUtils.isEmpty(info.weeklyScheduler.name) ? info.weeklyScheduler.name : null);
        view.displayContract(!TextUtils.isEmpty(info.contract) ? info.contract : null);
        view.displayScheduledPay(info.scheduledPay != null && !TextUtils.isEmpty(info.scheduledPay.name) ? info.scheduledPay.name : null);
        view.displayStage(info.workflow != null && !TextUtils.isEmpty(info.workflow.name) ? info.workflow.name : null);
    }

    private void setPersonalInformation(ResponseEmployeeDetails info) {
        boolean isDataAvailable = false;
        if(!TextUtils.isEmpty(info.gender)) {
            view.displayGender(info.gender);
            isDataAvailable = true;
        } else {
            view.displayGender(null);
        }
        if(!TextUtils.isEmpty(info.employmentType)) {
            view.displayEmploymentType(info.employmentType);
            isDataAvailable = true;
        } else {
            view.displayEmploymentType(null);
        }
        if(!TextUtils.isEmpty(info.marital)) {
            view.displayMaritalStatus(info.marital);
            isDataAvailable = true;
        } else {
            view.displayMaritalStatus(null);
        }
        if(!TextUtils.isEmpty(info.nationality)) {
            view.displayNationality(info.nationality);
            isDataAvailable = true;
        } else {
            view.displayNationality(null);
        }
        if(!TextUtils.isEmpty(info.identNo)) {
            view.displayIdentificationNumber(info.identNo);
            isDataAvailable = true;
        } else {
            view.displayIdentificationNumber(null);
        }
        if(!TextUtils.isEmpty(info.passportNo)) {
            view.displayPassportNumber(info.passportNo);
            isDataAvailable = true;
        } else {
            view.displayPassportNumber(null);
        }
        if(!TextUtils.isEmpty(info.bankAccountNo)) {
            view.displayBankAccountNumber(info.bankAccountNo);
            isDataAvailable = true;
        } else {
            view.displayBankAccountNumber(null);
        }
        if(!TextUtils.isEmpty(info.otherId)) {
            view.displayOtherID(info.otherId);
            isDataAvailable = true;
        } else {
            view.displayOtherID(null);
        }
        view.showPersonalInformation(isDataAvailable);
    }

    private void setAddress(ResponseEmployeeDetails info) {
        boolean isDataAvailable = false;
        if(info.homeAddress != null) {
            if(!TextUtils.isEmpty(info.homeAddress.street)) {
                view.displayStreet(info.homeAddress.street);
                isDataAvailable = true;
            } else {
                view.displayStreet(null);
            }
            if(!TextUtils.isEmpty(info.homeAddress.city)) {
                view.displayCity(info.homeAddress.city);
                isDataAvailable = true;
            } else {
                view.displayCity(null);
            }
            if(!TextUtils.isEmpty(info.homeAddress.state)) {
                view.displayState(info.homeAddress.state);
                isDataAvailable = true;
            } else {
                view.displayState(null);
            }
            if(!TextUtils.isEmpty(info.homeAddress.zip)) {
                view.displayZip(info.homeAddress.zip);
                isDataAvailable = true;
            } else {
                view.displayZip(null);
            }
            if(!TextUtils.isEmpty(info.homeAddress.country)) {
                view.displayCountry(info.homeAddress.country);
                isDataAvailable = true;
            } else {
                view.displayCountry(null);
            }
        } else {
            isDataAvailable = false;
            view.displayStreet(null);
            view.displayCity(null);
            view.displayState(null);
            view.displayZip(null);
            view.displayCountry(null);
        }
        view.showAddress(isDataAvailable);
    }

    private void setAttachments(ResponseEmployeeDetails info) {
        boolean isAttachmentsAvailable = false;
        if(info.attachments != null && !info.attachments.isEmpty()) {
            ArrayList<AttachmentDH> result = new ArrayList<>();
            for(AttachmentItem item : info.attachments) result.add(new AttachmentDH(item));
            view.displayAttachments(result);
            isAttachmentsAvailable = true;
        }
        view.showAttachments(isAttachmentsAvailable);
    }

    private void setHistory(ResponseEmployeeDetails info) {
        Collections.reverse(info.notes);
        view.displayHistory(SimpleNoteDH.convert(info.notes));
        view.showHistory(isVisibleHistory);
    }
}
