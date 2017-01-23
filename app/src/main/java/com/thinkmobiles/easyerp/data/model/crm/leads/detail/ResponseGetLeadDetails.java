package com.thinkmobiles.easyerp.data.model.crm.leads.detail;

import android.os.Parcel;
import android.os.Parcelable;


import com.thinkmobiles.easyerp.data.model.crm.leads.LeadTag;
import com.thinkmobiles.easyerp.data.model.crm.leads.LeadWorkflow;

import java.util.ArrayList;


public class ResponseGetLeadDetails implements Parcelable {

    /**
    {
        "_id": "587dd7a923ddd08c2030f0e8",
        "dateBirth": "1999-01-17T00:00:00.000Z",
        "skype": "skype_aaron",
        "social": {
            "LI": "https://my[].com",
            "FB": ""
        },
        "attachments": [
        {
            "_id": "587dd80fc6a640eb205e8ca2",
                "name": "2017-calendar-template-05.jpg",
                "shortPas": "uploads%2Fopportunities%2F587dd7a923ddd08c2030f0e8%2F2017-calendar-template-05.jpg",
                "size": "0.534&nbsp;Mb",
                "uploadDate": "2017-01-17T08:38:39.219Z",
                "uploaderName": "testAdmin"
        },
        {
            "_id": "587dd82bab1707af208fdb9a",
                "name": "2017-calendar-template-05(1).jpg",
                "shortPas": "uploads%2Fopportunities%2F587dd7a923ddd08c2030f0e8%2F2017-calendar-template-05(1).jpg",
                "size": "0.534&nbsp;Mb",
                "uploadDate": "2017-01-17T08:39:07.026Z",
                "uploaderName": "testAdmin"
        }
        ],
        "notes": [
        {
            "date": "2017-01-17T08:36:57.095Z",
            "history": {
                "editedBy": {
                    "_id": "585cdc6ed210f7ec05c45f1f",
                    "login": "testAdmin"
                },
                "prevValue": null,
                "newValue": "Alex Michenko",
                "changedField": "salesPerson",
                "collectionName": "Opportunities",
                "contentId": "587dd7a923ddd08c2030f0e8",
                "date": "2017-01-17T08:36:57.095Z"
            },
            "user": {
                "_id": "585cdc6ed210f7ec05c45f1f",
                "login": "testAdmin"
            },
            "_id": ""
        },
        {
            "_id": "587dd7d4d3438c9220bcfea1",
            "note": "This is lead note.",
            "user": {
                "login": "testAdmin",
                "_id": "585cdc6ed210f7ec05c45f1f"
            },
            "date": "2017-01-17T08:37:40.569Z",
            "task": null
        },
        {
            "date": "2017-01-17T08:38:03.817Z",
            "task": {
                "_id": "587dd7ebab1707af208fdb93",
                "description": "This is the task for lead.",
                "dealDate": "2017-01-17T08:38:03.817Z",
                "attachments": [],
                "notes": [],
                "type": "",
                "workflow": {
                    "_id": "5783b351df8b918c31af24a8",
                    "name": "Not Started",
                    "status": "New",
                    "wId": "DealTasks",
                    "wName": "Deal Tasks",
                    "sequence": 3,
                    "visible": true
                },
                "dueDate": "2017-01-27T08:29:00.000Z",
                "contact": null,
                "company": null,
                "sequence": 64,
                "category": {
                    "_id": "587762390e749d2d08633c3d",
                    "name": "Find",
                    "color": "bgGreenDark",
                    "type": "Category",
                    "__v": 0
                },
                "assignedTo": {
                    "_id": "55b92ad221e4b7c40f00009e",
                    "name": {
                        "last": "Michenko",
                        "first": "Alex"
                    },
                    "imageSrc": "data:image/jpeg;base64",
                    "fullName": "Alex Michenko",
                    "id": "55b92ad221e4b7c40f00009e"
                },
                "deal": {
                    "_id": "587dd7a923ddd08c2030f0e8",
                    "name": "Android application for Enterprise"
                },
                "taskCount": 1,
                "__v": 0,
                "editedBy": {
                    "date": "2017-01-17T08:38:03.853Z",
                    "user": {
                        "_id": "585cdc6ed210f7ec05c45f1f",
                        "login": "testAdmin"
                    }
                }
            },
            "_id": "587dd7ebab1707af208fdb93",
            "user": {
                "_id": "585cdc6ed210f7ec05c45f1f",
                "login": "testAdmin"
            }
        },
        ],
        "source": "Outbound LinkedIn",
        "campaign": "",
        "editedBy": {
            "date": "2017-01-17T08:39:41.096Z",
            "user": {
                "_id": "585cdc6ed210f7ec05c45f1f",
                "login": "testAdmin"
            }
        },
        "createdBy": {
            "date": "2017-01-17T08:36:57.086Z",
            "user": {
                "_id": "585cdc6ed210f7ec05c45f1f",
                "login": "testAdmin"
            }
        },
        "groups": {
            "group": [],
            "users": [],
            "owner": null
        },
        "whoCanRW": "everyOne",
        "workflow": {
            "_id": "528ce779f3f67bc40b00001f",
            "name": "Sent",
            "sequence": 2
        },
        "priority": "Hot",
        "expectedClosing": "2017-01-31T00:00:00.000Z",
        "nextAction": {
            "date": "2017-01-17T08:36:57.086Z",
            "desc": ""
        },
        "internalNotes": "",
        "salesPerson": {
            "_id": "55b92ad221e4b7c40f00009e",
            "name": {
                "last": "Michenko",
                "first": "Alex"
            },
            "fullName": "Alex Michenko",
            "id": "55b92ad221e4b7c40f00009e"
        },
        "phones": {
            "fax": "",
            "phone": "+380507770000",
            "mobile": ""
        },
        "email": "person@email.com",
        "contactName": {
            "last": "Hurst",
            "first": "Aaron"
        },
        "address": {
            "country": "Afghanistan",
            "zip": "75446",
            "state": "Transcarpathia",
            "city": "Birmingham",
            "street": "Notsafe str. 6"
        },
        "tags": [
        {
            "_id": "5787a2a276819b8549566451",
                "name": "Android",
                "color": "bgGreenDark"
        },
        ],
        "customer": {
            "_id": "57ce6557a3c7f97535e33da5",
            "dateBirth": "",
            "__v": 0,
            "channel": null,
            "integrationId": "",
            "companyInfo": {
                "industry": null
            },
            "editedBy": {
                "date": "2016-09-06T06:42:31.976Z",
                "user": "577a78347134263421cab3ed"
            },
            "createdBy": {
                "date": "2016-09-06T06:42:31.976Z",
                "user": "577a78347134263421cab3ed"
            },
            "history": [],
            "attachments": [],
            "notes": [],
            "groups": {
                "group": [],
                "users": [],
                "owner": null
            },
            "whoCanRW": "everyOne",
            "social": {
                "LI": "",
                "FB": ""
            },
            "color": "#4d5a75",
            "relatedUser": null,
            "salesPurchases": {
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
            "title": "",
            "internalNotes": "",
            "contacts": [],
            "phones": {
                "fax": "",
                "mobile": "",
                "phone": ""
            },
            "skype": "aaronius0000",
            "jobPosition": "CEO",
            "website": "",
            "shippingAddress": {
                "name": "",
                "country": "",
                "zip": "",
                "state": "",
                "city": "",
                "street": ""
            },
            "address": {
                "country": "Select",
                "zip": "",
                "state": "",
                "city": "",
                "street": ""
            },
            "timezone": "UTC",
            "department": null,
            "company": null,
            "email": "",
            "imageSrc": "data:image/png;base64",
            "name": {
                "last": "Hurst",
                "first": "Aaron"
            },
            "isHidden": false,
            "isOwn": false,
            "type": "Person",
            "fullName": "Aaron Hurst",
            "id": "57ce6557a3c7f97535e33da5"
        },
        "company": {
            "_id": "5877a4243336a81d081bd4a9",
            "__v": 0,
            "channel": null,
            "integrationId": "",
            "companyInfo": {
                "industry": null
            },
            "editedBy": {
                "date": "2017-01-12T15:47:31.076Z",
                "user": "585cdc6ed210f7ec05c45f1f"
            },
            "createdBy": {
                "date": "2017-01-12T15:43:32.231Z",
                "user": "585cdc6ed210f7ec05c45f1f"
            },
            "history": [],
            "attachments": [],
            "notes": [],
            "groups": {
                "group": [],
                "users": [],
                "owner": null
            },
            "whoCanRW": "everyOne",
            "social": {
                "LI": "nopromises",
                "FB": "nopromises"
            },
            "color": "#4d5a75",
            "relatedUser": null,
            "salesPurchases": {
                "receiveMessages": 0,
                "language": "English",
                "reference": "",
                "active": false,
                "implementedBy": null,
                "salesTeam": null,
                "salesPerson": "55b92ad221e4b7c40f000048",
                "isSupplier": false,
                "isCustomer": false
            },
            "title": "",
            "internalNotes": "",
            "contacts": [],
            "phones": {
                "fax": "",
                "mobile": "+478520588",
                "phone": "+547852145"
            },
            "skype": "",
            "jobPosition": "",
            "website": "nopromises.com",
            "shippingAddress": {
                "name": "Eternity Insurance group",
                "country": "",
                "zip": "",
                "state": "",
                "city": "",
                "street": ""
            },
            "address": {
                "country": "Antarctica",
                "zip": "75446",
                "state": "Transcarpathia",
                "city": "Birmingham",
                "street": "Notsafe str. 6"
            },
            "timezone": "UTC",
            "department": null,
            "company": null,
            "email": "nopromises@mail.com",
            "imageSrc": "data:image/jpeg;base64",
            "name": {
                "last": "",
                "first": "Eternity Insurance group"
            },
            "isHidden": false,
            "isOwn": false,
            "type": "Company",
            "fullName": "Eternity Insurance group ",
            "id": "5877a4243336a81d081bd4a9"
        },
        "tempCompanyField": "Eternity Insurance group",
        "jobPosition": "CEO",
        "expectedRevenue": {
            "currency": "",
            "progress": 0,
            "value": 0
        },
        "name": "Android application for Enterprise",
        "isOpportunitie": false,
        "followers": [
            {
                "name": "Alex Filchak",
                "followerId": "564dac3e9b85f8b16b574fea",
                "_id": "587dd81923ddd08c2030f0ef"
            },
        ]
    }
    */

