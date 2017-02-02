package com.thinkmobiles.easyerp.presentation.screens.crm.opportunities.details;

import android.text.TextUtils;

import com.thinkmobiles.easyerp.data.model.crm.opportunities.detail.ResponseGetOpportunityDetails;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.HistoryDH;
import com.thinkmobiles.easyerp.presentation.managers.DateManager;
import com.thinkmobiles.easyerp.presentation.utils.StringUtil;

import java.util.Collections;
import java.util.Locale;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by Lynx on 2/1/2017.
 */

public class OpportunityDetailsPresenter implements OpportunityDetailsContract.OpportunityDetailsPresenter {

    private OpportunityDetailsContract.OpportunityDetailsView view;
    private OpportunityDetailsContract.OpportunityDetailsModel model;
    private String opportunityID;
    private ResponseGetOpportunityDetails currentOpportunity;
    private CompositeSubscription compositeSubscription;
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
                    view.showProgress(false);
                    view.showError(null);
                }, throwable -> {
                    view.showProgress(false);
                    if(currentOpportunity != null && currentOpportunity.id.equalsIgnoreCase(opportunityID))
                        view.showMessage(throwable.getMessage());
                    else
                        view.showError(throwable.getMessage());
                }));
    }

    @Override
    public void subscribe() {
        if (currentOpportunity == null) {
            view.showProgress(true);
            refresh();
        } else {
            setData(currentOpportunity);
        }
    }

    @Override
    public void unsubscribe() {
        if(compositeSubscription.hasSubscriptions()) compositeSubscription.clear();
    }

    private void setData(ResponseGetOpportunityDetails data) {
        if(!TextUtils.isEmpty(data.name)) view.displayOpportunityName(data.name);
        if(data.workflow != null && !TextUtils.isEmpty(data.workflow.name)) view.displayStatus(data.workflow.name);
        if(data.expectedRevenue != null) {
            view.displayRevenue(String.format(Locale.US, "%d %s",
                    data.expectedRevenue.value,
                    TextUtils.isEmpty(data.expectedRevenue.currency) ? "$" : data.expectedRevenue.currency));
        }
        if(!TextUtils.isEmpty(data.expectedClosing))
            view.displayCloseDate(DateManager.convert(data.expectedClosing)
                    .setDstPattern(DateManager.PATTERN_DATE_SIMPLE_PREVIEW)
                    .toString());
        if(data.salesPerson != null && !TextUtils.isEmpty(data.salesPerson.fullName)) view.displayAssignedTo(data.salesPerson.fullName);
        view.displayContactInfo(data.customer != null && !TextUtils.isEmpty(data.customer.fullName));
        if(data.customer != null) {
            if(!TextUtils.isEmpty(data.customer.imageSrc)) view.displayContactImage(data.customer.imageSrc);
            if(!TextUtils.isEmpty(data.customer.fullName)) view.displayContactFullName(data.customer.fullName);
            if(!TextUtils.isEmpty(data.customer.email)) view.displayContactEmail(data.customer.email);
        }
        view.displayCompanyInfo(data.company != null && !TextUtils.isEmpty(data.company.fullName));
        if(data.company != null) {
            if(!TextUtils.isEmpty(data.company.imageSrc)) view.displayCompanyImage(data.company.imageSrc);
            if(!TextUtils.isEmpty(data.company.fullName)) view.displayCompanyTitleName(data.company.fullName);
            if(!TextUtils.isEmpty(data.company.website)) view.displayCompanyUrl(StringUtil.getClickableUrl(data.company.website, data.company.website));
            if(!TextUtils.isEmpty(data.company.fullName)) view.displayCompanyName(data.company.fullName);
            if(data.company.address != null) {
                if(!TextUtils.isEmpty(data.company.address.street)) view.displayCompanyStreet(data.company.address.street);
                if(!TextUtils.isEmpty(data.company.address.city)) view.displayCompanyCity(data.company.address.city);
                if(!TextUtils.isEmpty(data.company.address.state)) view.displayCompanyState(data.company.address.state);
                if(!TextUtils.isEmpty(data.company.address.zip)) view.displayCompanyZip(data.company.address.zip);
                if(!TextUtils.isEmpty(data.company.address.country)) view.displayCompanyCountry(data.company.address.country);
            }
            if(data.company.phones != null) {
                if(!TextUtils.isEmpty(data.company.phones.phone)) view.displayCompanyPhone(data.company.phones.phone);
                else if(!TextUtils.isEmpty(data.company.phones.mobile)) view.displayCompanyPhone(data.company.phones.mobile);
                else if(!TextUtils.isEmpty(data.company.phones.fax)) view.displayCompanyPhone(data.company.phones.fax);
            }
            if(!TextUtils.isEmpty(data.company.email)) view.displayCompanyEmail(data.company.email);
        }
        if(data.tags != null && !data.tags.isEmpty()) view.displayTags(StringUtil.prepareTags(data.tags));
        if(data.attachments != null && !data.attachments.isEmpty()) {
            view.setAttachments(StringUtil.getAttachments(data.attachments));
        }
        Collections.reverse(data.notes);

        view.displayHistory(HistoryDH.convert(data.notes));
        view.showHistory(isVisibleHistory);
    }
}
