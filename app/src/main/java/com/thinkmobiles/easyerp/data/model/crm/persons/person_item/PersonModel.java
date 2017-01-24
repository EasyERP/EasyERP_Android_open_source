package com.thinkmobiles.easyerp.data.model.crm.persons.person_item;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Lynx on 1/20/2017.
 */

public class PersonModel implements Parcelable {
    public String _id;
    public PersonCreatedEditedBy editedBy;
    public PersonCreatedEditedBy createdBy;
    public PersonPhones phones;
    public PersonAddress address;
    public String email;
    public PersonName name;
    public String fullName;
    public String id;

    public PersonModel() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this._id);
        dest.writeParcelable(this.editedBy, flags);
        dest.writeParcelable(this.createdBy, flags);
        dest.writeParcelable(this.phones, flags);
        dest.writeParcelable(this.address, flags);
        dest.writeString(this.email);
        dest.writeParcelable(this.name, flags);
        dest.writeString(this.fullName);
        dest.writeString(this.id);
    }

    protected PersonModel(Parcel in) {
        this._id = in.readString();
        this.editedBy = in.readParcelable(PersonCreatedEditedBy.class.getClassLoader());
        this.createdBy = in.readParcelable(PersonCreatedEditedBy.class.getClassLoader());
        this.phones = in.readParcelable(PersonPhones.class.getClassLoader());
        this.address = in.readParcelable(PersonAddress.class.getClassLoader());
        this.email = in.readString();
        this.name = in.readParcelable(PersonName.class.getClassLoader());
        this.fullName = in.readString();
        this.id = in.readString();
    }

    public static final Creator<PersonModel> CREATOR = new Creator<PersonModel>() {
        @Override
        public PersonModel createFromParcel(Parcel source) {
            return new PersonModel(source);
        }

        @Override
        public PersonModel[] newArray(int size) {
            return new PersonModel[size];
        }
    };
}
