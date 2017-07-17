package com.thinkmobiles.easyerp.data.model.user;

public class UpdateUser {

    public String first;
    public String last;
    public String mobilePhone;
    public String website;
    public String company;

    public UpdateUser(String first, String last, String mobilePhone, String company, String website) {
        this.first = first;
        this.last = last;
        this.mobilePhone = mobilePhone;
        this.company = company;
        this.website = website;
    }

}
