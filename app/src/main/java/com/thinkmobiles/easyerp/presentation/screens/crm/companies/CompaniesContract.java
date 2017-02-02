package com.thinkmobiles.easyerp.presentation.screens.crm.companies;

import com.thinkmobiles.easyerp.data.model.crm.common.alphabet.AlphabetItem;
import com.thinkmobiles.easyerp.data.model.crm.common.alphabet.ResponseGetAlphabet;
import com.thinkmobiles.easyerp.data.model.crm.companies.ResponseGetCompanies;
import com.thinkmobiles.easyerp.data.model.crm.persons.images.ResponseGetCustomersImages;
import com.thinkmobiles.easyerp.presentation.base.BaseModel;
import com.thinkmobiles.easyerp.presentation.base.rules.ErrorViewHelper;
import com.thinkmobiles.easyerp.presentation.base.rules.MasterFlowSelectableBasePresenter;
import com.thinkmobiles.easyerp.presentation.base.rules.MasterFlowSelectableBaseView;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.CompanyDH;

import java.util.ArrayList;

import rx.Observable;

/**
 * Created by Lynx on 2/2/2017.
 */

public interface CompaniesContract {
    interface CompaniesView extends MasterFlowSelectableBaseView<CompaniesPresenter> {
        void displayEnabledLetters(ArrayList<AlphabetItem> enabledAlphabetItems);
        void displayCompanies(ArrayList<CompanyDH> companyDHs, boolean needClear);
        void displayError(final String msg, final ErrorViewHelper.ErrorType errorType);
        void openCompanyDetailsScreen(String companyID);
    }
    interface CompaniesPresenter extends MasterFlowSelectableBasePresenter<String, CompanyDH> {
        void setLetter(String letter);
        void loadAlphabet();
        void loadMore(int page);
    }
    interface CompaniesModel extends BaseModel {
        Observable<ResponseGetAlphabet> getCompaniesAlphabet();
        Observable<ResponseGetCustomersImages> getCompanyImages(ArrayList<String> companyIdList);
        Observable<ResponseGetCompanies> getAllCompanies(int page);
        Observable<ResponseGetCompanies> getCompaniesByLetter(String letter, int page);
    }
}
