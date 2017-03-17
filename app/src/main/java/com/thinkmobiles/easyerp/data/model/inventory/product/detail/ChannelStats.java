package com.thinkmobiles.easyerp.data.model.inventory.product.detail;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by samson on 13.03.17.
 */

public class ChannelStats implements Parcelable {

    public ArrayList<StatItem> importedProducts;
    public ArrayList<StatItem> importedOrders;
    public ArrayList<StatItem> conflictProducts;
    public ArrayList<StatItem> unlinkedOrders;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.importedProducts);
        dest.writeTypedList(this.importedOrders);
        dest.writeTypedList(this.conflictProducts);
        dest.writeTypedList(this.unlinkedOrders);
    }

    public ChannelStats() {
    }

    protected ChannelStats(Parcel in) {
        this.importedProducts = in.createTypedArrayList(StatItem.CREATOR);
        this.importedOrders = in.createTypedArrayList(StatItem.CREATOR);
        this.conflictProducts = in.createTypedArrayList(StatItem.CREATOR);
        this.unlinkedOrders = in.createTypedArrayList(StatItem.CREATOR);
    }

    public static final Parcelable.Creator<ChannelStats> CREATOR = new Parcelable.Creator<ChannelStats>() {
        @Override
        public ChannelStats createFromParcel(Parcel source) {
            return new ChannelStats(source);
        }

        @Override
        public ChannelStats[] newArray(int size) {
            return new ChannelStats[size];
        }
    };
}
