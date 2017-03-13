package com.thinkmobiles.easyerp.presentation.screens.inventory.stock_corrections.details;

import com.thinkmobiles.easyerp.data.model.inventory.stock_correction.details.ResponseGetStockCorrectionDetails;
import com.thinkmobiles.easyerp.presentation.base.BaseModel;
import com.thinkmobiles.easyerp.presentation.base.BaseView;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentPresenter;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentView;
import com.thinkmobiles.easyerp.presentation.holders.data.inventory.OrderRowDH;

import java.util.ArrayList;

import rx.Observable;

/**
 * Created by samson on 10.03.17.
 */

public interface StockCorrectionsDetailsContract {
    interface StockCorrectionsDetailsView extends ContentView, BaseView<StockCorrectionsDetailsPresenter> {
        void setTitle(String title);
        void setDate(String date);
        void setWarehouse(String warehouse);
        void setDescription(String description);
        void setAdjustedBy(String adjustedBy);
        void setProducts(ArrayList<OrderRowDH> list);
    }

    interface StockCorrectionsDetailsPresenter extends ContentPresenter {

    }

    interface StockCorrectionsDetailsModel extends BaseModel {
        Observable<ResponseGetStockCorrectionDetails> getStockCorrectionDetails(String id);
    }
}
