package com.thinkmobiles.easyerp.data.model.leads;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Lynx on 1/16/2017.
 */

public class LeadSalesPerson implements Parcelable {
    /**
     * _id: "56e17661177f76f72edf774c",
     name: "Bogdana Stets"
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

    public LeadSalesPerson() {
    }

    protected LeadSalesPerson(Parcel in) {
        this._id = in.readString();
        this.name = in.readString();
    }

    public static final Parcelable.Creator<LeadSalesPerson> CREATOR = new Parcelable.Creator<LeadSalesPerson>() {
        @Override
        public LeadSalesPerson createFromParcel(Parcel source) {
            return new LeadSalesPerson(source);
        }

        @Override
        public LeadSalesPerson[] newArray(int size) {
            return new LeadSalesPerson[size];
        }
    };
}
