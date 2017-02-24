package com.thinkmobiles.easyerp.presentation.screens.crm.persons.details;

import android.text.TextUtils;

import com.thinkmobiles.easyerp.data.model.crm.leads.detail.AttachmentItem;
import com.thinkmobiles.easyerp.data.model.crm.persons.details.OpportunityItem;
import com.thinkmobiles.easyerp.data.model.crm.persons.details.ResponseGetPersonDetails;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentPresenterHelper;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentView;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.AttachmentDH;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.HistoryDH;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.LeadAndOpportunityDH;
import com.thinkmobiles.easyerp.presentation.managers.DateManager;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Lynx on 1/24/2017.
 */

public class PersonDetailsPresenter extends ContentPresenterHelper implements PersonDetailsContract.PersonDetailsPresenter {

    private PersonDetailsContract.PersonDetailsView view;
    private PersonDetailsContract.PersonDetailsModel model;
    private String personID;

    private ResponseGetPersonDetails currentPerson;
    private boolean isVisibleHistory;

    public PersonDetailsPresenter(PersonDetailsContract.PersonDetailsView view, PersonDetailsContract.PersonDetailsModel model, String personID) {
        this.view = view;
        this.model = model;
        this.personID = personID;

        view.setPresenter(this);
    }

    @Override
    protected ContentView getView() {
        return view;
    }

    @Override
    protected boolean hasContent() {
        return currentPerson != null;
    }

    @Override
    protected void retainInstance() {
        setData(currentPerson);
    }

    @Override
    public void changeNotesVisibility() {
        isVisibleHistory = !isVisibleHistory;
        view.showHistory(isVisibleHistory);
    }

    @Override
    public void refresh() {
        compositeSubscription.add(model.getPersonDetails(personID)
                .subscribe(responseGetPersonDetails -> {
                    view.showProgress(Constants.ProgressType.NONE);
                    currentPerson = responseGetPersonDetails;
                    setData(currentPerson);
                },  t -> error(t)));
    }

    @Override
    public void startAttachment(int pos) {
        String url = String.format("%sdownload/%s", Constants.BASE_URL, currentPerson.attachments.get(pos).shortPath);
        view.startUrlIntent(url);
    }

    private void setData(ResponseGetPersonDetails data) {
        setBasicInfo(data);
        setSocialInfo(data);
        setAddress(data);
        setSalesPurchasesInfo(data);
        setCompanyInfo(data);
        setLeadsAndOpportunities(data);
        setAttachments(data);
        setHistory(data);
    }

    private void setBasicInfo(ResponseGetPersonDetails data) {
        if(!TextUtils.isEmpty(data.fullName)) view.displayPersonName(data.fullName);
        if(!TextUtils.isEmpty(data.imageSrc)) view.displayPersonAvatar(data.imageSrc);

        if(!TextUtils.isEmpty(data.jobPosition)) {
            view.showJobPosition(true);
            view.displayJobPosition(data.jobPosition);
        } else
            view.showJobPosition(false);

        if(!TextUtils.isEmpty(data.email)) view.displayEmail(data.email);
        if(!TextUtils.isEmpty(data.skype)) view.displaySkype(data.skype);
        if(data.phones != null) {
            if(!TextUtils.isEmpty(data.phones.phone)) view.displayPhone(data.phones.phone);
            if(!TextUtils.isEmpty(data.phones.mobile)) view.displayMobile(data.phones.mobile);
        }
        if(!TextUtils.isEmpty(data.dateBirth)) {
            view.displayDateOfBirth(DateManager.convert(data.dateBirth)
                    .setDstPattern(DateManager.PATTERN_DATE_SIMPLE_PREVIEW)
                    .toString());
        }
        if(!TextUtils.isEmpty(data.skype)) view.enableSkypeIcon(data.skype);
    }

    private void setSocialInfo(ResponseGetPersonDetails data) {
        if(data.social != null) {
            if(!TextUtils.isEmpty(data.social.linkedIn)) view.displayLinkedIn(data.social.linkedIn);
            if(!TextUtils.isEmpty(data.social.facebook)) view.displayFacebook(data.social.facebook);
            if(!TextUtils.isEmpty(data.social.linkedIn)) view.enableLinkedInIcon(data.social.linkedIn);
            if(!TextUtils.isEmpty(data.social.facebook)) view.enableFacebookIcon(data.social.facebook);
        }
    }

