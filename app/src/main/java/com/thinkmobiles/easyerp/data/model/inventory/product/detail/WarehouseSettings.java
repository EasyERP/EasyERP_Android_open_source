package com.thinkmobiles.easyerp.data.model.inventory.product.detail;

import android.os.Parcel;
import android.os.Parcelable;

import com.thinkmobiles.easyerp.data.model.inventory.goods_out_notes.details.LocationsDeliver;
import com.thinkmobiles.easyerp.data.model.inventory.transfers.WarehouseInfo;

/**
 * Created by samson on 13.03.17.
 */

public class WarehouseSettings implements Parcelable {
    public LocationsDeliver location;
    public WarehouseInfo warehouse;

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
        this.location = in.readParcelable(LocationsDeliver.class.getClassLoader());
        this.warehouse = in.readParcelable(WarehouseInfo.class.getClassLoader());
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
