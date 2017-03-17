package com.thinkmobiles.easyerp.data.model.inventory.product.detail;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by samson on 13.03.17.
 */

public class ProductValue implements Parcelable {


    @SerializedName("_id")
    public String id;
    public ArrayList<String> values;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeStringList(this.values);
    }

    public ProductValue() {
    }

    protected ProductValue(Parcel in) {
        this.id = in.readString();
        this.values = in.createStringArrayList();
    }

    public static final Parcelable.Creator<ProductValue> CREATOR = new Parcelable.Creator<ProductValue>() {
        @Override
        public ProductValue createFromParcel(Parcel source) {
            return new ProductValue(source);
        }

        @Override
        public ProductValue[] newArray(int size) {
            return new ProductValue[size];
        }
    };
}
