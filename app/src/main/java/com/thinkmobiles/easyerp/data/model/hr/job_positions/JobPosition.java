package com.thinkmobiles.easyerp.data.model.hr.job_positions;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.thinkmobiles.easyerp.data.model.crm.filter.FilterItem;
import com.thinkmobiles.easyerp.data.model.crm.leads.EditedBy;
import com.thinkmobiles.easyerp.data.model.crm.leads.Workflow;

/**
 * @author Michael Soyma (Created on 3/14/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public final class JobPosition implements Parcelable {

    @SerializedName("_id")
    public String id;
    public EditedBy createdBy;
    public EditedBy editedBy;
    public String description;
    public FilterItem department;
    public int expectedRecruitment;
    public FilterItem interviewForm;
    public String name;
    public int numberOfEmployees;
    public int totalForecastedEmployees;
    public String requirements;
    public String whoCanRW;
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
        dest.writeString(this.description);
        dest.writeParcelable(this.department, flags);
        dest.writeInt(this.expectedRecruitment);
        dest.writeParcelable(this.interviewForm, flags);
        dest.writeString(this.name);
        dest.writeInt(this.numberOfEmployees);
        dest.writeInt(this.totalForecastedEmployees);
        dest.writeString(this.requirements);
        dest.writeString(this.whoCanRW);
        dest.writeParcelable(this.workflow, flags);
    }

    public JobPosition() {
    }

    protected JobPosition(Parcel in) {
        this.id = in.readString();
        this.createdBy = in.readParcelable(EditedBy.class.getClassLoader());
        this.editedBy = in.readParcelable(EditedBy.class.getClassLoader());
        this.description = in.readString();
        this.department = in.readParcelable(FilterItem.class.getClassLoader());
        this.expectedRecruitment = in.readInt();
        this.interviewForm = in.readParcelable(FilterItem.class.getClassLoader());
        this.name = in.readString();
        this.numberOfEmployees = in.readInt();
        this.totalForecastedEmployees = in.readInt();
        this.requirements = in.readString();
        this.whoCanRW = in.readString();
        this.workflow = in.readParcelable(Workflow.class.getClassLoader());
    }

    public static final Parcelable.Creator<JobPosition> CREATOR = new Parcelable.Creator<JobPosition>() {
        @Override
        public JobPosition createFromParcel(Parcel source) {
            return new JobPosition(source);
        }

        @Override
        public JobPosition[] newArray(int size) {
            return new JobPosition[size];
        }
    };
}
