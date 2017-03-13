package com.thinkmobiles.easyerp.presentation.screens.inventory.stock_returns.details;

import com.thinkmobiles.easyerp.data.model.inventory.stock_correction.details.OrderRow;
import com.thinkmobiles.easyerp.data.model.inventory.stock_returns.details.ResponseGetStockReturnsDetails;
import com.thinkmobiles.easyerp.data.model.user.organization.OrganizationSettings;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentPresenterHelper;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentView;
import com.thinkmobiles.easyerp.presentation.holders.data.inventory.OrderRowDH;
import com.thinkmobiles.easyerp.presentation.managers.DateManager;
import com.thinkmobiles.easyerp.presentation.utils.Constants;
import com.thinkmobiles.easyerp.presentation.utils.StringUtil;

import java.util.ArrayList;

/**
 * Created by samson on 09.03.17.
 */

public class StockReturnsDetailsPresenter extends ContentPresenterHelper implements StockReturnsDetailsContract.StockReturnsDetailsPresenter {

    private StockReturnsDetailsContract.StockReturnsDetailsView view;
    private StockReturnsDetailsContract.StockReturnsDetailsModel model;
    private String id;
    private ResponseGetStockReturnsDetails stockReturnsDetails;
    private OrganizationSettings organizationSettings;

    public StockReturnsDetailsPresenter(StockReturnsDetailsContract.StockReturnsDetailsView view, StockReturnsDetailsContract.StockReturnsDetailsModel model, String id) {
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
        return stockReturnsDetails != null;
    }

    @Override
    protected void retainInstance() {
        setData(stockReturnsDetails);
        setOrgData(organizationSettings);
    }

    @Override
    public void refresh() {
        compositeSubscription.add(model.getStockReturnsDetails(id)
                .zipWith(model.getOrganizationSettings(), (responseGetStockReturnsDetails, responseGetOrganizationSettings) -> {
                    setOrgData(responseGetOrganizationSettings.data);
                    return responseGetStockReturnsDetails;
                })
                .subscribe(responseGetStockReturnsDetails -> {
                    view.showProgress(Constants.ProgressType.NONE);
                    setData(responseGetStockReturnsDetails);
                }, t -> error(t)));
    }

    private void setData(ResponseGetStockReturnsDetails response) {
        stockReturnsDetails = response;
        view.setName(response.name);
        view.setTitle(String.format("Stock Returns #%s", response.name));
        view.setDate(DateManager.convert(response.date).setDstPattern(DateManager.PATTERN_DATE_SIMPLE_PREVIEW).toString());
        if (response.status != null && response.status.receivedById != null)
            view.setReceivedBy(response.status.receivedById.login);
        view.setDescription(response.description);
        view.setProducts(prepareList(response.orderRows));
    }

    private void setOrgData(OrganizationSettings data) {
        organizationSettings = data;

        if (organizationSettings != null) {
            view.setCompanyName(organizationSettings.name);
            if (organizationSettings.address != null)
                view.setCompanyAddress(StringUtil.getAddress(organizationSettings.address));
        }
    }

    private ArrayList<OrderRowDH> prepareList(ArrayList<OrderRow> list) {
        ArrayList<OrderRowDH> rows = new ArrayList<>();
        for (OrderRow row : list) {
            rows.add(new OrderRowDH(row));
        }
        return rows;
    }
}
