package com.thinkmobiles.easyerp.presentation.screens.inventory.goods_out_notes;

import com.thinkmobiles.easyerp.data.model.inventory.goods_out_notes.GoodOutNoteItem;
import com.thinkmobiles.easyerp.presentation.base.rules.master.filterable.FilterableModel;
import com.thinkmobiles.easyerp.presentation.base.rules.master.filterable.FilterableView;
import com.thinkmobiles.easyerp.presentation.base.rules.master.filterable.MasterFilterablePresenterHelper;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.GoodOutNoteDH;
import com.thinkmobiles.easyerp.presentation.managers.ErrorManager;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lynx on 3/6/2017.
 */

public class GoodsOutNotesPresenter extends MasterFilterablePresenterHelper implements GoodsOutNotesContract.GoodsOutNotesPresenter {

    private GoodsOutNotesContract.GoodsOutNotesView view;
    private GoodsOutNotesContract.GoodsOutNotesModel model;

    private ArrayList<GoodOutNoteItem> goodOutNotesList = new ArrayList<>();

    public GoodsOutNotesPresenter(GoodsOutNotesContract.GoodsOutNotesView view, GoodsOutNotesContract.GoodsOutNotesModel model) {
        this.view = view;
        this.model = model;

        view.setPresenter(this);
    }

    @Override
    public void clickItem(int position) {
        String id = goodOutNotesList.get(position)._id;
        if (super.selectItem(id, position))
            view.openDetailsScreen(id);
    }

    @Override
    protected boolean hasContent() {
        return !goodOutNotesList.isEmpty();
    }

    @Override
    protected void retainInstance() {
        setData(goodOutNotesList, true);
    }

    @Override
    protected void loadPage(int page) {
        final boolean needClear = page == 1;
        compositeSubscription.add(
                model.getFilteredGoodsOutNotes(helper, page).subscribe(
                        responseGoodOutNotes -> {
                            currentPage = page;
                            totalItems = responseGoodOutNotes.total;
                            saveData(responseGoodOutNotes.data, needClear);
                            setData(responseGoodOutNotes.data, needClear);
                        },  t -> error(t)));
    }

    @Override
    protected int getCountItems() {
        return goodOutNotesList.size();
    }

    @Override
    protected FilterableView getView() {
        return view;
    }

    @Override
    protected FilterableModel getModel() {
        return model;
    }

    private void saveData(final List<GoodOutNoteItem> goodOutNoteItems, boolean needClear) {
        if (needClear)
            goodOutNotesList.clear();
        goodOutNotesList.addAll(goodOutNoteItems);
    }

    private void setData(final List<GoodOutNoteItem> goodOutNoteItems, boolean needClear) {
        view.setDataList(prepareOrderDHs(goodOutNoteItems, needClear), needClear);
        if (goodOutNotesList.isEmpty()) {
            view.displayErrorState(ErrorManager.getErrorType(null));
        } else {
            view.showProgress(Constants.ProgressType.NONE);
        }
    }

    private ArrayList<GoodOutNoteDH> prepareOrderDHs(final List<GoodOutNoteItem> goodOutNoteItems, boolean needClear) {
        int position = 0;
        final ArrayList<GoodOutNoteDH> result = new ArrayList<>();
        for (GoodOutNoteItem item : goodOutNoteItems) {
            final GoodOutNoteDH goodOutNoteDH = new GoodOutNoteDH(item);
            makeSelectedDHIfNeed(goodOutNoteDH, position++, needClear);
            result.add(goodOutNoteDH);
        }
        selectFirstElementIfNeed(result);
        return result;
    }
}
