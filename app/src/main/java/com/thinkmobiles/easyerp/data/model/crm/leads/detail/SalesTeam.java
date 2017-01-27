package com.thinkmobiles.easyerp.data.model.crm.leads.detail;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Lynx on 1/25/2017.
 */

public class SalesTeam implements Parcelable {
    /**
     * {
     _id: "55b92ace21e4b7c40f000010",
     name: "Android"
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

    public SalesTeam() {
    }

    protected SalesTeam(Parcel in) {
        this._id = in.readString();
        this.name = in.readString();
    }

    public static final Parcelable.Creator<SalesTeam> CREATOR = new Parcelable.Creator<SalesTeam>() {
        @Override
        public SalesTeam createFromParcel(Parcel source) {
            return new SalesTeam(source);
        }

        @Override
        public SalesTeam[] newArray(int size) {
            return new SalesTeam[size];
        }
    };
}
