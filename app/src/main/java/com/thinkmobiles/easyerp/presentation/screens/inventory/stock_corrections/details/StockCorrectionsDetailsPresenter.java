package com.thinkmobiles.easyerp.presentation.screens.inventory.stock_corrections.details;

import com.thinkmobiles.easyerp.data.model.inventory.goods_out_notes.details.OrderRow;
import com.thinkmobiles.easyerp.data.model.inventory.stock_correction.details.ResponseGetStockCorrectionDetails;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentPresenterHelper;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentView;
import com.thinkmobiles.easyerp.presentation.holders.data.inventory.OrderRowDH;
import com.thinkmobiles.easyerp.presentation.managers.DateManager;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import java.util.ArrayList;

/**
 * Created by samson on 10.03.17.
 */

public class StockCorrectionsDetailsPresenter extends ContentPresenterHelper implements StockCorrectionsDetailsContract.StockCorrectionsDetailsPresenter {

    private StockCorrectionsDetailsContract.StockCorrectionsDetailsView view;
    private StockCorrectionsDetailsContract.StockCorrectionsDetailsModel model;
    private String id;

    private ResponseGetStockCorrectionDetails stockCorrectionDetails;

    public StockCorrectionsDetailsPresenter(StockCorrectionsDetailsContract.StockCorrectionsDetailsView view, StockCorrectionsDetailsContract.StockCorrectionsDetailsModel model, String id) {
        this.view = view;
        this.model = model;
        this.id = id;
        view.setPresenter(this);
    }

    @Override
    protected ContentView getView() {
        return view;
    }

    @Override
    protected boolean hasContent() {
        return stockCorrectionDetails != null;
    }

    @Override
    protected void retainInstance() {
        setData(stockCorrectionDetails);
    }

    @Override
    public void refresh() {
        compositeSubscription.add(model.getStockCorrectionDetails(id)
                .subscribe(responseGetStockCorrectionDetails -> {
                    view.showProgress(Constants.ProgressType.NONE);
                    setData(responseGetStockCorrectionDetails);
                }, t -> error(t)));
    }

    private void setData(ResponseGetStockCorrectionDetails response) {
        stockCorrectionDetails = response;
        view.setDate(DateManager.convert(response.date).setDstPattern(DateManager.PATTERN_DATE_SIMPLE_PREVIEW).toString());
        view.setTitle(response.warehouse.name);
        view.setWarehouse(response.warehouse.name);
        view.setDescription(response.description);
        view.setAdjustedBy(response.createdBy.user.login);
        view.setProducts(prepareList(response.orderRows));
    }


    private ArrayList<OrderRowDH> prepareList(ArrayList<OrderRow> list) {
        ArrayList<OrderRowDH> rows = new ArrayList<>();
        for (OrderRow row : list) {
            rows.add(new OrderRowDH(row));
        }
        return rows;
    }
}
