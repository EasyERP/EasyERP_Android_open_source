package com.thinkmobiles.easyerp.data.model.hr.employees;

import android.os.Parcel;
import android.os.Parcelable;

import com.thinkmobiles.easyerp.data.model.crm.filter.FilterItem;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.Address;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.AttachmentItem;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.CreatedEditedBy;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.LeadDetailWorkflow;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.Name;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.Phone;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.Social;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.User;
import com.thinkmobiles.easyerp.data.model.hr.employees.details.EmployeeTransferItem;
import com.thinkmobiles.easyerp.data.model.hr.employees.details.EventReason;
import com.thinkmobiles.easyerp.data.model.hr.employees.details.SimpleNoteItem;
import com.thinkmobiles.easyerp.data.model.hr.employees.item.Manager;
import com.thinkmobiles.easyerp.data.model.hr.job_positions.detail.Groups;

import java.util.ArrayList;

/**
 * Created by Lynx on 3/17/2017.
 */

public class ResponseEmployeeDetails implements Parcelable {

    public String id;
    public String nextAction;
    public int expectedSalary;
    public int proposedSalary;
    public String identNo;
    public int passportNo;
    public String dateBirth;
//    public String __v;
    public String userName;
//    public String externalId;   //String?
//    public String lastFire; //String?
    public ArrayList<EmployeeTransferItem> transfer;
    public ArrayList<String> fire;  //String?
    public ArrayList<String> hire;
    public Social social;
    public int sequence;
    public String jobType;
    public String gender;
    public String employmentType;
    public String marital;
    public EventReason eventReason;
    public ArrayList<SimpleNoteItem> notes;
    public ArrayList<AttachmentItem> attachments;
    public CreatedEditedBy editedBy;
    public CreatedEditedBy createdBy;
    public String creationDate;
    public String color;
    public String otherInfo;
    public Groups groups;
    public String whoCanRW;
    public LeadDetailWorkflow workflow;
    public String referredBy;
    public String source;
    public int age;
    public Address homeAddress;
    public String otherId;
    public String bankAccountNo;
    public String nationality;
    public Manager manager;
    public FilterItem scheduledPay;
    public String payrollStructureType;
    public FilterItem weeklyScheduler;
    public FilterItem jobPosition;
    public FilterItem department;
    public String visibility;
    public User relatedUser;
    public String officeLocation;
    public String skype;
    public Phone workPhones;
    public String personalEmail;
    public String workEmail;
    public Address workAddress;
    public ArrayList<String> tags;
    public Name name;
    public String subject;
    public String imageSrc;
    public boolean isEmployee;
    public String fullName;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.nextAction);
        dest.writeInt(this.expectedSalary);
        dest.writeInt(this.proposedSalary);
        dest.writeString(this.identNo);
        dest.writeInt(this.passportNo);
        dest.writeString(this.dateBirth);
        dest.writeString(this.userName);
        dest.writeTypedList(this.transfer);
        dest.writeStringList(this.fire);
        dest.writeStringList(this.hire);
        dest.writeParcelable(this.social, flags);
        dest.writeInt(this.sequence);
        dest.writeString(this.jobType);
        dest.writeString(this.gender);
        dest.writeString(this.employmentType);
        dest.writeString(this.marital);
        dest.writeParcelable(this.eventReason, flags);
        dest.writeTypedList(this.notes);
        dest.writeTypedList(this.attachments);
        dest.writeParcelable(this.editedBy, flags);
        dest.writeParcelable(this.createdBy, flags);
        dest.writeString(this.creationDate);
        dest.writeString(this.color);
        dest.writeString(this.otherInfo);
        dest.writeParcelable(this.groups, flags);
        dest.writeString(this.whoCanRW);
        dest.writeParcelable(this.workflow, flags);
        dest.writeString(this.referredBy);
        dest.writeString(this.source);
        dest.writeInt(this.age);
        dest.writeParcelable(this.homeAddress, flags);
        dest.writeString(this.otherId);
        dest.writeString(this.bankAccountNo);
        dest.writeString(this.nationality);
        dest.writeParcelable(this.manager, flags);
        dest.writeParcelable(this.scheduledPay, flags);
        dest.writeString(this.payrollStructureType);
        dest.writeParcelable(this.weeklyScheduler, flags);
        dest.writeParcelable(this.jobPosition, flags);
        dest.writeParcelable(this.department, flags);
        dest.writeString(this.visibility);
        dest.writeParcelable(this.relatedUser, flags);
        dest.writeString(this.officeLocation);
        dest.writeString(this.skype);
        dest.writeParcelable(this.workPhones, flags);
        dest.writeString(this.personalEmail);
        dest.writeString(this.workEmail);
        dest.writeParcelable(this.workAddress, flags);
        dest.writeStringList(this.tags);
        dest.writeParcelable(this.name, flags);
        dest.writeString(this.subject);
        dest.writeString(this.imageSrc);
        dest.writeByte(this.isEmployee ? (byte) 1 : (byte) 0);
        dest.writeString(this.fullName);
    }

    public ResponseEmployeeDetails() {
    }

    protected ResponseEmployeeDetails(Parcel in) {
        this.id = in.readString();
        this.nextAction = in.readString();
        this.expectedSalary = in.readInt();
        this.proposedSalary = in.readInt();
        this.identNo = in.readString();
        this.passportNo = in.readInt();
        this.dateBirth = in.readString();
        this.userName = in.readString();
        this.transfer = in.createTypedArrayList(EmployeeTransferItem.CREATOR);
        this.fire = in.createStringArrayList();
        this.hire = in.createStringArrayList();
        this.social = in.readParcelable(Social.class.getClassLoader());
        this.sequence = in.readInt();
        this.jobType = in.readString();
        this.gender = in.readString();
        this.employmentType = in.readString();
        this.marital = in.readString();
        this.eventReason = in.readParcelable(EventReason.class.getClassLoader());
        this.notes = in.createTypedArrayList(SimpleNoteItem.CREATOR);
        this.attachments = in.createTypedArrayList(AttachmentItem.CREATOR);
        this.editedBy = in.readParcelable(CreatedEditedBy.class.getClassLoader());
        this.createdBy = in.readParcelable(CreatedEditedBy.class.getClassLoader());
        this.creationDate = in.readString();
        this.color = in.readString();
        this.otherInfo = in.readString();
        this.groups = in.readParcelable(Groups.class.getClassLoader());
        this.whoCanRW = in.readString();
        this.workflow = in.readParcelable(LeadDetailWorkflow.class.getClassLoader());
        this.referredBy = in.readString();
        this.source = in.readString();
        this.age = in.readInt();
        this.homeAddress = in.readParcelable(Address.class.getClassLoader());
        this.otherId = in.readString();
        this.bankAccountNo = in.readString();
        this.nationality = in.readString();
        this.manager = in.readParcelable(Manager.class.getClassLoader());
        this.scheduledPay = in.readParcelable(FilterItem.class.getClassLoader());
        this.payrollStructureType = in.readString();
        this.weeklyScheduler = in.readParcelable(FilterItem.class.getClassLoader());
        this.jobPosition = in.readParcelable(FilterItem.class.getClassLoader());
        this.department = in.readParcelable(FilterItem.class.getClassLoader());
        this.visibility = in.readString();
        this.relatedUser = in.readParcelable(User.class.getClassLoader());
        this.officeLocation = in.readString();
        this.skype = in.readString();
        this.workPhones = in.readParcelable(Phone.class.getClassLoader());
        this.personalEmail = in.readString();
        this.workEmail = in.readString();
        this.workAddress = in.readParcelable(Address.class.getClassLoader());
        this.tags = in.createStringArrayList();
        this.name = in.readParcelable(Name.class.getClassLoader());
        this.subject = in.readString();
        this.imageSrc = in.readString();
        this.isEmployee = in.readByte() != 0;
        this.fullName = in.readString();
    }

    public static final Parcelable.Creator<ResponseEmployeeDetails> CREATOR = new Parcelable.Creator<ResponseEmployeeDetails>() {
        @Override
        public ResponseEmployeeDetails createFromParcel(Parcel source) {
            return new ResponseEmployeeDetails(source);
        }

        @Override
        public ResponseEmployeeDetails[] newArray(int size) {
            return new ResponseEmployeeDetails[size];
        }
    };
}
