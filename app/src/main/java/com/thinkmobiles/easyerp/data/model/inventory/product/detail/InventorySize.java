package com.thinkmobiles.easyerp.data.model.inventory.product.detail;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by samson on 13.03.17.
 */

public class InventorySize implements Parcelable {

    public String dimension;
    public int height;
    public int width;
    public int length;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.dimension);
        dest.writeInt(this.height);
        dest.writeInt(this.width);
        dest.writeInt(this.length);
    }

    public InventorySize() {
    }

    protected InventorySize(Parcel in) {
        this.dimension = in.readString();
        this.height = in.readInt();
        this.width = in.readInt();
        this.length = in.readInt();
    }

    public static final Parcelable.Creator<InventorySize> CREATOR = new Parcelable.Creator<InventorySize>() {
        @Override
        public InventorySize createFromParcel(Parcel source) {
            return new InventorySize(source);
        }

        @Override
        public InventorySize[] newArray(int size) {
            return new InventorySize[size];
        }
    };
}
