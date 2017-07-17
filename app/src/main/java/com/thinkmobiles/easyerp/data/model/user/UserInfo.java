package com.thinkmobiles.easyerp.data.model.user;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Lynx on 1/18/2017.
 */

public final class UserInfo implements Parcelable {
    @SerializedName("_id")
    public String id;
    public String imageSrc; //base64
    public String login;
    public String email;
    public String lastAccess;
    public UserFavorite favorite;
    public UserProfile profile;
    public String mobilePhone;
    public String company;
    public String website;

    public UserInfo() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.imageSrc);
        dest.writeString(this.login);
        dest.writeString(this.email);
        dest.writeString(this.lastAccess);
        dest.writeParcelable(this.favorite, flags);
        dest.writeParcelable(this.profile, flags);
        dest.writeString(this.mobilePhone);
        dest.writeString(this.company);
        dest.writeString(this.website);
    }

    protected UserInfo(Parcel in) {
        this.id = in.readString();
        this.imageSrc = in.readString();
        this.login = in.readString();
        this.email = in.readString();
        this.lastAccess = in.readString();
        this.favorite = in.readParcelable(UserFavorite.class.getClassLoader());
        this.profile = in.readParcelable(UserProfile.class.getClassLoader());
        this.mobilePhone = in.readString();
        this.company = in.readString();
        this.website = in.readString();
    }

    public static final Creator<UserInfo> CREATOR = new Creator<UserInfo>() {
        @Override
        public UserInfo createFromParcel(Parcel source) {
            return new UserInfo(source);
        }

        @Override
        public UserInfo[] newArray(int size) {
            return new UserInfo[size];
        }
    };
}
