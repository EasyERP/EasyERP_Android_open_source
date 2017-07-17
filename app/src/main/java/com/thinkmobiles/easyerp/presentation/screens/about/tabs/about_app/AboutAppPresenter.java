package com.thinkmobiles.easyerp.presentation.screens.about.tabs.about_app;

import com.thinkmobiles.easyerp.presentation.utils.IntentActionHelper;

/**
 * @author Michael Soyma (Created on 2/22/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public class AboutAppPresenter implements AboutAppContract.AboutAppPresenter {

    private static final String SUFFIX_FACEBOOK = "easyerp";
    private static final String SUFFIX_LINKEDIN = "easyerp";
    private static final String SUFFIX_YOUTUBE = "UCXylOIZMcCvjE6ri0EufVWg";
    private static final String SUFFIX_TWITER = "easy_erp";
    private static final String SUFFIX_SKYPE = "live:ligintir";

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
        view.sendEmailIntent(CONTACT_US_EMAIL, CONTACT_US_SUBJECT);
    }

    @Override
    public void rateTheApp(String packageName) {
        view.sendViewIntent(
                String.format(IntentActionHelper.FORMAT_MARKET, packageName),
                String.format(IntentActionHelper.FORMAT_LINK_MARKET, packageName));
    }

    @Override
    public void openSocial(ShareSocialType socialType) {
        String url = null;
        switch (socialType) {
            case FACEBOOK:
                url = String.format(IntentActionHelper.FORMAT_FACEBOOK, SUFFIX_FACEBOOK);
                break;
            case SKYPE:
                url = String.format(IntentActionHelper.FORMAT_SKYPE, SUFFIX_SKYPE);
                break;
            case LINKEDIN:
                url = String.format(IntentActionHelper.FORMAT_LINKEDIN_COMPANY, SUFFIX_LINKEDIN);
                break;
            case TWITTER:
                url = String.format(IntentActionHelper.FORMAT_TWITTER, SUFFIX_TWITER);
                break;
            case YOUTUBE:
                url = String.format(IntentActionHelper.FORMAT_YOUTUBE, SUFFIX_YOUTUBE);
                break;
        }
        view.sendViewIntent(url, null);
    }
}
