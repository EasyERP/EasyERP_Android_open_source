package com.thinkmobiles.easyerp.presentation.screens.about.tabs.powered_by;

import com.thinkmobiles.easyerp.presentation.base.BasePresenter;
import com.thinkmobiles.easyerp.presentation.base.BaseView;

/**
 * @author Michael Soyma (Created on 2/23/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public interface PoweredByContract {
    interface PoweredByView extends BaseView<PoweredByPresenter> {
        void visitSite(final String urlSite);
    }
    interface PoweredByPresenter extends BasePresenter {
        void visitPoweredByCompany();
    }
}
