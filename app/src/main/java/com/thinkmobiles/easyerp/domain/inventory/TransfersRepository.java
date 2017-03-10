package com.thinkmobiles.easyerp.domain.inventory;

import com.thinkmobiles.easyerp.data.api.Rest;
import com.thinkmobiles.easyerp.data.model.crm.filter.ResponseFilters;
import com.thinkmobiles.easyerp.data.model.inventory.transfers.ResponseGetTransfers;
import com.thinkmobiles.easyerp.data.services.FilterService;
import com.thinkmobiles.easyerp.data.services.TransfersService;
import com.thinkmobiles.easyerp.presentation.base.NetworkRepository;
import com.thinkmobiles.easyerp.presentation.screens.inventory.transfers.TransfersContract;
import com.thinkmobiles.easyerp.presentation.utils.Constants;
import com.thinkmobiles.easyerp.presentation.utils.filter.FilterHelper;

import org.androidannotations.annotations.EBean;

import rx.Observable;

/**
 * Created by Lynx on 3/7/2017.
 */

@EBean(scope = EBean.Scope.Singleton)
public class TransfersRepository extends NetworkRepository implements TransfersContract.TransfersModel {

    private TransfersService transfersService;
    private FilterService filterService;

    public TransfersRepository() {
        transfersService = Rest.getInstance().getTransfersService();
        filterService = Rest.getInstance().getFilterService();
    }

    @Override
    public Observable<ResponseGetTransfers> getFilteredTransfers(FilterHelper query, int page) {
        return getNetworkObservable(transfersService.getTransfers(query
                .createUrl(Constants.GET_TRANSACTIONS, "stockTransactions", page)
                .build()
                .toString()
        ));
    }

    @Override
    public Observable<ResponseFilters> getFilters() {
        return getNetworkObservable(filterService.getListFilters("stockTransactions"));
    }
}
