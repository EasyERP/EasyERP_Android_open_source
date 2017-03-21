package com.thinkmobiles.easyerp.data.model.hr.employees.details;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Lynx on 3/17/2017.
 */

public class EventReason implements Parcelable {
    public String date;
    public String reason;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.date);
        dest.writeString(this.reason);
    }

    public EventReason() {
    }

    protected EventReason(Parcel in) {
        this.date = in.readString();
        this.reason = in.readString();
    }

    public static final Parcelable.Creator<EventReason> CREATOR = new Parcelable.Creator<EventReason>() {
        @Override
        public EventReason createFromParcel(Parcel source) {
            return new EventReason(source);
        }

        @Override
        public EventReason[] newArray(int size) {
            return new EventReason[size];
        }
    };
}
