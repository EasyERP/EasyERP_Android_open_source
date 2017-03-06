package com.thinkmobiles.easyerp.data.model.inventory.goods_out_notes;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Lynx on 3/6/2017.
 */

public class GoodsOutNotesResponse implements Parcelable {
    public int total;
    public ArrayList<GoodOutNoteItem> data;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.total);
        dest.writeTypedList(this.data);
    }

    public GoodsOutNotesResponse() {
    }

    protected GoodsOutNotesResponse(Parcel in) {
        this.total = in.readInt();
        this.data = in.createTypedArrayList(GoodOutNoteItem.CREATOR);
    }

    public static final Parcelable.Creator<GoodsOutNotesResponse> CREATOR = new Parcelable.Creator<GoodsOutNotesResponse>() {
        @Override
        public GoodsOutNotesResponse createFromParcel(Parcel source) {
            return new GoodsOutNotesResponse(source);
        }

        @Override
        public GoodsOutNotesResponse[] newArray(int size) {
            return new GoodsOutNotesResponse[size];
        }
    };
}
