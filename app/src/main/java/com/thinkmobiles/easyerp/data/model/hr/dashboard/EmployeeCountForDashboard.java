package com.thinkmobiles.easyerp.data.model.hr.dashboard;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Michael Soyma (Created on 3/22/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public final class EmployeeCountForDashboard implements Parcelable {

    public int employeeCount;
    public int hiredCount;
    public int firedCount;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.employeeCount);
        dest.writeInt(this.hiredCount);
        dest.writeInt(this.firedCount);
    }

    public EmployeeCountForDashboard() {
    }

    protected EmployeeCountForDashboard(Parcel in) {
        this.employeeCount = in.readInt();
        this.hiredCount = in.readInt();
        this.firedCount = in.readInt();
    }

    public static final Parcelable.Creator<EmployeeCountForDashboard> CREATOR = new Parcelable.Creator<EmployeeCountForDashboard>() {
        @Override
        public EmployeeCountForDashboard createFromParcel(Parcel source) {
            return new EmployeeCountForDashboard(source);
        }

        @Override
        public EmployeeCountForDashboard[] newArray(int size) {
            return new EmployeeCountForDashboard[size];
        }
    };
}
