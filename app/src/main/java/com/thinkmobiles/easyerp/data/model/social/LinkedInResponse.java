package com.thinkmobiles.easyerp.data.model.social;

import com.google.gson.annotations.SerializedName;

/**
 * @author Michael Soyma (Created on 4/25/2017).
 *         Company: Thinkmobiles
 *         Email:  michael.soyma@thinkmobiles.com
 */
public final class LinkedInResponse {
    //id,first-name,last-name,email-address,location,public-profile-url

    public String id;
    @SerializedName("firstName")
    public String first;
    @SerializedName("lastName")
    public String last;
    @SerializedName("emailAddress")
    public String email;
    public Location location;
    @SerializedName("publicProfileUrl")
    public String link;

    public class Location {
        public String name;
    }
}
