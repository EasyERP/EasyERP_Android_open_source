package com.thinkmobiles.easyerp.data.model.crm.order.detail;


import android.os.Parcel;
import android.os.Parcelable;

public class SubProduct implements Parcelable {

    /**
     * "product": {
     "_id": "58873d77de350f8f3e25b0bd",
     "info": {
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
     "name": "ASUS Transformer Book"
     },
     */

    public String _id;
    public ProductInfo info;
    public String name;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this._id);
        dest.writeParcelable(this.info, flags);
        dest.writeString(this.name);
    }

    public SubProduct() {
    }

    protected SubProduct(Parcel in) {
        this._id = in.readString();
        this.info = in.readParcelable(ProductInfo.class.getClassLoader());
        this.name = in.readString();
    }

    public static final Parcelable.Creator<SubProduct> CREATOR = new Parcelable.Creator<SubProduct>() {
        @Override
        public SubProduct createFromParcel(Parcel source) {
            return new SubProduct(source);
        }

        @Override
        public SubProduct[] newArray(int size) {
            return new SubProduct[size];
        }
    };
}
