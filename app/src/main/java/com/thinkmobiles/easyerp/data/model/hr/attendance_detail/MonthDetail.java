package com.thinkmobiles.easyerp.data.model.hr.attendance_detail;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.thinkmobiles.easyerp.data.model.crm.filter.FilterItem;
import com.thinkmobiles.easyerp.data.model.hr.employees.item.EmployeeItem;

import java.util.ArrayList;

/**
 * Created by Alex Michenko on 27.03.2017.
 */

public class MonthDetail implements Parcelable {

    @SerializedName("_id")
    public String id;
    public FilterItem department;
    public int month;
    public int monthTotal;
    public EmployeeItem employee;
    public ArrayList<String> vacArray;
    int year;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeParcelable(this.department, flags);
        dest.writeInt(this.month);
        dest.writeInt(this.monthTotal);
        dest.writeParcelable(this.employee, flags);
        dest.writeStringList(this.vacArray);
        dest.writeInt(this.year);
    }

    public MonthDetail() {
    }

    protected MonthDetail(Parcel in) {
        this.id = in.readString();
        this.department = in.readParcelable(FilterItem.class.getClassLoader());
        this.month = in.readInt();
        this.monthTotal = in.readInt();
        this.employee = in.readParcelable(EmployeeItem.class.getClassLoader());
        this.vacArray = in.createStringArrayList();
        this.year = in.readInt();
    }

    public static final Parcelable.Creator<MonthDetail> CREATOR = new Parcelable.Creator<MonthDetail>() {
        @Override
        public MonthDetail createFromParcel(Parcel source) {
            return new MonthDetail(source);
        }

        @Override
        public MonthDetail[] newArray(int size) {
            return new MonthDetail[size];
        }
    };
}
