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
    public int sum;
    public ArrayList<String> names;
    public String date;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this._id);
        dest.writeInt(this.sum);
        dest.writeStringList(this.names);
        dest.writeString(this.date);
    }

    public Prepayment() {
    }

    protected Prepayment(Parcel in) {
        this._id = in.readString();
        this.sum = in.readInt();
        this.names = in.createStringArrayList();
        this.date = in.readString();
    }

    public static final Parcelable.Creator<Prepayment> CREATOR = new Parcelable.Creator<Prepayment>() {
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
