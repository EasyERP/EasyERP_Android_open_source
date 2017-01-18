package com.thinkmobiles.easyerp.data.model.leads.details;

import android.os.Parcel;
import android.os.Parcelable;


public class LeadCategory implements Parcelable {

    /**
     * "category": {
     "_id": "587762390e749d2d08633c3d",
     "name": "Find",
     "color": "bgGreenDark",
     "type": "Category",
     "__v": 0
     },
     */

    public String _id;
    public String name;
    public String color;
    public String type;
    public int __v;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this._id);
        dest.writeString(this.name);
        dest.writeString(this.color);
        dest.writeString(this.type);
        dest.writeInt(this.__v);
    }

    public LeadCategory() {
    }

    protected LeadCategory(Parcel in) {
        this._id = in.readString();
        this.name = in.readString();
        this.color = in.readString();
        this.type = in.readString();
        this.__v = in.readInt();
    }

    public static final Parcelable.Creator<LeadCategory> CREATOR = new Parcelable.Creator<LeadCategory>() {
        @Override
        public LeadCategory createFromParcel(Parcel source) {
            return new LeadCategory(source);
        }

        @Override
        public LeadCategory[] newArray(int size) {
            return new LeadCategory[size];
        }
    };
}
