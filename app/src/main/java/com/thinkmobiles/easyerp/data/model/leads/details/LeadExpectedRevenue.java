package com.thinkmobiles.easyerp.data.model.leads.details;


import android.os.Parcel;
import android.os.Parcelable;

public class LeadExpectedRevenue implements Parcelable {

    /**
     * "expectedRevenue": {
     "currency": "",
     "progress": 0,
     "value": 0
     },
     */

    public String currency;
    public int progress;
    public int value;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.currency);
        dest.writeInt(this.progress);
        dest.writeInt(this.value);
    }

    public LeadExpectedRevenue() {
    }

    protected LeadExpectedRevenue(Parcel in) {
        this.currency = in.readString();
        this.progress = in.readInt();
        this.value = in.readInt();
    }

    public static final Parcelable.Creator<LeadExpectedRevenue> CREATOR = new Parcelable.Creator<LeadExpectedRevenue>() {
        @Override
        public LeadExpectedRevenue createFromParcel(Parcel source) {
            return new LeadExpectedRevenue(source);
        }

        @Override
        public LeadExpectedRevenue[] newArray(int size) {
            return new LeadExpectedRevenue[size];
        }
    };
}
