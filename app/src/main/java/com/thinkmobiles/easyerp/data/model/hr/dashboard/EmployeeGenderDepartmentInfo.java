package com.thinkmobiles.easyerp.data.model.hr.dashboard;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * @author Michael Soyma (Created on 3/22/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public final class EmployeeGenderDepartmentInfo implements Parcelable {

    @SerializedName("_id")
    public String id;
    public int employeesCount;
    public int maleCount;
    public int femaleCount;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeInt(this.employeesCount);
        dest.writeInt(this.maleCount);
        dest.writeInt(this.femaleCount);
    }

    public EmployeeGenderDepartmentInfo() {
    }

    protected EmployeeGenderDepartmentInfo(Parcel in) {
        this.id = in.readString();
        this.employeesCount = in.readInt();
        this.maleCount = in.readInt();
        this.femaleCount = in.readInt();
    }

    public static final Parcelable.Creator<EmployeeGenderDepartmentInfo> CREATOR = new Parcelable.Creator<EmployeeGenderDepartmentInfo>() {
        @Override
        public EmployeeGenderDepartmentInfo createFromParcel(Parcel source) {
            return new EmployeeGenderDepartmentInfo(source);
        }

        @Override
        public EmployeeGenderDepartmentInfo[] newArray(int size) {
            return new EmployeeGenderDepartmentInfo[size];
        }
    };
}
