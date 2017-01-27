package com.thinkmobiles.easyerp.data.model.crm.leads.detail;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by samson on 16.01.17.
 */

public class User implements Parcelable {


    /**
    "user": {
        "_id": "5836ec22d291dd500cf6e16a",
                "login": "testAdmin"
    },
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

    public User() {
    }

    protected User(Parcel in) {
        this.id = in.readString();
        this.login = in.readString();
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
