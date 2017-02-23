package com.thinkmobiles.easyerp.presentation.screens.about.tabs.about_app;

import com.thinkmobiles.easyerp.presentation.base.BasePresenter;
import com.thinkmobiles.easyerp.presentation.base.BaseView;

/**
 * @author Michael Soyma (Created on 2/22/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public interface AboutAppContract {
    interface AboutAppView extends BaseView<AboutAppPresenter> {
        void sendContactIntent(final String email, final String subject);
        void sendViewIntent(final String uriPath, final String alternativeUriPath);
    }
    interface AboutAppPresenter extends BasePresenter {
        void contactUs();
        void rateTheApp(final String packageName);
        void openSocial(final SocialType socialType);
    }
}
