package com.thinkmobiles.easyerp.domain.hr;

import android.net.Uri;

import com.thinkmobiles.easyerp.data.api.Rest;
import com.thinkmobiles.easyerp.data.model.crm.common.alphabet.ResponseGetAlphabet;
import com.thinkmobiles.easyerp.data.model.crm.common.images.ResponseGetImages;
import com.thinkmobiles.easyerp.data.model.crm.filter.ResponseFilters;
import com.thinkmobiles.easyerp.data.model.crm.persons.ResponseGetPersons;
import com.thinkmobiles.easyerp.data.model.hr.employees.ResponseGetEmployees;
import com.thinkmobiles.easyerp.data.services.EmployeesService;
import com.thinkmobiles.easyerp.data.services.FilterService;
import com.thinkmobiles.easyerp.data.services.ImageService;
import com.thinkmobiles.easyerp.presentation.base.NetworkRepository;
import com.thinkmobiles.easyerp.presentation.screens.hr.employees.EmployeesContract;
import com.thinkmobiles.easyerp.presentation.utils.Constants;
import com.thinkmobiles.easyerp.presentation.utils.filter.FilterHelper;

import org.androidannotations.annotations.EBean;

import java.util.ArrayList;

import rx.Observable;

/**
 * Created by Lynx on 3/13/2017.
 */

@EBean(scope = EBean.Scope.Singleton)
public class EmployeesRepository extends NetworkRepository implements EmployeesContract.EmployeeModel {

    private EmployeesService employeesService;
    private ImageService imageService;
    private FilterService filterService;

    public EmployeesRepository() {
        employeesService = Rest.getInstance().getEmployeesService();
        imageService = Rest.getInstance().getImageService();
        filterService = Rest.getInstance().getFilterService();
    }

    @Override
    public Observable<ResponseGetAlphabet> getAlphabet() {
        return getNetworkObservable(employeesService.getEmployeesAlphabet());
    }

    @Override
    public Observable<ResponseGetEmployees> getEmployees(FilterHelper helper, String letter, int page) {
        Uri.Builder builder = helper.createUrl(Constants.GET_EMPLOYEES, "Employees", page);
        if (!letter.equalsIgnoreCase("All")) {
            builder.appendQueryParameter("filter[letter][key]", "name.first")
                    .appendQueryParameter("filter[letter][value]", letter)
                    .appendQueryParameter("filter[letter][type]", "letter");
        }
        return getNetworkObservable(employeesService.getEmployees(builder.build().toString()));
    }

    @Override
    public Observable<ResponseGetImages> getEmployeeImages(ArrayList<String> employeeIDs) {
        return getNetworkObservable(imageService.getEmployeesImages(employeeIDs));
    }

    @Override
    public Observable<ResponseFilters> getFilters() {
        return getNetworkObservable(filterService.getListFilters("Employees"));
    }
}
