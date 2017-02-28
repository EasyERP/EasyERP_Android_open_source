package com.thinkmobiles.easyerp.presentation.screens.crm.companies.details;

import android.text.TextUtils;

import com.thinkmobiles.easyerp.data.model.crm.companies.detail.ResponseGetCompanyDetails;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.AttachmentItem;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.Customer;
import com.thinkmobiles.easyerp.data.model.crm.persons.details.OpportunityItem;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentPresenterHelper;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentView;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.AttachmentDH;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.ContactDH;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.HistoryDH;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.LeadAndOpportunityDH;
import com.thinkmobiles.easyerp.presentation.utils.Constants;
import com.thinkmobiles.easyerp.presentation.utils.IntentActionHelper;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Lynx on 2/2/2017.
 */

public class CompanyDetailsPresenter extends ContentPresenterHelper implements CompanyDetailsContract.CompanyDetailsPresenter {

    private CompanyDetailsContract.CompanyDetailsView view;
    private CompanyDetailsContract.CompanyDetailsModel model;
    private String companyID;

    private ResponseGetCompanyDetails currentData;
    private boolean isVisibleHistory;

    public CompanyDetailsPresenter(CompanyDetailsContract.CompanyDetailsView view, CompanyDetailsContract.CompanyDetailsModel model, String companyID) {
        this.view = view;
        this.model = model;
        this.companyID = companyID;

        view.setPresenter(this);
    }

    @Override
    public void changeNotesVisibility() {
        isVisibleHistory = !isVisibleHistory;
        view.showHistory(isVisibleHistory);
    }

    @Override
    public void refresh() {
        compositeSubscription.add(model.getCompanyDetails(companyID)
                .subscribe(responseGetCompanyDetails -> {
                    currentData = responseGetCompanyDetails;
                    view.showProgress(Constants.ProgressType.NONE);
                    setData(currentData);
                },  t -> error(t)));
    }

    @Override
    public void startAttachment(int pos) {
        String url = String.format("%sdownload/%s", Constants.BASE_URL, currentData.attachments.get(pos).shortPath);
        view.startUrlIntent(url);
    }

    @Override
    protected ContentView getView() {
        return view;
    }

    @Override
    protected boolean hasContent() {
        return currentData != null;
    }

    @Override
    protected void retainInstance() {
        setData(currentData);
    }

    private void setData(ResponseGetCompanyDetails data) {
        setGeneralInfo(data);
        setBillingAddress(data);
        setShippingAddress(data);
        setSalesAndPurchases(data);
        setContacts(data);
        setLeadsAndOpportunities(data);
        setAttachments(data);
        setHistory(data);
    }

    private void setGeneralInfo(ResponseGetCompanyDetails data) {
        if(!TextUtils.isEmpty(data.imageSrc)) view.displayCompanyImage(data.imageSrc);
        if(!TextUtils.isEmpty(data.fullName)) view.displayCompanyName(data.fullName);
        if(!TextUtils.isEmpty(data.website)) {
            if(!data.website.startsWith("http://")) data.website = "http://" + data.website;
            view.displayCompanyUrl(data.website);
        }
        if(!TextUtils.isEmpty(data.skype)) {
            view.enableSkypeButton(String.format(IntentActionHelper.FORMAT_SKYPE, data.skype));
        }
        if(data.social != null) {
            if(!TextUtils.isEmpty(data.social.linkedIn)) {
                view.displayLinkedIn(data.social.linkedIn);
                view.enableLinkedInButton(data.social.linkedIn);
            }
            if(!TextUtils.isEmpty(data.social.facebook)) {
                view.displayFacebook(data.social.facebook);
                view.enableFacebookButton(data.social.facebook);
            }
        }
        if(!TextUtils.isEmpty(data.email)) {
            view.displayEmail(data.email);
            view.enableEmailActionClick(data.email);
        }
        if(data.salesPurchases != null && data.salesPurchases.salesPerson != null && !TextUtils.isEmpty(data.salesPurchases.salesPerson.fullName)) {
            view.displayAssignedTo(data.salesPurchases.salesPerson.fullName);
        }
        if(data.phones != null && !TextUtils.isEmpty(data.phones.phone)) {
            view.displayPhone(data.phones.phone);
            view.enablePhoneActionClick(data.phones.phone);
        }
        if(data.phones != null && !TextUtils.isEmpty(data.phones.mobile)) {
            view.displayMobile(data.phones.mobile);
            view.enableMobileActionClick(data.phones.mobile);
        }
    }

    private void setBillingAddress(ResponseGetCompanyDetails data) {
        boolean isBillingInfoAvailable = false;
        if(data.address != null) {
            if(!TextUtils.isEmpty(data.address.street)) {
                view.displayBillingStreet(data.address.street);
                isBillingInfoAvailable = true;
            }
            if(!TextUtils.isEmpty(data.address.city)) {
                view.displayBillingCity(data.address.city);
                isBillingInfoAvailable = true;
            }
            if(!TextUtils.isEmpty(data.address.state)) {
                view.displayBillingState(data.address.state);
                isBillingInfoAvailable = true;
            }
            if(!TextUtils.isEmpty(data.address.zip)) {
                view.displayBillingZip(data.address.zip);
                isBillingInfoAvailable = true;
            }
            if(!TextUtils.isEmpty(data.address.country)) {
                view.displayBillingCountry(data.address.country);
                isBillingInfoAvailable = true;
            }
        }
        view.showBillingAddress(isBillingInfoAvailable);
    }

