package com.thinkmobiles.easyerp.data.model.reports.general;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.thinkmobiles.easyerp.data.model.crm.leads.EditedBy;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.CreatedEditedBy;

import java.util.List;

/**
 * @author Michael Soyma (Created on 4/8/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public final class Report implements Parcelable {

    @SerializedName("_id")
    public String id;
    public EditedBy createdBy;
    public DateRange dateRange;
    public String description;
    public CreatedEditedBy editedBy;
    public String name;
    public boolean publicAccess;
    public String recentDate;
    public String reportCategory;
    public String reportType;
    public List<String> rows;

    public Report() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeParcelable(this.createdBy, flags);
        dest.writeParcelable(this.dateRange, flags);
        dest.writeString(this.description);
        dest.writeParcelable(this.editedBy, flags);
        dest.writeString(this.name);
        dest.writeByte(this.publicAccess ? (byte) 1 : (byte) 0);
        dest.writeString(this.recentDate);
        dest.writeString(this.reportCategory);
        dest.writeString(this.reportType);
        dest.writeStringList(this.rows);
    }

    protected Report(Parcel in) {
        this.id = in.readString();
        this.createdBy = in.readParcelable(EditedBy.class.getClassLoader());
        this.dateRange = in.readParcelable(DateRange.class.getClassLoader());
        this.description = in.readString();
        this.editedBy = in.readParcelable(CreatedEditedBy.class.getClassLoader());
        this.name = in.readString();
        this.publicAccess = in.readByte() != 0;
        this.recentDate = in.readString();
        this.reportCategory = in.readString();
        this.reportType = in.readString();
        this.rows = in.createStringArrayList();
    }

    public static final Creator<Report> CREATOR = new Creator<Report>() {
        @Override
        public Report createFromParcel(Parcel source) {
            return new Report(source);
        }

        @Override
        public Report[] newArray(int size) {
            return new Report[size];
        }
    };
}
