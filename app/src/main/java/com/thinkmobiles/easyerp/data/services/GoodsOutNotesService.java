package com.thinkmobiles.easyerp.data.services;

import com.thinkmobiles.easyerp.data.model.inventory.goods_out_notes.ResponseGoodsOutNotes;

import retrofit2.http.GET;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by Lynx on 3/6/2017.
 */

public interface GoodsOutNotesService {

    @GET
    Observable<ResponseGoodsOutNotes> getGoodsOutNotes(@Url String url);

}
