package com.thinkmobiles.easyerp.presentation.screens.inventory.transfers.details;

import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentPresenterHelper;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentView;

/**
 * Created by Lynx on 3/9/2017.
 */

public class TransferDetailsPresenter extends ContentPresenterHelper implements TransferDetailsContract.TransferDetailsPresenter {

    private TransferDetailsContract.TransferDetailsView view;
    private TransferDetailsContract.TransferDetailsModel model;
    private String id;

    public TransferDetailsPresenter(TransferDetailsContract.TransferDetailsView view, TransferDetailsContract.TransferDetailsModel model, String id) {
        this.view = view;
        this.model = model;
        this.id = id;

        view.setPresenter(this);
    }

    @Override
    public void refresh() {

    }

    @Override
    protected ContentView getView() {
        return null;
    }

    @Override
    protected boolean hasContent() {
        return false;
    }

    @Override
    protected void retainInstance() {

    }

    @Override
    public void startAttachment(int pos) {

    }
}
