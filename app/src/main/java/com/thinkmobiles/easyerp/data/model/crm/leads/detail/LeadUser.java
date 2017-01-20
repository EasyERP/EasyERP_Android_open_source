package com.thinkmobiles.easyerp.data.model.crm.leads.detail;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by samson on 16.01.17.
 */

public class LeadUser implements Parcelable {


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

    public LeadUser() {
    }

    protected LeadUser(Parcel in) {
        this.id = in.readString();
        this.login = in.readString();
    }

    public static final Parcelable.Creator<LeadUser> CREATOR = new Parcelable.Creator<LeadUser>() {
        @Override
        public LeadUser createFromParcel(Parcel source) {
            return new LeadUser(source);
        }

        @Override
        public LeadUser[] newArray(int size) {
            return new LeadUser[size];
        }
    };
}
