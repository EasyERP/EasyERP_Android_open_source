package com.thinkmobiles.easyerp.data.model.crm.persons.details;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Lynx on 1/24/2017.
 */

public class CreatedEditedUserString implements Parcelable {
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

    public CreatedEditedUserString() {
    }

    protected CreatedEditedUserString(Parcel in) {
        this.date = in.readString();
        this.user = in.readString();
    }

    public static final Parcelable.Creator<CreatedEditedUserString> CREATOR = new Parcelable.Creator<CreatedEditedUserString>() {
        @Override
        public CreatedEditedUserString createFromParcel(Parcel source) {
            return new CreatedEditedUserString(source);
        }

        @Override
        public CreatedEditedUserString[] newArray(int size) {
            return new CreatedEditedUserString[size];
        }
    };
}
