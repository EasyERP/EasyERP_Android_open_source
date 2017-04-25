package com.thinkmobiles.easyerp.data.model.social;

import android.text.TextUtils;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

/**
 * @author Michael Soyma (Created on 4/24/2017).
 *         Company: Thinkmobiles
 *         Email:  michael.soyma@thinkmobiles.com
 */
public final class SocialRegisterProfile {

    public String accessToken;
    public String userId;
    public String email;
    public String login;
    public String first;
    public String last;
    public String country;
    public String profileUrl;
    public String flag;
    public String dbId;

    public static SocialRegisterProfile withFacebook(final FacebookResponse facebookResponse) {
        final SocialRegisterProfile socialRegisterProfile = new SocialRegisterProfile();
        socialRegisterProfile.flag = SocialType.FACEBOOK.getLabel();
        socialRegisterProfile.userId = facebookResponse.id;
        socialRegisterProfile.email = facebookResponse.email;
        socialRegisterProfile.login = TextUtils.isEmpty(facebookResponse.email) ? facebookResponse.id : facebookResponse.email;
        socialRegisterProfile.first = facebookResponse.first_name;
        socialRegisterProfile.last = facebookResponse.last_name;
        socialRegisterProfile.country = facebookResponse.location != null ? facebookResponse.location.name : null;
        socialRegisterProfile.profileUrl = facebookResponse.link;
        return socialRegisterProfile;
    }

    public static SocialRegisterProfile withLinkedIn(final LinkedInResponse linkedInResponse) {
        final SocialRegisterProfile socialRegisterProfile = new SocialRegisterProfile();
        socialRegisterProfile.flag = SocialType.LIKENDIN.getLabel();
        socialRegisterProfile.userId = linkedInResponse.id;
        socialRegisterProfile.email = linkedInResponse.email;
        socialRegisterProfile.login = TextUtils.isEmpty(linkedInResponse.email) ? linkedInResponse.id : linkedInResponse.email;
        socialRegisterProfile.first = linkedInResponse.first;
        socialRegisterProfile.last = linkedInResponse.last;
        socialRegisterProfile.country = linkedInResponse.location != null ? linkedInResponse.location.name : null;
        socialRegisterProfile.profileUrl = linkedInResponse.link;
        return socialRegisterProfile;
    }

    public static SocialRegisterProfile withGoogle(final GoogleSignInAccount googleAccount) {
        final SocialRegisterProfile socialRegisterProfile = new SocialRegisterProfile();
        socialRegisterProfile.flag = SocialType.GPLUS.getLabel();
        socialRegisterProfile.accessToken = googleAccount.getIdToken();
        socialRegisterProfile.userId = googleAccount.getId();
        socialRegisterProfile.email = googleAccount.getEmail();
        socialRegisterProfile.login = TextUtils.isEmpty(googleAccount.getEmail()) ? googleAccount.getId() : googleAccount.getEmail();
        socialRegisterProfile.first = googleAccount.getGivenName();
        socialRegisterProfile.last = googleAccount.getFamilyName();
        socialRegisterProfile.profileUrl = String.format("https://plus.google.com/%s", googleAccount.getId());
        return socialRegisterProfile;
    }
}
