package com.thinkmobiles.easyerp.data.model.crm.leads.detail;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by samson on 16.01.17.
 */

public class AttachmentItem implements Parcelable {

    /**
     * {
     "_id": "587cd2eb377e64887cbbad91",
     "name": "eye_line.png",
     "shortPath": "uploads%2Fopportunities%2F583da46aed5a2cbf0db9f537%2Feye_line.png",
     "size": "0.002&nbsp;Mb",
     "uploadDate": "2017-01-16T14:04:27.306Z",
     "uploaderName": "testAdmin"
     }
     */

    @SerializedName("_id")
    public String id;
    public String name;
    @SerializedName("shortPath")
    public String shortPath;
    public String size;
    public String uploadDate;
    public String uploaderName;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.shortPath);
        dest.writeString(this.size);
        dest.writeString(this.uploadDate);
        dest.writeString(this.uploaderName);
    }

    public AttachmentItem() {
    }

    protected AttachmentItem(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.shortPath = in.readString();
        this.size = in.readString();
        this.uploadDate = in.readString();
        this.uploaderName = in.readString();
    }

    public static final Parcelable.Creator<AttachmentItem> CREATOR = new Parcelable.Creator<AttachmentItem>() {
        @Override
        public AttachmentItem createFromParcel(Parcel source) {
            return new AttachmentItem(source);
        }

        @Override
        public AttachmentItem[] newArray(int size) {
            return new AttachmentItem[size];
        }
    };
}
