package com.thinkmobiles.easyerp.data.model.crm.leads.detail;

import android.os.Parcel;
import android.os.Parcelable;


/**
 * Created by samson on 16.01.17.
 */

public class LeadHistory implements Parcelable {

    /**
    "history": {
        "editedBy": {
            "_id": "5836ec22d291dd500cf6e16a",
            "login": "testAdmin"
        },
        "prevValue": "New",
        "newValue": "Don't Contact",
        "changedField": "workflow",
        "collectionName": "Opportunities",
        "contentId": "583da46aed5a2cbf0db9f537",
        "date": "2017-01-16T13:56:13.187Z"
    },
     */

    public LeadUser editedBy;
    public String prevValue;
    public String newValue;
    public String changedField;
    public String collectionName;
    public String contentId;
    public String date;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.editedBy, flags);
        dest.writeString(this.prevValue);
        dest.writeString(this.newValue);
        dest.writeString(this.changedField);
        dest.writeString(this.collectionName);
        dest.writeString(this.contentId);
        dest.writeString(this.date);
    }

    public LeadHistory() {
    }

    protected LeadHistory(Parcel in) {
        this.editedBy = in.readParcelable(LeadUser.class.getClassLoader());
        this.prevValue = in.readString();
        this.newValue = in.readString();
        this.changedField = in.readString();
        this.collectionName = in.readString();
        this.contentId = in.readString();
        this.date = in.readString();
    }

    public static final Parcelable.Creator<LeadHistory> CREATOR = new Parcelable.Creator<LeadHistory>() {
        @Override
        public LeadHistory createFromParcel(Parcel source) {
            return new LeadHistory(source);
        }

        @Override
        public LeadHistory[] newArray(int size) {
            return new LeadHistory[size];
        }
    };
}
