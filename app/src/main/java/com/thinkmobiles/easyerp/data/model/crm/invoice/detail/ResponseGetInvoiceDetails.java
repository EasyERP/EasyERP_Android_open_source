package com.thinkmobiles.easyerp.data.model.crm.invoice.detail;

import android.os.Parcel;
import android.os.Parcelable;

import com.thinkmobiles.easyerp.data.model.crm.filter.FilterItem;
import com.thinkmobiles.easyerp.data.model.crm.invoice.Currency;
import com.thinkmobiles.easyerp.data.model.crm.invoice.PaymentInfo;
import com.thinkmobiles.easyerp.data.model.crm.leads.Workflow;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.AttachmentItem;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.CreatedEditedBy;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.Groups;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.NoteItem;
import com.thinkmobiles.easyerp.data.model.crm.order.detail.OrderProduct;
import com.thinkmobiles.easyerp.data.model.crm.order.detail.PaymentMethod;
import com.thinkmobiles.easyerp.data.model.crm.order.detail.Prepayment;
import com.thinkmobiles.easyerp.data.model.crm.order.detail.Supplier;
import com.thinkmobiles.easyerp.data.model.crm.persons.details.CreatedEditedUserString;

import java.util.ArrayList;

/**
 * @author Alex Michenko (Created on 06.02.17).
 *         Company: Thinkmobiles
 *         Email: alex.michenko@thinkmobiles.com
 */

public class ResponseGetInvoiceDetails implements Parcelable {

    /**
     * {
     id: "5891c112c1a1d14c48299930",
     _type: "Invoices",
     paymentDate: "2017-01-31T09:44:31.000Z",
     __v: 0,
     reconcile: true,
     integrationId: "",
     project: null,
     emailed: false,
     approved: false,
     removable: true,
     invoiced: false,
     notes: [
     {
     history: {
     date: "2017-02-01T11:05:54.727Z",
     contentId: "5891c112c1a1d14c48299930",
     collectionName: "Invoice",
     changedField: "Status",
     newValue: "Partially Paid"
     },
     date: "2017-02-01T11:05:54.727Z"
     },
     {
     history: {
     date: "2017-02-01T11:05:54.727Z",
     contentId: "5891c112c1a1d14c48299930",
     collectionName: "Invoice",
     changedField: "Invoice Date",
     newValue: "2017-02-01T11:05:54.000Z",
     prevValue: null
     },
     date: "2017-02-01T11:05:54.727Z"
     },
     {
     history: {
     date: "2017-02-01T11:05:54.727Z",
     contentId: "5891c112c1a1d14c48299930",
     collectionName: "Invoice",
     changedField: "Creation Date",
     newValue: "2017-01-31T09:43:01.730Z",
     prevValue: null
     },
     date: "2017-02-01T11:05:54.727Z"
     }
     ],
     attachments: [ ],
     channel: null,
     editedBy: {
     date: "2017-02-01T11:05:54.207Z",
     user: {
     id: "585cdc6ed210f7ec05c45f1f",
     login: "testAdmin"
     }
     },
     createdBy: {
     date: "2017-01-31T09:43:01.730Z",
     user: {
     id: "585cdc6ed210f7ec05c45f1f",
     login: "testAdmin"
     }
     },
     creationDate: "2017-01-31T09:43:01.730Z",
     groups: {
     group: [ ],
     users: [ ],
     owner: null
     },
     whoCanRW: "everyOne",
     workflow: {
     id: "55647d952e4aa3804a765eca",
     status: "In Progress",
     name: "Partially Paid"
     },
     payments: [
     {
     _id: "588889b360a8e5cc35aaf080",
     paymentRef: "",
     createdBy: {
     date: "2017-01-25T11:19:15.745Z",
     user: {
     _id: "5797555d10343a8c275f3e70",
     login: "liliya.mykhailova"
     }
     },
     currency: {
     rate: 1,
     _id: {
     _id: "USD",
     symbol: "$"
     }
     },
     name: "PP_48",
     date: "2017-01-25T11:19:15.000Z",
     paidAmount: 110000
     }
     ],
     paymentInfo: {
     taxes: 1814,
     discount: 0,
     unTaxed: 11500,
     balance: 578.9999999999982,
     total: 13313.999999999998
     },
     paymentMethod: {
     id: "575fb4c97330dff16a34038d",
     name: "OTP",
     account: "OTP USD",
     bank: "OTP",
     swiftCode: "",
     address: "",
     owner: ""
     },
     paymentTerms: null,
     salesPerson: "55b92ad221e4b7c40f00009e",
     currency: {
     rate: 1,
     id: {
     id: "USD",
     active: true,
     decPlace: 2,
     symbol: "$",
     name: "USD"
     }
     },
     journal: {
     id: "565ef6ba270f53d02ee71d65",
     name: "Invoice Journal"
     },
     invoiceDate: "2017-02-01T11:05:54.000Z",
     paymentReference: "SO_37",
     sourceDocument: null,
     supplier: {
     id: "57cea443a3c7f97535e33dc3",
     address: {
     country: "",
     zip: "",
     state: "",
     city: "",
     street: ""
     },
     name: {
     last: "Fernandes",
     first: "Aderito"
     },
     fullName: "Aderito Fernandes",
     id: "57cea443a3c7f97535e33dc3"
     },
     forSales: true,
     name: "SI42",
     id: "5891c112c1a1d14c48299930"
     }
     */


