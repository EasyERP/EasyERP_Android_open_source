package com.thinkmobiles.easyerp.data.model.crm.leads.detail;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by samson on 16.01.17.
 */

public class LeadGroups implements Parcelable {

    /**
     *
     "groups": {
     "group": [],
     "users": [],
     "owner": null
     },
     */

    public ArrayList<Object> group;
    public ArrayList<LeadUser> users;
    public LeadUser owner;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.group);
        dest.writeTypedList(this.users);
        dest.writeParcelable(this.owner, flags);
    }

    public LeadGroups() {
    }

    protected LeadGroups(Parcel in) {
        this.group = new ArrayList<Object>();
        in.readList(this.group, Object.class.getClassLoader());
        this.users = in.createTypedArrayList(LeadUser.CREATOR);
        this.owner = in.readParcelable(LeadUser.class.getClassLoader());
    }

    public static final Parcelable.Creator<LeadGroups> CREATOR = new Parcelable.Creator<LeadGroups>() {
        @Override
        public LeadGroups createFromParcel(Parcel source) {
            return new LeadGroups(source);
        }

        @Override
        public LeadGroups[] newArray(int size) {
            return new LeadGroups[size];
        }
    };
}
