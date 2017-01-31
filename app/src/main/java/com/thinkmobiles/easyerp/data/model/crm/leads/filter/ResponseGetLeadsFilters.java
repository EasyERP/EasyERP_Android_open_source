package com.thinkmobiles.easyerp.data.model.crm.leads.filter;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


public class ResponseGetLeadsFilters implements Parcelable {

    /**
     * {
     "_id": null,
     "contactName": [
     {
     "_id": "Ross OCrowley",
     "name": "Ross OCrowley"
     },
     ],
     "source": [
     {
     "_id": "Offline Meetings",
     "name": "Offline Meetings"
     },
     ],
     "workflow": [
     {
     "_id": "528ce74ef3f67bc40b00001e",
     "name": "Draft"
     },
     ],
     "salesPerson": [
     {
     "_id": "55b92ad221e4b7c40f00005f",
     "name": "rene "
     },
     ]
     }
     */

    public ArrayList<FilterItem> contactName;
    public ArrayList<FilterItem> source;
    public ArrayList<FilterItem> workflow;
    public ArrayList<FilterItem> salesPerson;
    public ArrayList<FilterItem> createdBy;


    public ResponseGetLeadsFilters() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.contactName);
        dest.writeTypedList(this.source);
        dest.writeTypedList(this.workflow);
        dest.writeTypedList(this.salesPerson);
        dest.writeTypedList(this.createdBy);
    }

    protected ResponseGetLeadsFilters(Parcel in) {
        this.contactName = in.createTypedArrayList(FilterItem.CREATOR);
        this.source = in.createTypedArrayList(FilterItem.CREATOR);
        this.workflow = in.createTypedArrayList(FilterItem.CREATOR);
        this.salesPerson = in.createTypedArrayList(FilterItem.CREATOR);
        this.createdBy = in.createTypedArrayList(FilterItem.CREATOR);
    }

    public static final Creator<ResponseGetLeadsFilters> CREATOR = new Creator<ResponseGetLeadsFilters>() {
        @Override
        public ResponseGetLeadsFilters createFromParcel(Parcel source) {
            return new ResponseGetLeadsFilters(source);
        }

        @Override
        public ResponseGetLeadsFilters[] newArray(int size) {
            return new ResponseGetLeadsFilters[size];
        }
    };
}
