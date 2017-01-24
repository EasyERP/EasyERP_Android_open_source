package com.thinkmobiles.easyerp.data.model.crm.leads.detail;

import android.os.Parcel;
import android.os.Parcelable;


public class LeadNote implements Parcelable {

    /**
    {
        "date": "2017-01-16T13:56:13.187Z",
        "note": "message:Web portal for travelers",
        "history": {
            "editedBy": {
                "_id": "5836ec22d291dd500cf6e16a",
                "login": "testAdmin"
            },
            "newValue": "Don't Contact",
            "changedField": "workflow",
            "collectionName": "Opportunities",
            "contentId": "583da46aed5a2cbf0db9f537",
            "date": "2017-01-16T13:56:13.187Z"
        },
        "user": {
            "_id": "5836ec22d291dd500cf6e16a",
            "login": "testAdmin"
        },
        "_id": "",
        "task": null,
        "attachment": {
            "shortPas": "uploads%2Fopportunities%2F583da46aed5a2cbf0db9f537%2Feye_line.png",
            "name": "eye_line.png"
        },
    }
     */

    public String _id;
    public String  date;
    public String  note;
    public LeadTask  task;
    public LeadHistory history;
    public LeadUser user;
    public LeadAttachment attachment;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this._id);
        dest.writeString(this.date);
        dest.writeString(this.note);
        dest.writeParcelable(this.task, flags);
        dest.writeParcelable(this.history, flags);
        dest.writeParcelable(this.user, flags);
        dest.writeParcelable(this.attachment, flags);
    }

    public LeadNote() {
    }

    protected LeadNote(Parcel in) {
        this._id = in.readString();
        this.date = in.readString();
        this.note = in.readString();
        this.task = in.readParcelable(LeadTask.class.getClassLoader());
        this.history = in.readParcelable(LeadHistory.class.getClassLoader());
        this.user = in.readParcelable(LeadUser.class.getClassLoader());
        this.attachment = in.readParcelable(LeadAttachment.class.getClassLoader());
    }

    public static final Creator<LeadNote> CREATOR = new Creator<LeadNote>() {
        @Override
        public LeadNote createFromParcel(Parcel source) {
            return new LeadNote(source);
        }

        @Override
        public LeadNote[] newArray(int size) {
            return new LeadNote[size];
        }
    };
}
