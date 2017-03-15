package com.thinkmobiles.easyerp.data.model.hr.job_positions.detail;

import android.os.Parcel;
import android.os.Parcelable;

import com.thinkmobiles.easyerp.data.model.crm.filter.FilterItem;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Michael Soyma (Created on 3/15/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public final class Groups implements Parcelable {

    public List<Object> group;
    public List<User> users;
    public User owner;

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
        dest.writeParcelable(this.owner, flags);
    }

    protected Groups(Parcel in) {
        this.group = new ArrayList<Object>();
        in.readList(this.group, Object.class.getClassLoader());
        this.users = in.createTypedArrayList(User.CREATOR);
        this.owner = in.readParcelable(FilterItem.class.getClassLoader());
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
