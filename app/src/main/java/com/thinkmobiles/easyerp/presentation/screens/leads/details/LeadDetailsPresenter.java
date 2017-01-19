package com.thinkmobiles.easyerp.presentation.screens.leads.details;


import com.thinkmobiles.easyerp.data.model.leads.details.ResponseGetLeadDetails;
import com.thinkmobiles.easyerp.presentation.holders.data.LeadHistoryDH;
import com.thinkmobiles.easyerp.presentation.managers.DateManager;
import com.thinkmobiles.easyerp.presentation.utils.StringUtil;

import java.util.Arrays;
import java.util.Collections;

import rx.subscriptions.CompositeSubscription;

public class LeadDetailsPresenter implements LeadDetailsContract.LeadDetailsPresenter {

    private LeadDetailsContract.LeadDetailsView view;
    private LeadDetailsContract.LeadDetailsModel model;
    private CompositeSubscription compositeSubscription;
    private String leadId;

    private ResponseGetLeadDetails currentLead;
    private boolean isVisibleHistory;

    public LeadDetailsPresenter(LeadDetailsContract.LeadDetailsView view, LeadDetailsContract.LeadDetailsModel model, String leadId) {
        this.view = view;
        this.model = model;
        this.leadId = leadId;
        view.setPresenter(this);

        compositeSubscription = new CompositeSubscription();
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
                    view.showProgress(false);
                    setData(responseGetLeadDetails);
                }, throwable -> {
                    view.showProgress(false);
                }));
    }

    private void setData(ResponseGetLeadDetails response) {
        currentLead = response;
        view.setNameLead(response.name);

        if (response.expectedClosing != null)
            view.setCloseDate(StringUtil.getField(DateManager.convertLeadDate(response.expectedClosing)));
        view.setAssignedTo(response.salesPerson != null ? response.salesPerson.fullName : "No specified");
        view.setPriority(StringUtil.getField(response.priority));
        view.setSource(StringUtil.getField(response.source));
        view.setTags(StringUtil.prepareTags(response.tags));
        view.setPersonName(StringUtil.getFullName(response.contactName.first, response.contactName.last));
        view.setJobPosition(StringUtil.getField(response.jobPosition));
        view.setDob(StringUtil.getField(response.dateBirth));
        view.setEmail(StringUtil.getField(response.email));
        view.setPhone(StringUtil.getField(response.phones.phone));
        view.setSkype(StringUtil.getField(response.skype));
        view.setLinkedIn(StringUtil.getField(response.social.linkedIn));
        if (response.company != null) {
            view.setCompanyName(StringUtil.getField(response.company.fullName));
        }
        if (response.address != null) {
            view.setCompanyAddress(StringUtil.getField(StringUtil.getAddress(response.address)));
        }

        if (response.attachments != null && response.attachments.size() > 0) {
            view.setAttachments(response.attachments);
        }

        Collections.reverse(response.notes);
        view.setHistory(LeadHistoryDH.convert(response.notes));
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
        compositeSubscription.clear();
    }
}
