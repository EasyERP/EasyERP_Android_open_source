package com.thinkmobiles.easyerp.data.model.crm.invoice;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * @author michael.soyma@thinkmobiles.com (Created on 1/19/2017.)
 */

public class CurrencyID implements Parcelable {

    /**
     {
        "_id": "USD",
        "name": "USD",
        "decPlace": 2,
        "symbol": "$",
        "active": true
     }
     */

    @SerializedName("_id")
    public String id;
    public String name;
    public Integer decPlace;
    public String symbol;
    public boolean active;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeValue(this.decPlace);
        dest.writeString(this.symbol);
        dest.writeByte(this.active ? (byte) 1 : (byte) 0);
    }

    public CurrencyID() {
    }

    protected CurrencyID(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.decPlace = (Integer) in.readValue(Integer.class.getClassLoader());
        this.symbol = in.readString();
        this.active = in.readByte() != 0;
    }

    public static final Parcelable.Creator<CurrencyID> CREATOR = new Parcelable.Creator<CurrencyID>() {
        @Override
        public CurrencyID createFromParcel(Parcel source) {
            return new CurrencyID(source);
        }

        @Override
        public CurrencyID[] newArray(int size) {
            return new CurrencyID[size];
        }
    };
}
