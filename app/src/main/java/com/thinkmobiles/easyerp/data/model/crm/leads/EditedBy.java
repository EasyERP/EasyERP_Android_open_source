package com.thinkmobiles.easyerp.data.model.crm.leads;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Lynx on 1/16/2017.
 */

public class EditedBy implements Parcelable {
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

    public EditedBy() {
    }

    protected EditedBy(Parcel in) {
        this.date = in.readString();
        this.user = in.readString();
    }

    public static final Parcelable.Creator<EditedBy> CREATOR = new Parcelable.Creator<EditedBy>() {
        @Override
        public EditedBy createFromParcel(Parcel source) {
            return new EditedBy(source);
        }

        @Override
        public EditedBy[] newArray(int size) {
            return new EditedBy[size];
        }
    };
}
