package com.thinkmobiles.easyerp.data.model.crm.opportunities.list_item;

import android.os.Parcel;
import android.os.Parcelable;

import com.thinkmobiles.easyerp.data.model.crm.dashboard.detail.invoice.SalesPerson;
import com.thinkmobiles.easyerp.data.model.crm.leads.EditedBy;
import com.thinkmobiles.easyerp.data.model.crm.leads.Workflow;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.Customer;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.NextAction;

/**
 * Created by Lynx on 1/30/2017.
 */

public class OpportunityListItem implements Parcelable {
    public String _id;
    public int total;
    public String creationDate; //2016-11-29T11:30:42.423Z
    public String name;
    public ExpectedRevenue expectedRevenue;
    public Customer customer;
    public NextAction nextAction;
    public int sequence;
    public Workflow workflow;
    public String expectedClosing;
    public SalesPerson salesPerson;
    public EditedBy editedBy;

    public OpportunityListItem() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this._id);
        dest.writeInt(this.total);
        dest.writeString(this.creationDate);
        dest.writeString(this.name);
        dest.writeParcelable(this.expectedRevenue, flags);
        dest.writeParcelable(this.customer, flags);
        dest.writeParcelable(this.nextAction, flags);
        dest.writeInt(this.sequence);
        dest.writeParcelable(this.workflow, flags);
        dest.writeString(this.expectedClosing);
        dest.writeParcelable(this.salesPerson, flags);
        dest.writeParcelable(this.editedBy, flags);
    }

    protected OpportunityListItem(Parcel in) {
        this._id = in.readString();
        this.total = in.readInt();
        this.creationDate = in.readString();
        this.name = in.readString();
        this.expectedRevenue = in.readParcelable(ExpectedRevenue.class.getClassLoader());
        this.customer = in.readParcelable(Customer.class.getClassLoader());
        this.nextAction = in.readParcelable(NextAction.class.getClassLoader());
        this.sequence = in.readInt();
        this.workflow = in.readParcelable(Workflow.class.getClassLoader());
        this.expectedClosing = in.readString();
        this.salesPerson = in.readParcelable(SalesPerson.class.getClassLoader());
        this.editedBy = in.readParcelable(EditedBy.class.getClassLoader());
    }

    public static final Creator<OpportunityListItem> CREATOR = new Creator<OpportunityListItem>() {
        @Override
        public OpportunityListItem createFromParcel(Parcel source) {
            return new OpportunityListItem(source);
        }

        @Override
        public OpportunityListItem[] newArray(int size) {
            return new OpportunityListItem[size];
        }
    };
}
