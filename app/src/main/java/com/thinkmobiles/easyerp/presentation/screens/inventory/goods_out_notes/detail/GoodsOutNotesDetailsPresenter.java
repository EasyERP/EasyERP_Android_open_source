package com.thinkmobiles.easyerp.presentation.screens.inventory.goods_out_notes.detail;

import com.thinkmobiles.easyerp.data.model.inventory.goods_out_notes.details.OrderRow;
import com.thinkmobiles.easyerp.data.model.inventory.goods_out_notes.details.ResponseGetGoodsOutNoteDetails;
import com.thinkmobiles.easyerp.data.model.user.organization.OrganizationSettings;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentPresenterHelper;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentView;
import com.thinkmobiles.easyerp.presentation.holders.data.inventory.OrderRowDH;
import com.thinkmobiles.easyerp.presentation.managers.DateManager;
import com.thinkmobiles.easyerp.presentation.utils.Constants;
import com.thinkmobiles.easyerp.presentation.utils.StringUtil;

import java.util.ArrayList;

/**
 * Created by samson on 07.03.17.
 */

public class GoodsOutNotesDetailsPresenter extends ContentPresenterHelper implements GoodsOutNotesDetailsContract.GoodsOutNotesDetailsPresenter {

    private GoodsOutNotesDetailsContract.GoodsOutNotesDetailsView view;
    private GoodsOutNotesDetailsContract.GoodsOutNotesDetailsModel model;
    private String noteId;

    private ResponseGetGoodsOutNoteDetails noteDetails;
    private OrganizationSettings organizationSettings;

    public GoodsOutNotesDetailsPresenter(GoodsOutNotesDetailsContract.GoodsOutNotesDetailsView view, GoodsOutNotesDetailsContract.GoodsOutNotesDetailsModel model, String noteId) {
        this.view = view;
        this.model = model;
        this.noteId = noteId;

        view.setPresenter(this);
    }

    @Override
    protected ContentView getView() {
        return view;
    }

    @Override
    protected boolean hasContent() {
        return noteDetails != null;
    }

    @Override
    protected void retainInstance() {
        setData(noteDetails);
        setOrgData(organizationSettings);
    }

    @Override
    public void refresh() {
        compositeSubscription.add(model.getGoodsOutNotesDetails(noteId)
                .zipWith(model.getOrganizationSettings(), (responseGetNoteDetails, responseGetOrganizationSettings) -> {
                    setOrgData(responseGetOrganizationSettings.data);
                    return responseGetNoteDetails;
                })
                .subscribe(responseGetNoteDetails -> {
                    view.showProgress(Constants.ProgressType.NONE);
                    setData(responseGetNoteDetails);
                }, t -> error(t)));
    }

    private void setData(ResponseGetGoodsOutNoteDetails response) {
        noteDetails = response;
        view.setName(response.name);
        view.setTitle(String.format("Goods-out Notes #%s", response.name));
        view.setDate(DateManager.convert(response.date).setDstPattern(DateManager.PATTERN_DATE_AND_TIME).toString());
        if (response.status != null) {
            view.setPrint(response.status.printed
                    ? DateManager.convert(response.status.printedOn).setDstPattern(DateManager.PATTERN_DATE_AND_TIME).toString()
                    : null);
            view.setPick(response.status.picked
                    ? DateManager.convert(response.status.pickedOn).setDstPattern(DateManager.PATTERN_DATE_AND_TIME).toString()
                    : null);
            view.setPack(response.status.packed
                    ? DateManager.convert(response.status.packedOn).setDstPattern(DateManager.PATTERN_DATE_AND_TIME).toString()
                    : null);
            view.setShip(response.status.shipped
                    ? DateManager.convert(response.status.shippedOn).setDstPattern(DateManager.PATTERN_DATE_AND_TIME).toString()
                    : null);
        }
        if (response.order != null && response.order.supplier != null) {
            view.setSupplierName(response.order.supplier.fullName);
            view.setSupplierAddress(StringUtil.getAddress(response.order.supplier.address));
        }
        if (response.shippingMethod != null)
            view.setShipping(response.shippingMethod.name);
        view.setReference(response.reference);
        view.setProducts(prepareList(response.orderRows));
    }

    private void setOrgData(OrganizationSettings data) {
        organizationSettings = data;

        if (organizationSettings != null) {
            view.setCompanyName(organizationSettings.name);
            if (organizationSettings.address != null)
                view.setCompanyAddress(StringUtil.getAddress(organizationSettings.address));
        }
    }

    private ArrayList<OrderRowDH> prepareList(ArrayList<OrderRow> list) {
        ArrayList<OrderRowDH> rows = new ArrayList<>();
        for (OrderRow row : list) {
            rows.add(new OrderRowDH(row));
        }
        return rows;
    }
}
