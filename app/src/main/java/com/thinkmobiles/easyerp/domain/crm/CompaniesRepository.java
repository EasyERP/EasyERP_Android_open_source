package com.thinkmobiles.easyerp.domain.crm;

import com.thinkmobiles.easyerp.data.api.Rest;
import com.thinkmobiles.easyerp.data.model.crm.common.alphabet.ResponseGetAlphabet;
import com.thinkmobiles.easyerp.data.model.crm.companies.ResponseGetCompanies;
import com.thinkmobiles.easyerp.data.services.CompaniesService;
import com.thinkmobiles.easyerp.presentation.base.NetworkRepository;

import org.androidannotations.annotations.EBean;

import rx.Observable;

/**
 * Created by Lynx on 2/2/2017.
 */

@EBean(scope = EBean.Scope.Singleton)
public class CompaniesRepository extends NetworkRepository {

    private CompaniesService companiesService;

    public CompaniesRepository() {
        companiesService = Rest.getInstance().getCompaniesService();
    }

    public Observable<ResponseGetAlphabet> getCompaniesAlphabeet() {
        return getNetworkObservable(companiesService.getCompaniesAlphabet("Companies"));
    }

    public Observable<ResponseGetCompanies> getAllCompanies(int page) {
        return getNetworkObservable(companiesService.getCompanies("list", "Companies", 50, page));
    }

    public Observable<ResponseGetCompanies> getCompaniesByLetter(String letter, int page) {
        return getNetworkObservable(companiesService.getCompaniesByLetter("list", "Companies", "name.first", letter, "letter", 50, page));
    }

}
