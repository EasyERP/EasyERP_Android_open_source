package com.thinkmobiles.easyerp.presentation.screens.crm.companies.details;

import android.text.TextUtils;

import com.thinkmobiles.easyerp.data.model.crm.companies.detail.ResponseGetCompanyDetails;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.Customer;
import com.thinkmobiles.easyerp.data.model.crm.persons.details.OpportunityItem;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.ContactDH;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.HistoryDH;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.OpportunityAndLeadDH;
import com.thinkmobiles.easyerp.presentation.utils.StringUtil;

import java.util.ArrayList;
import java.util.Collections;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by Lynx on 2/2/2017.
 */

public class CompanyDetailsPresenter implements CompanyDetailsContract.CompanyDetailsPresenter {

    private CompanyDetailsContract.CompanyDetailsView view;
    private CompanyDetailsContract.CompanyDetailsModel model;
    private String companyID;
    private CompositeSubscription compositeSubscription;

    private ResponseGetCompanyDetails currentData;
    private boolean isVisibleHistory;

    public CompanyDetailsPresenter(CompanyDetailsContract.CompanyDetailsView view, CompanyDetailsContract.CompanyDetailsModel model, String companyID) {
        this.view = view;
        this.model = model;
        this.companyID = companyID;
        compositeSubscription = new CompositeSubscription();

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
                    setData(currentData);
                    view.showProgress(false);
                    view.displayError(null);
                }, throwable -> {
                    view.showProgress(false);
                    if(currentData != null && currentData.id.equalsIgnoreCase(companyID))
                        view.showMessage(throwable.getMessage());
                    else
                        view.displayError(throwable.getMessage());
                }));
    }

    @Override
    public void subscribe() {
        if (currentData == null) {
            view.showProgress(true);
            refresh();
        } else {
            setData(currentData);
        }
    }

    @Override
    public void unsubscribe() {
        if(compositeSubscription.hasSubscriptions()) compositeSubscription.clear();
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
        if(!TextUtils.isEmpty(data.website)) view.displayCompanyUrl(StringUtil.getClickableUrl(data.website, data.website));
        if(data.social != null) {
            if(!TextUtils.isEmpty(data.social.facebook)) {
                view.enableFacebookButton(data.social.facebook);
                view.displayFacebook(data.social.facebook);
            }
            if(!TextUtils.isEmpty(data.social.linkedIn)) {
                view.enableLinkedInButton(data.social.linkedIn);
                view.displayLinkedIn(data.social.linkedIn);
            }
        }
        if(!TextUtils.isEmpty(data.email)) view.displayEmail(data.email);
        if(data.salesPurchases != null && data.salesPurchases.salesPerson != null && !TextUtils.isEmpty(data.salesPurchases.salesPerson.fullName)) {
            view.displayAssignedTo(data.salesPurchases.salesPerson.fullName);
        }
        if(data.phones != null && !TextUtils.isEmpty(data.phones.phone)) view.displayPhone(data.phones.phone);
        if(data.phones != null && !TextUtils.isEmpty(data.phones.mobile)) view.displayMobile(data.phones.mobile);
    }

    private void setBillingAddress(ResponseGetCompanyDetails data) {
        if(data.address != null) {
            if(!TextUtils.isEmpty(data.address.street)) view.displayBillingStreet(data.address.street);
            if(!TextUtils.isEmpty(data.address.city)) view.displayBillingCity(data.address.city);
            if(!TextUtils.isEmpty(data.address.state)) view.displayBillingState(data.address.state);
            if(!TextUtils.isEmpty(data.address.zip)) view.displayBillingZip(data.address.zip);
            if(!TextUtils.isEmpty(data.address.country)) view.displayBillingCountry(data.address.country);
        }
    }

    private void setShippingAddress(ResponseGetCompanyDetails data) {
        if(data.shippingAddress != null) {
            if(!TextUtils.isEmpty(data.shippingAddress.name)) view.displayShippingFullName(data.shippingAddress.name);
            if(!TextUtils.isEmpty(data.shippingAddress.street)) view.displayShippingStreet(data.shippingAddress.street);
            if(!TextUtils.isEmpty(data.shippingAddress.city)) view.displayShippingCity(data.shippingAddress.city);
            if(!TextUtils.isEmpty(data.shippingAddress.state)) view.displayShippingState(data.shippingAddress.state);
            if(!TextUtils.isEmpty(data.shippingAddress.zip)) view.displayShippingZip(data.shippingAddress.zip);
            if(!TextUtils.isEmpty(data.shippingAddress.country)) view.displayShippingCountry(data.shippingAddress.zip);
        }
    }

    private void setSalesAndPurchases(ResponseGetCompanyDetails data) {
        if(data.salesPurchases != null) {
            if(!TextUtils.isEmpty(data.salesPurchases.reference)) view.displaySalesReference(data.salesPurchases.reference);
            view.displaySalesIsCustomer(data.salesPurchases.isCustomer);
            view.displaySalesIsSupplier(data.salesPurchases.isSupplier);
            if(data.salesPurchases.salesTeam != null && !TextUtils.isEmpty(data.salesPurchases.salesTeam.name)) {
                view.displaySalesTeam(data.salesPurchases.salesTeam.name);
            }
            if(data.salesPurchases.salesPerson != null && !TextUtils.isEmpty(data.salesPurchases.salesPerson.fullName)) {
                view.displaySalesPerson(data.salesPurchases.salesPerson.fullName);
            }
            if(data.salesPurchases.implementedBy != null && !TextUtils.isEmpty(data.salesPurchases.implementedBy.fullName)) {
                view.displaySalesImplementedBy(data.salesPurchases.implementedBy.fullName);
            }
            if(!TextUtils.isEmpty(data.salesPurchases.language)) view.displaySalesLanguage(data.salesPurchases.language);
        }
    }

    private void setContacts(ResponseGetCompanyDetails data) {
        if(data.contacts != null && !data.contacts.isEmpty()) {
            ArrayList<ContactDH> result = new ArrayList<>();
            for(Customer customer : data.contacts) {
                result.add(new ContactDH(customer));
            }
            view.displayContacts(result);
        }
    }

    private void setLeadsAndOpportunities(ResponseGetCompanyDetails data) {
        if(data.opportunities != null && !data.opportunities.isEmpty()) {
            ArrayList<OpportunityAndLeadDH> result = new ArrayList<>();
            for(OpportunityItem opportunityItem : data.opportunities) {
                result.add(new OpportunityAndLeadDH(opportunityItem));
            }
            view.displayLeadAndOpportunity(result);
        }
    }

    private void setAttachments(ResponseGetCompanyDetails data) {
        if(data.attachments != null && !data.attachments.isEmpty()) {
            view.displayAttachments(StringUtil.getAttachments(data.attachments));
        }
    }

    private void setHistory(ResponseGetCompanyDetails data) {
        Collections.reverse(data.notes);
        view.displayHistory(HistoryDH.convert(data.notes));
        view.showHistory(isVisibleHistory);
    }
}
