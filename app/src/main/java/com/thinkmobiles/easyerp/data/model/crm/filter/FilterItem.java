package com.thinkmobiles.easyerp.data.model.crm.filter;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


public class FilterItem implements Parcelable {

    /**
     * {
     "_id": "Ross OCrowley",
     "name": "Ross OCrowley"
     }
     */
    @SerializedName("_id")
    public String id;
    public String name;

    @Override
    public String toString() {
        return name;
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

    public FilterItem() {
    }

    protected FilterItem(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
    }

    public static final Parcelable.Creator<FilterItem> CREATOR = new Parcelable.Creator<FilterItem>() {
        @Override
        public FilterItem createFromParcel(Parcel source) {
            return new FilterItem(source);
        }

        @Override
        public FilterItem[] newArray(int size) {
            return new FilterItem[size];
        }
    };
}
