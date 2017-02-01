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
    public String owner;


    public Groups() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.group);
        dest.writeTypedList(this.users);
        dest.writeString(this.owner);
    }

    protected Groups(Parcel in) {
        this.group = new ArrayList<Object>();
        in.readList(this.group, Object.class.getClassLoader());
        this.users = in.createTypedArrayList(User.CREATOR);
        this.owner = in.readString();
    }

    public static final Creator<Groups> CREATOR = new Creator<Groups>() {
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
