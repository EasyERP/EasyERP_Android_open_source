package com.thinkmobiles.easyerp.presentation.screens.inventory.goods_out_notes;

import com.thinkmobiles.easyerp.presentation.base.rules.master.filterable.FilterableModel;
import com.thinkmobiles.easyerp.presentation.base.rules.master.filterable.FilterableView;
import com.thinkmobiles.easyerp.presentation.base.rules.master.filterable.MasterFilterablePresenterHelper;

/**
 * Created by Lynx on 3/6/2017.
 */

public class GoodsOutNotesPresenter extends MasterFilterablePresenterHelper implements GoodsOutNotesContract.GoodsOutNotesPresenter {
    @Override
    public void clickItem(int position) {

    }

    @Override
    protected boolean hasContent() {
        return false;
    }

    @Override
    protected void retainInstance() {

    }

    @Override
    protected void loadPage(int page) {

    }

    @Override
    protected int getCountItems() {
        return 0;
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
