package com.thinkmobiles.easyerp.data.model.hr.employees.details;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Lynx on 3/17/2017.
 */

public class SimpleNoteItem implements Parcelable {
    @SerializedName("_id")
    public String id;
    public String note;
    public String date;
    public String author;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.note);
        dest.writeString(this.date);
        dest.writeString(this.author);
    }

    public SimpleNoteItem() {
    }

    protected SimpleNoteItem(Parcel in) {
        this.id = in.readString();
        this.note = in.readString();
        this.date = in.readString();
        this.author = in.readString();
    }

    public static final Parcelable.Creator<SimpleNoteItem> CREATOR = new Parcelable.Creator<SimpleNoteItem>() {
        @Override
        public SimpleNoteItem createFromParcel(Parcel source) {
            return new SimpleNoteItem(source);
        }

        @Override
        public SimpleNoteItem[] newArray(int size) {
            return new SimpleNoteItem[size];
        }
    };
}
