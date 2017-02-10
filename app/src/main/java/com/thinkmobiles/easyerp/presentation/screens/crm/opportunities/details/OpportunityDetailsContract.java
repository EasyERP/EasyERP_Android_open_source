package com.thinkmobiles.easyerp.presentation.screens.crm.opportunities.details;

import android.text.SpannableStringBuilder;

import com.thinkmobiles.easyerp.data.model.crm.opportunities.detail.ResponseGetOpportunityDetails;
import com.thinkmobiles.easyerp.presentation.base.BaseModel;
import com.thinkmobiles.easyerp.presentation.base.BasePresenter;
import com.thinkmobiles.easyerp.presentation.base.BaseView;
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

        void displayContactInfo(boolean isVisible);
        void displayCompanyInfo(boolean isVisible);

        void displayOpportunityName(String opportunityName);
        void displayStatus(String opportunityStatus);
        void displayRevenue(String revenue);
        void displayCloseDate(String closeDate);
        void displayAssignedTo(String assignedTo);

        void displayContactImage(String contactImageBase64);
        void displayContactFullName(String contactFullname);
        void displayContactEmail(String contactEmail);

        void displayCompanyImage(String companyImageBase64);
        void displayCompanyTitleName(String companyTitleName);
        void displayCompanyUrl(String companyUrl);
        void displayCompanyName(String companyName);
        void displayCompanyStreet(String companyStreet);
        void displayCompanyCity(String companyCity);
        void displayCompanyState(String companyState);
        void displayCompanyZip(String companyZip);
        void displayCompanyCountry(String companyCountry);
        void displayCompanyPhone(String companyPhone);
        void displayCompanyEmail(String companyEmail);

        void setAttachments(String attachments);
        void displayTags(SpannableStringBuilder tags);
        void displayHistory(ArrayList<HistoryDH> history);
    }
    interface OpportunityDetailsPresenter extends BasePresenter {
        void changeNotesVisibility();
        void refresh();
    }
    interface OpportunityDetailsModel extends BaseModel {
        Observable<ResponseGetOpportunityDetails> getOpportunityDetails(String opportunityID);
    }
}
