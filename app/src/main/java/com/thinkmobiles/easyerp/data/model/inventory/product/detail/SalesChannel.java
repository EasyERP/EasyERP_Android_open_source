package com.thinkmobiles.easyerp.data.model.inventory.product.detail;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by samson on 13.03.17.
 */

public class SalesChannel implements Parcelable {

    public ChannelStats stats;
    public ArrayList<ChannelResult> result;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.stats, flags);
        dest.writeTypedList(this.result);
    }

    public SalesChannel() {
    }

    protected SalesChannel(Parcel in) {
        this.stats = in.readParcelable(ChannelStats.class.getClassLoader());
        this.result = in.createTypedArrayList(ChannelResult.CREATOR);
    }

    public static final Parcelable.Creator<SalesChannel> CREATOR = new Parcelable.Creator<SalesChannel>() {
        @Override
        public SalesChannel createFromParcel(Parcel source) {
            return new SalesChannel(source);
        }

        @Override
        public SalesChannel[] newArray(int size) {
            return new SalesChannel[size];
        }
    };
}
