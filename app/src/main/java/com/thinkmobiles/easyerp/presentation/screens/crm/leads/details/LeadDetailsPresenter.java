package com.thinkmobiles.easyerp.presentation.screens.crm.leads.details;


import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.ResponseGetLeadDetails;
import com.thinkmobiles.easyerp.presentation.EasyErpApplication;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.HistoryDH;
import com.thinkmobiles.easyerp.presentation.managers.DateManager;
import com.thinkmobiles.easyerp.presentation.utils.StringUtil;

import java.util.Collections;

import rx.subscriptions.CompositeSubscription;

public class LeadDetailsPresenter implements LeadDetailsContract.LeadDetailsPresenter {

    private LeadDetailsContract.LeadDetailsView view;
    private LeadDetailsContract.LeadDetailsModel model;
    private CompositeSubscription compositeSubscription;
    private String leadId;

    private ResponseGetLeadDetails currentLead;
    private boolean isVisibleHistory;
    private String notSpecified;

    public LeadDetailsPresenter(LeadDetailsContract.LeadDetailsView view, LeadDetailsContract.LeadDetailsModel model, String leadId) {
        this.view = view;
        this.model = model;
        this.leadId = leadId;
        view.setPresenter(this);

        compositeSubscription = new CompositeSubscription();

        notSpecified = EasyErpApplication.getInstance().getString(R.string.err_not_specified);
    }

    @Override
    public void changeNotesVisibility() {
        isVisibleHistory = !isVisibleHistory;
        view.showHistory(isVisibleHistory);
    }

    @Override
    public void refresh() {
        compositeSubscription.add(model.getLeadDetails(leadId)
                .subscribe(responseGetLeadDetails -> {
                    currentLead = responseGetLeadDetails;
                    setData(currentLead);
                    view.showProgress(false);
                    view.showError(null);
                }, throwable -> {
                    view.showProgress(false);
                    if(currentLead != null && currentLead._id.equalsIgnoreCase(leadId))
                        view.showMessage(throwable.getMessage());
                    else
                        view.showError(throwable.getMessage());
                }));
    }

    private void setData(ResponseGetLeadDetails response) {
        currentLead = response;

        if(response.workflow != null) view.setCurrentStatus(StringUtil.getField(response.workflow.name, notSpecified));
        view.setLeadName(StringUtil.getField(response.name, notSpecified));
        view.setCloseDate(DateManager.convert(response.expectedClosing).toString());
        view.setAssignedTo(response.salesPerson != null ? response.salesPerson.fullName : notSpecified);
        view.setPriority(StringUtil.getField(response.priority, notSpecified));
        view.setSource(StringUtil.getField(response.source, notSpecified));
        if (!response.tags.isEmpty())
            view.setTags(response.tags);
        view.setPersonName(StringUtil.getFullName(response.contactName.first, response.contactName.last));
        view.setFirstName(StringUtil.getField(response.contactName.first, notSpecified));
        view.setLastName(StringUtil.getField(response.contactName.last, notSpecified));
        view.setJobPosition(StringUtil.getField(response.jobPosition, notSpecified));
        view.setDob(StringUtil.getField(response.dateBirth, notSpecified));
        view.setEmail(StringUtil.getField(response.email, notSpecified));
        view.setPhone(StringUtil.getField(response.phones.phone, notSpecified));
        view.setSkype(StringUtil.getField(response.skype, notSpecified));
        view.setLinkedIn(StringUtil.getField(response.social.linkedIn, notSpecified));
        view.setTvFacebook(StringUtil.getField(response.social.facebook, notSpecified));
        view.setCompanyName(StringUtil.getField(response.company != null ? response.company.fullName : response.tempCompanyField, notSpecified));
        if (response.company != null && response.company.address != null) {
            view.setCompanyStreet(StringUtil.getField(response.company.address.street, notSpecified));
            view.setCompanyCity(StringUtil.getField(response.company.address.city, notSpecified));
            view.setCompanyState(StringUtil.getField(response.company.address.street, notSpecified));
            view.setCompanyZipcode(StringUtil.getField(response.company.address.zip, notSpecified));
            view.setCompanyCountry(StringUtil.getField(response.company.address.country, notSpecified));
        } else {
            view.setCompanyStreet(notSpecified);
            view.setCompanyCity(notSpecified);
            view.setCompanyState(notSpecified);
            view.setCompanyZipcode(notSpecified);
            view.setCompanyCountry(notSpecified);
        }

        if (response.attachments != null && !response.attachments.isEmpty()) {
            view.setAttachments(StringUtil.getAttachments(response.attachments));
        }

        Collections.reverse(response.notes);
        view.setHistory(HistoryDH.convert(response.notes));
        view.showHistory(isVisibleHistory);
    }

    @Override
    public void subscribe() {
        if (currentLead == null) {
            view.showProgress(true);
            refresh();
        } else {
            setData(currentLead);
        }
    }

    @Override
    public void unsubscribe() {
        if (compositeSubscription.hasSubscriptions())
            compositeSubscription.clear();
    }
}
