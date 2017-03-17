package com.thinkmobiles.easyerp.data.model.inventory.product.detail;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by samson on 13.03.17.
 */

public class StatItem implements Parcelable {

    @SerializedName("_id")
    public String id;
    public int count;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeInt(this.count);
    }

    public StatItem() {
    }

    protected StatItem(Parcel in) {
        this.id = in.readString();
        this.count = in.readInt();
    }

    public static final Parcelable.Creator<StatItem> CREATOR = new Parcelable.Creator<StatItem>() {
        @Override
        public StatItem createFromParcel(Parcel source) {
            return new StatItem(source);
        }

        @Override
        public StatItem[] newArray(int size) {
            return new StatItem[size];
        }
    };
}
