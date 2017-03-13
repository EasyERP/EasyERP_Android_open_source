package com.thinkmobiles.easyerp.data.model.hr.employees;

import android.os.Parcel;
import android.os.Parcelable;

import com.thinkmobiles.easyerp.data.model.hr.employees.item.EmployeeItem;

import java.util.ArrayList;

/**
 * Created by Lynx on 3/13/2017.
 */

public class ResponseGetEmployees implements Parcelable {
    public int total;
    public ArrayList<EmployeeItem> data;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.total);
        dest.writeTypedList(this.data);
    }

    public ResponseGetEmployees() {
    }

    protected ResponseGetEmployees(Parcel in) {
        this.total = in.readInt();
        this.data = in.createTypedArrayList(EmployeeItem.CREATOR);
    }

    public static final Parcelable.Creator<ResponseGetEmployees> CREATOR = new Parcelable.Creator<ResponseGetEmployees>() {
        @Override
        public ResponseGetEmployees createFromParcel(Parcel source) {
            return new ResponseGetEmployees(source);
        }

        @Override
        public ResponseGetEmployees[] newArray(int size) {
            return new ResponseGetEmployees[size];
        }
    };
}
