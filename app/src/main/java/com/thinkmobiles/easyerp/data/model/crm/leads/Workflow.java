package com.thinkmobiles.easyerp.data.model.crm.leads;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Lynx on 1/16/2017.
 */

public class Workflow implements Parcelable {
    /**
     *  id: "528ce74ef3f67bc40b00001e",
     name: "New",
     status: "New"
     */
    public String _id;
    public String name;
    public String status;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this._id);
        dest.writeString(this.name);
        dest.writeString(this.status);
    }

    public Workflow() {
    }

    protected Workflow(Parcel in) {
        this._id = in.readString();
        this.name = in.readString();
        this.status = in.readString();
    }

    public static final Parcelable.Creator<Workflow> CREATOR = new Parcelable.Creator<Workflow>() {
        @Override
        public Workflow createFromParcel(Parcel source) {
            return new Workflow(source);
        }

        @Override
        public Workflow[] newArray(int size) {
            return new Workflow[size];
        }
    };
}
