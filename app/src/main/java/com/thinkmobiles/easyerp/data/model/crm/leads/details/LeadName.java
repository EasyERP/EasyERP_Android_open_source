package com.thinkmobiles.easyerp.data.model.crm.leads.details;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by samson on 17.01.17.
 */

public class LeadName implements Parcelable {

    /**
     * "name": {
     "last": "Hoshylyk",
     "first": "Alex"
     },
     */

    public String first;
    public String last;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.first);
        dest.writeString(this.last);
    }

    public LeadName() {
    }

    protected LeadName(Parcel in) {
        this.first = in.readString();
        this.last = in.readString();
    }

    public static final Parcelable.Creator<LeadName> CREATOR = new Parcelable.Creator<LeadName>() {
        @Override
        public LeadName createFromParcel(Parcel source) {
            return new LeadName(source);
        }

        @Override
        public LeadName[] newArray(int size) {
            return new LeadName[size];
        }
    };
}
