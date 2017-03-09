package com.thinkmobiles.easyerp.data.model.inventory.goods_out_notes.details;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.thinkmobiles.easyerp.data.model.crm.order.detail.Supplier;

/**
 * Created by samson on 07.03.17.
 */

public class GoodsOutNoteOrder implements Parcelable {

    @SerializedName("_id")
    public String id;
    public String _type;
    public int _v;
    //    public ArrayList conflictTypes;
//    public CreatedEditedUserString editedBy;
    //    public Object channel;
//    public CreatedEditedUserString createdBy;
    //    public Object project;
//    public String creationDate;
//    public Groups groups;
//    public ArrayList<NoteItem> notes;
//    public ArrayList<AttachmentItem> attachments;
//    public String whoCanRW;
//    public String warehouse;
//    public String tempWorkflow;
//    public String workflow;
    //    public Object shippingExpenses;
//    public PaymentInfo paymentInfo;
//    public FilterItem priceList;
    //    public Object costList;
//    public SalesPerson salesPerson;
    //    public Object paymentTerm;
//    public Object destination;
    public String name;
//    public String paymentMethod;
//    public OrderStatus status;
//    public String integrationId;
//    public String expectedDate;
//    public String orderDate;
    public Supplier supplier;
//    public String type;
//    public boolean forSales;
//    public Currency currency;
//    public ArrayList<OrderProduct> products;
    //    public Object account;
//    public ArrayList<NoteItem> goodsNotes;
//    public Prepayment prepayment;
//    public InvoiceItem invoice;

    public GoodsOutNoteOrder() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this._type);
        dest.writeInt(this._v);
        dest.writeString(this.name);
        dest.writeParcelable(this.supplier, flags);
    }

    protected GoodsOutNoteOrder(Parcel in) {
        this.id = in.readString();
        this._type = in.readString();
        this._v = in.readInt();
        this.name = in.readString();
        this.supplier = in.readParcelable(Supplier.class.getClassLoader());
    }

    public static final Creator<GoodsOutNoteOrder> CREATOR = new Creator<GoodsOutNoteOrder>() {
        @Override
        public GoodsOutNoteOrder createFromParcel(Parcel source) {
            return new GoodsOutNoteOrder(source);
        }

        @Override
        public GoodsOutNoteOrder[] newArray(int size) {
            return new GoodsOutNoteOrder[size];
        }
    };
}
