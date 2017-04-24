package com.thinkmobiles.easyerp.data.model.social;

/**
 * @author Michael Soyma (Created on 4/24/2017).
 *         Company: Thinkmobiles
 *         Email:  michael.soyma@thinkmobiles.com
 */
public final class FacebookResponse {

    public String id;
    public String email;
    public String first_name;
    public String last_name;
    public String link;
    public Location location;

    public class Location {
        public String id;
        public String name;
    }
}
