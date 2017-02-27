package com.thinkmobiles.easyerp.presentation.screens.crm.opportunities.details;

import android.text.TextUtils;

import com.thinkmobiles.easyerp.data.model.crm.leads.detail.AttachmentItem;
import com.thinkmobiles.easyerp.data.model.crm.opportunities.detail.ResponseGetOpportunityDetails;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentPresenterHelper;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentView;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.AttachmentDH;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.ContactDH;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.HistoryDH;
import com.thinkmobiles.easyerp.presentation.managers.DateManager;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by Lynx on 2/1/2017.
 */

public class OpportunityDetailsPresenter extends ContentPresenterHelper implements OpportunityDetailsContract.OpportunityDetailsPresenter {

    private OpportunityDetailsContract.OpportunityDetailsView view;
    private OpportunityDetailsContract.OpportunityDetailsModel model;
    private String opportunityID;
    private ResponseGetOpportunityDetails currentOpportunity;
    private boolean isVisibleHistory;

    public OpportunityDetailsPresenter(OpportunityDetailsContract.OpportunityDetailsView view, OpportunityDetailsContract.OpportunityDetailsModel model, String opportunityID) {
        this.view = view;
        this.model = model;
        this.opportunityID = opportunityID;
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
        compositeSubscription.add(model.getOpportunityDetails(opportunityID)
                .subscribe(responseGetLeadDetails -> {
                    currentOpportunity = responseGetLeadDetails;
                    setData(currentOpportunity);
                    view.showProgress(Constants.ProgressType.NONE);
                },  t -> error(t)));
    }

    @Override
    public void startAttachment(int pos) {
        String url = String.format("%sdownload/%s", Constants.BASE_URL, currentOpportunity.attachments.get(pos).shortPath);
        view.startUrlIntent(url);
    }

    @Override
    protected ContentView getView() {
        return view;
    }

    @Override
    protected boolean hasContent() {
        return currentOpportunity != null;
    }

    @Override
    protected void retainInstance() {
        setData(currentOpportunity);
    }

    private void setData(ResponseGetOpportunityDetails data) {
        if(!TextUtils.isEmpty(data.name)) view.displayOpportunityName(data.name);
        if(data.workflow != null && !TextUtils.isEmpty(data.workflow.name))
            view.displayStatus(data.workflow.name);
        if(data.expectedRevenue != null) {
            view.displayRevenue(String.format(Locale.US, "%d %s",
                    data.expectedRevenue.value,
                    TextUtils.isEmpty(data.expectedRevenue.currency) ? "$" : data.expectedRevenue.currency));
        }
        if(!TextUtils.isEmpty(data.expectedClosing))
            view.displayCloseDate(DateManager.convert(data.expectedClosing)
                    .setDstPattern(DateManager.PATTERN_DATE_SIMPLE_PREVIEW)
                    .toString());
        if(data.salesPerson != null && !TextUtils.isEmpty(data.salesPerson.fullName))
            view.displayAssignedTo(data.salesPerson.fullName);
        view.showContact(data.customer != null && !TextUtils.isEmpty(data.customer.fullName));
        setContacts(data);
        view.showCompany(data.company != null && !TextUtils.isEmpty(data.company.fullName));
        displayCompany(data);
        displayTags(data);
        displayAttachments(data);
        displayHistory(data);
    }

    private void displayCompany(ResponseGetOpportunityDetails currentOpportunity) {
        boolean isCompanyInfoAvailable = false;
        if(!TextUtils.isEmpty(currentOpportunity.tempCompanyField)) {
            view.displayCompanyName(currentOpportunity.tempCompanyField);
            isCompanyInfoAvailable = true;
        }
        if(currentOpportunity.company != null) {
            if(currentOpportunity.company.address != null) {
                if(!TextUtils.isEmpty(currentOpportunity.company.address.street)) {
                    view.displayCompanyStreet(currentOpportunity.company.address.street);
                    isCompanyInfoAvailable = true;
                }
                if(!TextUtils.isEmpty(currentOpportunity.company.address.city)) {
                    view.displayCompanyCity(currentOpportunity.company.address.city);
                    isCompanyInfoAvailable = true;
                }
                if(!TextUtils.isEmpty(currentOpportunity.company.address.state)) {
                    view.displayCompanyState(currentOpportunity.company.address.state);
                    isCompanyInfoAvailable = true;
                }
                if(!TextUtils.isEmpty(currentOpportunity.company.address.zip)) {
                    view.displayCompanyZip(currentOpportunity.company.address.zip);
                    isCompanyInfoAvailable = true;
                }
                if(!TextUtils.isEmpty(currentOpportunity.company.address.country)) {
                    view.displayCompanyCountry(currentOpportunity.company.address.country);
                    isCompanyInfoAvailable = true;
                }
            }
            if(!TextUtils.isEmpty(currentOpportunity.company.imageSrc)) {
                view.displayCompanyImage(currentOpportunity.company.imageSrc);
                isCompanyInfoAvailable = true;
            }
            if(!TextUtils.isEmpty(currentOpportunity.company.website)) {
                view.displayCompanyUrl(currentOpportunity.company.website);
                isCompanyInfoAvailable = true;
            }
            if(currentOpportunity.company.phones != null) {
                if(!TextUtils.isEmpty(currentOpportunity.company.phones.phone)) {
                    view.displayCompanyPhone(currentOpportunity.company.phones.phone);
                    isCompanyInfoAvailable = true;
                } else if(!TextUtils.isEmpty(currentOpportunity.company.phones.mobile)) {
                    view.displayCompanyPhone(currentOpportunity.company.phones.mobile);
                    isCompanyInfoAvailable = true;
                } else if(!TextUtils.isEmpty(currentOpportunity.company.phones.fax)) {
                    view.displayCompanyPhone(currentOpportunity.company.phones.fax);
                    isCompanyInfoAvailable = true;
                }
            }
            if(!TextUtils.isEmpty(currentOpportunity.company.email))
                view.displayCompanyEmail(currentOpportunity.company.email);
        }
        view.showCompany(isCompanyInfoAvailable);
    }

    private void setContacts(ResponseGetOpportunityDetails currentOpportunity) {
        boolean isContactsAvailable = false;
        if(currentOpportunity.customer != null) {
            ArrayList<ContactDH> result = new ArrayList<>();
            result.add(new ContactDH(currentOpportunity.customer));
            view.displayContacts(result);
            isContactsAvailable = true;
        }
        view.showContact(isContactsAvailable);
    }

    private void displayTags(ResponseGetOpportunityDetails currentOpportunity) {
        if(currentOpportunity.tags != null && !currentOpportunity.tags.isEmpty()) {
            view.setTags(currentOpportunity.tags);
        }
    }

    private void displayAttachments(ResponseGetOpportunityDetails currentOpportunity) {
        if(currentOpportunity.attachments != null && !currentOpportunity.attachments.isEmpty()) {
            ArrayList<AttachmentDH> result = new ArrayList<>();
            for (AttachmentItem item : currentOpportunity.attachments)
                result.add(new AttachmentDH(item));
            view.displayAttachments(result);
        } else
            view.showAttachments(false);
    }

    private void displayHistory(ResponseGetOpportunityDetails currentOpportunity) {
        Collections.reverse(currentOpportunity.notes);
        view.displayHistory(HistoryDH.convert(currentOpportunity.notes));
        view.showHistory(isVisibleHistory);
    }
}
