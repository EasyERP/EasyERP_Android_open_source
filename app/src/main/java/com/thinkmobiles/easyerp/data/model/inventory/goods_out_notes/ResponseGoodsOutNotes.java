package com.thinkmobiles.easyerp.data.model.inventory.goods_out_notes;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Lynx on 3/6/2017.
 */

public class ResponseGoodsOutNotes implements Parcelable {
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

    public ResponseGoodsOutNotes() {
    }

    protected ResponseGoodsOutNotes(Parcel in) {
        this.total = in.readInt();
        this.data = in.createTypedArrayList(GoodOutNoteItem.CREATOR);
    }

    public static final Parcelable.Creator<ResponseGoodsOutNotes> CREATOR = new Parcelable.Creator<ResponseGoodsOutNotes>() {
        @Override
        public ResponseGoodsOutNotes createFromParcel(Parcel source) {
            return new ResponseGoodsOutNotes(source);
        }

        @Override
        public ResponseGoodsOutNotes[] newArray(int size) {
            return new ResponseGoodsOutNotes[size];
        }
    };
}
