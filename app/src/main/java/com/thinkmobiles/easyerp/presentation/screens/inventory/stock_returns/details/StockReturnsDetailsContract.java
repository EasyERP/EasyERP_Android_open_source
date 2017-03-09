package com.thinkmobiles.easyerp.presentation.screens.inventory.stock_returns.details;

import com.thinkmobiles.easyerp.data.model.inventory.stock_returns.details.ResponseGetStockReturnsDetails;
import com.thinkmobiles.easyerp.data.model.user.organization.ResponseGetOrganizationSettings;
import com.thinkmobiles.easyerp.presentation.base.BaseModel;
import com.thinkmobiles.easyerp.presentation.base.BaseView;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentPresenter;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentView;
import com.thinkmobiles.easyerp.presentation.holders.data.inventory.OrderRowDH;

import java.util.ArrayList;

import rx.Observable;

public interface StockReturnsDetailsContract {

    interface StockReturnsDetailsView extends ContentView, BaseView<StockReturnsDetailsPresenter> {
        void setTitle(String title);
        void setReceivedBy(String receivedBy);
        void setDescription(String description);
        void setCompanyName(String companyName);
        void setCompanyAddress(String companyAddress);
        void setName(String name);
        void setDate(String date);
        void setProducts(ArrayList<OrderRowDH> rowDHs);
    }

    interface StockReturnsDetailsPresenter extends ContentPresenter {

    }

    interface StockReturnsDetailsModel extends BaseModel {
        Observable<ResponseGetStockReturnsDetails> getStockReturnsDetails(String id);

        Observable<ResponseGetOrganizationSettings> getOrganizationSettings();
    }
}
