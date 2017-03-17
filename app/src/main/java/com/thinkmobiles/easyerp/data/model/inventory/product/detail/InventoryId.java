package com.thinkmobiles.easyerp.data.model.inventory.product.detail;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by samson on 13.03.17.
 */

public class InventoryId implements Parcelable {

    public String location;
    public String warehouse;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.location);
        dest.writeString(this.warehouse);
    }

    public InventoryId() {
    }

    protected InventoryId(Parcel in) {
        this.location = in.readString();
        this.warehouse = in.readString();
    }

    public static final Parcelable.Creator<InventoryId> CREATOR = new Parcelable.Creator<InventoryId>() {
        @Override
        public InventoryId createFromParcel(Parcel source) {
            return new InventoryId(source);
        }

        @Override
        public InventoryId[] newArray(int size) {
            return new InventoryId[size];
        }
    };
}
