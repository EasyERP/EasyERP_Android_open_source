package com.thinkmobiles.easyerp.presentation.screens.about.tabs.about_app;

/**
 * @author Michael Soyma (Created on 2/22/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public class AboutAppPresenter implements AboutAppContract.AboutAppPresenter {

    private static final String LINK_FACEBOOK = "https://www.facebook.com/easyerp/";
    private static final String LINK_LINKEDIN = "https://www.linkedin.com/company/easyerp/";
    private static final String LINK_YOUTUBE = "https://www.youtube.com/user/EasyERP/";
    private static final String LINK_TWITER = "https://twitter.com/easy_erp/";
    private static final String LINK_SKYPE = "skype:live:ligintir";

    public static final String SCHEME_MARKET = "market://details?id=%s";
    public static final String LINK_MARKET = "http://play.google.com/store/apps/details?id=%s";

    private static final String CONTACT_US_EMAIL = "info@easyerp.com";
    private static final String CONTACT_US_SUBJECT = "EasyERP Android";


    private AboutAppContract.AboutAppView view;

    public AboutAppPresenter(AboutAppContract.AboutAppView view) {
        this.view = view;

        this.view.setPresenter(this);
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }

    @Override
    public void contactUs() {
        view.sendContactIntent(CONTACT_US_EMAIL, CONTACT_US_SUBJECT);
    }

    @Override
    public void rateTheApp(String packageName) {
        view.sendViewIntent(String.format(SCHEME_MARKET, packageName), String.format(LINK_MARKET, packageName));
    }

    @Override
    public void openSocial(SocialType socialType) {
        String url = null;
        switch (socialType) {
            case FACEBOOK:
                url = LINK_FACEBOOK;
                break;
            case SKYPE:
                url = LINK_SKYPE;
                break;
            case LINKEDIN:
                url = LINK_LINKEDIN;
                break;
            case TWITTER:
                url = LINK_TWITER;
                break;
            case YOUTUBE:
                url = LINK_YOUTUBE;
                break;
        }
        view.sendViewIntent(url, null);
    }
}
