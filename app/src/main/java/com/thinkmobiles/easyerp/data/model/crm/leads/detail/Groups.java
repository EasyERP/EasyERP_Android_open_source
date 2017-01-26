package com.thinkmobiles.easyerp.data.model.crm.leads.detail;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by samson on 16.01.17.
 */

public class Groups implements Parcelable {

    /**
     *
     "groups": {
     "group": [],
     "users": [],
     "owner": null
     },
     */

    public ArrayList<Object> group;
    public ArrayList<User> users;
    public User owner;


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

    public Groups() {
    }

    protected Groups(Parcel in) {
        this.group = new ArrayList<Object>();
        in.readList(this.group, Object.class.getClassLoader());
        this.users = in.createTypedArrayList(User.CREATOR);
        this.owner = in.readParcelable(User.class.getClassLoader());
    }

    public static final Parcelable.Creator<Groups> CREATOR = new Parcelable.Creator<Groups>() {
        @Override
        public Groups createFromParcel(Parcel source) {
            return new Groups(source);
        }

        @Override
        public Groups[] newArray(int size) {
            return new Groups[size];
        }
    };
}
