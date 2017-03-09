package com.thinkmobiles.easyerp.presentation.screens.inventory.stock_corrections;

import com.thinkmobiles.easyerp.data.model.inventory.stock_correction.StockCorrection;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.MasterSelectablePresenterHelper;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectableView;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.InvoiceDH;
import com.thinkmobiles.easyerp.presentation.holders.data.inventory.StockCorrectionDH;
import com.thinkmobiles.easyerp.presentation.managers.ErrorManager;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Michael Soyma (Created on 3/7/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public class StockCorrectionsListPresenter extends MasterSelectablePresenterHelper implements StockCorrectionsListContract.StockCorrectionsListPresenter {

    private StockCorrectionsListContract.StockCorrectionsListView view;
    private StockCorrectionsListContract.StockCorrectionsListModel model;

    private List<StockCorrection> stockCorrections = new ArrayList<>();

    public StockCorrectionsListPresenter(StockCorrectionsListContract.StockCorrectionsListView view, StockCorrectionsListContract.StockCorrectionsListModel model) {
        this.view = view;
        this.model = model;

        this.view.setPresenter(this);
    }

    @Override
    public void clickItem(int position) {
        String id = stockCorrections.get(position).id;
        if(super.selectItem(id, position))
            view.openStockCorrectionDetail(id);
    }

    @Override
    protected SelectableView getView() {
        return view;
    }

    @Override
    protected boolean hasContent() {
        return !stockCorrections.isEmpty();
    }

    @Override
    protected void retainInstance() {
        setData(stockCorrections, true);
    }

    @Override
    protected void loadPage(int page) {
        final boolean needClear = page == 1;
        compositeSubscription.add(
                model.getStockCorrections(page).subscribe(
                        listResponseGetTotalItems -> {
                            currentPage = page;
                            totalItems = listResponseGetTotalItems.total;
                            saveData(listResponseGetTotalItems.data, needClear);
                            setData(listResponseGetTotalItems.data, needClear);
                        },
                        t -> error(t))
        );
    }

    @Override
    protected int getCountItems() {
        return stockCorrections.size();
    }

    private void saveData(final List<StockCorrection> stockCorrections, boolean needClear) {
        if (needClear)
            this.stockCorrections.clear();
        this.stockCorrections.addAll(stockCorrections);
    }

    public void setData(final List<StockCorrection> stockCorrections, boolean needClear) {
        view.setDataList(prepareStockCorrectionDHs(stockCorrections), needClear);
        if (this.stockCorrections.isEmpty()) {
            view.displayErrorState(ErrorManager.getErrorType(null));
        } else {
            view.showProgress(Constants.ProgressType.NONE);
        }
    }

    private ArrayList<StockCorrectionDH> prepareStockCorrectionDHs(final List<StockCorrection> stockCorrections) {
        final ArrayList<StockCorrectionDH> result = new ArrayList<>();
        for (StockCorrection stockCorrection : stockCorrections) {
            final StockCorrectionDH stockCorrectionDH = new StockCorrectionDH(stockCorrection);
            makeSelectedDHIfNeed(stockCorrectionDH, this.stockCorrections.indexOf(stockCorrection));
            result.add(stockCorrectionDH);
        }
        selectFirstElementIfNeed(result);
        return result;
    }
}
