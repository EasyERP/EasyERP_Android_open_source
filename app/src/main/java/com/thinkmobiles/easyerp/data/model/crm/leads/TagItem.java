package com.thinkmobiles.easyerp.data.model.crm.leads;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Lynx on 1/16/2017.
 */

public class TagItem implements Parcelable {
    /**
     * {id:"57a0448f0c873fdf1c596058",
     color:"bgBlueLight",
     name:".NET"}
     */
    public String _id;
    public String color;
    public String name;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this._id);
        dest.writeString(this.color);
        dest.writeString(this.name);
    }

    public TagItem() {
    }

    protected TagItem(Parcel in) {
        this._id = in.readString();
        this.color = in.readString();
        this.name = in.readString();
    }

    public static final Parcelable.Creator<TagItem> CREATOR = new Parcelable.Creator<TagItem>() {
        @Override
        public TagItem createFromParcel(Parcel source) {
            return new TagItem(source);
        }

        @Override
        public TagItem[] newArray(int size) {
            return new TagItem[size];
        }
    };
}
