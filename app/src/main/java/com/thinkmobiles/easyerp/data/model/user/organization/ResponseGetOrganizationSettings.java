package com.thinkmobiles.easyerp.data.model.user.organization;

import android.os.Parcel;
import android.os.Parcelable;


public class ResponseGetOrganizationSettings implements Parcelable {

    public OrganizationSettings data;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.data, flags);
    }

    public ResponseGetOrganizationSettings() {
    }

    protected ResponseGetOrganizationSettings(Parcel in) {
        this.data = in.readParcelable(OrganizationSettings.class.getClassLoader());
    }

    public static final Parcelable.Creator<ResponseGetOrganizationSettings> CREATOR = new Parcelable.Creator<ResponseGetOrganizationSettings>() {
        @Override
        public ResponseGetOrganizationSettings createFromParcel(Parcel source) {
            return new ResponseGetOrganizationSettings(source);
        }

        @Override
        public ResponseGetOrganizationSettings[] newArray(int size) {
            return new ResponseGetOrganizationSettings[size];
        }
    };
}
