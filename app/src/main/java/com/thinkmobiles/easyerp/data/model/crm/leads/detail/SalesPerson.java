package com.thinkmobiles.easyerp.data.model.crm.leads.detail;

import android.os.Parcel;
import android.os.Parcelable;


public class SalesPerson implements Parcelable {

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
    public Name name;
    public String fullName;


    public static final Creator<SalesPerson> CREATOR = new Creator<SalesPerson>() {
        @Override
        public SalesPerson createFromParcel(Parcel in) {
            return new SalesPerson(in);
        }

        @Override
        public SalesPerson[] newArray(int size) {
            return new SalesPerson[size];
        }
    };

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

    public SalesPerson() {
    }

    protected SalesPerson(Parcel in) {
        this._id = in.readString();
        this.id = in.readString();
        this.name = in.readParcelable(Name.class.getClassLoader());
        this.fullName = in.readString();
    }

}
