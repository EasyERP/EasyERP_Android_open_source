package com.thinkmobiles.easyerp.data.model.social;

/**
 * @author Michael Soyma (Created on 4/24/2017).
 *         Company: Thinkmobiles
 *         Email:  michael.soyma@thinkmobiles.com
 */
public enum SocialType {
    FACEBOOK("facebook"), LIKENDIN("linkedin"), GPLUS("google");

    final String label;

    SocialType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
