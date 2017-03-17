package com.thinkmobiles.easyerp.data.model.inventory.product.detail;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by samson on 13.03.17.
 */

public class StockInventory implements Parcelable {
    public ArrayList<InventoryItem> data;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.data);
    }

    public StockInventory() {
    }

    protected StockInventory(Parcel in) {
        this.data = in.createTypedArrayList(InventoryItem.CREATOR);
    }

    public static final Parcelable.Creator<StockInventory> CREATOR = new Parcelable.Creator<StockInventory>() {
        @Override
        public StockInventory createFromParcel(Parcel source) {
            return new StockInventory(source);
        }

        @Override
        public StockInventory[] newArray(int size) {
            return new StockInventory[size];
        }
    };
}
