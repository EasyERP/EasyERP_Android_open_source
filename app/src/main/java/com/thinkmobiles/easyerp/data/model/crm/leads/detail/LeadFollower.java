package com.thinkmobiles.easyerp.data.model.crm.leads.detail;


import android.os.Parcel;
import android.os.Parcelable;

public class LeadFollower implements Parcelable {

    /**
     * {
     "name": "Alex Filchak",
     "followerId": "564dac3e9b85f8b16b574fea",
     "_id": "587dd81923ddd08c2030f0ef"
     },
     */

    public String name;
    public String followerId;
    public String _id;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.followerId);
        dest.writeString(this._id);
    }

    public LeadFollower() {
    }

    protected LeadFollower(Parcel in) {
        this.name = in.readString();
        this.followerId = in.readString();
        this._id = in.readString();
    }

    public static final Parcelable.Creator<LeadFollower> CREATOR = new Parcelable.Creator<LeadFollower>() {
        @Override
        public LeadFollower createFromParcel(Parcel source) {
            return new LeadFollower(source);
        }

        @Override
        public LeadFollower[] newArray(int size) {
            return new LeadFollower[size];
        }
    };
}
