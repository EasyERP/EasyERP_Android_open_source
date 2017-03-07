package com.thinkmobiles.easyerp.presentation.screens.inventory.transfers;

import com.thinkmobiles.easyerp.presentation.base.rules.master.filterable.FilterableModel;
import com.thinkmobiles.easyerp.presentation.base.rules.master.filterable.FilterableView;
import com.thinkmobiles.easyerp.presentation.base.rules.master.filterable.MasterFilterablePresenterHelper;

/**
 * Created by Lynx on 3/7/2017.
 */

public class TransfersPresenter extends MasterFilterablePresenterHelper implements TransfersContract.TransfersPresenter {
    @Override
    public void clickItem(int position) {

    }

    @Override
    protected void loadPage(int page) {

    }

    @Override
    protected int getCountItems() {
        return 0;
    }

    @Override
    protected boolean hasContent() {
        return false;
    }

    @Override
    protected void retainInstance() {

    }

    @Override
    protected FilterableView getView() {
        return null;
    }

    @Override
    protected FilterableModel getModel() {
        return null;
    }
}
