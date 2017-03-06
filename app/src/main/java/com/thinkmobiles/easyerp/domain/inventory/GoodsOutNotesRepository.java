package com.thinkmobiles.easyerp.domain.inventory;

import com.thinkmobiles.easyerp.data.api.Rest;
import com.thinkmobiles.easyerp.data.model.crm.filter.ResponseFilters;
import com.thinkmobiles.easyerp.data.model.inventory.goods_out_notes.GoodsOutNotesResponse;
import com.thinkmobiles.easyerp.data.services.FilterService;
import com.thinkmobiles.easyerp.data.services.GoodsOutNotesService;
import com.thinkmobiles.easyerp.presentation.base.NetworkRepository;
import com.thinkmobiles.easyerp.presentation.utils.Constants;
import com.thinkmobiles.easyerp.presentation.utils.filter.FilterHelper;

import org.androidannotations.annotations.EBean;

import rx.Observable;

/**
 * Created by Lynx on 3/6/2017.
 */

@EBean(scope = EBean.Scope.Singleton)
public class GoodsOutNotesRepository extends NetworkRepository {

    private GoodsOutNotesService goodsOutNotesService;
    private FilterService filterService;

    public GoodsOutNotesRepository() {
        goodsOutNotesService = Rest.getInstance().getGoodsOutNotesService();
        filterService = Rest.getInstance().getFilterService();
    }

    public Observable<GoodsOutNotesResponse> getFilteredGoodsOutNotes(FilterHelper query, int page) {
        return getNetworkObservable(goodsOutNotesService.getGoodsOutNotes(query
                .createUrl(Constants.GET_GOODS_OUT_NOTES, "goodsOutNotes", page)
                .build()
                .toString()

        ));
    }

    public Observable<ResponseFilters> getFilters() {
        return getNetworkObservable(filterService.getListFilters("goodsOutNotes"));
    }
}
