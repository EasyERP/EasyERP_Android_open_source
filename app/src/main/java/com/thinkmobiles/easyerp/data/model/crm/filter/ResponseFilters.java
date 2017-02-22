package com.thinkmobiles.easyerp.data.model.crm.filter;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * @author Alex Michenko (Created on 16.02.17).
 *         Company: Thinkmobiles
 *         Email: alex.michenko@thinkmobiles.com
 */

public class ResponseFilters implements Parcelable {

    public ArrayList<FilterInfo> filters = new ArrayList<>();

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.filters);
    }

    public ResponseFilters() {
    }

    protected ResponseFilters(Parcel in) {
        this.filters = in.createTypedArrayList(FilterInfo.CREATOR);
    }

    public static final Parcelable.Creator<ResponseFilters> CREATOR = new Parcelable.Creator<ResponseFilters>() {
        @Override
        public ResponseFilters createFromParcel(Parcel source) {
            return new ResponseFilters(source);
        }

        @Override
        public ResponseFilters[] newArray(int size) {
            return new ResponseFilters[size];
        }
    };
}
