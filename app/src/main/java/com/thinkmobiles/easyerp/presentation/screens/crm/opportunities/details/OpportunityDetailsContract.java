package com.thinkmobiles.easyerp.presentation.screens.crm.opportunities.details;

import android.text.SpannableStringBuilder;

import com.thinkmobiles.easyerp.data.model.crm.leads.TagItem;
import com.thinkmobiles.easyerp.data.model.crm.opportunities.detail.ResponseGetOpportunityDetails;
import com.thinkmobiles.easyerp.presentation.base.BaseModel;
import com.thinkmobiles.easyerp.presentation.base.BasePresenter;
import com.thinkmobiles.easyerp.presentation.base.BaseView;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.AttachmentDH;
import com.thinkmobiles.easyerp.presentation.base.rules.ErrorViewHelper;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.HistoryDH;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import java.util.ArrayList;

import rx.Observable;

/**
 * Created by Lynx on 2/1/2017.
 */

public interface OpportunityDetailsContract {
    interface OpportunityDetailsView extends BaseView<OpportunityDetailsPresenter> {
        void showProgress(Constants.ProgressType type);
        void showHistory(boolean enable);
        void displayErrorState(String errMsg, ErrorViewHelper.ErrorType errorType);
        void displayErrorToast(String message);

        void showContact(boolean isShown);
        void showCompany(boolean isShown);
        void showAttachments(boolean isShown);

        void displayOpportunityName(String opportunityName);
        void displayStatus(String opportunityStatus);
        void displayRevenue(String revenue);
        void displayCloseDate(String closeDate);
        void displayAssignedTo(String assignedTo);

        void displayContactImage(String contactImageBase64);
        void displayContactFullName(String contactFullname);
        void displayContactEmail(String contactEmail);

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

        void setTags(ArrayList<TagItem> tags);
        void displayAttachments(ArrayList<AttachmentDH> attachmentDHs);
        void startUrlIntent(String url);

        void displayHistory(ArrayList<HistoryDH> history);
    }
    interface OpportunityDetailsPresenter extends BasePresenter {
        void changeNotesVisibility();
        void refresh();
        void startAttachment(int pos);
    }
    interface OpportunityDetailsModel extends BaseModel {
        Observable<ResponseGetOpportunityDetails> getOpportunityDetails(String opportunityID);
    }
}
