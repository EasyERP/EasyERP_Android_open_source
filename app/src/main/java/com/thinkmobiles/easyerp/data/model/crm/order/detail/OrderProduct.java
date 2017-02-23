package com.thinkmobiles.easyerp.data.model.crm.order.detail;


import android.os.Parcel;
import android.os.Parcelable;

import com.thinkmobiles.easyerp.data.model.crm.filter.FilterItem;

import java.util.ArrayList;

public class OrderProduct implements Parcelable {

    /**
     * {
     "_id": "58905c25a46efb480a4f11db",
     "description": "ASUS Transformer Book",
     "totalTaxes": 158,
     "channel": null,
     "creditAccount": {
     "_id": "565eb53a6aa50532e5df0bc8",
     "name": "100000 Fixed Asset Account"
     },
     "debitAccount": null,
     "creationDate": "2017-01-31T14:45:04.981Z",
     "nominalCode": 0,
     "subTotal": 1000,
     "costPrice": null,
     "unitPrice": 1000,
     "taxes": [
     {
     "tax": 158,
     "taxCode": {
     "_id": "5889f706a83fa1b318c61fee",
     "rate": 0.1577,
     "fullName": "TST Test Demo tax 15.77%"
     }
     }
     ],
     "quantity": 1,
     "warehouse": {
     "_id": "587e439bab1707af208fdbbe",
     "name": "Arctica main"
     },
     "order": "58905c25a46efb480a4f11d5",
     "product": {
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
     "goodsNotes": [],
     "fulfilled": 0
     },
     */

    public String _id;
    public String description;
    public Double totalTaxes;
//    public Object channel;
    public FilterItem creditAccount;
    public FilterItem debitAccount;
    public String creationDate;
    public Double nominalCode;
    public Double subTotal;
    public Double costPrice;
    public Double unitPrice;
    public ArrayList<OrderTax> taxes;
    public Double quantity;
    public FilterItem warehouse;
    public String order;
    public SubProduct product;
//    public ArrayList<NoteItem> goodsNotes;
    public Double fulfilled;


    public OrderProduct() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this._id);
        dest.writeString(this.description);
        dest.writeValue(this.totalTaxes);
        dest.writeParcelable(this.creditAccount, flags);
        dest.writeParcelable(this.debitAccount, flags);
        dest.writeString(this.creationDate);
        dest.writeValue(this.nominalCode);
        dest.writeValue(this.subTotal);
        dest.writeValue(this.costPrice);
        dest.writeValue(this.unitPrice);
        dest.writeTypedList(this.taxes);
        dest.writeValue(this.quantity);
        dest.writeParcelable(this.warehouse, flags);
        dest.writeString(this.order);
        dest.writeParcelable(this.product, flags);
        dest.writeValue(this.fulfilled);
    }

    protected OrderProduct(Parcel in) {
        this._id = in.readString();
        this.description = in.readString();
        this.totalTaxes = (Double) in.readValue(Double.class.getClassLoader());
        this.creditAccount = in.readParcelable(FilterItem.class.getClassLoader());
        this.debitAccount = in.readParcelable(FilterItem.class.getClassLoader());
        this.creationDate = in.readString();
        this.nominalCode = (Double) in.readValue(Double.class.getClassLoader());
        this.subTotal = (Double) in.readValue(Double.class.getClassLoader());
        this.costPrice = (Double) in.readValue(Double.class.getClassLoader());
        this.unitPrice = (Double) in.readValue(Double.class.getClassLoader());
        this.taxes = in.createTypedArrayList(OrderTax.CREATOR);
        this.quantity = (Double) in.readValue(Double.class.getClassLoader());
        this.warehouse = in.readParcelable(FilterItem.class.getClassLoader());
        this.order = in.readString();
        this.product = in.readParcelable(SubProduct.class.getClassLoader());
        this.fulfilled = (Double) in.readValue(Double.class.getClassLoader());
    }

    public static final Creator<OrderProduct> CREATOR = new Creator<OrderProduct>() {
        @Override
        public OrderProduct createFromParcel(Parcel source) {
            return new OrderProduct(source);
        }

        @Override
        public OrderProduct[] newArray(int size) {
            return new OrderProduct[size];
        }
    };
}

