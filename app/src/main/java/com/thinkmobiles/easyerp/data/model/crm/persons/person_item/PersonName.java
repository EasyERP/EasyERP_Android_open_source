package com.thinkmobiles.easyerp.data.model.crm.persons.person_item;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Lynx on 1/20/2017.
 */

public class PersonName implements Parcelable {
    public String first;
    public String last;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.first);
        dest.writeString(this.last);
    }

    public PersonName() {
    }

    protected PersonName(Parcel in) {
        this.first = in.readString();
        this.last = in.readString();
    }

    public static final Parcelable.Creator<PersonName> CREATOR = new Parcelable.Creator<PersonName>() {
        @Override
        public PersonName createFromParcel(Parcel source) {
            return new PersonName(source);
        }

        @Override
        public PersonName[] newArray(int size) {
            return new PersonName[size];
        }
    };
}