    private void setAddress(ResponseGetPersonDetails data) {
        if(data.address != null) {
            if(!TextUtils.isEmpty(data.address.street)) view.displayBillingStreet(data.address.street);
            if(!TextUtils.isEmpty(data.address.city)) view.displayBillingCity(data.address.city);
            if(!TextUtils.isEmpty(data.address.state)) view.displayBillingState(data.address.state);
            if(!TextUtils.isEmpty(data.address.zip)) view.displayBillingZipcode(data.address.zip);
            if(!TextUtils.isEmpty(data.address.country)) view.displayBillingCountry(data.address.country);
        }
        if(data.shippingAddress != null) {
            if(!TextUtils.isEmpty(data.shippingAddress.name)) view.displayShippingFullName(data.shippingAddress.name);
            if(!TextUtils.isEmpty(data.shippingAddress.street)) view.displayShippingStreet(data.shippingAddress.street);
            if(!TextUtils.isEmpty(data.shippingAddress.city)) view.displayShippingCity(data.shippingAddress.city);
            if(!TextUtils.isEmpty(data.shippingAddress.state)) view.displayShippingState(data.shippingAddress.state);
            if(!TextUtils.isEmpty(data.shippingAddress.zip)) view.displayShippingZipcode(data.shippingAddress.zip);
            if(!TextUtils.isEmpty(data.shippingAddress.country)) view.displayShippingCountry(data.shippingAddress.country);
        }
    }

    private void setSalesPurchasesInfo(ResponseGetPersonDetails data) {
        if(data.salesPurchases != null && (data.salesPurchases.isCustomer || data.salesPurchases.isSupplier)) {
            view.showSalesPurchasesInfo(true);
            view.displayIsCustomer(data.salesPurchases.isCustomer);
            view.displayIsSupplier(data.salesPurchases.isSupplier);
            if(data.salesPurchases.salesTeam != null && !TextUtils.isEmpty(data.salesPurchases.salesTeam.name)) {
                view.displaySalesTeam(data.salesPurchases.salesTeam.name);
            }
            if(data.salesPurchases.salesPerson != null && !TextUtils.isEmpty(data.salesPurchases.salesPerson.fullName)) {
                view.displaySalesPerson(data.salesPurchases.salesPerson.fullName);
            }
            if(data.salesPurchases.implementedBy != null && !TextUtils.isEmpty(data.salesPurchases.implementedBy.fullName)) {
                view.displaySalesImplementedBy(data.salesPurchases.implementedBy.fullName);
            }
            if(!TextUtils.isEmpty(data.salesPurchases.reference)) {
                view.displaySalesReference(data.salesPurchases.reference);
            }
            if(!TextUtils.isEmpty(data.salesPurchases.language)) {
                view.displaySalesLanguage(data.salesPurchases.language);
            }
        } else
            view.showSalesPurchasesInfo(false);
    }

    private void setCompanyInfo(ResponseGetPersonDetails data) {
        if(data.company != null && !TextUtils.isEmpty(data.company.fullName)) {
            view.showCompanyInfo(true);
            view.showCompany(true);
            view.displayCompanyName(data.company.fullName);
            if(!TextUtils.isEmpty(data.company.imageSrc)) view.displayCompanyImage(data.company.imageSrc);
            if(!TextUtils.isEmpty(data.company.website)) view.displayCompanyUrl(data.company.website);
            if(data.company.address != null) {
                if(!TextUtils.isEmpty(data.company.address.street)) view.displayCompanyStreet(data.company.address.street);
                if(!TextUtils.isEmpty(data.company.address.city)) view.displayCompanyCity(data.company.address.city);
                if(!TextUtils.isEmpty(data.company.address.state)) view.displayCompanyState(data.company.address.state);
                if(!TextUtils.isEmpty(data.company.address.zip)) view.displayCompanyZipcode(data.company.address.zip);
                if(!TextUtils.isEmpty(data.company.address.country)) view.displayCompanyCountry(data.company.address.country);
            }
            if(data.company.phones != null) {
                if(!TextUtils.isEmpty(data.company.phones.phone)) view.displayCompanyPhone(data.company.phones.phone);
                else if(!TextUtils.isEmpty(data.company.phones.mobile)) view.displayCompanyPhone(data.company.phones.mobile);
                else if(!TextUtils.isEmpty(data.company.phones.fax)) view.displayCompanyPhone(data.company.phones.fax);
            }
            if(!TextUtils.isEmpty(data.company.email)) view.displayCompanyEmail(data.company.email);
        } else {
            view.showCompany(false);
            view.showCompanyInfo(false);
        }
    }

    private void setLeadsAndOpportunities(ResponseGetPersonDetails data) {
        if(data.opportunities != null && !data.opportunities.isEmpty()) {
            ArrayList<LeadAndOpportunityDH> result = new ArrayList<>();
            for(OpportunityItem opportunityItem : data.opportunities) {
                result.add(new LeadAndOpportunityDH(opportunityItem));
            }
            view.displayLeadAndOpportunity(result);
        }
        else
            view.showLeadsAndOpportunities(false);
    }

    private void setAttachments(ResponseGetPersonDetails data) {
        ArrayList<AttachmentDH> result = new ArrayList<>();
        if(data.attachments != null && !data.attachments.isEmpty()) {
            for(AttachmentItem item : data.attachments) result.add(new AttachmentDH(item));
            view.displayAttachments(result);
        } else
            view.showAttachments(false);
    }

    private void setHistory(ResponseGetPersonDetails data) {
        if(data.notes != null && !data.notes.isEmpty()) {
            Collections.reverse(data.notes);
            view.displayHistory(HistoryDH.convert(data.notes));
            view.showHistory(isVisibleHistory);
        }
    }
}
