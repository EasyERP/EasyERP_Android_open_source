package com.thinkmobiles.easyerp.data.model.crm.invoice;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * @author michael.soyma@thinkmobiles.com (Created on 1/19/2017.)
 */

public class Currency implements Parcelable {

    public Double rate;
    @SerializedName("_id")
    public CurrencyID id;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.rate);
        dest.writeParcelable(this.id, flags);
    }

    public Currency() {
    }

    protected Currency(Parcel in) {
        this.rate = (Double) in.readValue(Double.class.getClassLoader());
        this.id = in.readParcelable(CurrencyID.class.getClassLoader());
    }

    public static final Creator<Currency> CREATOR = new Creator<Currency>() {
        @Override
        public Currency createFromParcel(Parcel source) {
            return new Currency(source);
        }

        @Override
        public Currency[] newArray(int size) {
            return new Currency[size];
        }
    };
}
