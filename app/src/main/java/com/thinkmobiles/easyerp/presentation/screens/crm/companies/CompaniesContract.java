package com.thinkmobiles.easyerp.presentation.screens.crm.companies;

import com.thinkmobiles.easyerp.data.model.crm.common.images.ResponseGetCustomersImages;
import com.thinkmobiles.easyerp.data.model.crm.companies.ResponseGetCompanies;
import com.thinkmobiles.easyerp.presentation.base.BaseView;
import com.thinkmobiles.easyerp.presentation.base.rules.master.alphabetical.AlphabeticalModel;
import com.thinkmobiles.easyerp.presentation.base.rules.master.alphabetical.AlphabeticalPresenter;
import com.thinkmobiles.easyerp.presentation.base.rules.master.alphabetical.AlphabeticalView;
import com.thinkmobiles.easyerp.presentation.utils.filter.FilterHelper;

import java.util.ArrayList;

import rx.Observable;

/**
 * Created by Lynx on 2/2/2017.
 */

public interface CompaniesContract {
    interface CompaniesView extends BaseView<CompaniesPresenter>, AlphabeticalView {
        void openDetailsScreen(String companyID);
    }
    interface CompaniesPresenter extends AlphabeticalPresenter {}
    interface CompaniesModel extends AlphabeticalModel {
        Observable<ResponseGetCustomersImages> getCompanyImages(ArrayList<String> companyIdList);
        Observable<ResponseGetCompanies> getCompanies(FilterHelper helper, String letter, int page);
    }
}
