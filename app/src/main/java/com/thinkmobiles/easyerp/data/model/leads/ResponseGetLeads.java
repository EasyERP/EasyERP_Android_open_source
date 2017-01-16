package com.thinkmobiles.easyerp.data.model.leads;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Lynx on 1/16/2017.
 */

public class ResponseGetLeads implements Parcelable {
    public ArrayList<QualifiedItem> qualifiedBy;
    public ArrayList<QualifiedItem> qualifiedFrom;
    public ArrayList<QualifiedItem> leadsBySales;
    public ArrayList<AssignItem> createdBy;
    public ArrayList<AssignItem> assignedTo;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.qualifiedBy);
        dest.writeTypedList(this.qualifiedFrom);
        dest.writeTypedList(this.leadsBySales);
        dest.writeTypedList(this.createdBy);
        dest.writeTypedList(this.assignedTo);
    }

    public ResponseGetLeads() {
    }

    protected ResponseGetLeads(Parcel in) {
        this.qualifiedBy = in.createTypedArrayList(QualifiedItem.CREATOR);
        this.qualifiedFrom = in.createTypedArrayList(QualifiedItem.CREATOR);
        this.leadsBySales = in.createTypedArrayList(QualifiedItem.CREATOR);
        this.createdBy = in.createTypedArrayList(AssignItem.CREATOR);
        this.assignedTo = in.createTypedArrayList(AssignItem.CREATOR);
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
