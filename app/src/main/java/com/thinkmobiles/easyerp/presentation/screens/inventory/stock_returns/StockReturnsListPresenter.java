package com.thinkmobiles.easyerp.presentation.screens.inventory.stock_returns;

import com.thinkmobiles.easyerp.data.model.inventory.stock_returns.StockReturn;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.MasterSelectablePresenterHelper;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectableView;
import com.thinkmobiles.easyerp.presentation.holders.data.inventory.StockReturnDH;
import com.thinkmobiles.easyerp.presentation.managers.ErrorManager;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Michael Soyma (Created on 3/6/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public class StockReturnsListPresenter extends MasterSelectablePresenterHelper implements StockReturnsListContract.StockReturnsListPresenter {

    private StockReturnsListContract.StockReturnsListView view;
    private StockReturnsListContract.StockReturnsListModel model;

    private List<StockReturn> stockReturns = new ArrayList<>();

    public StockReturnsListPresenter(StockReturnsListContract.StockReturnsListView view, StockReturnsListContract.StockReturnsListModel model) {
        this.view = view;
        this.model = model;

        this.view.setPresenter(this);
    }

    @Override
    public void clickItem(int position) {
        StockReturn stockReturn = stockReturns.get(position);
        if (super.selectItem(stockReturn.id, position))
            view.openStockReturnsDetail(stockReturn.id);
    }

    @Override
    protected SelectableView getView() {
        return view;
    }

    @Override
    protected void loadPage(int page) {
        compositeSubscription.add(
                model.getStockReturns(page)
                .subscribe(
                        stockReturns -> {
                            totalItems = 1;
                            this.stockReturns = stockReturns;
                            setData();
                        },
                        t -> error(t))
        );
    }

    @Override
    protected int getCountItems() {
        return stockReturns.size();
    }

    @Override
    protected boolean hasContent() {
        return !stockReturns.isEmpty();
    }

    @Override
    protected void retainInstance() {
        setData();
    }

    private void setData() {
        if (stockReturns.isEmpty()) {
            view.displayErrorState(ErrorManager.getErrorType(null));
        } else {
            view.showProgress(Constants.ProgressType.NONE);
            view.setDataList(prepareStockReturnDHs(stockReturns, true), true);
        }
    }

    private ArrayList<StockReturnDH> prepareStockReturnDHs(final List<StockReturn> stockReturnItems, boolean needClear) {
        int position = 0;
        final ArrayList<StockReturnDH> result = new ArrayList<>();
        for (StockReturn stockReturn : stockReturnItems) {
            final StockReturnDH stockReturnDH = new StockReturnDH(stockReturn);
            makeSelectedDHIfNeed(stockReturnDH, position++, needClear);
            result.add(stockReturnDH);
        }
        selectFirstElementIfNeed(result);
        return result;
    }
}
