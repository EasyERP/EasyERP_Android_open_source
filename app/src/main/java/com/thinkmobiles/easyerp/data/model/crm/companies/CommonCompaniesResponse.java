package com.thinkmobiles.easyerp.data.model.crm.companies;

import android.os.Parcel;
import android.os.Parcelable;

import com.thinkmobiles.easyerp.data.model.crm.common.images.ResponseGetImages;

/**
 * Created by Lynx on 2/2/2017.
 */

public class CommonCompaniesResponse implements Parcelable {
    public ResponseGetCompanies responseGetCompanies;
    public ResponseGetImages responseGetImages;

    public CommonCompaniesResponse(ResponseGetCompanies responseGetCompanies, ResponseGetImages responseGetImages) {
        this.responseGetCompanies = responseGetCompanies;
        this.responseGetImages = responseGetImages;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.responseGetCompanies, flags);
        dest.writeParcelable(this.responseGetImages, flags);
    }

    protected CommonCompaniesResponse(Parcel in) {
        this.responseGetCompanies = in.readParcelable(ResponseGetCompanies.class.getClassLoader());
        this.responseGetImages = in.readParcelable(ResponseGetImages.class.getClassLoader());
    }

    public static final Parcelable.Creator<CommonCompaniesResponse> CREATOR = new Parcelable.Creator<CommonCompaniesResponse>() {
        @Override
        public CommonCompaniesResponse createFromParcel(Parcel source) {
            return new CommonCompaniesResponse(source);
        }

        @Override
        public CommonCompaniesResponse[] newArray(int size) {
            return new CommonCompaniesResponse[size];
        }
    };
}
