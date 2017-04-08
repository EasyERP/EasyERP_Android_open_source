package com.thinkmobiles.easyerp.data.model.reports.general;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Michael Soyma (Created on 4/8/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public final class DateRange implements Parcelable {

    public String from;
    public String to;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.from);
        dest.writeString(this.to);
    }

    public DateRange() {
    }

    protected DateRange(Parcel in) {
        this.from = in.readString();
        this.to = in.readString();
    }

    public static final Parcelable.Creator<DateRange> CREATOR = new Parcelable.Creator<DateRange>() {
        @Override
        public DateRange createFromParcel(Parcel source) {
            return new DateRange(source);
        }

        @Override
        public DateRange[] newArray(int size) {
            return new DateRange[size];
        }
    };
}
