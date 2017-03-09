package com.thinkmobiles.easyerp.presentation.screens.inventory.goods_out_notes;

import com.thinkmobiles.easyerp.data.model.inventory.goods_out_notes.GoodsOutNoteItem;
import com.thinkmobiles.easyerp.presentation.base.rules.master.filterable.FilterableModel;
import com.thinkmobiles.easyerp.presentation.base.rules.master.filterable.FilterableView;
import com.thinkmobiles.easyerp.presentation.base.rules.master.filterable.MasterFilterablePresenterHelper;
import com.thinkmobiles.easyerp.presentation.holders.data.inventory.GoodsOutNoteDH;
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

    private ArrayList<GoodsOutNoteItem> goodOutNotesList = new ArrayList<>();

    public GoodsOutNotesPresenter(GoodsOutNotesContract.GoodsOutNotesView view, GoodsOutNotesContract.GoodsOutNotesModel model) {
        this.view = view;
        this.model = model;

        view.setPresenter(this);
    }

    @Override
    public void clickItem(int position) {
        String id = goodOutNotesList.get(position).id;
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

    private void saveData(final List<GoodsOutNoteItem> goodsOutNoteItems, boolean needClear) {
        if (needClear)
            goodOutNotesList.clear();
        goodOutNotesList.addAll(goodsOutNoteItems);
    }

    private void setData(final List<GoodsOutNoteItem> goodsOutNoteItems, boolean needClear) {
        view.setDataList(prepareGoodsOutNotesDHs(goodsOutNoteItems), needClear);
        if (goodOutNotesList.isEmpty()) {
            view.displayErrorState(ErrorManager.getErrorType(null));
        } else {
            view.showProgress(Constants.ProgressType.NONE);
        }
    }

    private ArrayList<GoodsOutNoteDH> prepareGoodsOutNotesDHs(final List<GoodsOutNoteItem> goodsOutNoteItems) {
        final ArrayList<GoodsOutNoteDH> result = new ArrayList<>();
        for (GoodsOutNoteItem item : goodsOutNoteItems) {
            final GoodsOutNoteDH goodsOutNoteDH = new GoodsOutNoteDH(item);
            makeSelectedDHIfNeed(goodsOutNoteDH, goodOutNotesList.indexOf(item));
            result.add(goodsOutNoteDH);
        }
        selectFirstElementIfNeed(result);
        return result;
    }
}
