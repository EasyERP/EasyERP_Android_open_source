package com.thinkmobiles.easyerp.presentation.screens.about.tabs.powered_by;

import android.content.Intent;
import android.net.Uri;
import android.text.Html;
import android.widget.TextView;

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
 * @author Michael Soyma (Created on 2/23/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
@EFragment(R.layout.fragment_about_us_powered_by)
public class PoweredByFragment extends BaseSupportFragment<AboutUsActivity> implements PoweredByContract.PoweredByView {

    private PoweredByContract.PoweredByPresenter presenter;

    @ViewById
    protected TextView tvDescription_FAUPB;

    @AfterInject
    @Override
    public void initPresenter() {
        new PoweredByPresenter(this);
    }

    @Override
    public void setPresenter(PoweredByContract.PoweredByPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public String getScreenName() {
        return "About us (Powered By) screen";
    }

    @AfterViews
    protected void initUI() {
        tvDescription_FAUPB.setText(Html.fromHtml(getString(R.string.thinkmobiles_description)));
        presenter.subscribe();
    }

    @Click(R.id.btnVisitUs_FAUPB)
    protected void visitPoweredByCompany() {
        presenter.visitPoweredByCompany();
    }

    @Override
    public void visitSite(String uriPath) {
        IntentActionHelper.callViewIntent(mActivity, uriPath, null);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.unsubscribe();
    }
}
