package com.thinkmobiles.easyerp.data.model.crm.common.images;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Lynx on 1/20/2017.
 */

public class ResponseGetCustomersImages implements Parcelable {
    public ArrayList<CustomerImageItem> data;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.data);
    }

    public ResponseGetCustomersImages() {
    }

    protected ResponseGetCustomersImages(Parcel in) {
        this.data = in.createTypedArrayList(CustomerImageItem.CREATOR);
    }

    public static final Parcelable.Creator<ResponseGetCustomersImages> CREATOR = new Parcelable.Creator<ResponseGetCustomersImages>() {
        @Override
        public ResponseGetCustomersImages createFromParcel(Parcel source) {
            return new ResponseGetCustomersImages(source);
        }

        @Override
        public ResponseGetCustomersImages[] newArray(int size) {
            return new ResponseGetCustomersImages[size];
        }
    };
}
