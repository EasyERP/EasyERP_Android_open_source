package com.thinkmobiles.easyerp.data.model.crm.orders.detail;


import android.os.Parcel;
import android.os.Parcelable;

public class OrderTax implements Parcelable {

    /**
     * "taxes": [
     {
     "tax": 158,
     "taxCode": {
     "_id": "5889f706a83fa1b318c61fee",
     "rate": 0.1577,
     "fullName": "TST Test Demo tax 15.77%"
     }
     }
     ],
     */

    public Double tax;
    public OrderTaxCode taxCode;


    public OrderTax() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.tax);
        dest.writeParcelable(this.taxCode, flags);
    }

    protected OrderTax(Parcel in) {
        this.tax = (Double) in.readValue(Double.class.getClassLoader());
        this.taxCode = in.readParcelable(OrderTaxCode.class.getClassLoader());
    }

    public static final Creator<OrderTax> CREATOR = new Creator<OrderTax>() {
        @Override
        public OrderTax createFromParcel(Parcel source) {
            return new OrderTax(source);
        }

        @Override
        public OrderTax[] newArray(int size) {
            return new OrderTax[size];
        }
    };
}
