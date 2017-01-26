package com.thinkmobiles.easyerp.data.model.crm.dashboard.detail.invoice;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by samson on 17.01.17.
 */

public class SalesPerson implements Parcelable {

    /**
     * "salesPerson": {
     "_id": "57b2b364bd6aa28c23f3ee9b",
     "name": "asdsad",
     "fullName": "Alex Hoshylyk",
     "id": "57b2b364bd6aa28c23f3ee9b"
     },
     */

    public String _id;
    public String id;
    public String name;
    public String fullName;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this._id);
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.fullName);
    }

    public SalesPerson() {
    }

    protected SalesPerson(Parcel in) {
        this._id = in.readString();
        this.id = in.readString();
        this.name = in.readString();
        this.fullName = in.readString();
    }

    public static final Creator<SalesPerson> CREATOR = new Creator<SalesPerson>() {
        @Override
        public SalesPerson createFromParcel(Parcel source) {
            return new SalesPerson(source);
        }

        @Override
        public SalesPerson[] newArray(int size) {
            return new SalesPerson[size];
        }
    };
}
