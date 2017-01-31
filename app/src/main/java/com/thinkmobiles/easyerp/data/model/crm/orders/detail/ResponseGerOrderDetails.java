package com.thinkmobiles.easyerp.data.model.crm.orders.detail;


import android.os.Parcel;
import android.os.Parcelable;

import com.thinkmobiles.easyerp.data.model.crm.dashboard.detail.invoice.Currency;
import com.thinkmobiles.easyerp.data.model.crm.dashboard.detail.invoice.InvoiceItem;
import com.thinkmobiles.easyerp.data.model.crm.dashboard.detail.invoice.PaymentInfo;
import com.thinkmobiles.easyerp.data.model.crm.dashboard.detail.order.OrderStatus;
import com.thinkmobiles.easyerp.data.model.crm.leads.EditedBy;
import com.thinkmobiles.easyerp.data.model.crm.leads.Workflow;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.AttachmentItem;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.CreatedEditedBy;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.Groups;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.Name;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.NoteItem;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.SalesPerson;
import com.thinkmobiles.easyerp.data.model.crm.leads.filter.FilterItem;
import com.thinkmobiles.easyerp.data.model.crm.persons.details.CreatedEditedUserString;

import java.util.ArrayList;

public class ResponseGerOrderDetails implements Parcelable {

    /**
     * {
     "_id": "5890560b3743284b65d83f15",
     "_type": "Order",
     "__v": 0,
     "conflictTypes": [],
     "editedBy": {
     "date": "2017-01-31T10:57:19.927Z",
     "user": {
     "_id": "585cdc6ed210f7ec05c45f1f",
     "login": "testAdmin"
     }
     },
     "channel": null,
     "createdBy": {
     "date": "2017-01-31T09:16:59.706Z",
     "user": "585cdc6ed210f7ec05c45f1f"
     },
     "project": null,
     "creationDate": "2017-01-31T09:16:59.706Z",
     "groups": {
     "group": [],
     "users": [],
     "owner": null
     },
     "notes": [
     {
     "date": "2017-01-31T09:16:59.718Z",
     "history": {
     "newValue": "New Order",
     "changedField": "Status",
     "collectionName": "ORDER",
     "contentId": "5890560b3743284b65d83f15",
     "date": "2017-01-31T09:16:59.718Z"
     }
     },
     {
     "date": "2017-01-31T09:16:59.718Z",
     "history": {
     "prevValue": null,
     "newValue": "2017-01-31T09:16:59.706Z",
     "changedField": "Creation Date",
     "collectionName": "ORDER",
     "contentId": "5890560b3743284b65d83f15",
     "date": "2017-01-31T09:16:59.718Z"
     }
     },
     {
     "date": "2017-01-31T09:16:59.718Z",
     "history": {
     "prevValue": null,
     "newValue": "2017-01-31T00:00:00.000Z",
     "changedField": "Payment Due Date",
     "collectionName": "ORDER",
     "contentId": "5890560b3743284b65d83f15",
     "date": "2017-01-31T09:16:59.718Z"
     }
     },
     {
     "date": "2017-01-31T09:16:59.718Z",
     "history": {
     "prevValue": null,
     "newValue": "2017-01-31T09:16:59.000Z",
     "changedField": "Order Date",
     "collectionName": "ORDER",
     "contentId": "5890560b3743284b65d83f15",
     "date": "2017-01-31T09:16:59.718Z"
     }
     },
     {
     "date": "2017-01-31T09:16:59.718Z",
     "history": {
     "prevValue": null,
     "newValue": "NOT",
     "changedField": "Fulfilled",
     "collectionName": "ORDER",
     "contentId": "5890560b3743284b65d83f15",
     "date": "2017-01-31T09:16:59.718Z"
     }
     },
     {
     "date": "2017-01-31T10:57:19.973Z",
     "history": {
     "prevValue": "2017-01-31T00:00:00.000Z",
     "newValue": "31 Jan, 2017",
     "changedField": "Payment Due Date",
     "collectionName": "ORDER",
     "contentId": "5890560b3743284b65d83f15",
     "date": "2017-01-31T10:57:19.973Z"
     }
     },
     {
     "date": "2017-01-31T10:57:19.973Z",
     "history": {
     "prevValue": "2017-01-31T09:16:59.000Z",
     "newValue": "31 Jan, 2017",
     "changedField": "Order Date",
     "collectionName": "ORDER",
     "contentId": "5890560b3743284b65d83f15",
     "date": "2017-01-31T10:57:19.973Z"
     }
     },
     {
     "_id": "58907e69c6eaaa0a4b797045",
     "attachment": {
     "name": "26995-37240-20.jpg",
     "shortPas": "uploads%2Forder%2F5890560b3743284b65d83f15%2F26995-37240-20.jpg"
     },
     "user": {
     "_id": "585cdc6ed210f7ec05c45f1f",
     "login": "testAdmin"
     },
     "date": "2017-01-31T12:09:13.498Z"
     },
     {
     "_id": "58907e836f8cd71e4bf06a8c",
     "attachment": {
     "name": "Chocolate_chip_cookie.jpg",
     "shortPas": "uploads%2Forder%2F5890560b3743284b65d83f15%2FChocolate_chip_cookie.jpg"
     },
     "user": {
     "_id": "585cdc6ed210f7ec05c45f1f",
     "login": "testAdmin"
     },
     "date": "2017-01-31T12:09:39.788Z"
     }
     ],
     "attachments": [
     {
     "_id": "58907e69c6eaaa0a4b797044",
     "name": "26995-37240-20.jpg",
     "shortPas": "uploads%2Forder%2F5890560b3743284b65d83f15%2F26995-37240-20.jpg",
     "size": "0.157&nbsp;Mb",
     "uploadDate": "2017-01-31T12:09:13.497Z",
     "uploaderName": "testAdmin"
     },
     {
     "_id": "58907e836f8cd71e4bf06a8b",
     "name": "Chocolate_chip_cookie.jpg",
     "shortPas": "uploads%2Forder%2F5890560b3743284b65d83f15%2FChocolate_chip_cookie.jpg",
     "size": "0.279&nbsp;Mb",
     "uploadDate": "2017-01-31T12:09:39.788Z",
     "uploaderName": "testAdmin"
     }
     ],
     "whoCanRW": "everyOne",
     "warehouse": {
     "_id": "57dfc6ea6066337b771e99e2",
     "name": "Main Warehouse"
     },
     "tempWorkflow": null,
     "workflow": {
     "_id": "57f4bce848c62c5c68690dbb",
     "status": "New",
     "name": "New Order"
     },
     "shippingExpenses": {
     "account": null,
     "amount": 0
     },
     "paymentInfo": {
     "taxes": 396964780,
     "unTaxed": 2517223400,
     "discount": 10000,
     "total": 2914188180
     },
     "priceList": {
     "_id": "58109ae869b3249417f74baf",
     "name": "Sale Prices"
     },
     "costList": null,
     "salesPerson": {
     "_id": "55b92ad221e4b7c40f00009e",
     "name": {
     "last": "Michenko",
     "first": "Alex"
     },
     "fullName": "Alex Michenko",
     "id": "55b92ad221e4b7c40f00009e"
     },
     "paymentTerm": null,
     "destination": null,
     "name": "SO_35",
     "paymentMethod": {
     "_id": "565f2e05ab70d49024242e0f",
     "name": "CASH USD",
     "account": "CASH USD",
     "bank": "CASH USD",
     "swiftCode": "",
     "address": "",
     "owner": "CASH USD"
     },
     "status": {
     "shippingStatus": "NOT",
     "fulfillStatus": "NOT",
     "allocateStatus": "NOT"
     },
     "integrationId": "",
     "expectedDate": "2017-01-31T00:00:00.000Z",
     "orderDate": "2017-01-31T00:00:00.000Z",
     "supplier": {
     "_id": "57a8a926c2bdd8bf07e54a3c",
     "address": {
     "country": "",
     "zip": "",
     "state": "",
     "city": "",
     "street": ""
     },
     "name": {
     "last": "Abu",
     "first": "Abdul"
     },
     "fullName": "Abdul Abu",
     "id": "57a8a926c2bdd8bf07e54a3c"
     },
     "type": "Not Ordered",
     "forSales": true,
     "currency": {
     "rate": 1,
     "_id": {
     "_id": "USD",
     "active": true,
     "decPlace": 2,
     "symbol": "$",
     "name": "USD"
     }
     },
     "products": [
     {
     "_id": "5890560b3743284b65d83f1b",
     "description": "ASUS Transformer Book",
     "totalTaxes": 2340,
     "channel": null,
     "creditAccount": {
     "_id": "565eb53a6aa50532e5df0bd6",
     "name": "101401 Erste EUR"
     },
     "debitAccount": null,
     "creationDate": "2017-01-31T12:09:45.698Z",
     "nominalCode": 0,
     "subTotal": 23400,
     "costPrice": null,
     "unitPrice": 7800,
     "taxes": [
     {
     "tax": 2340,
     "taxCode": {
     "_id": "585a5ed1b17bbb2c276244fd",
     "rate": 0.1,
     "fullName": "T1 tax 1 10.00%"
     }
     }
     ],
     "quantity": 3,
     "warehouse": {
     "_id": "57dfc6ea6066337b771e99e2",
     "name": "Main Warehouse"
     },
     "order": "5890560b3743284b65d83f15",
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
     {
     "_id": "5890560b3743284b65d83f1c",
     "description": "Samsung Chromebook",
     "totalTaxes": 15,
     "channel": null,
     "creditAccount": {
     "_id": "5788b4be52adaf4c49e4b51c",
     "name": "104002 Inventory"
     },
     "debitAccount": null,
     "creationDate": "2017-01-31T12:09:45.699Z",
     "nominalCode": 0,
     "subTotal": 2517200000,
     "costPrice": null,
     "unitPrice": 1258600000,
     "taxes": [
     {
     "tax": 396962440,
     "taxCode": {
     "_id": "5889f706a83fa1b318c61fee",
     "rate": 0.1577,
     "fullName": "TST Test Demo tax 15.77%"
     }
     }
     ],
     "quantity": 2,
     "warehouse": {
     "_id": "57dfc6ea6066337b771e99e2",
     "name": "Main Warehouse"
     },
     "order": "5890560b3743284b65d83f15",
     "product": {
     "_id": "586cd2fb037ea6d05621ba6e",
     "info": {
     "EAN": "",
     "ISBN": "",
     "UPC": "",
     "SKU": "XE303C12-A01US3",
     "categories": [
     "564591f9624e48551dfe3b23",
     "586bac939fa9655f562aca99",
     "586bac71d8ce008d56e5f764"
     ],
     "brand": null,
     "description": "",
     "barcode": "",
     "isActive": true,
     "productType": "586ba88d8358528156787df7"
     },
     "name": "Samsung Chromebook"
     },
     "goodsNotes": [],
     "fulfilled": 0
     }
     ],
     "account": null,
     "goodsNotes": [],
     "prepayment": {},
     "invoice": {}
     }
     */

