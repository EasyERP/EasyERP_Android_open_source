package com.thinkmobiles.easyerp.data.model.crm.leads;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Lynx on 1/16/2017.
 */

public class LeadItem implements Parcelable {
    /**
     {
     id: "583d66e2aefcfc140ea84b9d",
     total: 994,
     salesPerson: {
     id: "56e17661177f76f72edf774c",
     name: "Bogdana Stets"
     },
     workflow: {
     id: "528ce74ef3f67bc40b00001e",
     name: "New",
     status: "New"
     },
     editedBy: {
     date: "2016-11-29T11:30:42.424Z"
     },
     expectedClosing: null,
     name: "Sajid Ghafoor",
     priority: "Cold",
     tags: [ ],
     source: "ThinkMobiles"
     },
     */

    @SerializedName("_id")
    public String id;
    public int total;
    public LeadSalesPerson salesPerson;
    public LeadWorkflow workflow;
    public LeadEditedBy editedBy;
    public String expectedClosing;
    public String name;
    public String priority;
    public ArrayList<LeadTag> tags;
    public String source;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeInt(this.total);
        dest.writeParcelable(this.salesPerson, flags);
        dest.writeParcelable(this.workflow, flags);
        dest.writeParcelable(this.editedBy, flags);
        dest.writeString(this.expectedClosing);
        dest.writeString(this.name);
        dest.writeString(this.priority);
        dest.writeTypedList(this.tags);
        dest.writeString(this.source);
    }

    public LeadItem() {
    }

    protected LeadItem(Parcel in) {
        this.id = in.readString();
        this.total = in.readInt();
        this.salesPerson = in.readParcelable(LeadSalesPerson.class.getClassLoader());
        this.workflow = in.readParcelable(LeadWorkflow.class.getClassLoader());
        this.editedBy = in.readParcelable(LeadEditedBy.class.getClassLoader());
        this.expectedClosing = in.readString();
        this.name = in.readString();
        this.priority = in.readString();
        this.tags = in.createTypedArrayList(LeadTag.CREATOR);
        this.source = in.readString();
    }

    public static final Parcelable.Creator<LeadItem> CREATOR = new Parcelable.Creator<LeadItem>() {
        @Override
        public LeadItem createFromParcel(Parcel source) {
            return new LeadItem(source);
        }

        @Override
        public LeadItem[] newArray(int size) {
            return new LeadItem[size];
        }
    };
}
