package com.thinkmobiles.easyerp.presentation.screens.about.tabs.powered_by;

/**
 * @author Michael Soyma (Created on 2/23/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public class PoweredByPresenter implements PoweredByContract.PoweredByPresenter {

    private static final String LINK_COMPANY_POWERED_BY = "https://thinkmobiles.com/";

    private final PoweredByContract.PoweredByView view;

    public PoweredByPresenter(PoweredByContract.PoweredByView view) {
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
    public void visitPoweredByCompany() {
        view.visitSite(LINK_COMPANY_POWERED_BY);
    }
}
