package com.thinkmobiles.easyerp.presentation.screens.inventory.goods_out_notes;

import com.thinkmobiles.easyerp.data.model.inventory.goods_out_notes.ResponseGoodsOutNotes;
import com.thinkmobiles.easyerp.presentation.base.BaseModel;
import com.thinkmobiles.easyerp.presentation.base.BaseView;
import com.thinkmobiles.easyerp.presentation.base.rules.master.filterable.FilterableModel;
import com.thinkmobiles.easyerp.presentation.base.rules.master.filterable.FilterablePresenter;
import com.thinkmobiles.easyerp.presentation.base.rules.master.filterable.FilterableView;
import com.thinkmobiles.easyerp.presentation.utils.filter.FilterHelper;

import rx.Observable;

/**
 * Created by Lynx on 3/6/2017.
 */

public interface GoodsOutNotesContract {
    interface GoodsOutNotesView extends BaseView<GoodsOutNotesPresenter>, FilterableView {
        void openDetailsScreen(String goodOutNoteID);
    }
    interface GoodsOutNotesPresenter extends FilterablePresenter {

    }
    interface GoodsOutNotesModel extends BaseModel, FilterableModel {
        Observable<ResponseGoodsOutNotes> getFilteredGoodsOutNotes(FilterHelper query, int page);
    }
}
