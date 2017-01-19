package com.thinkmobiles.easyerp.data.model.crm.leads.details;


import android.os.Parcel;
import android.os.Parcelable;

public class LeadAssignedTo implements Parcelable {

    /**
     * "assignedTo": {
     "_id": "55b92ad221e4b7c40f00009e",
     "name": {
     "last": "Michenko",
     "first": "Alex"
     },
     "imageSrc": "data:image/jpeg;base64",
     "fullName": "Alex Michenko",
     "id": "55b92ad221e4b7c40f00009e"
     },
     */

    public String _id;
    public LeadName name;
    public String imageSrc;
    public String fullName;
    public String id;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this._id);
        dest.writeParcelable(this.name, flags);
        dest.writeString(this.imageSrc);
        dest.writeString(this.fullName);
        dest.writeString(this.id);
    }

    public LeadAssignedTo() {
    }

    protected LeadAssignedTo(Parcel in) {
        this._id = in.readString();
        this.name = in.readParcelable(LeadName.class.getClassLoader());
        this.imageSrc = in.readString();
        this.fullName = in.readString();
        this.id = in.readString();
    }

    public static final Parcelable.Creator<LeadAssignedTo> CREATOR = new Parcelable.Creator<LeadAssignedTo>() {
        @Override
        public LeadAssignedTo createFromParcel(Parcel source) {
            return new LeadAssignedTo(source);
        }

        @Override
        public LeadAssignedTo[] newArray(int size) {
            return new LeadAssignedTo[size];
        }
    };
}
