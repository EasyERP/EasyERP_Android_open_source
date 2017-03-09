package com.thinkmobiles.easyerp.presentation.screens.inventory.goods_out_notes.detail;

import com.thinkmobiles.easyerp.data.model.inventory.goods_out_notes.details.ResponseGetGoodsOutNoteDetails;
import com.thinkmobiles.easyerp.data.model.user.organization.ResponseGetOrganizationSettings;
import com.thinkmobiles.easyerp.presentation.base.BaseModel;
import com.thinkmobiles.easyerp.presentation.base.BaseView;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentPresenter;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentView;
import com.thinkmobiles.easyerp.presentation.holders.data.inventory.OrderRowDH;

import java.util.ArrayList;

import rx.Observable;

/**
 * Created by samson on 07.03.17.
 */

public interface GoodsOutNotesDetailsContract {

    interface GoodsOutNotesDetailsView extends ContentView, BaseView<GoodsOutNotesDetailsPresenter> {
        void setTitle(String title);
        void setCompanyName(String companyName);
        void setCompanyAddress(String companyAddress);
        void setName(String name);
        void setSupplierName(String supplierName);
        void setSupplierAddress(String supplierAddress);
        void setPrint(String print);
        void setPick(String pick);
        void setPack(String pack);
        void setShip(String ship);
        void setReference(String reference);
        void setShipping(String shipping);
        void setDate(String date);
        void setProducts(ArrayList<OrderRowDH> rowDHs);
    }

    interface GoodsOutNotesDetailsPresenter extends ContentPresenter {
    }

    interface GoodsOutNotesDetailsModel extends BaseModel {
        Observable<ResponseGetGoodsOutNoteDetails> getGoodsOutNotesDetails(String id);

        Observable<ResponseGetOrganizationSettings> getOrganizationSettings();
    }

}
