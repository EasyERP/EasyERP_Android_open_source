package com.thinkmobiles.easyerp.data.model.crm.opportunities.list_item;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Lynx on 1/30/2017.
 */

public class ExpectedRevenue implements Parcelable {
    public String currency;
    public long progress;
    public long value;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.currency);
        dest.writeLong(this.progress);
        dest.writeLong(this.value);
    }

    public ExpectedRevenue() {
    }

    protected ExpectedRevenue(Parcel in) {
        this.currency = in.readString();
        this.progress = in.readLong();
        this.value = in.readLong();
    }

    public static final Parcelable.Creator<ExpectedRevenue> CREATOR = new Parcelable.Creator<ExpectedRevenue>() {
        @Override
        public ExpectedRevenue createFromParcel(Parcel source) {
            return new ExpectedRevenue(source);
        }

        @Override
        public ExpectedRevenue[] newArray(int size) {
            return new ExpectedRevenue[size];
        }
    };
}
