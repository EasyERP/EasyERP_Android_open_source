package com.thinkmobiles.easyerp.data.model.inventory.product.detail;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by samson on 13.03.17.
 */

public class ProductInventory implements Parcelable {

    public int minStockLevel;
    public String warehouseMsg;
    public InventorySize size;
    public double weight;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.minStockLevel);
        dest.writeString(this.warehouseMsg);
        dest.writeParcelable(this.size, flags);
        dest.writeDouble(this.weight);
    }

    public ProductInventory() {
    }

    protected ProductInventory(Parcel in) {
        this.minStockLevel = in.readInt();
        this.warehouseMsg = in.readString();
        this.size = in.readParcelable(InventorySize.class.getClassLoader());
        this.weight = in.readDouble();
    }

    public static final Parcelable.Creator<ProductInventory> CREATOR = new Parcelable.Creator<ProductInventory>() {
        @Override
        public ProductInventory createFromParcel(Parcel source) {
            return new ProductInventory(source);
        }

        @Override
        public ProductInventory[] newArray(int size) {
            return new ProductInventory[size];
        }
    };
}
