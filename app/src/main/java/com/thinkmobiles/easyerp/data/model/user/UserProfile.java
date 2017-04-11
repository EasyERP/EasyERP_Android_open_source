package com.thinkmobiles.easyerp.data.model.user;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Lynx on 1/18/2017.
 */

public final class UserProfile implements Parcelable {
    @SerializedName("_id")
    public long id;
    public String profileName;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.profileName);
    }

    public UserProfile() {
    }

    protected UserProfile(Parcel in) {
        this.id = in.readLong();
        this.profileName = in.readString();
    }

    public static final Parcelable.Creator<UserProfile> CREATOR = new Parcelable.Creator<UserProfile>() {
        @Override
        public UserProfile createFromParcel(Parcel source) {
            return new UserProfile(source);
        }

        @Override
        public UserProfile[] newArray(int size) {
            return new UserProfile[size];
        }
    };
}
