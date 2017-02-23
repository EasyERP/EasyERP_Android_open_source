package com.thinkmobiles.easyerp.presentation.screens.about;

import com.thinkmobiles.easyerp.presentation.base.BaseModel;
import com.thinkmobiles.easyerp.presentation.base.BasePresenter;
import com.thinkmobiles.easyerp.presentation.base.BaseView;

import java.util.List;

/**
 * @author Michael Soyma (Created on 2/22/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public interface AboutUsContract {
    interface AboutUsView extends BaseView<AboutUsPresenter> {
    }
    interface AboutUsPresenter extends BasePresenter {
    }
    interface AboutUsModel extends BaseModel {
        List<?> getLibraries();
    }
}
