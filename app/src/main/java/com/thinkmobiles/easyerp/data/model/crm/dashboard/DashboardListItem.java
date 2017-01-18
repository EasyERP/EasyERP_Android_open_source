package com.thinkmobiles.easyerp.data.model.crm.dashboard;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Asus_Dev on 1/18/2017.
 */

public class DashboardListItem implements Parcelable {

    public int id;
    public String paramUrl;
    public String label;

    public DashboardListItem(int id, String paramUrl, String label) {
        this.id = id;
        this.paramUrl = paramUrl;
        this.label = label;
    }

    protected DashboardListItem(Parcel in) {
        this.id = in.readInt();
        this.paramUrl = in.readString();
        this.label = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.paramUrl);
        dest.writeString(this.label);
    }

    public static final Parcelable.Creator<DashboardListItem> CREATOR = new Parcelable.Creator<DashboardListItem>() {
        @Override
        public DashboardListItem createFromParcel(Parcel source) {
            return new DashboardListItem(source);
        }

        @Override
        public DashboardListItem[] newArray(int size) {
            return new DashboardListItem[size];
        }
    };
}
