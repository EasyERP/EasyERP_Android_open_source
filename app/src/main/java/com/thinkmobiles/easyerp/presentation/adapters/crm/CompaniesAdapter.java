package com.thinkmobiles.easyerp.presentation.adapters.crm;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectableAdapter;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.CompanyDH;
import com.thinkmobiles.easyerp.presentation.holders.view.crm.CompanyVH;

import org.androidannotations.annotations.EBean;

/**
 * Created by Lynx on 2/2/2017.
 */

@EBean
public class CompaniesAdapter extends SelectableAdapter<CompanyDH, CompanyVH> {
    @Override
    protected int getItemLayout() {
        return R.layout.view_list_item_company;
    }
}
