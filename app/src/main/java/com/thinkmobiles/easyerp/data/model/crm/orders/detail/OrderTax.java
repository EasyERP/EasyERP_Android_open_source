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

    public int tax;
    public OrderTaxCode taxCode;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.tax);
        dest.writeParcelable(this.taxCode, flags);
    }

    public OrderTax() {
    }

    protected OrderTax(Parcel in) {
        this.tax = in.readInt();
        this.taxCode = in.readParcelable(OrderTaxCode.class.getClassLoader());
    }

    public static final Parcelable.Creator<OrderTax> CREATOR = new Parcelable.Creator<OrderTax>() {
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
