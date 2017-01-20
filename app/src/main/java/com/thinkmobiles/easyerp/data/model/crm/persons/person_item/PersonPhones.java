package com.thinkmobiles.easyerp.data.model.crm.persons.person_item;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Lynx on 1/20/2017.
 */

public class PersonPhones implements Parcelable {
    public String phone;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.phone);
    }

    public PersonPhones() {
    }

    protected PersonPhones(Parcel in) {
        this.phone = in.readString();
    }

    public static final Parcelable.Creator<PersonPhones> CREATOR = new Parcelable.Creator<PersonPhones>() {
        @Override
        public PersonPhones createFromParcel(Parcel source) {
            return new PersonPhones(source);
        }

        @Override
        public PersonPhones[] newArray(int size) {
            return new PersonPhones[size];
        }
    };
}
