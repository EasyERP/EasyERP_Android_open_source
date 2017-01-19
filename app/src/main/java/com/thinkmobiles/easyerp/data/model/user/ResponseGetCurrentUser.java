package com.thinkmobiles.easyerp.data.model.user;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Lynx on 1/18/2017.
 */

public class ResponseGetCurrentUser implements Parcelable {
    public UserInfo user;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.user, flags);
    }

    public ResponseGetCurrentUser() {
    }

    protected ResponseGetCurrentUser(Parcel in) {
        this.user = in.readParcelable(UserInfo.class.getClassLoader());
    }

    public static final Parcelable.Creator<ResponseGetCurrentUser> CREATOR = new Parcelable.Creator<ResponseGetCurrentUser>() {
        @Override
        public ResponseGetCurrentUser createFromParcel(Parcel source) {
            return new ResponseGetCurrentUser(source);
        }

        @Override
        public ResponseGetCurrentUser[] newArray(int size) {
            return new ResponseGetCurrentUser[size];
        }
    };
}
