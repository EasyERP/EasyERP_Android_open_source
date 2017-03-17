package com.thinkmobiles.easyerp.data.model.inventory.product.detail;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by samson on 13.03.17.
 */

public class InventoryItem implements Parcelable {

    @SerializedName("_id")
    public InventoryId id;
    public String location;
    public String warehouse;
    public int available;
    public int allocated;
    public int onHand;
    public int incoming;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.id, flags);
        dest.writeString(this.location);
        dest.writeString(this.warehouse);
        dest.writeInt(this.available);
        dest.writeInt(this.allocated);
        dest.writeInt(this.onHand);
        dest.writeInt(this.incoming);
    }

    public InventoryItem() {
    }

    protected InventoryItem(Parcel in) {
        this.id = in.readParcelable(InventoryId.class.getClassLoader());
        this.location = in.readString();
        this.warehouse = in.readString();
        this.available = in.readInt();
        this.allocated = in.readInt();
        this.onHand = in.readInt();
        this.incoming = in.readInt();
    }

    public static final Parcelable.Creator<InventoryItem> CREATOR = new Parcelable.Creator<InventoryItem>() {
        @Override
        public InventoryItem createFromParcel(Parcel source) {
            return new InventoryItem(source);
        }

        @Override
        public InventoryItem[] newArray(int size) {
            return new InventoryItem[size];
        }
    };
}
