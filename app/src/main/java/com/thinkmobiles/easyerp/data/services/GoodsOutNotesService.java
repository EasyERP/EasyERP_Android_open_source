package com.thinkmobiles.easyerp.data.services;

import com.thinkmobiles.easyerp.data.model.inventory.goods_out_notes.GoodsOutNotesResponse;
import com.thinkmobiles.easyerp.data.model.inventory.goods_out_notes.details.ResponseGetNoteDetails;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.Path;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by Lynx on 3/6/2017.
 */

public interface GoodsOutNotesService {

    @GET
    Observable<GoodsOutNotesResponse> getGoodsOutNotes(@Url String url);

    @GET(Constants.GET_GOODS_OUT_NOTES_DETAILS)
    Observable<ResponseGetNoteDetails> getNoteDetails(@Path("id") String id);

}
