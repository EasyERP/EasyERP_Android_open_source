package com.thinkmobiles.easyerp.presentation.screens.crm.persons.details;

import com.thinkmobiles.easyerp.data.model.crm.persons.details.ResponseGetPersonDetails;
import com.thinkmobiles.easyerp.presentation.base.BaseModel;
import com.thinkmobiles.easyerp.presentation.base.BasePresenter;
import com.thinkmobiles.easyerp.presentation.base.BaseView;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.AttachmentDH;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.HistoryDH;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.OpportunityAndLeadDH;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.OpportunityPreviewDH;

import java.util.ArrayList;

import rx.Observable;

/**
 * Created by Lynx on 1/24/2017.
 */

public interface PersonDetailsContract {
    interface PersonDetailsView extends BaseView<PersonDetailsPresenter> {
        void showProgress(boolean enable);
        void showHistory(boolean isShow);

        void showCompanyInfo(boolean isShow);
        void showSalesPurchasesInfo(boolean isShow);
        void showLeadsAndOpportunities(boolean isShown);
        void showAttachments(boolean isShown);
        void displayError(final String msg);
        void showMessage(String message);

        void showJobPosition(boolean isShown);
        void showCompany(boolean isShown);

        void displayPersonAvatar(String base64Avatar);
        void displayPersonName(String personName);
        void displayJobPosition(String jobPosition);
        void displayEmail(String email);
        void displaySkype(String skype);
        void displayLinkedIn(String linkedIn);
        void displayFacebook(String facebook);
        void displayPhone(String phone);
        void displayMobile(String mobile);
        void displayDateOfBirth(String dateOfBirth);

        void enableSkypeIcon(String skype);
        void enableLinkedInIcon(String linkedIn);
        void enableFacebookIcon(String facebook);

        void displayBillingStreet(String billingStreet);
        void displayBillingCity(String billingCity);
        void displayBillingState(String billingState);
        void displayBillingZipcode(String billingZipcode);
        void displayBillingCountry(String billingCountry);

        void displayShippingFullName(String shippingFullname);
        void displayShippingStreet(String shippingStreet);
        void displayShippingCity(String shippingCity);
        void displayShippingState(String shippingState);
        void displayShippingZipcode(String shippingZipcode);
        void displayShippingCountry(String shippingCountry);

        void displayIsCustomer(boolean isCustomer);
        void displayIsSupplier(boolean isSupplier);
        void displaySalesTeam(String salesTeam);
        void displaySalesPerson(String salesPerson);
        void displaySalesImplementedBy(String implementedBy);
        void displaySalesReference(String reference);
        void displaySalesLanguage(String language);

        void displayCompanyImage(String base64CompanyImage);
        void displayCompanyUrl(String companyUrl);
        void displayCompanyName(String companyName);
        void displayCompanyStreet(String companyStreet);
        void displayCompanyCity(String companyCity);
        void displayCompanyState(String companyState);
        void displayCompanyZipcode(String companyZipcode);
        void displayCompanyCountry(String companyCountry);
        void displayCompanyPhone(String companyPhone);
        void displayCompanyEmail(String companyEmail);

        void displayLeadAndOpportunity(ArrayList<OpportunityAndLeadDH> opportunityAndLeadDHs);
        void displayAttachments(ArrayList<AttachmentDH> attachmentDHs);
        void displayHistory(ArrayList<HistoryDH> historyDHs);
        void startUrlIntent(String url);

    }
    interface PersonDetailsPresenter extends BasePresenter {
        void changeNotesVisibility();
        void refresh();
        void startAttachment(int pos);
    }
    interface PersonDetailsModel extends BaseModel {
        Observable<ResponseGetPersonDetails> getPersonDetails(String personID);
    }
}
