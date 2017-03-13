package com.thinkmobiles.easyerp.data.model.hr.employees;

import android.os.Parcel;
import android.os.Parcelable;

import com.thinkmobiles.easyerp.data.model.crm.common.images.ResponseGetImages;

/**
 * Created by Lynx on 3/13/2017.
 */

public class ResponseCommonEmployees implements Parcelable {

    public ResponseGetEmployees responseGetEmployees;
    public ResponseGetImages responseGetImages;

    public ResponseCommonEmployees(ResponseGetEmployees responseGetEmployees, ResponseGetImages responseGetImages) {
        this.responseGetEmployees = responseGetEmployees;
        this.responseGetImages = responseGetImages;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.responseGetEmployees, flags);
        dest.writeParcelable(this.responseGetImages, flags);
    }

    protected ResponseCommonEmployees(Parcel in) {
        this.responseGetEmployees = in.readParcelable(ResponseGetEmployees.class.getClassLoader());
        this.responseGetImages = in.readParcelable(ResponseGetImages.class.getClassLoader());
    }

    public static final Creator<ResponseCommonEmployees> CREATOR = new Creator<ResponseCommonEmployees>() {
        @Override
        public ResponseCommonEmployees createFromParcel(Parcel source) {
            return new ResponseCommonEmployees(source);
        }

        @Override
        public ResponseCommonEmployees[] newArray(int size) {
            return new ResponseCommonEmployees[size];
        }
    };
}
