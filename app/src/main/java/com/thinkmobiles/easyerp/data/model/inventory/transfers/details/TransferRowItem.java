package com.thinkmobiles.easyerp.data.model.inventory.transfers.details;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.thinkmobiles.easyerp.data.model.inventory.goods_out_notes.details.LocationsDeliver;

import java.util.ArrayList;

/**
 * Created by Lynx on 3/9/2017.
 */

public class TransferRowItem implements Parcelable {

    @SerializedName("_id")
    public String id;
    public int quantity;
    public int cost;
    public ArrayList<LocationReceivedItem> locationsReceived;
    public ArrayList<BatcherDeliverItem> batchesDeliver;
    public ArrayList<LocationsDeliver> locationsDeliver;
    public TransferProduct product;

    public TransferRowItem() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeInt(this.quantity);
        dest.writeInt(this.cost);
        dest.writeList(this.locationsReceived);
        dest.writeTypedList(this.batchesDeliver);
        dest.writeTypedList(this.locationsDeliver);
        dest.writeParcelable(this.product, flags);
    }

    protected TransferRowItem(Parcel in) {
        this.id = in.readString();
        this.quantity = in.readInt();
        this.cost = in.readInt();
        this.locationsReceived = new ArrayList<LocationReceivedItem>();
        in.readList(this.locationsReceived, LocationReceivedItem.class.getClassLoader());
        this.batchesDeliver = in.createTypedArrayList(BatcherDeliverItem.CREATOR);
        this.locationsDeliver = in.createTypedArrayList(LocationsDeliver.CREATOR);
        this.product = in.readParcelable(TransferProduct.class.getClassLoader());
    }

    public static final Creator<TransferRowItem> CREATOR = new Creator<TransferRowItem>() {
        @Override
        public TransferRowItem createFromParcel(Parcel source) {
            return new TransferRowItem(source);
        }

        @Override
        public TransferRowItem[] newArray(int size) {
            return new TransferRowItem[size];
        }
    };
}
