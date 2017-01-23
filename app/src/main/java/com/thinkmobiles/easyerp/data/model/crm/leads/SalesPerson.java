package com.thinkmobiles.easyerp.data.model.crm.leads;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Lynx on 1/16/2017.
 */

public class SalesPerson implements Parcelable {
    /**
     * id: "56e17661177f76f72edf774c",
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

    public SalesPerson() {
    }

    protected SalesPerson(Parcel in) {
        this._id = in.readString();
        this.name = in.readString();
    }

    public static final Parcelable.Creator<SalesPerson> CREATOR = new Parcelable.Creator<SalesPerson>() {
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
