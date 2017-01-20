package com.thinkmobiles.easyerp.data.model.crm.persons.person_item;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Lynx on 1/20/2017.
 */

public class PersonCreatedEditedUser implements Parcelable {
    /**
     * user: {
     _id: "5836ec22d291dd500cf6e16a",
     login: "testAdmin"
     }
     */
    @SerializedName("_id")
    public String id;
    public String login;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.login);
    }

    public PersonCreatedEditedUser() {
    }

    protected PersonCreatedEditedUser(Parcel in) {
        this.id = in.readString();
        this.login = in.readString();
    }

    public static final Parcelable.Creator<PersonCreatedEditedUser> CREATOR = new Parcelable.Creator<PersonCreatedEditedUser>() {
        @Override
        public PersonCreatedEditedUser createFromParcel(Parcel source) {
            return new PersonCreatedEditedUser(source);
        }

        @Override
        public PersonCreatedEditedUser[] newArray(int size) {
            return new PersonCreatedEditedUser[size];
        }
    };
}