    public String id;
    public String _type;
    public int _v;
    public boolean reconcile;
    public boolean emailed;
    public boolean approved;
    public boolean removable;
    public boolean invoiced;
    public String integrationId;
    public ArrayList<NoteItem> notes;
    public ArrayList<AttachmentItem> attachments;
    //    public Object channel;
    public CreatedEditedUserString editedBy;
    public CreatedEditedUserString createdBy;
    //    public Object project;
    public String creationDate;
    public Groups groups;
    public String whoCanRW;
    public Workflow workflow;
    public PaymentInfo paymentInfo;
    public PaymentMethod paymentMethod;
    //    public Object paymentTerms;
    public String salesPerson;
    public Currency currency;
    public FilterItem journal;
    public String invoiceDate;
    public String dueDate;
    public String paymentReference;
    public String name;
    public ArrayList<InvoicePayment> payments;
    //    public Object sourceDocument;
    public Supplier supplier;
    public boolean forSales;
    public ArrayList<OrderProduct> products;
    public FilterItem account;
//    public ArrayList<Object> goodsNotes;
    public Prepayment prepayment;


    public ResponseGetInvoiceDetails() {
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
        dest.writeByte(this.reconcile ? (byte) 1 : (byte) 0);
        dest.writeByte(this.emailed ? (byte) 1 : (byte) 0);
        dest.writeByte(this.approved ? (byte) 1 : (byte) 0);
        dest.writeByte(this.removable ? (byte) 1 : (byte) 0);
        dest.writeByte(this.invoiced ? (byte) 1 : (byte) 0);
        dest.writeString(this.integrationId);
        dest.writeTypedList(this.notes);
        dest.writeTypedList(this.attachments);
        dest.writeParcelable(this.editedBy, flags);
        dest.writeParcelable(this.createdBy, flags);
        dest.writeString(this.creationDate);
        dest.writeParcelable(this.groups, flags);
        dest.writeString(this.whoCanRW);
        dest.writeParcelable(this.workflow, flags);
        dest.writeParcelable(this.paymentInfo, flags);
        dest.writeParcelable(this.paymentMethod, flags);
        dest.writeString(this.salesPerson);
        dest.writeParcelable(this.currency, flags);
        dest.writeParcelable(this.journal, flags);
        dest.writeString(this.invoiceDate);
        dest.writeString(this.dueDate);
        dest.writeString(this.paymentReference);
        dest.writeString(this.name);
        dest.writeTypedList(this.payments);
        dest.writeParcelable(this.supplier, flags);
        dest.writeByte(this.forSales ? (byte) 1 : (byte) 0);
        dest.writeTypedList(this.products);
        dest.writeParcelable(this.account, flags);
        dest.writeParcelable(this.prepayment, flags);
    }

    protected ResponseGetInvoiceDetails(Parcel in) {
        this.id = in.readString();
        this._type = in.readString();
        this._v = in.readInt();
        this.reconcile = in.readByte() != 0;
        this.emailed = in.readByte() != 0;
        this.approved = in.readByte() != 0;
        this.removable = in.readByte() != 0;
        this.invoiced = in.readByte() != 0;
        this.integrationId = in.readString();
        this.notes = in.createTypedArrayList(NoteItem.CREATOR);
        this.attachments = in.createTypedArrayList(AttachmentItem.CREATOR);
        this.editedBy = in.readParcelable(CreatedEditedBy.class.getClassLoader());
        this.createdBy = in.readParcelable(CreatedEditedBy.class.getClassLoader());
        this.creationDate = in.readString();
        this.groups = in.readParcelable(Groups.class.getClassLoader());
        this.whoCanRW = in.readString();
        this.workflow = in.readParcelable(Workflow.class.getClassLoader());
        this.paymentInfo = in.readParcelable(PaymentInfo.class.getClassLoader());
        this.paymentMethod = in.readParcelable(PaymentMethod.class.getClassLoader());
        this.salesPerson = in.readString();
        this.currency = in.readParcelable(Currency.class.getClassLoader());
        this.journal = in.readParcelable(FilterItem.class.getClassLoader());
        this.invoiceDate = in.readString();
        this.dueDate = in.readString();
        this.paymentReference = in.readString();
        this.name = in.readString();
        this.payments = in.createTypedArrayList(InvoicePayment.CREATOR);
        this.supplier = in.readParcelable(Supplier.class.getClassLoader());
        this.forSales = in.readByte() != 0;
        this.products = in.createTypedArrayList(OrderProduct.CREATOR);
        this.account = in.readParcelable(FilterItem.class.getClassLoader());
        this.prepayment = in.readParcelable(Prepayment.class.getClassLoader());
    }

    public static final Creator<ResponseGetInvoiceDetails> CREATOR = new Creator<ResponseGetInvoiceDetails>() {
        @Override
        public ResponseGetInvoiceDetails createFromParcel(Parcel source) {
            return new ResponseGetInvoiceDetails(source);
        }

        @Override
        public ResponseGetInvoiceDetails[] newArray(int size) {
            return new ResponseGetInvoiceDetails[size];
        }
    };
}
