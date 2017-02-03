package com.thinkmobiles.easyerp.domain.crm;

import com.thinkmobiles.easyerp.data.api.Rest;
import com.thinkmobiles.easyerp.data.model.crm.common.alphabet.ResponseGetAlphabet;
import com.thinkmobiles.easyerp.data.model.crm.companies.ResponseGetCompanies;
import com.thinkmobiles.easyerp.data.model.crm.common.images.ResponseGetCustomersImages;
import com.thinkmobiles.easyerp.data.model.crm.companies.detail.ResponseGetCompanyDetails;
import com.thinkmobiles.easyerp.data.services.CompaniesService;
import com.thinkmobiles.easyerp.data.services.CustomerService;
import com.thinkmobiles.easyerp.presentation.base.NetworkRepository;
import com.thinkmobiles.easyerp.presentation.screens.crm.companies.CompaniesContract;
import com.thinkmobiles.easyerp.presentation.screens.crm.companies.details.CompanyDetailsContract;

import org.androidannotations.annotations.EBean;

import java.util.ArrayList;

import rx.Observable;

/**
 * Created by Lynx on 2/2/2017.
 */

@EBean(scope = EBean.Scope.Singleton)
public class CompaniesRepository extends NetworkRepository implements CompaniesContract.CompaniesModel, CompanyDetailsContract.CompanyDetailsModel {

    private CompaniesService companiesService;
    private CustomerService customerService;

    public CompaniesRepository() {
        companiesService = Rest.getInstance().getCompaniesService();
        customerService = Rest.getInstance().getCustomerService();
    }

    @Override
    public Observable<ResponseGetAlphabet> getCompaniesAlphabet() {
        return getNetworkObservable(companiesService.getCompaniesAlphabet("Companies"));
    }

    @Override
    public Observable<ResponseGetCustomersImages> getCompanyImages(ArrayList<String> companyIdList) {
        return getNetworkObservable(customerService.getCustomerImages(companyIdList));
    }

    @Override
    public Observable<ResponseGetCompanies> getAllCompanies(int page) {
        return getNetworkObservable(companiesService.getCompanies("list", "Companies", 50, page));
    }

    @Override
    public Observable<ResponseGetCompanies> getCompaniesByLetter(String letter, int page) {
        return getNetworkObservable(companiesService.getCompaniesByLetter("list", "Companies", "name.first", letter, "letter", 50, page));
    }

    public Observable<ResponseGetCompanyDetails> getCompanyDetails(String companyID) {
        return getNetworkObservable(companiesService.getCompanyDetails(companyID));
    }
}
