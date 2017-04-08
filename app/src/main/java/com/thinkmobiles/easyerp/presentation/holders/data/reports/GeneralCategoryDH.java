package com.thinkmobiles.easyerp.presentation.holders.data.reports;

import com.thinkmobiles.easyerp.data.model.reports.general.Category;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectableDHHelper;

/**
 * @author Michael Soyma (Created on 4/8/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public final class GeneralCategoryDH extends SelectableDHHelper {

    private final Category category;

    public GeneralCategoryDH(Category category) {
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    @Override
    public String getId() {
        return category.id;
    }
}