    public String _id;
    public String _type;
    public int _v;
//    public ArrayList conflictTypes;
    public CreatedEditedBy editedBy;
//    public Object channel;
    public CreatedEditedUserString createdBy;
//    public Object project;
    public String creationDate;
    public Groups groups;
    public ArrayList<NoteItem> notes;
    public ArrayList<AttachmentItem> attachments;
    public String whoCanRW;
    public FilterItem warehouse;
    public Workflow tempWorkflow;
    public Workflow workflow;
//    public Object shippingExpenses;
    public PaymentInfo paymentInfo;
    public FilterItem priceList;
//    public Object costList;
    public SalesPerson salesPerson;
//    public Object paymentTerm;
//    public Object destination;
    public String name;
    public PaymentMethod paymentMethod;
    public OrderStatus status;
    public String integrationId;
    public String expectedDate;
    public String orderDate;
    public Supplier supplier;
    public String type;
    public boolean forSales;
    public Currency currency;
    public ArrayList<OrderProduct> products;
//    public Object account;
//    public ArrayList<NoteItem> goodsNotes;
    public Prepayment prepayment;
//    public InvoiceItem invoice;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this._id);
        dest.writeString(this._type);
        dest.writeInt(this._v);
        dest.writeParcelable(this.editedBy, flags);
        dest.writeParcelable(this.createdBy, flags);
        dest.writeString(this.creationDate);
        dest.writeParcelable(this.groups, flags);
        dest.writeTypedList(this.notes);
        dest.writeTypedList(this.attachments);
        dest.writeString(this.whoCanRW);
        dest.writeParcelable(this.warehouse, flags);
        dest.writeParcelable(this.tempWorkflow, flags);
        dest.writeParcelable(this.workflow, flags);
        dest.writeParcelable(this.paymentInfo, flags);
        dest.writeParcelable(this.priceList, flags);
        dest.writeParcelable(this.salesPerson, flags);
        dest.writeString(this.name);
        dest.writeParcelable(this.paymentMethod, flags);
        dest.writeParcelable(this.status, flags);
        dest.writeString(this.integrationId);
        dest.writeString(this.expectedDate);
        dest.writeString(this.orderDate);
        dest.writeParcelable(this.supplier, flags);
        dest.writeString(this.type);
        dest.writeByte(this.forSales ? (byte) 1 : (byte) 0);
        dest.writeParcelable(this.currency, flags);
        dest.writeTypedList(this.products);
        dest.writeParcelable(this.prepayment, flags);
    }

    public ResponseGerOrderDetails() {
    }

    protected ResponseGerOrderDetails(Parcel in) {
        this._id = in.readString();
        this._type = in.readString();
        this._v = in.readInt();
        this.editedBy = in.readParcelable(CreatedEditedBy.class.getClassLoader());
        this.createdBy = in.readParcelable(CreatedEditedUserString.class.getClassLoader());
        this.creationDate = in.readString();
        this.groups = in.readParcelable(Groups.class.getClassLoader());
        this.notes = in.createTypedArrayList(NoteItem.CREATOR);
        this.attachments = in.createTypedArrayList(AttachmentItem.CREATOR);
        this.whoCanRW = in.readString();
        this.warehouse = in.readParcelable(FilterItem.class.getClassLoader());
        this.tempWorkflow = in.readParcelable(Workflow.class.getClassLoader());
        this.workflow = in.readParcelable(Workflow.class.getClassLoader());
        this.paymentInfo = in.readParcelable(PaymentInfo.class.getClassLoader());
        this.priceList = in.readParcelable(FilterItem.class.getClassLoader());
        this.salesPerson = in.readParcelable(SalesPerson.class.getClassLoader());
        this.name = in.readString();
        this.paymentMethod = in.readParcelable(PaymentMethod.class.getClassLoader());
        this.status = in.readParcelable(OrderStatus.class.getClassLoader());
        this.integrationId = in.readString();
        this.expectedDate = in.readString();
        this.orderDate = in.readString();
        this.supplier = in.readParcelable(Supplier.class.getClassLoader());
        this.type = in.readString();
        this.forSales = in.readByte() != 0;
        this.currency = in.readParcelable(Currency.class.getClassLoader());
        this.products = in.createTypedArrayList(OrderProduct.CREATOR);
        this.prepayment = in.readParcelable(Prepayment.class.getClassLoader());
    }

    public static final Parcelable.Creator<ResponseGerOrderDetails> CREATOR = new Parcelable.Creator<ResponseGerOrderDetails>() {
        @Override
        public ResponseGerOrderDetails createFromParcel(Parcel source) {
            return new ResponseGerOrderDetails(source);
        }

        @Override
        public ResponseGerOrderDetails[] newArray(int size) {
            return new ResponseGerOrderDetails[size];
        }
    };
}
