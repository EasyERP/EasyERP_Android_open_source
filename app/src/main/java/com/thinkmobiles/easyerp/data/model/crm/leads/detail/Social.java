package com.thinkmobiles.easyerp.data.model.crm.leads.detail;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Social implements Parcelable {

    /**
     * "social": {
            "LI": "",
            "FB": ""
     },
     */

    @SerializedName("LI")
    public String linkedIn;
    @SerializedName("FB")
    public String facebook;
    @SerializedName("GP")
    public String googlePlus;

    public Social() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.linkedIn);
        dest.writeString(this.facebook);
        dest.writeString(this.googlePlus);
    }

    protected Social(Parcel in) {
        this.linkedIn = in.readString();
        this.facebook = in.readString();
        this.googlePlus = in.readString();
    }

    public static final Creator<Social> CREATOR = new Creator<Social>() {
        @Override
        public Social createFromParcel(Parcel source) {
            return new Social(source);
        }

        @Override
        public Social[] newArray(int size) {
            return new Social[size];
        }
    };
}
