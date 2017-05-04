package com.thinkmobiles.easyerp.data.model.integrations;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Michael Soyma (Created on 5/3/2017).
 *         Company: Thinkmobiles
 *         Email:  michael.soyma@thinkmobiles.com
 */
public final class WarehouseSettings implements Parcelable {

    public Location location;
    public Warehouse warehouse;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.location, flags);
        dest.writeParcelable(this.warehouse, flags);
    }

    public WarehouseSettings() {
    }

    protected WarehouseSettings(Parcel in) {
        this.location = in.readParcelable(Location.class.getClassLoader());
        this.warehouse = in.readParcelable(Warehouse.class.getClassLoader());
    }

    public static final Parcelable.Creator<WarehouseSettings> CREATOR = new Parcelable.Creator<WarehouseSettings>() {
        @Override
        public WarehouseSettings createFromParcel(Parcel source) {
            return new WarehouseSettings(source);
        }

        @Override
        public WarehouseSettings[] newArray(int size) {
            return new WarehouseSettings[size];
        }
    };
}
