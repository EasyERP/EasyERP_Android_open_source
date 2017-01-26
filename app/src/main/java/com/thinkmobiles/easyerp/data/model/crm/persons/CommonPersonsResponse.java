package com.thinkmobiles.easyerp.data.model.crm.persons;

import android.os.Parcel;
import android.os.Parcelable;

import com.thinkmobiles.easyerp.data.model.crm.persons.images.ResponseGetCustomersImages;

/**
 * Created by Lynx on 1/23/2017.
 */

public class CommonPersonsResponse implements Parcelable {
    public ResponseGetPersons responseGetPersons;
    public ResponseGetCustomersImages responseGetCustomersImages;

    public CommonPersonsResponse(ResponseGetPersons responseGetPersons, ResponseGetCustomersImages responseGetCustomersImages) {
        this.responseGetPersons = responseGetPersons;
        this.responseGetCustomersImages = responseGetCustomersImages;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.responseGetPersons, flags);
        dest.writeParcelable(this.responseGetCustomersImages, flags);
    }

    protected CommonPersonsResponse(Parcel in) {
        this.responseGetPersons = in.readParcelable(ResponseGetPersons.class.getClassLoader());
        this.responseGetCustomersImages = in.readParcelable(ResponseGetCustomersImages.class.getClassLoader());
    }

    public static final Parcelable.Creator<CommonPersonsResponse> CREATOR = new Parcelable.Creator<CommonPersonsResponse>() {
        @Override
        public CommonPersonsResponse createFromParcel(Parcel source) {
            return new CommonPersonsResponse(source);
        }

        @Override
        public CommonPersonsResponse[] newArray(int size) {
            return new CommonPersonsResponse[size];
        }
    };
}
