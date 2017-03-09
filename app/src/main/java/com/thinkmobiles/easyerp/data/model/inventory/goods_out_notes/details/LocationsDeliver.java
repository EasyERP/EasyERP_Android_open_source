package com.thinkmobiles.easyerp.data.model.inventory.goods_out_notes.details;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by samson on 06.03.17.
 */

public class LocationsDeliver implements Parcelable {

    @SerializedName("_id")
    public String id;
//    public CreatedEditedUserString editedBy;
//    public CreatedEditedUserString createdBy;
//    public String zone;
//    public String warehouse;
//    public String groupingD;
//    public String groupingC;
//    public String groupingB;
//    public String groupingA;
    public String name;


    public LocationsDeliver() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
    }

    protected LocationsDeliver(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
    }

    public static final Creator<LocationsDeliver> CREATOR = new Creator<LocationsDeliver>() {
        @Override
        public LocationsDeliver createFromParcel(Parcel source) {
            return new LocationsDeliver(source);
        }

        @Override
        public LocationsDeliver[] newArray(int size) {
            return new LocationsDeliver[size];
        }
    };
}
