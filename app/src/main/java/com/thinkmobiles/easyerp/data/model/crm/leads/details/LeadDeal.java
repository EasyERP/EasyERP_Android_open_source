package com.thinkmobiles.easyerp.data.model.crm.leads.details;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by samson on 17.01.17.
 */

public class LeadDeal implements Parcelable {

    /**
     * "deal": {
     "_id": "587dd7a923ddd08c2030f0e8",
     "name": "Android application for Enterprise"
     },
     */

    public String _id;
    public String name;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this._id);
        dest.writeString(this.name);
    }

    public LeadDeal() {
    }

    protected LeadDeal(Parcel in) {
        this._id = in.readString();
        this.name = in.readString();
    }

    public static final Parcelable.Creator<LeadDeal> CREATOR = new Parcelable.Creator<LeadDeal>() {
        @Override
        public LeadDeal createFromParcel(Parcel source) {
            return new LeadDeal(source);
        }

        @Override
        public LeadDeal[] newArray(int size) {
            return new LeadDeal[size];
        }
    };
}
