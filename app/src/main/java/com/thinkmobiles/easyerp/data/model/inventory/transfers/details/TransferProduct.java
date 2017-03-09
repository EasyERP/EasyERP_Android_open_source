package com.thinkmobiles.easyerp.data.model.inventory.transfers.details;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.thinkmobiles.easyerp.data.model.crm.order.detail.ProductInfo;

/**
 * Created by Lynx on 3/9/2017.
 */

public class TransferProduct implements Parcelable {
    @SerializedName("_id")
    public String id;
    public String name;
    public ProductInfo info;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeParcelable(this.info, flags);
    }

    public TransferProduct() {
    }

    protected TransferProduct(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.info = in.readParcelable(ProductInfo.class.getClassLoader());
    }

    public static final Parcelable.Creator<TransferProduct> CREATOR = new Parcelable.Creator<TransferProduct>() {
        @Override
        public TransferProduct createFromParcel(Parcel source) {
            return new TransferProduct(source);
        }

        @Override
        public TransferProduct[] newArray(int size) {
            return new TransferProduct[size];
        }
    };
}
