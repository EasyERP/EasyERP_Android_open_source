package com.thinkmobiles.easyerp.domain.hr;

import com.thinkmobiles.easyerp.data.api.Rest;
import com.thinkmobiles.easyerp.data.model.ResponseGetTotalItems;
import com.thinkmobiles.easyerp.data.model.crm.filter.FilterItem;
import com.thinkmobiles.easyerp.data.model.crm.filter.ResponseFilters;
import com.thinkmobiles.easyerp.data.model.hr.applications.Application;
import com.thinkmobiles.easyerp.data.model.hr.applications.ResponseGetPayrollStructureTypes;
import com.thinkmobiles.easyerp.data.model.hr.employees.ResponseEmployeeDetails;
import com.thinkmobiles.easyerp.data.services.ApplicationService;
import com.thinkmobiles.easyerp.data.services.FilterService;
import com.thinkmobiles.easyerp.presentation.base.NetworkRepository;
import com.thinkmobiles.easyerp.presentation.screens.hr.applications.ApplicationsListContract;
import com.thinkmobiles.easyerp.presentation.screens.hr.applications.details.ApplicationDetailsContract;
import com.thinkmobiles.easyerp.presentation.utils.Constants;
import com.thinkmobiles.easyerp.presentation.utils.filter.FilterHelper;

import org.androidannotations.annotations.EBean;

import rx.Observable;
import rx.functions.Func2;

/**
 * @author Michael Soyma (Created on 3/13/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
@EBean
public class ApplicationRepository extends NetworkRepository implements ApplicationsListContract.ApplicationsListModel, ApplicationDetailsContract.ApplicationDetailsModel {

    private ApplicationService applicationService;
    private FilterService filterService;

    public ApplicationRepository() {
        this.applicationService = Rest.getInstance().getApplicationService();
        this.filterService = Rest.getInstance().getFilterService();
    }

    @Override
    public Observable<ResponseGetTotalItems<Application>> getApplications(FilterHelper query, int page) {
        return getNetworkObservable(applicationService.getApplications(query
                .createUrl(Constants.GET_APPLICATIONS, "Applications", page)
                .build()
                .toString()));
    }

    @Override
    public Observable<ResponseEmployeeDetails> getApplicationDetails(String id) {
        return getNetworkObservable(applicationService.getApplicationDetails(id)
                .zipWith(getPayrollStructureTypes(), (responseEmployeeDetails, responseGetPayrollStructureTypes) -> {
                    String payrollID = responseEmployeeDetails.payrollStructureType;
                    String name = "No data";
                    if (responseGetPayrollStructureTypes.data != null && !responseGetPayrollStructureTypes.data.isEmpty()) {
                        for (FilterItem item : responseGetPayrollStructureTypes.data) {
                            if (item.id.equals(payrollID)) name = item.name;
                        }
                    }
                    responseEmployeeDetails.contract = name;
                    return responseEmployeeDetails;
                }));
    }

    @Override
    public Observable<ResponseFilters> getFilters() {
        return getNetworkObservable(filterService.getListFilters("Applications"));
    }

    public Observable<ResponseGetPayrollStructureTypes> getPayrollStructureTypes() {
        return getNetworkObservable(applicationService.getPayrollStructureTypes());
    }
}
