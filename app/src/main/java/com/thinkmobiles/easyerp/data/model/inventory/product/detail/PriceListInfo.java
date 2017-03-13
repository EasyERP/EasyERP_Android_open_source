package com.thinkmobiles.easyerp.data.model.inventory.product.detail;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.thinkmobiles.easyerp.data.model.crm.invoice.Currency;
import com.thinkmobiles.easyerp.data.model.crm.invoice.CurrencyID;

/**
 * Created by samson on 13.03.17.
 */

public class PriceListInfo implements Parcelable {

    @SerializedName("_id")
    public String id;
    public String priceListCode;
    public String name;
    public CurrencyID currency;
    public boolean cost;

    public PriceListInfo() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.priceListCode);
        dest.writeString(this.name);
        dest.writeParcelable(this.currency, flags);
        dest.writeByte(this.cost ? (byte) 1 : (byte) 0);
    }

    protected PriceListInfo(Parcel in) {
        this.id = in.readString();
        this.priceListCode = in.readString();
        this.name = in.readString();
        this.currency = in.readParcelable(CurrencyID.class.getClassLoader());
        this.cost = in.readByte() != 0;
    }

    public static final Creator<PriceListInfo> CREATOR = new Creator<PriceListInfo>() {
        @Override
        public PriceListInfo createFromParcel(Parcel source) {
            return new PriceListInfo(source);
        }

        @Override
        public PriceListInfo[] newArray(int size) {
            return new PriceListInfo[size];
        }
    };
}
