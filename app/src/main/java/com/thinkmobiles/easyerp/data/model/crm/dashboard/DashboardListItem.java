package com.thinkmobiles.easyerp.data.model.crm.dashboard;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.thinkmobiles.easyerp.data.model.crm.dashboard.detail.DashboardChartType;

/**
 * Created by Asus_Dev on 1/18/2017.
 */

public class DashboardListItem implements Parcelable {

    /**
     "_id": "582d617bb11d8d9405a196b1",
     "dataHeight": 1,
     "dataWidth": 6,
     "indexY": 0,
     "indexX": 6,
     "nameId": "chart",
     "type": "overview",
     "dataset": "totalSalesRevenue",
     "startDate": "01, Jan 2017",
     "endDate": "31, Jan 2017",
     "dashboard": "582bfabf5a43a4bc2524bf09",
     "name": "Sales Invoices"
     */

    @SerializedName("_id")
    public String id;
    public String type;
    public String dataset;
    public String name;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.type);
        dest.writeString(this.dataset);
        dest.writeString(this.name);
    }

    protected DashboardListItem(Parcel in) {
        this.id = in.readString();
        this.type = in.readString();
        this.dataset = in.readString();
        this.name = in.readString();
    }

    public static final Creator<DashboardListItem> CREATOR = new Creator<DashboardListItem>() {
        @Override
        public DashboardListItem createFromParcel(Parcel source) {
            return new DashboardListItem(source);
        }

        @Override
        public DashboardListItem[] newArray(int size) {
            return new DashboardListItem[size];
        }
    };

    public DashboardChartType getChartType() {
        return DashboardChartType.valueOf(type.toUpperCase());
    }

}
