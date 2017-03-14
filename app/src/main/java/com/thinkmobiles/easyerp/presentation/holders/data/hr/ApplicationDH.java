package com.thinkmobiles.easyerp.presentation.holders.data.hr;

import com.thinkmobiles.easyerp.data.model.hr.applications.Application;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectableDHHelper;

/**
 * @author Michael Soyma (Created on 3/13/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public class ApplicationDH extends SelectableDHHelper {

    private final Application application;

    public ApplicationDH(Application application) {
        this.application = application;
    }

    public Application getApplication() {
        return application;
    }

    @Override
    public String getId() {
        return application.id;
    }
}
