package com.thinkmobiles.easyerp.data.model.crm.filter;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;


public class ResponseGetFilters implements Parcelable {

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

    public ArrayList<FilterItem> contactName;       //Leads
    public ArrayList<FilterItem> source;            //Leads
    public ArrayList<FilterItem> workflow;          //Leads
    public ArrayList<FilterItem> salesPerson;       //Leads, Opportunities
    public ArrayList<FilterItem> createdBy;         //Leads, Opportunities
    public ArrayList<FilterItem> customer;          //Opportunities
    public ArrayList<FilterItem> name;              //Opportunities


    public ResponseGetFilters() {
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
        dest.writeTypedList(this.customer);
        dest.writeTypedList(this.name);
    }

    protected ResponseGetFilters(Parcel in) {
        this.contactName = in.createTypedArrayList(FilterItem.CREATOR);
        this.source = in.createTypedArrayList(FilterItem.CREATOR);
        this.workflow = in.createTypedArrayList(FilterItem.CREATOR);
        this.salesPerson = in.createTypedArrayList(FilterItem.CREATOR);
        this.createdBy = in.createTypedArrayList(FilterItem.CREATOR);
        this.customer = in.createTypedArrayList(FilterItem.CREATOR);
        this.name = in.createTypedArrayList(FilterItem.CREATOR);
    }

    public static final Creator<ResponseGetFilters> CREATOR = new Creator<ResponseGetFilters>() {
        @Override
        public ResponseGetFilters createFromParcel(Parcel source) {
            return new ResponseGetFilters(source);
        }

        @Override
        public ResponseGetFilters[] newArray(int size) {
            return new ResponseGetFilters[size];
        }
    };
}
