package com.thinkmobiles.easyerp.data.model.crm.orders.detail;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class ProductInfo implements Parcelable {

    /**
     *"info": {
     "EAN": "",
     "ISBN": "",
     "UPC": "",
     "SKU": "T100TAF-DK001B",
     "categories": [
     "586baca69fa9655f562aca9b",
     "586bad60f1bdb6265636f9c7"
     ],
     "brand": null,
     "description": "",
     "barcode": "",
     "isActive": true,
     "productType": "586ba8c1f1bdb6265636f9c0"
     },
     */

    public String EAN;
    public String ISBN;
    public String UPC;
    public String SKU;
    public ArrayList<String> categories;
//    public Object brand;
    public String description;
    public String barcode;
    public boolean isActive;
    public String productType;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.EAN);
        dest.writeString(this.ISBN);
        dest.writeString(this.UPC);
        dest.writeString(this.SKU);
        dest.writeStringList(this.categories);
        dest.writeString(this.description);
        dest.writeString(this.barcode);
        dest.writeByte(this.isActive ? (byte) 1 : (byte) 0);
        dest.writeString(this.productType);
    }

    public ProductInfo() {
    }

    protected ProductInfo(Parcel in) {
        this.EAN = in.readString();
        this.ISBN = in.readString();
        this.UPC = in.readString();
        this.SKU = in.readString();
        this.categories = in.createStringArrayList();
        this.description = in.readString();
        this.barcode = in.readString();
        this.isActive = in.readByte() != 0;
        this.productType = in.readString();
    }

    public static final Parcelable.Creator<ProductInfo> CREATOR = new Parcelable.Creator<ProductInfo>() {
        @Override
        public ProductInfo createFromParcel(Parcel source) {
            return new ProductInfo(source);
        }

        @Override
        public ProductInfo[] newArray(int size) {
            return new ProductInfo[size];
        }
    };
}
