package com.thinkmobiles.easyerp.data.model.crm.leads.detail;


import android.os.Parcel;
import android.os.Parcelable;

public class CreatedEditedBy implements Parcelable {

    public String date;
    public User user;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.date);
        dest.writeParcelable(this.user, flags);
    }

    public CreatedEditedBy() {
    }

    protected CreatedEditedBy(Parcel in) {
        this.date = in.readString();
        this.user = in.readParcelable(User.class.getClassLoader());
    }

    public static final Parcelable.Creator<CreatedEditedBy> CREATOR = new Parcelable.Creator<CreatedEditedBy>() {
        @Override
        public CreatedEditedBy createFromParcel(Parcel source) {
            return new CreatedEditedBy(source);
        }

        @Override
        public CreatedEditedBy[] newArray(int size) {
            return new CreatedEditedBy[size];
        }
    };
}
