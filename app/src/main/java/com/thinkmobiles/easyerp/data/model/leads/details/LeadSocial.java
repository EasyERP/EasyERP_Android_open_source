package com.thinkmobiles.easyerp.data.model.leads.details;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class LeadSocial implements Parcelable {

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.linkedIn);
        dest.writeString(this.facebook);
    }

    public LeadSocial() {
    }

    protected LeadSocial(Parcel in) {
        this.linkedIn = in.readString();
        this.facebook = in.readString();
    }

    public static final Parcelable.Creator<LeadSocial> CREATOR = new Parcelable.Creator<LeadSocial>() {
        @Override
        public LeadSocial createFromParcel(Parcel source) {
            return new LeadSocial(source);
        }

        @Override
        public LeadSocial[] newArray(int size) {
            return new LeadSocial[size];
        }
    };
}
