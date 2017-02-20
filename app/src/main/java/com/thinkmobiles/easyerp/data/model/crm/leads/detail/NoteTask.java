package com.thinkmobiles.easyerp.data.model.crm.leads.detail;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class NoteTask implements Parcelable {

    /**
    "task": {
        "_id": "587dd7ebab1707af208fdb93",
                "description": "This is the task for lead.",
                "dealDate": "2017-01-17T08:38:03.817Z",
                "attachments": [],
        "notes": [],
        "type": "",
                "workflow": {
            "_id": "5783b351df8b918c31af24a8",
                    "name": "Not Started",
                    "status": "New",
                    "wId": "DealTasks",
                    "wName": "Deal Tasks",
                    "sequence": 3,
                    "visible": true
        },
        "dueDate": "2017-01-27T08:29:00.000Z",
                "contact": null,
                "company": null,
                "sequence": 64,
                "category": {
            "_id": "587762390e749d2d08633c3d",
                    "name": "Find",
                    "color": "bgGreenDark",
                    "type": "Category",
                    "__v": 0
        },
        "assignedTo": {
            "_id": "55b92ad221e4b7c40f00009e",
                    "name": {
                "last": "Michenko",
                        "first": "Alex"
            },
            "imageSrc": "url:image/jpeg;base64",
                    "fullName": "Alex Michenko",
                    "id": "55b92ad221e4b7c40f00009e"
        },
        "deal": {
            "_id": "587dd7a923ddd08c2030f0e8",
                    "name": "Android application for Enterprise"
        },
        "taskCount": 1,
                "__v": 0,
                "editedBy": {
            "date": "2017-01-17T08:38:03.853Z",
                    "user": {
                "_id": "585cdc6ed210f7ec05c45f1f",
                        "login": "testAdmin"
            }
        }
    },
     */

    public String _id;
    public String description;
    public String dealDate;
    public ArrayList<AttachmentItem> attachments;
    public ArrayList<NoteItem> notes;
    public String type;
    public LeadDetailWorkflow workflow;
    public String dueDate;
    public String contact;
    public Company company;
    public int sequence;
    public LeadCategory category;
    public LeadAssignedTo assignedTo;
    public LeadDeal deal;
    public int taskCount;
    public int __v;
    public CreatedEditedBy editedBy;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this._id);
        dest.writeString(this.description);
        dest.writeString(this.dealDate);
        dest.writeTypedList(this.attachments);
        dest.writeTypedList(this.notes);
        dest.writeString(this.type);
        dest.writeParcelable(this.workflow, flags);
        dest.writeString(this.dueDate);
        dest.writeString(this.contact);
        dest.writeParcelable(this.company, flags);
        dest.writeInt(this.sequence);
        dest.writeParcelable(this.category, flags);
        dest.writeParcelable(this.assignedTo, flags);
        dest.writeParcelable(this.deal, flags);
        dest.writeInt(this.taskCount);
        dest.writeInt(this.__v);
        dest.writeParcelable(this.editedBy, flags);
    }

    public NoteTask() {
    }

    protected NoteTask(Parcel in) {
        this._id = in.readString();
        this.description = in.readString();
        this.dealDate = in.readString();
        this.attachments = in.createTypedArrayList(AttachmentItem.CREATOR);
        this.notes = in.createTypedArrayList(NoteItem.CREATOR);
        this.type = in.readString();
        this.workflow = in.readParcelable(LeadDetailWorkflow.class.getClassLoader());
        this.dueDate = in.readString();
        this.contact = in.readString();
        this.company = in.readParcelable(Company.class.getClassLoader());
        this.sequence = in.readInt();
        this.category = in.readParcelable(LeadCategory.class.getClassLoader());
        this.assignedTo = in.readParcelable(LeadAssignedTo.class.getClassLoader());
        this.deal = in.readParcelable(LeadDeal.class.getClassLoader());
        this.taskCount = in.readInt();
        this.__v = in.readInt();
        this.editedBy = in.readParcelable(CreatedEditedBy.class.getClassLoader());
    }

    public static final Parcelable.Creator<NoteTask> CREATOR = new Parcelable.Creator<NoteTask>() {
        @Override
        public NoteTask createFromParcel(Parcel source) {
            return new NoteTask(source);
        }

        @Override
        public NoteTask[] newArray(int size) {
            return new NoteTask[size];
        }
    };
}
