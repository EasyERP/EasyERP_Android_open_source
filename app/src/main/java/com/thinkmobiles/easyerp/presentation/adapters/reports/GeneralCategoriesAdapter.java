package com.thinkmobiles.easyerp.presentation.adapters.reports;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectableAdapter;
import com.thinkmobiles.easyerp.presentation.holders.data.reports.GeneralCategoryDH;
import com.thinkmobiles.easyerp.presentation.holders.view.reports.GeneralCategoryVH;

import org.androidannotations.annotations.EBean;

/**
 * @author Michael Soyma (Created on 4/8/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
@EBean
public class GeneralCategoriesAdapter extends SelectableAdapter<GeneralCategoryDH, GeneralCategoryVH> {
    @Override
    protected int getItemLayout() {
        return R.layout.view_list_item_general_category;
    }
}
