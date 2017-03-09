package com.thinkmobiles.easyerp.data.model.inventory.goods_out_notes.details;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.thinkmobiles.easyerp.data.model.crm.filter.FilterItem;
import com.thinkmobiles.easyerp.data.model.inventory.goods_out_notes.GoodsOutNoteStatus;

import java.util.ArrayList;

/**
 * Created by samson on 06.03.17.
 */

public class ResponseGetGoodsOutNoteDetails implements Parcelable {

    @SerializedName("_id")
    public String id;
    public String name;
    public GoodsOutNoteStatus status;
    public ArrayList<OrderRow> orderRows;
    public GoodsOutNoteOrder order;
    public String description;
    public String date;
    public String reference;
    public FilterItem shippingMethod;


    public ResponseGetGoodsOutNoteDetails() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeParcelable(this.status, flags);
        dest.writeTypedList(this.orderRows);
        dest.writeParcelable(this.order, flags);
        dest.writeString(this.description);
        dest.writeString(this.date);
        dest.writeString(this.reference);
        dest.writeParcelable(this.shippingMethod, flags);
    }

    protected ResponseGetGoodsOutNoteDetails(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.status = in.readParcelable(GoodsOutNoteStatus.class.getClassLoader());
        this.orderRows = in.createTypedArrayList(OrderRow.CREATOR);
        this.order = in.readParcelable(GoodsOutNoteOrder.class.getClassLoader());
        this.description = in.readString();
        this.date = in.readString();
        this.reference = in.readString();
        this.shippingMethod = in.readParcelable(FilterItem.class.getClassLoader());
    }

    public static final Creator<ResponseGetGoodsOutNoteDetails> CREATOR = new Creator<ResponseGetGoodsOutNoteDetails>() {
        @Override
        public ResponseGetGoodsOutNoteDetails createFromParcel(Parcel source) {
            return new ResponseGetGoodsOutNoteDetails(source);
        }

        @Override
        public ResponseGetGoodsOutNoteDetails[] newArray(int size) {
            return new ResponseGetGoodsOutNoteDetails[size];
        }
    };
}
