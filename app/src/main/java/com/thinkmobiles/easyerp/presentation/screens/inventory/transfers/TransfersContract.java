package com.thinkmobiles.easyerp.presentation.screens.inventory.transfers;

import com.thinkmobiles.easyerp.data.model.ResponseGetTotalItems;
import com.thinkmobiles.easyerp.data.model.inventory.transfers.details.TransferItem;
import com.thinkmobiles.easyerp.presentation.base.BaseModel;
import com.thinkmobiles.easyerp.presentation.base.BaseView;
import com.thinkmobiles.easyerp.presentation.base.rules.master.filterable.FilterableModel;
import com.thinkmobiles.easyerp.presentation.base.rules.master.filterable.FilterablePresenter;
import com.thinkmobiles.easyerp.presentation.base.rules.master.filterable.FilterableView;
import com.thinkmobiles.easyerp.presentation.utils.filter.FilterHelper;

import rx.Observable;

/**
 * Created by Lynx on 3/7/2017.
 */

public interface TransfersContract {
    interface TransfersView extends BaseView<TransfersPresenter>, FilterableView {
        void openDetailsScreen(String goodOutNoteID);
    }
    interface TransfersPresenter extends FilterablePresenter {

    }
    interface TransfersModel extends BaseModel, FilterableModel {
        Observable<ResponseGetTotalItems<TransferItem>> getFilteredTransfers(FilterHelper query, int page);
    }
}
