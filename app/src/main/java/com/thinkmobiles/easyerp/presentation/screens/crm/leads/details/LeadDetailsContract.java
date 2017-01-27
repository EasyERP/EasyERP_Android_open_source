package com.thinkmobiles.easyerp.presentation.screens.crm.leads.details;


import android.text.SpannableStringBuilder;

import com.thinkmobiles.easyerp.data.model.crm.leads.detail.LeadDetailWorkflow;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.ResponseGetLeadDetails;
import com.thinkmobiles.easyerp.presentation.base.BaseModel;
import com.thinkmobiles.easyerp.presentation.base.BasePresenter;
import com.thinkmobiles.easyerp.presentation.base.BaseView;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.HistoryDH;

import java.util.ArrayList;

import rx.Observable;

public interface LeadDetailsContract {

    interface LeadDetailsView extends BaseView<LeadDetailsPresenter> {
        void showProgress(boolean enable);
        void showHistory(boolean enable);

        void setLeadName(String leadName);
        void setCloseDate(String closeDate);
        void setAssignedTo(String assignedTo);
        void setPriority(String priority);
        void setSource(String source);
        void setTags(SpannableStringBuilder tags);
        void setPersonName(String personName);
        void setFirstName(String firstName);
        void setLastName(String lastName);
        void setJobPosition(String jobPosition);
        void setDob(String dob);
        void setEmail(String email);
        void setPhone(String phone);
        void setSkype(String skype);
        void setLinkedIn(String linkedIn);
        void setTvFacebook(String tvFacebook);
        void setCompanyName(String companyName);
        void setCompanyStreet(String companyStreet);
        void setCompanyCity(String companyCity);
        void setCompanyState(String companyState);
        void setCompanyZipcode(String companyZipcode);
        void setCompanyCountry(String companyCountry);
        void setAttachments(String attachments);
        void setHistory(ArrayList<HistoryDH> history);
    }

    interface LeadDetailsPresenter extends BasePresenter {
        void changeNotesVisibility();
        void refresh();
    }

    interface LeadDetailsModel extends BaseModel {
        Observable<ResponseGetLeadDetails> getLeadDetails(String leadId);
    }
}
