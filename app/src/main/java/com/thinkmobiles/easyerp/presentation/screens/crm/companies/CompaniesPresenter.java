package com.thinkmobiles.easyerp.presentation.screens.crm.companies;

import android.text.TextUtils;

import com.thinkmobiles.easyerp.data.model.crm.common.images.CustomerImageItem;
import com.thinkmobiles.easyerp.data.model.crm.companies.CommonCompaniesResponse;
import com.thinkmobiles.easyerp.data.model.crm.companies.CompanyListItem;
import com.thinkmobiles.easyerp.data.model.crm.companies.ResponseGetCompanies;
import com.thinkmobiles.easyerp.presentation.base.rules.ErrorViewHelper;
import com.thinkmobiles.easyerp.presentation.base.rules.MasterFlowSelectablePresenterHelper;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.CompanyDH;

import java.util.ArrayList;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by Lynx on 2/2/2017.
 */

public class CompaniesPresenter extends MasterFlowSelectablePresenterHelper<String, CompanyDH> implements CompaniesContract.CompaniesPresenter {

    private CompaniesContract.CompaniesView view;
    private CompaniesContract.CompaniesModel model;
    private CompositeSubscription compositeSubscription;

    private String selectedLetter;

    public CompaniesPresenter(CompaniesContract.CompaniesView view, CompaniesContract.CompaniesModel model) {
        this.view = view;
        this.model = model;
        compositeSubscription = new CompositeSubscription();

        view.setPresenter(this);
    }

    @Override
    public void selectItem(CompanyDH dh, int position) {
        if (super.selectItem(dh, position, view))
            view.openCompanyDetailsScreen(dh.getId());
    }

    @Override
    public void subscribe() {
        if(TextUtils.isEmpty(selectedLetter))
            loadAlphabet();
        loadMore(1);
    }

    @Override
    public void unsubscribe() {
        if(compositeSubscription.hasSubscriptions())
            compositeSubscription.clear();
    }

    @Override
    public void setLetter(String letter) {
        selectedLetter = letter;
        if (selectedLetter.equalsIgnoreCase("All")) selectedLetter = "";
    }

    @Override
    public void loadAlphabet() {
        compositeSubscription.add(
                model.getCompaniesAlphabet()
                        .subscribe(
                                responseGetCompaniesAlphabet -> view.displayEnabledLetters(responseGetCompaniesAlphabet.data),
                                t -> {})
        );
    }

    @Override
    public void loadMore(int page) {
        final boolean needClear = page == 1;
        if(TextUtils.isEmpty(selectedLetter)) {
            //load all
            compositeSubscription.add(
                    model.getAllCompanies(page)
                            .flatMap(responseGetCompanies -> model.getCompanyImages(prepareIDsForImagesRequest(responseGetCompanies)),
                                    CommonCompaniesResponse::new)
                            .subscribe(commonCompaniesResponse -> {
                                view.displayCompanies(prepareDataHolders(commonCompaniesResponse, needClear), needClear);
                            }, t -> view.displayError(t.getMessage(), ErrorViewHelper.ErrorType.NETWORK))
            );
        } else {
            //load by letter
            compositeSubscription.add(
                    model.getCompaniesByLetter(selectedLetter, page)
                            .flatMap(responseGetCompanies -> model.getCompanyImages(prepareIDsForImagesRequest(responseGetCompanies)),
                                    CommonCompaniesResponse::new)
                            .subscribe(commonCompaniesResponse -> {
                                view.displayCompanies(prepareDataHolders(commonCompaniesResponse, needClear), needClear);
                            }, t -> view.displayError(t.getMessage(), ErrorViewHelper.ErrorType.NETWORK))
            );
        }
    }

    private ArrayList<CompanyDH> prepareDataHolders(CommonCompaniesResponse commonCompaniesResponse, boolean needClear) {
        int position = 0;
        ArrayList<CompanyDH> result = new ArrayList<>();
        for(CompanyListItem companyListItem : commonCompaniesResponse.responseGetCompanies.data) {
            for(CustomerImageItem imageItem : commonCompaniesResponse.responseGetCustomersImages.data) {
                if(companyListItem.id.equalsIgnoreCase(imageItem.id)) {
                    final CompanyDH companyDH = new CompanyDH(companyListItem, imageItem.imageSrc);
                    makeSelectedDHIfNeed(companyDH, view, position++, needClear);
                    result.add(companyDH);
                }
            }
        }
        return result;
    }

    private ArrayList<String> prepareIDsForImagesRequest(ResponseGetCompanies responseGetCompanies) {
        ArrayList<String> companyIDs = new ArrayList<>();
        if(responseGetCompanies.total > 0 && responseGetCompanies.data != null && responseGetCompanies.data.size() > 0) {
            for(CompanyListItem companyListItem : responseGetCompanies.data) {
                companyIDs.add(companyListItem.id);
            }
        }
        return companyIDs;
    }
}
