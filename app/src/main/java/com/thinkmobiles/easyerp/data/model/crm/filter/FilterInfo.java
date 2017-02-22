package com.thinkmobiles.easyerp.data.model.crm.filter;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * @author Alex Michenko (Created on 16.02.17).
 *         Company: Thinkmobiles
 *         Email: alex.michenko@thinkmobiles.com
 */

public class FilterInfo implements Parcelable {

    public ArrayList<FilterItem> list;
    public String name;
    public String type;
    public String key;
    public int code;

    public FilterInfo(ArrayList<FilterItem> list, String name, String type, String key, int code) {
        this.list = list;
        this.name = name;
        this.type = type;
        this.key = key;
        this.code = code;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.list);
        dest.writeString(this.name);
        dest.writeString(this.type);
        dest.writeString(this.key);
        dest.writeInt(this.code);
    }

    protected FilterInfo(Parcel in) {
        this.list = in.createTypedArrayList(FilterItem.CREATOR);
        this.name = in.readString();
        this.type = in.readString();
        this.key = in.readString();
        this.code = in.readInt();
    }

    public static final Parcelable.Creator<FilterInfo> CREATOR = new Parcelable.Creator<FilterInfo>() {
        @Override
        public FilterInfo createFromParcel(Parcel source) {
            return new FilterInfo(source);
        }

        @Override
        public FilterInfo[] newArray(int size) {
            return new FilterInfo[size];
        }
    };
}
