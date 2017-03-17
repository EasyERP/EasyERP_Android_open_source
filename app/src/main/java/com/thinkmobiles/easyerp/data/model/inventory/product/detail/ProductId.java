package com.thinkmobiles.easyerp.data.model.inventory.product.detail;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by samson on 13.03.17.
 */

public class ProductId implements Parcelable {

    public String id;
    public String subId;
    public String productId;
    public String sku;

    public ProductId() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.subId);
        dest.writeString(this.productId);
        dest.writeString(this.sku);
    }

    protected ProductId(Parcel in) {
        this.id = in.readString();
        this.subId = in.readString();
        this.productId = in.readString();
        this.sku = in.readString();
    }

    public static final Creator<ProductId> CREATOR = new Creator<ProductId>() {
        @Override
        public ProductId createFromParcel(Parcel source) {
            return new ProductId(source);
        }

        @Override
        public ProductId[] newArray(int size) {
            return new ProductId[size];
        }
    };
}
