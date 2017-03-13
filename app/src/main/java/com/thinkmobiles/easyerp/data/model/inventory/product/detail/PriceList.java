package com.thinkmobiles.easyerp.data.model.inventory.product.detail;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by samson on 13.03.17.
 */

public class PriceList implements Parcelable {

    @SerializedName("_id")
    public String id;
    public List<Price> prices;
    public String product;
    public PriceListInfo priceLists;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeTypedList(this.prices);
        dest.writeString(this.product);
        dest.writeParcelable(this.priceLists, flags);
    }

    public PriceList() {
    }

    protected PriceList(Parcel in) {
        this.id = in.readString();
        this.prices = in.createTypedArrayList(Price.CREATOR);
        this.product = in.readString();
        this.priceLists = in.readParcelable(PriceListInfo.class.getClassLoader());
    }

    public static final Parcelable.Creator<PriceList> CREATOR = new Parcelable.Creator<PriceList>() {
        @Override
        public PriceList createFromParcel(Parcel source) {
            return new PriceList(source);
        }

        @Override
        public PriceList[] newArray(int size) {
            return new PriceList[size];
        }
    };
}
