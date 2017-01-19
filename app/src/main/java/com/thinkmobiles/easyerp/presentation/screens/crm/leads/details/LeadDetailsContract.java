package com.thinkmobiles.easyerp.presentation.screens.crm.leads.details;


import android.text.SpannableStringBuilder;

import com.thinkmobiles.easyerp.data.model.crm.leads.details.LeadAttachment;
import com.thinkmobiles.easyerp.data.model.crm.leads.details.LeadDetailWorkflow;
import com.thinkmobiles.easyerp.data.model.crm.leads.details.ResponseGetLeadDetails;
import com.thinkmobiles.easyerp.presentation.base.BaseModel;
import com.thinkmobiles.easyerp.presentation.base.BasePresenter;
import com.thinkmobiles.easyerp.presentation.base.BaseView;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.LeadHistoryDH;

import java.util.ArrayList;

import rx.Observable;

public interface LeadDetailsContract {

    interface LeadDetailsView extends BaseView<LeadDetailsPresenter> {
        void showProgress(boolean enable);
        void showHistory(boolean enable);

        void setNameLead(String nameLead);
        void setCloseDate(String closeDate);
        void setAssignedTo(String assignedTo);
        void setPriority(String priority);
        void setSource(String source);
        void setTags(SpannableStringBuilder tags);
        void setPersonName(String personName);
        void setJobPosition(String jobPosition);
        void setDob(String dob);
        void setEmail(String email);
        void setPhone(String phone);
        void setSkype(String skype);
        void setLinkedIn(String linkedIn);
        void setTvFacebook(String tvFacebook);
        void setCompanyName(String companyName);
        void setCompanyAddress(String companyAddress);
        void setAttachments(ArrayList<LeadAttachment> attachments);
        void setHistory(ArrayList<LeadHistoryDH> history);

        void setWorkflow(ArrayList<LeadDetailWorkflow> workflows);
    }

    interface LeadDetailsPresenter extends BasePresenter {
        void changeNotesVisibility();
        void refresh();
    }

    interface LeadDetailsModel extends BaseModel {
        Observable<ResponseGetLeadDetails> getLeadDetails(String leadId);
    }
}
