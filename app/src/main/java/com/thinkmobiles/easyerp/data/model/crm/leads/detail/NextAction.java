package com.thinkmobiles.easyerp.data.model.crm.leads.detail;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by samson on 16.01.17.
 */

public class NextAction implements Parcelable {

    /**
     *  "nextAction": {
     "date": "2016-11-29T15:53:14.335Z",
     "desc": ""
     },
     */

    public String date;
    public String desc;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.date);
        dest.writeString(this.desc);
    }

    public NextAction() {
    }

    protected NextAction(Parcel in) {
        this.date = in.readString();
        this.desc = in.readString();
    }

    public static final Parcelable.Creator<NextAction> CREATOR = new Parcelable.Creator<NextAction>() {
        @Override
        public NextAction createFromParcel(Parcel source) {
            return new NextAction(source);
        }

        @Override
        public NextAction[] newArray(int size) {
            return new NextAction[size];
        }
    };
}
