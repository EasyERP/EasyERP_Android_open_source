package com.thinkmobiles.easyerp.presentation.screens.inventory.goods_out_notes;

import com.thinkmobiles.easyerp.presentation.base.rules.master.filterable.FilterablePresenter;
import com.thinkmobiles.easyerp.presentation.base.rules.master.filterable.MasterFilterableFragment;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectableAdapter;

import org.androidannotations.annotations.EFragment;

/**
 * Created by Lynx on 3/6/2017.
 */

@EFragment
public class GoodsOutNotesFragment extends MasterFilterableFragment implements GoodsOutNotesContract.GoodsOutNotesView {

    private GoodsOutNotesContract.GoodsOutNotesPresenter presenter;

    @Override
    public void initPresenter() {

    }

    @Override
    public void setPresenter(GoodsOutNotesContract.GoodsOutNotesPresenter presenter) {

    }

    @Override
    public String getScreenName() {
        return null;
    }

    @Override
    protected SelectableAdapter getAdapter() {
        return null;
    }

    @Override
    public void openDetailsScreen(String goodOutNoteID) {

    }

    @Override
    protected FilterablePresenter getPresenter() {
        return null;
    }
}
