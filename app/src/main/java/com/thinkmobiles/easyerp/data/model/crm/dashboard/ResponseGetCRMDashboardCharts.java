package com.thinkmobiles.easyerp.data.model.crm.dashboard;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Lynx on 1/16/2017.
 */

public class ResponseGetCRMDashboardCharts implements Parcelable {

    @SerializedName("_id")
    public String id;
    public ArrayList<DashboardListItem> charts;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeTypedList(this.charts);
    }

    public ResponseGetCRMDashboardCharts(String id, ArrayList<DashboardListItem> charts) {
        this.id = id;
        this.charts = charts;
    }

    protected ResponseGetCRMDashboardCharts(Parcel in) {
        this.id = in.readString();
        this.charts = in.createTypedArrayList(DashboardListItem.CREATOR);
    }

    public static final Creator<ResponseGetCRMDashboardCharts> CREATOR = new Creator<ResponseGetCRMDashboardCharts>() {
        @Override
        public ResponseGetCRMDashboardCharts createFromParcel(Parcel source) {
            return new ResponseGetCRMDashboardCharts(source);
        }

        @Override
        public ResponseGetCRMDashboardCharts[] newArray(int size) {
            return new ResponseGetCRMDashboardCharts[size];
        }
    };

}
