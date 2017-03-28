package com.thinkmobiles.easyerp.data.model.hr.birthdays;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Michael Soyma (Created on 3/28/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public final class BirthdaysResponse implements Parcelable {

    public Birthdays data;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.data, flags);
    }

    public BirthdaysResponse() {
    }

    protected BirthdaysResponse(Parcel in) {
        this.data = in.readParcelable(Birthdays.class.getClassLoader());
    }

    public static final Parcelable.Creator<BirthdaysResponse> CREATOR = new Parcelable.Creator<BirthdaysResponse>() {
        @Override
        public BirthdaysResponse createFromParcel(Parcel source) {
            return new BirthdaysResponse(source);
        }

        @Override
        public BirthdaysResponse[] newArray(int size) {
            return new BirthdaysResponse[size];
        }
    };
}
