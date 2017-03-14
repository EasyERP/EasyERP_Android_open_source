package com.thinkmobiles.easyerp.data.model.hr.applications;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.thinkmobiles.easyerp.data.model.crm.filter.FilterItem;
import com.thinkmobiles.easyerp.data.model.crm.leads.EditedBy;
import com.thinkmobiles.easyerp.data.model.crm.leads.Workflow;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.Name;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.Phone;

import java.util.List;

/**
 * @author Michael Soyma (Created on 3/13/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public final class Application implements Parcelable {

    @SerializedName("_id")
    public String id;
    public EditedBy createdBy;
    public EditedBy editedBy;
    public String dateBirth;
    public FilterItem department;
    public FilterItem jobPosition;
    public List<String> fire, hire;
    public boolean isEmployee;
    public String jobType;
    public Name name;
    public String personalEmail;
    public int sequance, total;
    public String skype;
    public String workEmail;
    public Phone workPhones;
    public Workflow workflow;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeParcelable(this.createdBy, flags);
        dest.writeParcelable(this.editedBy, flags);
        dest.writeString(this.dateBirth);
        dest.writeParcelable(this.department, flags);
        dest.writeParcelable(this.jobPosition, flags);
        dest.writeStringList(this.fire);
        dest.writeStringList(this.hire);
        dest.writeByte(this.isEmployee ? (byte) 1 : (byte) 0);
        dest.writeString(this.jobType);
        dest.writeParcelable(this.name, flags);
        dest.writeString(this.personalEmail);
        dest.writeInt(this.sequance);
        dest.writeInt(this.total);
        dest.writeString(this.skype);
        dest.writeString(this.workEmail);
        dest.writeParcelable(this.workPhones, flags);
        dest.writeParcelable(this.workflow, flags);
    }

    public Application() {
    }

    protected Application(Parcel in) {
        this.id = in.readString();
        this.createdBy = in.readParcelable(EditedBy.class.getClassLoader());
        this.editedBy = in.readParcelable(EditedBy.class.getClassLoader());
        this.dateBirth = in.readString();
        this.department = in.readParcelable(FilterItem.class.getClassLoader());
        this.jobPosition = in.readParcelable(FilterItem.class.getClassLoader());
        this.fire = in.createStringArrayList();
        this.hire = in.createStringArrayList();
        this.isEmployee = in.readByte() != 0;
        this.jobType = in.readString();
        this.name = in.readParcelable(Name.class.getClassLoader());
        this.personalEmail = in.readString();
        this.sequance = in.readInt();
        this.total = in.readInt();
        this.skype = in.readString();
        this.workEmail = in.readString();
        this.workPhones = in.readParcelable(Phone.class.getClassLoader());
        this.workflow = in.readParcelable(Workflow.class.getClassLoader());
    }

    public static final Parcelable.Creator<Application> CREATOR = new Parcelable.Creator<Application>() {
        @Override
        public Application createFromParcel(Parcel source) {
            return new Application(source);
        }

        @Override
        public Application[] newArray(int size) {
            return new Application[size];
        }
    };
}
