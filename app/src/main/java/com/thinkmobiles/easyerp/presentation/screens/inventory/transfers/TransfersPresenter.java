package com.thinkmobiles.easyerp.presentation.screens.inventory.transfers;

import com.thinkmobiles.easyerp.data.model.inventory.transfers.details.TransferItem;
import com.thinkmobiles.easyerp.presentation.base.rules.master.filterable.FilterableModel;
import com.thinkmobiles.easyerp.presentation.base.rules.master.filterable.FilterableView;
import com.thinkmobiles.easyerp.presentation.base.rules.master.filterable.MasterFilterablePresenterHelper;
import com.thinkmobiles.easyerp.presentation.holders.data.inventory.TransferDH;
import com.thinkmobiles.easyerp.presentation.managers.ErrorManager;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lynx on 3/7/2017.
 */

public class TransfersPresenter extends MasterFilterablePresenterHelper implements TransfersContract.TransfersPresenter {

    private TransfersContract.TransfersView view;
    private TransfersContract.TransfersModel model;

    private ArrayList<TransferItem> listOfItems = new ArrayList<>();

    public TransfersPresenter(TransfersContract.TransfersView view, TransfersContract.TransfersModel model) {
        this.view = view;
        this.model = model;

        view.setPresenter(this);
    }

    @Override
    public void clickItem(int position) {
        String id = listOfItems.get(position).id;
        if (super.selectItem(id, position))
            view.openDetailsScreen(id);
    }

    @Override
    protected void loadPage(int page) {
        final boolean needClear = page == 1;
        compositeSubscription.add(model.getFilteredTransfers(helper, page).subscribe(
                transferItem -> {
                    currentPage = page;
                    totalItems = transferItem.total;
                    saveData(transferItem.data, needClear);
                    setData(transferItem.data, needClear);
                }, t -> error(t)));
    }

    @Override
    protected int getCountItems() {
        return listOfItems.size();
    }

    @Override
    protected boolean hasContent() {
        return !listOfItems.isEmpty();
    }

    @Override
    protected void retainInstance() {
        setData(listOfItems, true);
    }

    @Override
    protected FilterableView getView() {
        return view;
    }

    @Override
    protected FilterableModel getModel() {
        return model;
    }

    private void saveData(final List<TransferItem> transferItems, boolean needClear) {
        if (needClear)
            listOfItems.clear();
        listOfItems.addAll(transferItems);
    }

    private void setData(final List<TransferItem> transferItems, boolean needClear) {
        view.setDataList(prepareOrderDHs(transferItems), needClear);
        if (listOfItems.isEmpty()) {
            view.displayErrorState(ErrorManager.getErrorType(null));
        } else {
            view.showProgress(Constants.ProgressType.NONE);
        }
    }

    private ArrayList<TransferDH> prepareOrderDHs(final List<TransferItem> transferItems) {
        final ArrayList<TransferDH> result = new ArrayList<>();
        for (TransferItem item : transferItems) {
            final TransferDH goodOutNoteDH = new TransferDH(item);
            makeSelectedDHIfNeed(goodOutNoteDH, listOfItems.indexOf(item));
            result.add(goodOutNoteDH);
        }
        selectFirstElementIfNeed(result);
        return result;
    }
}
