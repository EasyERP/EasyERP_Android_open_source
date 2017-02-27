package com.thinkmobiles.easyerp.presentation.screens.crm.companies;

import com.thinkmobiles.easyerp.data.model.crm.common.images.CustomerImageItem;
import com.thinkmobiles.easyerp.data.model.crm.companies.CommonCompaniesResponse;
import com.thinkmobiles.easyerp.data.model.crm.companies.CompanyListItem;
import com.thinkmobiles.easyerp.data.model.crm.companies.ResponseGetCompanies;
import com.thinkmobiles.easyerp.presentation.base.rules.master.alphabetical.AlphabeticalModel;
import com.thinkmobiles.easyerp.presentation.base.rules.master.alphabetical.AlphabeticalView;
import com.thinkmobiles.easyerp.presentation.base.rules.master.alphabetical.MasterAlphabeticalPresenterHelper;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.CompanyDH;
import com.thinkmobiles.easyerp.presentation.managers.ErrorManager;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import java.util.ArrayList;

/**
 * Created by Lynx on 2/2/2017.
 */

public class CompaniesPresenter extends MasterAlphabeticalPresenterHelper implements CompaniesContract.CompaniesPresenter {

    private CompaniesContract.CompaniesView view;
    private CompaniesContract.CompaniesModel model;

    private CommonCompaniesResponse companiesResponse = null;

    public CompaniesPresenter(CompaniesContract.CompaniesView view, CompaniesContract.CompaniesModel model) {
        this.view = view;
        this.model = model;

        view.setPresenter(this);
    }

    @Override
    protected AlphabeticalView getView() {
        return view;
    }

    @Override
    protected AlphabeticalModel getModel() {
        return model;
    }

    @Override
    protected void loadPage(int page) {
        final boolean needClear = page == 1;
        compositeSubscription.add(
                model.getCompanies(helper, selectedLetter, page)
                        .flatMap(responseGetCompanies -> model.getCompanyImages(prepareIDsForImagesRequest(responseGetCompanies)),
                                CommonCompaniesResponse::new)
                        .subscribe(commonCompaniesResponse -> {
                            currentPage = page;
                            totalItems = commonCompaniesResponse.responseGetCompanies.total;
                            saveData(commonCompaniesResponse, needClear);
                            setData(commonCompaniesResponse, needClear);
                        },  t -> error(t))
        );
    }

    @Override
    protected int getCountItems() {
        return companiesResponse.responseGetCompanies.data.size();
    }

    @Override
    protected boolean hasContent() {
        return companiesResponse != null;
    }

    @Override
    protected void retainInstance() {
        setData(companiesResponse, true);
    }

    @Override
    public void clickItem(int position) {
        String id = companiesResponse.responseGetCompanies.data.get(position).id;
        if (super.selectItem(id, position))
            view.openDetailsScreen(id);
    }

    private void saveData(CommonCompaniesResponse commonPersonsResponse, boolean needClear) {
        if (needClear) companiesResponse = commonPersonsResponse;
        else if (companiesResponse != null) {
            companiesResponse.responseGetCustomersImages.data.addAll(commonPersonsResponse.responseGetCustomersImages.data);
            companiesResponse.responseGetCompanies.data.addAll(commonPersonsResponse.responseGetCompanies.data);
        }
    }

    private void setData(CommonCompaniesResponse commonPersonsResponse, boolean needClear) {
        view.displaySelectedLetter(selectedLetter);
        view.setDataList(prepareDataHolders(commonPersonsResponse, needClear), needClear);
        if (companiesResponse.responseGetCompanies.data.isEmpty()) {
            view.displayErrorState(ErrorManager.getErrorType(null));
        } else {
            view.showProgress(Constants.ProgressType.NONE);
        }
    }

    private ArrayList<CompanyDH> prepareDataHolders(CommonCompaniesResponse commonCompaniesResponse, boolean needClear) {
        int position = 0;
        ArrayList<CompanyDH> result = new ArrayList<>();
        for (CompanyListItem companyListItem : commonCompaniesResponse.responseGetCompanies.data) {
            for (CustomerImageItem imageItem : commonCompaniesResponse.responseGetCustomersImages.data) {
                if (companyListItem.id.equalsIgnoreCase(imageItem.id)) {
                    final CompanyDH companyDH = new CompanyDH(companyListItem, imageItem.imageSrc);
                    makeSelectedDHIfNeed(companyDH, position++, needClear);
                    result.add(companyDH);
                }
            }
        }
        selectFirstElementIfNeed(result);
        return result;
    }

    private ArrayList<String> prepareIDsForImagesRequest(ResponseGetCompanies responseGetCompanies) {
        ArrayList<String> companyIDs = new ArrayList<>();
        if (responseGetCompanies.total > 0 && responseGetCompanies.data != null && responseGetCompanies.data.size() > 0) {
            for (CompanyListItem companyListItem : responseGetCompanies.data) {
                companyIDs.add(companyListItem.id);
            }
        }
        return companyIDs;
    }
}
