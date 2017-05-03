package com.thinkmobiles.easyerp.data.model.integrations;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.thinkmobiles.easyerp.data.model.crm.leads.EditedBy;

/**
 * @author Michael Soyma (Created on 5/3/2017).
 *         Company: Thinkmobiles
 *         Email:  michael.soyma@thinkmobiles.com
 */
public final class Location implements Parcelable {

    @SerializedName("_id")
    public String id;
    public EditedBy createdBy;
    public EditedBy editedBy;
    public String groupingA;
    public String groupingB;
    public String groupingC;
    public String groupingD;
    public String name;
    public String warehouse;
    public String zone;

    public Location() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeParcelable(this.createdBy, flags);
        dest.writeParcelable(this.editedBy, flags);
        dest.writeString(this.groupingA);
        dest.writeString(this.groupingB);
        dest.writeString(this.groupingC);
        dest.writeString(this.groupingD);
        dest.writeString(this.name);
        dest.writeString(this.warehouse);
        dest.writeString(this.zone);
    }

    protected Location(Parcel in) {
        this.id = in.readString();
        this.createdBy = in.readParcelable(EditedBy.class.getClassLoader());
        this.editedBy = in.readParcelable(EditedBy.class.getClassLoader());
        this.groupingA = in.readString();
        this.groupingB = in.readString();
        this.groupingC = in.readString();
        this.groupingD = in.readString();
        this.name = in.readString();
        this.warehouse = in.readString();
        this.zone = in.readString();
    }

    public static final Creator<Location> CREATOR = new Creator<Location>() {
        @Override
        public Location createFromParcel(Parcel source) {
            return new Location(source);
        }

        @Override
        public Location[] newArray(int size) {
            return new Location[size];
        }
    };
}
