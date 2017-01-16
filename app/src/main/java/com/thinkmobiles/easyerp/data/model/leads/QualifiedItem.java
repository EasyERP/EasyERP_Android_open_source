package com.thinkmobiles.easyerp.data.model.leads;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Lynx on 1/16/2017.
 */

public class QualifiedItem implements Parcelable {
    /**
     * {
     count: 2,
     salesPerson: "yana.gusti"
     },
     */

    public int count;
    public String salesPerson;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.count);
        dest.writeString(this.salesPerson);
    }

    public QualifiedItem() {
    }

    protected QualifiedItem(Parcel in) {
        this.count = in.readInt();
        this.salesPerson = in.readString();
    }

    public static final Parcelable.Creator<QualifiedItem> CREATOR = new Parcelable.Creator<QualifiedItem>() {
        @Override
        public QualifiedItem createFromParcel(Parcel source) {
            return new QualifiedItem(source);
        }

        @Override
        public QualifiedItem[] newArray(int size) {
            return new QualifiedItem[size];
        }
    };
}
