package com.thinkmobiles.easyerp.data.model.hr.employees.item;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.Name;

/**
 * Created by Lynx on 3/13/2017.
 */

public class Manager implements Parcelable {
    @SerializedName("_id")
    public String id;
    public Name name;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeParcelable(this.name, flags);
    }

    public Manager() {
    }

    protected Manager(Parcel in) {
        this.id = in.readString();
        this.name = in.readParcelable(Name.class.getClassLoader());
    }

    public static final Parcelable.Creator<Manager> CREATOR = new Parcelable.Creator<Manager>() {
        @Override
        public Manager createFromParcel(Parcel source) {
            return new Manager(source);
        }

        @Override
        public Manager[] newArray(int size) {
            return new Manager[size];
        }
    };
}
