package com.thinkmobiles.easyerp.data.model.inventory.transfers;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Lynx on 3/9/2017.
 */

public class BatcherDeliverItem implements Parcelable {
    public int cost;
    public int quantity;
    @SerializedName("_id")
    public String id;
    public String goodsNote;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.cost);
        dest.writeInt(this.quantity);
        dest.writeString(this.id);
        dest.writeString(this.goodsNote);
    }

    public BatcherDeliverItem() {
    }

    protected BatcherDeliverItem(Parcel in) {
        this.cost = in.readInt();
        this.quantity = in.readInt();
        this.id = in.readString();
        this.goodsNote = in.readString();
    }

    public static final Parcelable.Creator<BatcherDeliverItem> CREATOR = new Parcelable.Creator<BatcherDeliverItem>() {
        @Override
        public BatcherDeliverItem createFromParcel(Parcel source) {
            return new BatcherDeliverItem(source);
        }

        @Override
        public BatcherDeliverItem[] newArray(int size) {
            return new BatcherDeliverItem[size];
        }
    };
}
