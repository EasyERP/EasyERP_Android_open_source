package com.thinkmobiles.easyerp.data.model.crm.orders.detail;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Prepayment implements Parcelable {

    /**
     * "prepayment": {
     "_id": null,
     "sum": 12735,
     "names": [
     "PP_80"
     ],
     "date": "2017-01-31T09:44:31.000Z"
     },
     */

    public String _id;
    public Long sum;
    public ArrayList<String> names;
    public String date;


    public Prepayment() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this._id);
        dest.writeValue(this.sum);
        dest.writeStringList(this.names);
        dest.writeString(this.date);
    }

    protected Prepayment(Parcel in) {
        this._id = in.readString();
        this.sum = (Long) in.readValue(Long.class.getClassLoader());
        this.names = in.createStringArrayList();
        this.date = in.readString();
    }

    public static final Creator<Prepayment> CREATOR = new Creator<Prepayment>() {
        @Override
        public Prepayment createFromParcel(Parcel source) {
            return new Prepayment(source);
        }

        @Override
        public Prepayment[] newArray(int size) {
            return new Prepayment[size];
        }
    };
}
