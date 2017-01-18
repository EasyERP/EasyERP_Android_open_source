package com.thinkmobiles.easyerp.data.model.leads.details;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by samson on 16.01.17.
 */

public class LeadNextAction implements Parcelable {

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

    public LeadNextAction() {
    }

    protected LeadNextAction(Parcel in) {
        this.date = in.readString();
        this.desc = in.readString();
    }

    public static final Parcelable.Creator<LeadNextAction> CREATOR = new Parcelable.Creator<LeadNextAction>() {
        @Override
        public LeadNextAction createFromParcel(Parcel source) {
            return new LeadNextAction(source);
        }

        @Override
        public LeadNextAction[] newArray(int size) {
            return new LeadNextAction[size];
        }
    };
}