    private void setShippingAddress(ResponseGetCompanyDetails data) {
        boolean isShippingInfoAvailable = false;
        if(data.shippingAddress != null) {
            if(!TextUtils.isEmpty(data.shippingAddress.name)) {
                view.displayShippingFullName(data.shippingAddress.name);
                isShippingInfoAvailable = true;
            }
            if(!TextUtils.isEmpty(data.shippingAddress.street)) {
                view.displayShippingStreet(data.shippingAddress.street);
                isShippingInfoAvailable = true;
            }
            if(!TextUtils.isEmpty(data.shippingAddress.city)) {
                view.displayShippingCity(data.shippingAddress.city);
                isShippingInfoAvailable = true;
            }
            if(!TextUtils.isEmpty(data.shippingAddress.state)) {
                view.displayShippingState(data.shippingAddress.state);
                isShippingInfoAvailable = true;
            }
            if(!TextUtils.isEmpty(data.shippingAddress.zip)) {
                view.displayShippingZip(data.shippingAddress.zip);
                isShippingInfoAvailable = true;
            }
            if(!TextUtils.isEmpty(data.shippingAddress.country)) {
                view.displayShippingCountry(data.shippingAddress.zip);
                isShippingInfoAvailable = true;
            }
        }
        view.showShippingAddress(isShippingInfoAvailable);
    }

    private void setSalesAndPurchases(ResponseGetCompanyDetails data) {
        boolean isSalesAndPurchsesAvailable = false;
        if(data.salesPurchases != null) {
            if(!TextUtils.isEmpty(data.salesPurchases.reference)) {
                view.displaySalesReference(data.salesPurchases.reference);
                isSalesAndPurchsesAvailable = true;
            }
            view.displaySalesIsCustomer(data.salesPurchases.isCustomer);
            view.displaySalesIsSupplier(data.salesPurchases.isSupplier);
            if(data.salesPurchases.salesTeam != null && !TextUtils.isEmpty(data.salesPurchases.salesTeam.name)) {
                view.displaySalesTeam(data.salesPurchases.salesTeam.name);
                isSalesAndPurchsesAvailable = true;
            }
            if(data.salesPurchases.salesPerson != null && !TextUtils.isEmpty(data.salesPurchases.salesPerson.fullName)) {
                view.displaySalesPerson(data.salesPurchases.salesPerson.fullName);
                isSalesAndPurchsesAvailable = true;
            }
            if(data.salesPurchases.implementedBy != null && !TextUtils.isEmpty(data.salesPurchases.implementedBy.fullName)) {
                view.displaySalesImplementedBy(data.salesPurchases.implementedBy.fullName);
                isSalesAndPurchsesAvailable = true;
            }
            if(!TextUtils.isEmpty(data.salesPurchases.language)) {
                view.displaySalesLanguage(data.salesPurchases.language);
                isSalesAndPurchsesAvailable = true;
            }
        }
        view.showSalesAndPurchases(isSalesAndPurchsesAvailable);
    }

    private void setContacts(ResponseGetCompanyDetails data) {
        boolean isContactsAvailable = false;
        if(data.contacts != null && !data.contacts.isEmpty()) {
            ArrayList<ContactDH> result = new ArrayList<>();
            for(Customer customer : data.contacts) {
                result.add(new ContactDH(customer));
            }
            view.displayContacts(result);
            isContactsAvailable = true;
        }
        view.showContact(isContactsAvailable);
    }

    private void setLeadsAndOpportunities(ResponseGetCompanyDetails data) {
        boolean isLeadsAndOpportunitiesAvailable = false;
        if(data.opportunities != null && !data.opportunities.isEmpty()) {
            ArrayList<LeadAndOpportunityDH> result = new ArrayList<>();
            for(OpportunityItem opportunityItem : data.opportunities) {
                result.add(new LeadAndOpportunityDH(opportunityItem));
            }
            view.displayLeadAndOpportunity(result);
            isLeadsAndOpportunitiesAvailable = true;
        }
        view.showLeadsAndOpportunities(isLeadsAndOpportunitiesAvailable);
    }

    private void setAttachments(ResponseGetCompanyDetails data) {
        boolean isAttachmentsAvailable = false;
        if(data.attachments != null && !data.attachments.isEmpty()) {
            ArrayList<AttachmentDH> result = new ArrayList<>();
            for(AttachmentItem item : currentData.attachments) result.add(new AttachmentDH(item));
            view.displayAttachments(result);
            isAttachmentsAvailable = true;
        }
        view.showAttachments(isAttachmentsAvailable);
    }

    private void setHistory(ResponseGetCompanyDetails data) {
        Collections.reverse(data.notes);
        view.displayHistory(HistoryDH.convert(data.notes));
        view.showHistory(isVisibleHistory);
    }

    @Override
    public void showOpportunityDetails(int pos) {
        OpportunityItem item = currentData.opportunities.get(pos);
        view.openOpportunityDetails(item._id, item.name);
    }

    @Override
    public void showPersonDetails(int pos) {
        Customer item = currentData.contacts.get(pos);
        view.openPersonDetails(item._id, item.fullName);
    }
}
