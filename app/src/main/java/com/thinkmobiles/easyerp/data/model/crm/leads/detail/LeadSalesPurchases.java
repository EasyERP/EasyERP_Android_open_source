package com.thinkmobiles.easyerp.data.model.crm.leads.detail;


import android.os.Parcel;
import android.os.Parcelable;

public class LeadSalesPurchases implements Parcelable {

    /**
     * "salesPurchases": {
     "receiveMessages": 0,
     "language": "English",
     "reference": "",
     "active": false,
     "implementedBy": null,
     "salesTeam": null,
     "salesPerson": null,
     "isSupplier": false,
     "isCustomer": true
     },
     */

    public int receiveMessages;
    public String language;
    public String reference;
    public boolean active;
//    public String implementedBy;
//    public String salesTeam;
//    public String salesPerson;
    public boolean isSupplier;
    public boolean isCustomer;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.receiveMessages);
        dest.writeString(this.language);
        dest.writeString(this.reference);
        dest.writeByte(this.active ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isSupplier ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isCustomer ? (byte) 1 : (byte) 0);
    }

    public LeadSalesPurchases() {
    }

    protected LeadSalesPurchases(Parcel in) {
        this.receiveMessages = in.readInt();
        this.language = in.readString();
        this.reference = in.readString();
        this.active = in.readByte() != 0;
        this.isSupplier = in.readByte() != 0;
        this.isCustomer = in.readByte() != 0;
    }

    public static final Parcelable.Creator<LeadSalesPurchases> CREATOR = new Parcelable.Creator<LeadSalesPurchases>() {
        @Override
        public LeadSalesPurchases createFromParcel(Parcel source) {
            return new LeadSalesPurchases(source);
        }

        @Override
        public LeadSalesPurchases[] newArray(int size) {
            return new LeadSalesPurchases[size];
        }
    };
}
