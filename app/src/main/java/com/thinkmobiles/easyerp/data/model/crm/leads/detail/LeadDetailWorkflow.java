package com.thinkmobiles.easyerp.data.model.crm.leads.detail;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by samson on 17.01.17.
 */

public class LeadDetailWorkflow implements Parcelable {

    /**
     * "workflow": {
     "_id": "5783b351df8b918c31af24a8",
     "color": "#2C3E50",
     "name": "Not Started",
     "status": "New",
     "wId": "DealTasks",
     "wName": "Deal Tasks",
     "sequence": 3,
     "visible": true
     },
     */

    public String _id;
    public String color;
    public String name;
    public String status;
    public String wId;
    public String wName;
    public int sequence;
    public boolean visible;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this._id);
        dest.writeString(this.name);
        dest.writeString(this.status);
        dest.writeString(this.wId);
        dest.writeString(this.wName);
        dest.writeInt(this.sequence);
        dest.writeByte(this.visible ? (byte) 1 : (byte) 0);
    }

    public LeadDetailWorkflow() {
    }

    protected LeadDetailWorkflow(Parcel in) {
        this._id = in.readString();
        this.name = in.readString();
        this.status = in.readString();
        this.wId = in.readString();
        this.wName = in.readString();
        this.sequence = in.readInt();
        this.visible = in.readByte() != 0;
    }

    public static final Parcelable.Creator<LeadDetailWorkflow> CREATOR = new Parcelable.Creator<LeadDetailWorkflow>() {
        @Override
        public LeadDetailWorkflow createFromParcel(Parcel source) {
            return new LeadDetailWorkflow(source);
        }

        @Override
        public LeadDetailWorkflow[] newArray(int size) {
            return new LeadDetailWorkflow[size];
        }
    };
}

