package com.thinkmobiles.easyerp.data.model.hr.attendance_detail;

import android.os.Parcel;
import android.os.Parcelable;

import com.thinkmobiles.easyerp.data.model.crm.filter.FilterItem;

import java.util.ArrayList;

/**
 * Created by Alex Michenko on 27.03.2017.
 */

public class ResponseGetAttendanceDetails implements Parcelable {

    public ArrayList<FilterItem> years;
    public ArrayList<MonthDetail> details;

    public ResponseGetAttendanceDetails(ArrayList<FilterItem> years, ArrayList<MonthDetail> details) {
        this.years = years;
        this.details = details;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.years);
        dest.writeTypedList(this.details);
    }

    public ResponseGetAttendanceDetails() {
    }

    protected ResponseGetAttendanceDetails(Parcel in) {
        this.years = in.createTypedArrayList(FilterItem.CREATOR);
        this.details = in.createTypedArrayList(MonthDetail.CREATOR);
    }

    public static final Parcelable.Creator<ResponseGetAttendanceDetails> CREATOR = new Parcelable.Creator<ResponseGetAttendanceDetails>() {
        @Override
        public ResponseGetAttendanceDetails createFromParcel(Parcel source) {
            return new ResponseGetAttendanceDetails(source);
        }

        @Override
        public ResponseGetAttendanceDetails[] newArray(int size) {
            return new ResponseGetAttendanceDetails[size];
        }
    };
}
