package com.thinkmobiles.easyerp.presentation.screens.inventory.transfers.details;

import com.thinkmobiles.easyerp.data.model.inventory.transfers.details.ResponseGetTransferDetails;
import com.thinkmobiles.easyerp.data.model.user.organization.ResponseGetOrganizationSettings;
import com.thinkmobiles.easyerp.presentation.base.BaseModel;
import com.thinkmobiles.easyerp.presentation.base.BaseView;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentPresenter;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentView;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.AttachmentDH;
import com.thinkmobiles.easyerp.presentation.holders.data.inventory.TransferRowDH;

import java.util.ArrayList;

import rx.Observable;

/**
 * Created by Lynx on 3/9/2017.
 */

public interface TransferDetailsContract {
    interface TransferDetailsView extends ContentView, BaseView<TransferDetailsPresenter> {
        void setTitle(String title);
        void setCompanyName(String companyName);
        void setCompanyAddress(String companyAddress);
        void setName(String name);
        void setWarehouseTo(String warehouseTo);
        void setWarehouseToCountry(String warehouseToCountry);
        void setPrint(String print);
        void setPack(String pack);
        void setShip(String ship);
        void setReceive(String receive);
        void setReference(String reference);
        void setShipping(String shipping);
        void setDate(String date);
        void setTransferRows(ArrayList<TransferRowDH> rowDHs);

        void setAttachments(ArrayList<AttachmentDH> attachmentDHs);
        void showAttachments(boolean isShown);
        void startUrlIntent(String url);
    }
    interface TransferDetailsPresenter extends ContentPresenter {
        void startAttachment(int pos);
    }
    interface TransferDetailsModel extends BaseModel {
        Observable<ResponseGetTransferDetails> getTransferDetails(String transferID);
        Observable<ResponseGetOrganizationSettings> getOrganizationSettings();
    }
}
