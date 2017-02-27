package com.thinkmobiles.easyerp.presentation.screens.crm.opportunities.details;

import com.thinkmobiles.easyerp.data.model.crm.leads.TagItem;
import com.thinkmobiles.easyerp.data.model.crm.opportunities.detail.ResponseGetOpportunityDetails;
import com.thinkmobiles.easyerp.presentation.base.BaseModel;
import com.thinkmobiles.easyerp.presentation.base.BaseView;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentPresenter;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentView;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.AttachmentDH;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.ContactDH;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.HistoryDH;

import java.util.ArrayList;

import rx.Observable;

/**
 * Created by Lynx on 2/1/2017.
 */

public interface OpportunityDetailsContract {
    interface OpportunityDetailsView extends BaseView<OpportunityDetailsPresenter>, ContentView {
        void showHistory(boolean enable);

        void showContact(boolean isShown);
        void showCompany(boolean isShown);
        void showAttachments(boolean isShown);

        void displayOpportunityName(String opportunityName);
        void displayStatus(String opportunityStatus);
        void displayRevenue(String revenue);
        void displayCloseDate(String closeDate);
        void displayAssignedTo(String assignedTo);
        void displayContacts(ArrayList<ContactDH> contactDHs);

        void displayCompanyImage(String companyImageBase64);
        void displayCompanyUrl(String companyUrl);
        void displayCompanyName(String companyName);
        void displayCompanyStreet(String companyStreet);
        void displayCompanyCity(String companyCity);
        void displayCompanyState(String companyState);
        void displayCompanyZip(String companyZip);
        void displayCompanyCountry(String companyCountry);
        void displayCompanyPhone(String companyPhone);
        void displayCompanyEmail(String companyEmail);

        void enableCompanyEmailActionClick(final String email);
        void enableCompanyPhoneActionClick(final String phone);

        void setTags(ArrayList<TagItem> tags);
        void displayAttachments(ArrayList<AttachmentDH> attachmentDHs);
        void startUrlIntent(String url);

        void displayHistory(ArrayList<HistoryDH> history);
    }
    interface OpportunityDetailsPresenter extends ContentPresenter {
        void changeNotesVisibility();
        void startAttachment(int pos);
    }
    interface OpportunityDetailsModel extends BaseModel {
        Observable<ResponseGetOpportunityDetails> getOpportunityDetails(String opportunityID);
    }
}
