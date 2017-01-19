package com.thinkmobiles.easyerp.data.model.crm.leads;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Lynx on 1/16/2017.
 */

public class ResponseGetLeads implements Parcelable {

    public int total;
    public ArrayList<LeadItem> data;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.total);
        dest.writeTypedList(this.data);
    }

    public ResponseGetLeads() {
    }

    protected ResponseGetLeads(Parcel in) {
        this.total = in.readInt();
        this.data = in.createTypedArrayList(LeadItem.CREATOR);
    }

    public static final Parcelable.Creator<ResponseGetLeads> CREATOR = new Parcelable.Creator<ResponseGetLeads>() {
        @Override
        public ResponseGetLeads createFromParcel(Parcel source) {
            return new ResponseGetLeads(source);
        }

        @Override
        public ResponseGetLeads[] newArray(int size) {
            return new ResponseGetLeads[size];
        }
    };
}
