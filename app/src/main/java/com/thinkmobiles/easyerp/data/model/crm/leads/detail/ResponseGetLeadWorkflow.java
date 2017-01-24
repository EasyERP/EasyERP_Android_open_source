package com.thinkmobiles.easyerp.data.model.crm.leads.detail;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;


public class ResponseGetLeadWorkflow implements Parcelable {

    /**
     * {
     "data": [
     {
     "_id": "528ce74ef3f67bc40b00001e",
     "__v": 0,
     "attachments": [

     ],
     "color": "#2C3E50",
     "name": "New",
     "sequence": 3,
     "status": "New",
     "wId": "Leads",
     "wName": "lead",
     "source": "lead",
     "targetSource": [
     "lead"
     ],
     "visible": true
     },

     ]
     }
     */

    public ArrayList<LeadDetailWorkflow> data;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.data);
    }

    public ResponseGetLeadWorkflow() {
    }

    protected ResponseGetLeadWorkflow(Parcel in) {
        this.data = in.createTypedArrayList(LeadDetailWorkflow.CREATOR);
    }

    public static final Parcelable.Creator<ResponseGetLeadWorkflow> CREATOR = new Parcelable.Creator<ResponseGetLeadWorkflow>() {
        @Override
        public ResponseGetLeadWorkflow createFromParcel(Parcel source) {
            return new ResponseGetLeadWorkflow(source);
        }

        @Override
        public ResponseGetLeadWorkflow[] newArray(int size) {
            return new ResponseGetLeadWorkflow[size];
        }
    };
}
