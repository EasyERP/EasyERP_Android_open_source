package com.thinkmobiles.easyerp.data.model.leads;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Lynx on 1/16/2017.
 */

public class AssignItem implements Parcelable {
    /**
     * {
     _id: 20160817,
     count: 1,
     isOpp: true,
     sourse: 230
     },
     */

    public int _id;
    public int count;
    public boolean isOpp;
    public int sourse;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this._id);
        dest.writeInt(this.count);
        dest.writeByte(this.isOpp ? (byte) 1 : (byte) 0);
        dest.writeInt(this.sourse);
    }

    public AssignItem() {
    }

    protected AssignItem(Parcel in) {
        this._id = in.readInt();
        this.count = in.readInt();
        this.isOpp = in.readByte() != 0;
        this.sourse = in.readInt();
    }

    public static final Parcelable.Creator<AssignItem> CREATOR = new Parcelable.Creator<AssignItem>() {
        @Override
        public AssignItem createFromParcel(Parcel source) {
            return new AssignItem(source);
        }

        @Override
        public AssignItem[] newArray(int size) {
            return new AssignItem[size];
        }
    };
}
