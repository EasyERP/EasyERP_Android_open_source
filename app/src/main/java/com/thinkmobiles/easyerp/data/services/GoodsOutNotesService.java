package com.thinkmobiles.easyerp.data.services;

import com.thinkmobiles.easyerp.data.model.ResponseGetTotalItems;
import com.thinkmobiles.easyerp.data.model.inventory.goods_out_notes.GoodsOutNoteItem;
import com.thinkmobiles.easyerp.data.model.inventory.goods_out_notes.details.ResponseGetGoodsOutNoteDetails;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by Lynx on 3/6/2017.
 */

public interface GoodsOutNotesService {

    @GET
    Observable<ResponseGetTotalItems<GoodsOutNoteItem>> getGoodsOutNotes(@Url String url);

    @GET(Constants.GET_GOODS_OUT_NOTES_DETAILS)
    Observable<ResponseGetGoodsOutNoteDetails> getNoteDetails(@Path("id") String id);

}
