package com.thinkmobiles.easyerp.data.model.crm.persons.person_item;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Lynx on 1/20/2017.
 */

public class PersonCreatedEditedBy implements Parcelable {
    public String date;
    public PersonCreatedEditedUser user;

    public PersonCreatedEditedBy() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.date);
        dest.writeParcelable(this.user, flags);
    }

    protected PersonCreatedEditedBy(Parcel in) {
        this.date = in.readString();
        this.user = in.readParcelable(PersonCreatedEditedUser.class.getClassLoader());
    }

    public static final Creator<PersonCreatedEditedBy> CREATOR = new Creator<PersonCreatedEditedBy>() {
        @Override
        public PersonCreatedEditedBy createFromParcel(Parcel source) {
            return new PersonCreatedEditedBy(source);
        }

        @Override
        public PersonCreatedEditedBy[] newArray(int size) {
            return new PersonCreatedEditedBy[size];
        }
    };
}
