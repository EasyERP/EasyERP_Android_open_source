package com.thinkmobiles.easyerp.data.model.crm.companies;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Lynx on 2/2/2017.
 */

public class ResponseGetCompanies implements Parcelable {
    public ArrayList<CompanyListItem> data;
    public int total;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.data);
        dest.writeInt(this.total);
    }

    public ResponseGetCompanies() {
    }

    protected ResponseGetCompanies(Parcel in) {
        this.data = in.createTypedArrayList(CompanyListItem.CREATOR);
        this.total = in.readInt();
    }

    public static final Parcelable.Creator<ResponseGetCompanies> CREATOR = new Parcelable.Creator<ResponseGetCompanies>() {
        @Override
        public ResponseGetCompanies createFromParcel(Parcel source) {
            return new ResponseGetCompanies(source);
        }

        @Override
        public ResponseGetCompanies[] newArray(int size) {
            return new ResponseGetCompanies[size];
        }
    };
}
