package com.thinkmobiles.easyerp.data.model.integrations;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * @author Michael Soyma (Created on 5/4/2017).
 *         Company: Thinkmobiles
 *         Email:  michael.soyma@thinkmobiles.com
 */
public final class Stats implements Parcelable {

    public List<StatsCount> conflictProducts;
    public List<StatsCount> importedOrders;
    public List<StatsCount> importedProducts;
    public List<StatsCount> unlinkedOrders;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.conflictProducts);
        dest.writeTypedList(this.importedOrders);
        dest.writeTypedList(this.importedProducts);
        dest.writeTypedList(this.unlinkedOrders);
    }

    public Stats() {
    }

    protected Stats(Parcel in) {
        this.conflictProducts = in.createTypedArrayList(StatsCount.CREATOR);
        this.importedOrders = in.createTypedArrayList(StatsCount.CREATOR);
        this.importedProducts = in.createTypedArrayList(StatsCount.CREATOR);
        this.unlinkedOrders = in.createTypedArrayList(StatsCount.CREATOR);
    }

    public static final Parcelable.Creator<Stats> CREATOR = new Parcelable.Creator<Stats>() {
        @Override
        public Stats createFromParcel(Parcel source) {
            return new Stats(source);
        }

        @Override
        public Stats[] newArray(int size) {
            return new Stats[size];
        }
    };
}
