package com.thinkmobiles.easyerp.data.model.crm.dashboard.detail.invoice;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Asus_Dev on 1/19/2017.
 */

public class Currency implements Parcelable {

    public int rate;
    @SerializedName("_id")
    public CurrencyID id;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.rate);
        dest.writeParcelable(this.id, flags);
    }

    public Currency() {
    }

    protected Currency(Parcel in) {
        this.rate = in.readInt();
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
