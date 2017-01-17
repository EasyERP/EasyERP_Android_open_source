package com.thinkmobiles.easyerp.data.model.leads;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Lynx on 1/16/2017.
 */

public class LeadEditedBy implements Parcelable {
    /**
     *  date: "2016-11-29T11:30:42.424Z"
     */
    public String date;
    public String user;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.date);
        dest.writeString(this.user);
    }

    public LeadEditedBy() {
    }

    protected LeadEditedBy(Parcel in) {
        this.date = in.readString();
        this.user = in.readString();
    }

    public static final Parcelable.Creator<LeadEditedBy> CREATOR = new Parcelable.Creator<LeadEditedBy>() {
        @Override
        public LeadEditedBy createFromParcel(Parcel source) {
            return new LeadEditedBy(source);
        }

        @Override
        public LeadEditedBy[] newArray(int size) {
            return new LeadEditedBy[size];
        }
    };
}
