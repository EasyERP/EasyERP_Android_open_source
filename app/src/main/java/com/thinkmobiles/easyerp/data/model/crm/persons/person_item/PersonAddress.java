package com.thinkmobiles.easyerp.data.model.crm.persons.person_item;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Lynx on 1/20/2017.
 */

public class PersonAddress implements Parcelable {
    public String country;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.country);
    }

    public PersonAddress() {
    }

    protected PersonAddress(Parcel in) {
        this.country = in.readString();
    }

    public static final Parcelable.Creator<PersonAddress> CREATOR = new Parcelable.Creator<PersonAddress>() {
        @Override
        public PersonAddress createFromParcel(Parcel source) {
            return new PersonAddress(source);
        }

        @Override
        public PersonAddress[] newArray(int size) {
            return new PersonAddress[size];
        }
    };
}
