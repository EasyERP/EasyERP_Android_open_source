package com.thinkmobiles.easyerp.presentation.screens.about.tabs.about_app;

import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

import com.thinkmobiles.easyerp.BuildConfig;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.base.BaseSupportFragment;
import com.thinkmobiles.easyerp.presentation.screens.about.AboutUsActivity;
import com.thinkmobiles.easyerp.presentation.utils.IntentActionHelper;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

/**
 * @author Michael Soyma (Created on 2/22/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
@EFragment(R.layout.fragment_about_us_easyerp)
public class AboutAppFragment extends BaseSupportFragment<AboutUsActivity> implements AboutAppContract.AboutAppView {

    private AboutAppContract.AboutAppPresenter presenter;

    @ViewById
    protected TextView tvDescription_FAUS;
    @ViewById
    protected TextView tvVersion_FAUS;

    @AfterInject
    @Override
    public void initPresenter() {
        new AboutAppPresenter(this);
    }

    @Override
    public void setPresenter(AboutAppContract.AboutAppPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public String getScreenName() {
        return "About app screen";
    }

    @AfterViews
    protected void initUI() {
        tvVersion_FAUS.setText(BuildConfig.VERSION_NAME);
        tvDescription_FAUS.setText(Html.fromHtml(getString(R.string.app_description)));
        tvDescription_FAUS.setMovementMethod(LinkMovementMethod.getInstance());
        presenter.subscribe();
    }

    @Click(R.id.btnContactUs_FAUE)
    protected void contactUs() {
        presenter.contactUs();
    }

    @Click(R.id.btnRateTheApp_FAUE)
    protected void rateTheApp() {
        presenter.rateTheApp(BuildConfig.APPLICATION_ID);
    }

    @Click({R.id.tvFacebook_FAUE, R.id.tvLinkedIn_FAUE, R.id.tvSkype_FAUE, R.id.tvTwitter_FAUE, R.id.tvYouTube_FAUE})
    protected void socialClick(final View clickedView) {
        switch (clickedView.getId()) {
            case R.id.tvFacebook_FAUE:
                presenter.openSocial(ShareSocialType.FACEBOOK);
                break;
            case R.id.tvLinkedIn_FAUE:
                presenter.openSocial(ShareSocialType.LINKEDIN);
                break;
            case R.id.tvSkype_FAUE:
                presenter.openSocial(ShareSocialType.SKYPE);
                break;
            case R.id.tvTwitter_FAUE:
                presenter.openSocial(ShareSocialType.TWITTER);
                break;
            case R.id.tvYouTube_FAUE:
                presenter.openSocial(ShareSocialType.YOUTUBE);
                break;
        }
    }

    @Override
    public void sendEmailIntent(String email, String subject) {
        IntentActionHelper.callSendEmailIntent(contextActivity(), email, subject);
    }

    @Override
    public void sendViewIntent(String uriPath, String alternativeUriPath) {
        IntentActionHelper.callViewIntent(contextActivity(), uriPath, alternativeUriPath);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.unsubscribe();
    }
}
