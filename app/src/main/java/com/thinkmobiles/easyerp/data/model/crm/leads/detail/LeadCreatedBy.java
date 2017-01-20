package com.thinkmobiles.easyerp.data.model.crm.leads.detail;


import android.os.Parcel;
import android.os.Parcelable;

public class LeadCreatedBy implements Parcelable {

    public String date;
    public LeadUser user;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.date);
        dest.writeParcelable(this.user, flags);
    }

    public LeadCreatedBy() {
    }

    protected LeadCreatedBy(Parcel in) {
        this.date = in.readString();
        this.user = in.readParcelable(LeadUser.class.getClassLoader());
    }

    public static final Parcelable.Creator<LeadCreatedBy> CREATOR = new Parcelable.Creator<LeadCreatedBy>() {
        @Override
        public LeadCreatedBy createFromParcel(Parcel source) {
            return new LeadCreatedBy(source);
        }

        @Override
        public LeadCreatedBy[] newArray(int size) {
            return new LeadCreatedBy[size];
        }
    };
}
