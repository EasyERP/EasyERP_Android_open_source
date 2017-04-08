package com.thinkmobiles.easyerp.data.model.reports.general;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Michael Soyma (Created on 4/8/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public final class Category implements Parcelable {

    public String id;
    public String label;
    public String key;

    public Category(String id, String label, String key) {
        this.id = id;
        this.label = label;
        this.key = key;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.label);
        dest.writeString(this.key);
    }

    protected Category(Parcel in) {
        this.id = in.readString();
        this.label = in.readString();
        this.key = in.readString();
    }

    public static final Creator<Category> CREATOR = new Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel source) {
            return new Category(source);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };
}
