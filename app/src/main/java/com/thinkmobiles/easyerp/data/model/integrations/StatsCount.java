package com.thinkmobiles.easyerp.data.model.integrations;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * @author Michael Soyma (Created on 5/4/2017).
 *         Company: Thinkmobiles
 *         Email:  michael.soyma@thinkmobiles.com
 */
public final class StatsCount implements Parcelable {

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

    public StatsCount() {
    }

    protected StatsCount(Parcel in) {
        this.id = in.readString();
        this.count = in.readInt();
    }

    public static final Parcelable.Creator<StatsCount> CREATOR = new Parcelable.Creator<StatsCount>() {
        @Override
        public StatsCount createFromParcel(Parcel source) {
            return new StatsCount(source);
        }

        @Override
        public StatsCount[] newArray(int size) {
            return new StatsCount[size];
        }
    };
}
