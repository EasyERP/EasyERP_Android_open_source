package com.thinkmobiles.easyerp.presentation.screens.crm.companies;

import com.thinkmobiles.easyerp.data.model.crm.common.alphabet.AlphabetItem;
import com.thinkmobiles.easyerp.data.model.crm.common.alphabet.ResponseGetAlphabet;
import com.thinkmobiles.easyerp.data.model.crm.common.images.ResponseGetCustomersImages;
import com.thinkmobiles.easyerp.data.model.crm.companies.ResponseGetCompanies;
import com.thinkmobiles.easyerp.presentation.base.BaseModel;
import com.thinkmobiles.easyerp.presentation.base.rules.ErrorType;
import com.thinkmobiles.easyerp.presentation.base.rules.MasterFlowSelectableBasePresenter;
import com.thinkmobiles.easyerp.presentation.base.rules.MasterFlowSelectableBaseView;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.CompanyDH;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import java.util.ArrayList;

import rx.Observable;

/**
 * Created by Lynx on 2/2/2017.
 */

public interface CompaniesContract {
    interface CompaniesView extends MasterFlowSelectableBaseView<CompaniesPresenter> {
        void displayEnabledLetters(ArrayList<AlphabetItem> enabledAlphabetItems);
        void displaySelectedLetter(String selectedLetter);
        void displayCompanies(ArrayList<CompanyDH> companyDHs, boolean needClear);
        void openCompanyDetailsScreen(String companyID);

        void displayErrorState(final ErrorType errorType);
        void displayErrorToast(final String msg);
        void showProgress(Constants.ProgressType type);
    }
    interface CompaniesPresenter extends MasterFlowSelectableBasePresenter<String, CompanyDH> {
        void setLetter(String letter);
        void refresh();
        void loadNextPage();
    }
    interface CompaniesModel extends BaseModel {
        Observable<ResponseGetAlphabet> getCompaniesAlphabet();
        Observable<ResponseGetCustomersImages> getCompanyImages(ArrayList<String> companyIdList);
        Observable<ResponseGetCompanies> getAllCompanies(int page);
        Observable<ResponseGetCompanies> getCompaniesByLetter(String letter, int page);
    }
}