    public String _id;
    public String dateBirth;
    public String skype;
    public LeadSocial social;
    public ArrayList<LeadAttachment> attachments;
    public ArrayList<LeadNote> notes;
    public String source;
    public String campaign;
    public LeadCreatedBy editedBy;
    public LeadCreatedBy createdBy;
    public LeadGroups groups;
    public String whoCanRW;
    public LeadWorkflow workflow;
    public String priority;
    public String expectedClosing;
    public LeadNextAction nextAction;
    public String internalNotes;
    public LeadSalesPerson salesPerson;
    public LeadPhone phones;
    public String email;
    public LeadName contactName;
    public LeadAddress address;
    public ArrayList<LeadTag> tags;
    public LeadCustomer customer;
    public LeadCompany company;
    public String tempCompanyField;
    public String jobPosition;
    public LeadExpectedRevenue expectedRevenue;
    public String name;
    public boolean isOpportunitie;
    public ArrayList<LeadFollower> followers;

    //for combine in Observable
    public ResponseGetLeadWorkflow leadWorkflow;


    public ResponseGetLeadDetails() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this._id);
        dest.writeString(this.dateBirth);
        dest.writeString(this.skype);
        dest.writeParcelable(this.social, flags);
        dest.writeTypedList(this.attachments);
        dest.writeTypedList(this.notes);
        dest.writeString(this.source);
        dest.writeString(this.campaign);
        dest.writeParcelable(this.editedBy, flags);
        dest.writeParcelable(this.createdBy, flags);
        dest.writeParcelable(this.groups, flags);
        dest.writeString(this.whoCanRW);
        dest.writeParcelable(this.workflow, flags);
        dest.writeString(this.priority);
        dest.writeString(this.expectedClosing);
        dest.writeParcelable(this.nextAction, flags);
        dest.writeString(this.internalNotes);
        dest.writeParcelable(this.salesPerson, flags);
        dest.writeParcelable(this.phones, flags);
        dest.writeString(this.email);
        dest.writeParcelable(this.contactName, flags);
        dest.writeParcelable(this.address, flags);
        dest.writeTypedList(this.tags);
        dest.writeParcelable(this.customer, flags);
        dest.writeParcelable(this.company, flags);
        dest.writeString(this.tempCompanyField);
        dest.writeString(this.jobPosition);
        dest.writeParcelable(this.expectedRevenue, flags);
        dest.writeString(this.name);
        dest.writeByte(this.isOpportunitie ? (byte) 1 : (byte) 0);
        dest.writeTypedList(this.followers);
        dest.writeParcelable(this.leadWorkflow, flags);
    }

    protected ResponseGetLeadDetails(Parcel in) {
        this._id = in.readString();
        this.dateBirth = in.readString();
        this.skype = in.readString();
        this.social = in.readParcelable(LeadSocial.class.getClassLoader());
        this.attachments = in.createTypedArrayList(LeadAttachment.CREATOR);
        this.notes = in.createTypedArrayList(LeadNote.CREATOR);
        this.source = in.readString();
        this.campaign = in.readString();
        this.editedBy = in.readParcelable(LeadCreatedBy.class.getClassLoader());
        this.createdBy = in.readParcelable(LeadCreatedBy.class.getClassLoader());
        this.groups = in.readParcelable(LeadGroups.class.getClassLoader());
        this.whoCanRW = in.readString();
        this.workflow = in.readParcelable(LeadWorkflow.class.getClassLoader());
        this.priority = in.readString();
        this.expectedClosing = in.readString();
        this.nextAction = in.readParcelable(LeadNextAction.class.getClassLoader());
        this.internalNotes = in.readString();
        this.salesPerson = in.readParcelable(LeadSalesPerson.class.getClassLoader());
        this.phones = in.readParcelable(LeadPhone.class.getClassLoader());
        this.email = in.readString();
        this.contactName = in.readParcelable(LeadName.class.getClassLoader());
        this.address = in.readParcelable(LeadAddress.class.getClassLoader());
        this.tags = in.createTypedArrayList(LeadTag.CREATOR);
        this.customer = in.readParcelable(LeadCustomer.class.getClassLoader());
        this.company = in.readParcelable(LeadCompany.class.getClassLoader());
        this.tempCompanyField = in.readString();
        this.jobPosition = in.readString();
        this.expectedRevenue = in.readParcelable(LeadExpectedRevenue.class.getClassLoader());
        this.name = in.readString();
        this.isOpportunitie = in.readByte() != 0;
        this.followers = in.createTypedArrayList(LeadFollower.CREATOR);
        this.leadWorkflow = in.readParcelable(ResponseGetLeadWorkflow.class.getClassLoader());
    }

    public static final Creator<ResponseGetLeadDetails> CREATOR = new Creator<ResponseGetLeadDetails>() {
        @Override
        public ResponseGetLeadDetails createFromParcel(Parcel source) {
            return new ResponseGetLeadDetails(source);
        }

        @Override
        public ResponseGetLeadDetails[] newArray(int size) {
            return new ResponseGetLeadDetails[size];
        }
    };
}
