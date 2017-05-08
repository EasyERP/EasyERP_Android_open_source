package com.thinkmobiles.easyerp.data.model.integrations;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * @author Michael Soyma (Created on 5/3/2017).
 *         Company: Thinkmobiles
 *         Email:  michael.soyma@thinkmobiles.com
 */
public final class PriceList implements Parcelable {

    @SerializedName("_id")
    public String id;
    public boolean cost;
    public String currency;
    public String name;
    public String priceListCode;

    public PriceList() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeByte(this.cost ? (byte) 1 : (byte) 0);
        dest.writeString(this.currency);
        dest.writeString(this.name);
        dest.writeString(this.priceListCode);
    }

    protected PriceList(Parcel in) {
        this.id = in.readString();
        this.cost = in.readByte() != 0;
        this.currency = in.readString();
        this.name = in.readString();
        this.priceListCode = in.readString();
    }

    public static final Creator<PriceList> CREATOR = new Creator<PriceList>() {
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
