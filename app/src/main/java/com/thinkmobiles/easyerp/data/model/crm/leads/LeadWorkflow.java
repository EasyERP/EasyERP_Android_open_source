package com.thinkmobiles.easyerp.data.model.crm.leads;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Lynx on 1/16/2017.
 */

public class LeadWorkflow implements Parcelable {
    /**
     *  id: "528ce74ef3f67bc40b00001e",
     name: "New",
     status: "New"
     */
    public String _id;
    public String name;
    public String status;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this._id);
        dest.writeString(this.name);
        dest.writeString(this.status);
    }

    public LeadWorkflow() {
    }

    protected LeadWorkflow(Parcel in) {
        this._id = in.readString();
        this.name = in.readString();
        this.status = in.readString();
    }

    public static final Parcelable.Creator<LeadWorkflow> CREATOR = new Parcelable.Creator<LeadWorkflow>() {
        @Override
        public LeadWorkflow createFromParcel(Parcel source) {
            return new LeadWorkflow(source);
        }

        @Override
        public LeadWorkflow[] newArray(int size) {
            return new LeadWorkflow[size];
        }
    };
}
