package com.thinkmobiles.easyerp.presentation.screens.inventory.transfers.details;

import android.text.TextUtils;

import com.thinkmobiles.easyerp.data.model.crm.leads.detail.AttachmentItem;
import com.thinkmobiles.easyerp.data.model.inventory.transfers.details.ResponseGetTransferDetails;
import com.thinkmobiles.easyerp.data.model.inventory.transfers.details.TransferRowItem;
import com.thinkmobiles.easyerp.data.model.user.organization.OrganizationSettings;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentPresenterHelper;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentView;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.AttachmentDH;
import com.thinkmobiles.easyerp.presentation.holders.data.inventory.TransferRowDH;
import com.thinkmobiles.easyerp.presentation.managers.DateManager;
import com.thinkmobiles.easyerp.presentation.utils.Constants;
import com.thinkmobiles.easyerp.presentation.utils.StringUtil;

import java.util.ArrayList;

/**
 * Created by Lynx on 3/9/2017.
 */

public class TransferDetailsPresenter extends ContentPresenterHelper implements TransferDetailsContract.TransferDetailsPresenter {

    private TransferDetailsContract.TransferDetailsView view;
    private TransferDetailsContract.TransferDetailsModel model;
    private String id;

    private ResponseGetTransferDetails responseGetTransferDetails;
    private OrganizationSettings organizationSettings;

    public TransferDetailsPresenter(TransferDetailsContract.TransferDetailsView view, TransferDetailsContract.TransferDetailsModel model, String id) {
        this.view = view;
        this.model = model;
        this.id = id;

        view.setPresenter(this);
    }

    @Override
    public void refresh() {
        compositeSubscription.add(model.getTransferDetails(id)
                .zipWith(model.getOrganizationSettings(), (responseGetTransferDetails, responseGetOrganizationSettings) -> {
                    setOrgData(responseGetOrganizationSettings.data);
                    return responseGetTransferDetails;
                })
                .subscribe(responseGetTransferDetails -> {
                    view.showProgress(Constants.ProgressType.NONE);
                    setData(responseGetTransferDetails);
                }, t -> error(t)));
    }

    @Override
    protected ContentView getView() {
        return view;
    }

    @Override
    protected boolean hasContent() {
        return false;
    }

    @Override
    protected void retainInstance() {
        setData(responseGetTransferDetails);
        setOrgData(organizationSettings);
    }

    @Override
    public void startAttachment(int pos) {
        String url = String.format("%sdownload/%s", Constants.BASE_URL, responseGetTransferDetails.attachments.get(pos).shortPath);
        view.startUrlIntent(url);
    }


    private void setData(ResponseGetTransferDetails response) {
        responseGetTransferDetails = response;
        view.setName(response.name);
        view.setTitle(response.name);
        view.setDate(DateManager.convert(response.date).setDstPattern(DateManager.PATTERN_DATE_AND_TIME).toString());
        view.setPrint(DateManager.convert(response.status.printedOn).setDstPattern(DateManager.PATTERN_DATE_AND_TIME).toString());
        view.setPack(DateManager.convert(response.status.packedOn).setDstPattern(DateManager.PATTERN_DATE_AND_TIME).toString());
        view.setShip(DateManager.convert(response.status.shippedOn).setDstPattern(DateManager.PATTERN_DATE_AND_TIME).toString());
        view.setReceive(DateManager.convert(response.status.receivedOn).setDstPattern(DateManager.PATTERN_DATE_AND_TIME).toString());

        if (response.warehouseTo != null && !TextUtils.isEmpty(response.warehouseTo.name)) {
            view.setWarehouseTo(response.warehouseTo.name);
        } else {
            view.setWarehouseTo(null);
        }
        if (response.warehouseTo != null && response.warehouseTo.address != null && !TextUtils.isEmpty(response.warehouseTo.address.country)) {
            view.setWarehouseToCountry(response.warehouseTo.address.country);
        } else {
            view.setWarehouseToCountry(null);
        }

        view.setShipping(response.shippingMethod);
        view.setReference(response.reference);
        view.setTransferRows(prepareList(response.orderRows));

        displayAttachments(responseGetTransferDetails);
    }

    private void setOrgData(OrganizationSettings data) {
        organizationSettings = data;

        if (organizationSettings != null) {
            view.setCompanyName(organizationSettings.name);
            if (organizationSettings.address != null)
                view.setCompanyAddress(StringUtil.getAddress(organizationSettings.address));
        }
    }

    private ArrayList<TransferRowDH> prepareList(ArrayList<TransferRowItem> list) {
        ArrayList<TransferRowDH> rows = new ArrayList<>();
        for (TransferRowItem row : list) {
            rows.add(new TransferRowDH(row));
        }
        return rows;
    }

    private void displayAttachments(ResponseGetTransferDetails response) {
        if (response.attachments != null && !response.attachments.isEmpty()) {
            ArrayList<AttachmentDH> result = new ArrayList<>();
            for(AttachmentItem item : response.attachments) result.add(new AttachmentDH(item));
            view.setAttachments(result);
        } else
            view.showAttachments(false);
    }
}
