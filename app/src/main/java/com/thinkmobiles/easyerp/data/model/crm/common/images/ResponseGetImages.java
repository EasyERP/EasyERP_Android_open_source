package com.thinkmobiles.easyerp.data.model.crm.common.images;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Lynx on 1/20/2017.
 */

public class ResponseGetImages implements Parcelable {
    public ArrayList<ImageItem> data;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.data);
    }

    public ResponseGetImages() {
    }

    protected ResponseGetImages(Parcel in) {
        this.data = in.createTypedArrayList(ImageItem.CREATOR);
    }

    public static final Parcelable.Creator<ResponseGetImages> CREATOR = new Parcelable.Creator<ResponseGetImages>() {
        @Override
        public ResponseGetImages createFromParcel(Parcel source) {
            return new ResponseGetImages(source);
        }

        @Override
        public ResponseGetImages[] newArray(int size) {
            return new ResponseGetImages[size];
        }
    };
}
