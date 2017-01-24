package com.thinkmobiles.easyerp.data.model.crm.persons.details;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Lynx on 1/24/2017.
 */

public class Categories implements Parcelable {
    public String name;
    public String id;

    public Categories() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.id);
    }

    protected Categories(Parcel in) {
        this.name = in.readString();
        this.id = in.readString();
    }

    public static final Creator<Categories> CREATOR = new Creator<Categories>() {
        @Override
        public Categories createFromParcel(Parcel source) {
            return new Categories(source);
        }

        @Override
        public Categories[] newArray(int size) {
            return new Categories[size];
        }
    };
}
