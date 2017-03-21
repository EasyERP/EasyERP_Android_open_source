package com.thinkmobiles.easyerp.data.model.hr.employees.details;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.thinkmobiles.easyerp.data.model.crm.filter.FilterItem;
import com.thinkmobiles.easyerp.data.model.hr.employees.item.Manager;

/**
 * Created by Lynx on 3/17/2017.
 */

public class EmployeeTransferItem implements Parcelable {
    public String status;
    public FilterItem department;
    public FilterItem jobPosition;
    public Manager manager;
    public FilterItem weeklyScheduler;
    public String jobType;
    public int salary;
    public String info;
    public FilterItem scheduledPay;
    public FilterItem payrollStructureType;
    public String date;
    public String employee;
    @SerializedName("_id")
    public String id;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.status);
        dest.writeParcelable(this.department, flags);
        dest.writeParcelable(this.jobPosition, flags);
        dest.writeParcelable(this.manager, flags);
        dest.writeParcelable(this.weeklyScheduler, flags);
        dest.writeString(this.jobType);
        dest.writeInt(this.salary);
        dest.writeString(this.info);
        dest.writeParcelable(this.scheduledPay, flags);
        dest.writeParcelable(this.payrollStructureType, flags);
        dest.writeString(this.date);
        dest.writeString(this.employee);
        dest.writeString(this.id);
    }

    public EmployeeTransferItem() {
    }

    protected EmployeeTransferItem(Parcel in) {
        this.status = in.readString();
        this.department = in.readParcelable(FilterItem.class.getClassLoader());
        this.jobPosition = in.readParcelable(FilterItem.class.getClassLoader());
        this.manager = in.readParcelable(Manager.class.getClassLoader());
        this.weeklyScheduler = in.readParcelable(FilterItem.class.getClassLoader());
        this.jobType = in.readString();
        this.salary = in.readInt();
        this.info = in.readString();
        this.scheduledPay = in.readParcelable(FilterItem.class.getClassLoader());
        this.payrollStructureType = in.readParcelable(FilterItem.class.getClassLoader());
        this.date = in.readString();
        this.employee = in.readString();
        this.id = in.readString();
    }

    public static final Parcelable.Creator<EmployeeTransferItem> CREATOR = new Parcelable.Creator<EmployeeTransferItem>() {
        @Override
        public EmployeeTransferItem createFromParcel(Parcel source) {
            return new EmployeeTransferItem(source);
        }

        @Override
        public EmployeeTransferItem[] newArray(int size) {
            return new EmployeeTransferItem[size];
        }
    };
}
