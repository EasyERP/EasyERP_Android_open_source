package com.thinkmobiles.easyerp.data.model.leads.details;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by samson on 17.01.17.
 */

public class LeadSalesPerson implements Parcelable {

    /**
     * "salesPerson": {
     "_id": "57b2b364bd6aa28c23f3ee9b",
     "name": {
     "last": "Hoshylyk",
     "first": "Alex"
     },
     "fullName": "Alex Hoshylyk",
     "id": "57b2b364bd6aa28c23f3ee9b"
     },
     */

    public String _id;
    public String id;
    public LeadName name;
    public String fullName;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this._id);
        dest.writeString(this.id);
        dest.writeParcelable(this.name, flags);
        dest.writeString(this.fullName);
    }

    public LeadSalesPerson() {
    }

    protected LeadSalesPerson(Parcel in) {
        this._id = in.readString();
        this.id = in.readString();
        this.name = in.readParcelable(LeadName.class.getClassLoader());
        this.fullName = in.readString();
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
