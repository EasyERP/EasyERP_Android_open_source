package com.thinkmobiles.easyerp.data.model.inventory.transfers.details;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.thinkmobiles.easyerp.data.model.inventory.goods_out_notes.details.LocationsDeliver;

/**
 * Created by Lynx on 3/9/2017.
 */

public class LocationReceivedItem implements Parcelable {
    public int quantity;
    @SerializedName("_id")
    public String id;
    public LocationsDeliver location;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.quantity);
        dest.writeString(this.id);
        dest.writeParcelable(this.location, flags);
    }

    public LocationReceivedItem() {
    }

    protected LocationReceivedItem(Parcel in) {
        this.quantity = in.readInt();
        this.id = in.readString();
        this.location = in.readParcelable(LocationsDeliver.class.getClassLoader());
    }

    public static final Parcelable.Creator<LocationReceivedItem> CREATOR = new Parcelable.Creator<LocationReceivedItem>() {
        @Override
        public LocationReceivedItem createFromParcel(Parcel source) {
            return new LocationReceivedItem(source);
        }

        @Override
        public LocationReceivedItem[] newArray(int size) {
            return new LocationReceivedItem[size];
        }
    };
}
